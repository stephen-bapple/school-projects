*-------------------------------------------------------------------;
* AUTHOR: Stephen Bapple                                            ;
*                                                                   ;
*Inputs: a negative hexadecimal, a, and a positive hexadecimal, b   ;
*    a list of bytes between A_MSD and A_LSD: the hex of a          ;
*    a list of bytes between B_MSD and B_LSD: the hex of b          ;
* Convert -a and b into their 2's complements and add them together ;
* Store the 2’s complements of (-a + b) to RESULT                ;
*-------------------------------------------------------------------;

; Mask declarations
testMSB EQU 0x80000000 ; Mask for testing the most significant bit
testMSH EQU 0xF0000000 ; Mask for testing the most significant hex
	
	AREA	homework4, CODE
	ENTRY
	EXPORT main

main
	; Get a and convert it from ascii to bin
	LDR R0, =A_MSD	    ; [R0] the address A_MSD
	LDR R1, =A_LSD	    ; [R1] the address A_LSD
	BL Hex2Bin          ; Subrtn rtns bin in R2
	TST R2, #testMSB	; Is [R2]'s MSB 0?
	BNE DONE			; [R2]'s MSB is 1!
	MVN R5, R2          ; [R5] <- a's 1's complement
	ADD R5, R5, #1      ; [R5] <- a's 2's complement
	
	; Get b and convert it from ascii to bin
	LDR R0, =B_MSD		;[R0] the address B_MSD
	LDR R1, =B_LSD		;[R1] the address B_LSD
	BL Hex2Bin          ; Subrtn rtns bin in R2
	TST R2, #testMSB    ;is [R2]'s MSB 0?
	BNE DONE			;[R2]'s MSB is 1!
	MOV R6, R2          ; b's bin, nc for +2's complement

	ADD R7, R5, R6	    ;[R7]<--(-a+b)'s 2's comp	

store                   ; Store the result  
    LDR R8, =RESULT     ; Get the address to store in
	STR R7, [R8]	    ; Store result's 2's comp
	MOV R0, R7
	
DONE	SWI 0x11		; Terminate program

*-------------------------------------------------------------;
* subroutine converting positive hex to bin
*    range of hex: 0 ~ $7FFFFFFF
*  inputs: R0/R1 - address of MSD/LSD
*  [R0] will be increased into [R1]+1
*  outputs: R2 - binary pattern of dec
*    [R2] <-- 0xFFFFFFFF if an invalid dec
*  tmp registers used: R3, R4
*-------------------------------------------------------------;

Hex2Bin		
	MOV R2, #0	      ;clear result register

LOOP_Hex2Bin
	; Test if hex is valid
	MOV R3, #0	  	  ;clear register taking a hex
	LDRB R3, [R0], #1 ;get MSD of hex
	CMP R3, #0	  	  ;is it lower then 0?
	BLO InvalidHex	  ;if true it is not a valid hex
	CMP R3, #0xF	  ;is it higher then F
	BHI InvalidHex	  ;if true it is not a valid hex

	;Next, [R2]<--[R2]*16+[R3]
	TST R2, #testMSH	   ; Are [R4]'s 1st 4 bits 0?
	BNE InvalidHex	   ; At least one  of [R4]'s MSBs is 1
	MOV R2, R2, LSL #4 ; [R4]<--original [R2]*16

	ADD R2, R2, R3	   ;[R2]<--original [R2]*16 + [R3]
	TST R2, #testMSB   ;is [R2]'s MSB zero?
	BNE InvalidHex	   ;[R2]'s MSB is 1

	CMP R0, R1
	BHI DONE_Hex2Bin ;LSD has been added
	B LOOP_Hex2Bin
	LTORG
	
DONE_Hex2Bin
	BX LR

*-------------------------------------------------------------;
* Store 0x00000000 if either number is out of range / invalid ;
*-------------------------------------------------------------;

InvalidHex	        ;a hex beyond 0-F or out of bounds
	MOV R7, #0
	B store
	
*-------------------------------------------------------------;
* Data area.                                                  ;
* Holds the two hexadecimal numbers to convert as well as the ;
* result of -a + b                                            ;
*-------------------------------------------------------------;

	AREA data, DATA
A_MSD	DCB 0xF
		DCB	0xB	;a's Most Significant Hex
		DCB	0x3
		DCB	0x0
		DCB	0x9
		DCB	0x2
A_LSD	DCB	0xF	;a's Least Significant Hex
	ALIGN
 
B_MSD	DCB	0xA	;a's Most Significant Hex
		DCB	0xF
		DCB	0xB
		DCB	0x0
		DCB	0x1
B_LSD	DCB	0xC	;a's Least Significant Hex
	ALIGN

RESULT
	DCD	0	;ascii code of '-' if negative
	
	END
