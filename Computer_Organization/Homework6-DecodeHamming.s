*--------------------------------------------------------------------------------------------------;;
* Hamming decoder									            ;
* AUTHOR: Stephen Bapple                                                                            ;
*                                                                                                   ;
* This program takes a given hamming code and extracts the source word.                             ;
* It also corrects up to one bit error                                                              ;
*--------------------------------------------------------------------------------------------------;;
* Symbol Declarations                                                                               ;
*--------------------------------------------------------------------------------------------------;;
MAX_LEN	EQU 100
null	EQU 0
	
	AREA homeWork6, CODE
	ENTRY
	EXPORT main
																									
main
	LDR R1, =0x00008080
	LDR R2, =0x00008070
	CMP R1, R2
	BGE true
	; Initialize registers
	LDR R1, =H_CODE - 1  ; Load the address of the Hamming code
	LDR R2, =SRC_WORD    ; Load the address of the source word
	MOV R3, #0 		     ; Hamming sequence numbers / index
	MOV R11, #0          ; Parity bit counter register
	
	BL countBits         ; Count the bits in each parity

testParity
	CMP R11, #0          ; Check if there is a parity error
	BEQ storeWord        ; Branch to store word if no error
	BL correctBit        ; Otherwise correct the error

storeWord
	BL copyCode          ; Copy the correct code to SRC_WORD
true
	SWI 0x11			 ; End program
	
*---------------------------------------------------------------------------------------------------;
* Subroutines																						;
*---------------------------------------------------------------------------------------------------;
* countBits:                                                                                        ;
* count the 1's in each check bit's parity                                                          ;
*---------------------------------------------------------------------------------------------------;
countBits
	MOV R4, #0 ; R4 holds the current bit
	
while
	ADD R3, R3, #1     ; Increase the sequence index
	LDRB R4, [R1, R3]  ; Load the next bit
	CMP R4, #0         ; Branch to done if 
	BEQ done           ; 	null encounted
	
	CMP R4, #'0'       ; Nothing needs to be done
	BEQ while          ; 	for zeros
	
	EOR R11, R3        ; Update parity even / odd counters
	B while            ; Loop back

done
	BX LR
	;MOV PC, LR
	
*---------------------------------------------------------------------------------------------------;
* correctBit:                                                                                       ;
* Corrects up to 1 bit in error                                                                     ;
*---------------------------------------------------------------------------------------------------;
correctBit
	LDRB R4, [R1, R11] ; Load the bit that's incorrect

isZero	
	CMP R4, #'0'       ; Check if the bit is zero or one 
	BNE isOne          ; Branch to store 1? 
	MOV R4, #'1'       
	STRB R4, [R1, R11] ; Store 1
	B doneInverting

isOne
	MOV R4, #'0'
	STRB R4, [R1, R11]  ; Store zero
	
doneInverting

	BX LR
	;MOV PC, LR
	
*---------------------------------------------------------------------------------------------------;
* copyCode extracts source word from corrected code                                                 ;
*---------------------------------------------------------------------------------------------------;
copyCode
	MOV R5, #1 ; Holds next check bit to test R3 against 
	MOV R3, #1 ; Clear the sequence index
	B whileLoop
	
updateCounters         ; Only executed after check bit encountered
	ADD R3, R3, #1     ; Increment sequence index
	MOV R5, R5, LSL #1 ; LSL for position of next check bit
	
whileLoop
	LDRB R4, [R1, R3] ; Load one byte
	CMP R4, #0        ; Branch if null
	BEQ doneCopy
	
	CMP R3, R5         ; Branch if check bit
	BEQ updateCounters ;
	
	STRB R4, [R2], #1  ; Store the byte to SRC_WORD
	ADD R3, R3, #1     ; Increment sequence index
	B whileLoop        ; Branch back to loop

doneCopy
	MOV R4, #null ; Null-terminate
	STRB R4, [R2] ; 	the string at SRC_WORD
	
	BX LR
	;MOV PC, LR
	
*---------------------------------------------------------------------------------------------------;
* Data Area                                                                                       	;
*---------------------------------------------------------------------------------------------------;
	AREA data, DATA

H_CODE
	DCB "111111000001101"
	DCB null
	ALIGN
		
SRC_WORD
	SPACE MAX_LEN
	;% MAX_LEN
	ALIGN
		
	END
