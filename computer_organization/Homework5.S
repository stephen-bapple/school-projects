*---------------------------------------------------------*
* AUTHOR: Stephen Bapple                                  *
*                                                         *
* Hex to decimal conversion program                       *
* Inputs: up to 8 hex symbols in ASCII                    *
* Output: the decimal equivalent in string form           *
*---------------------------------------------------------*
* Symbol Declarations                                     *
*---------------------------------------------------------*
null	 EQU 0x00 ; Null terminator / pure zero
divRadix EQU 0xA  ; Radix to divide by
minus    EQU 0x2D ; ASCII of '-'
	
	AREA homework5, CODE
	ENTRY
	EXPORT main

main
	; Load addresses
	LDR R8, =DecStr     ; [R8] <- address of DecStr
	LDR R9, =TwosComp   ; [R9] <- address of 2's complement
	LDR R10, =UserInput ; - uVision only -
	LDR R11, =RvsDecStr ; [R11] <- address of rvsd string
	
	; Call subroutines
	BL ASCIIToBin    ; Convert input characters to binary
	BL GetComplement ; Get the absolute value & store sign
	BL EnhancedDiv   ; Use modular division to get dec form
	;BL DivideNum    ; Obsolete, less efficent division
	BL ReverseStr    ; Put string in proper order
	
	; Output data 
	;LDR R0, =DecStr ; APM only: 
	;SWI 2           ; output
	
DONE SWI 0x11        
 
*---------------------------------------------------------;
* Subroutines                                             ;
*---------------------------------------------------------;
* Enhanced Division                                       ;
* Divides faster by subtracting greatest powers of 2 first;
* [R3] = remainder                                        ;
* [R4] = quotient                                         ;
* [R5] = division adder                                   ;
*---------------------------------------------------------;
EnhancedDiv
	LDR R3, [R9]      ; Get the whole 2's comp
	LDR R6, =divRadix ; Load the divisor
	
divideOnce
	MOV R2, R6         ; Reset the divisor
	MOV R5, #1         ; Reset the quotient adder
	MOV R5, R5, ROR #4 ; Move divisor to highest power
	MOV R2, R2, ROR #4 ; Move quotient adder to divs power

whileLess
	CMP R3, R2     ; Is the dividend < divisor? 
	BLO shiftRight ; If yes shift
	
	SUB R3, R3, R2 ; Subtract the divisor
	ADD R4, R4, R5 ; Add to the quotient
	B whileLess

shiftRight
	CMP R5, #1         ; Check if [R5] is at 2^0
	BEQ storeASCII     ; If so branch to store the ASCII
	MOV R2, R2, LSR #1 ; Otherwise shift right / lower
	MOV R5, R5, LSR #1 ; the powers
	B whileLess
	
storeASCII
	;LDR R2, =divRadix  ; Reset the divisor
	ADD R3, R3, #'0'   ; Convert remainder to ASCII
	STRB R3, [R11], #1 ; Store the character
	MOV R3, R4         ; Move the quotient
	MOV R4, #0         ; Clear quotient
	CMP R3, #0         ; If quotient was zero end division
	BNE divideOnce     ; Else loop
	
	BX LR      ; Return
	;MOV PC, LR ; APM return
	
*---------------------------------------------------------;
* Convert ASCII/ Hex input to binary                      ;
* [R1] is the two's complement                            ;
* [R2] is the temp loop index                             ;
*---------------------------------------------------------;
ASCIIToBin
	MOV R2, #0 ; Clear loop counter
	MOV R1, #0 ; Clear two's comp register
getAnother
	CMP R2, #8
	BEQ doneConverting
	; SWI 4           ; Read one byte - ALT
	LDRB R0, [R10, R2] ; Read one byte
	ADD R2, R2, #1    ; increment counter
	
	; Is the character a digit?
	CMP R0, #'0'
	BLO notDigit
	CMP R0, #'9'
	BHI notDigit
	; Convert to bin
	SUB R0, R0, #'0'
	MOV R1, R1, LSL #4
	ADD R1, R1, R0
	B getAnother

notDigit ; Is the character a hex?
	CMP R0, #'A'
	BLO doneConverting
	CMP R0, #'F'
	BHI InvalidNum
	; If so convert to bin
	SUB R0, R0, #'A'
	ADD R0, R0, #0xA
	MOV R1, R1, LSL #4
	ADD R1, R1, R0
	B getAnother
	
doneConverting
	BX LR      ; Return
	;MOV PC, LR ; APM return
	
*---------------------------------------------------------;
* GetComplement: checks if the number is negative         ;
* and stores it's absolute value                          ;
*---------------------------------------------------------;
GetComplement
	MOV R2, #1         ; Test the first bit
	TST R1, R2, ROR #1 ; If number is positive...
	BEQ storeComp      ; Branch to store it
	
	; If negative get it's absolute value
	MOV R2, #minus     ; If the number is negative
	STRB R2, [R8], #1  ; Store the minus sign
	MVN R1, R1         ; Convert to two's complement
	ADD R1, R1, #1     ; 
	
storeComp
	STR R1, [R9] ; Store absolute value of number
	 
	BX LR       ; Keil return
	;MOV PC, LR ; APM return
	
*---------------------------------------------------------;
* Divide the number until there's a remainder             ;
* OBSOLETE: very inneficient.                             ;
* [R3] = quotient                                         ;
* [R4] = remainder                                        ;
* [R2] = loop counter                                     ;
*---------------------------------------------------------;
DivideNum                                            ; OBS;
	LDR R3, [R9] ; get the number from memory        ; OBS;
loop                                                 ; OBS;
	MOV R4, R3   ; move it to the remainder          ; OBS;
	MOV R3, #0   ; clear the quotient                ; OBS;
                                                     ; OBS;
subLoop	                                             ; OBS;
	CMP R4, #10                                      ; OBS;
	BLO doneDividing                                 ; OBS;
	SUB R4, #10                                      ; OBS;
	ADD R3, #1                                       ; OBS;
	B subLoop                                        ; OBS;
	                                                 ; OBS;
doneDividing                                         ; OBS;
	ADD R4, R4, #'0' ; Convert remainder to ASCII    ; OBS;
	STRB R4, [R11], #1   ; Store the remainder       ; OBS;
	CMP R3, #0                                       ; OBS;
	BNE loop                                         ; OBS;
													 ; OBS;
	BX LR											 ; OBS;
	;MOV PC, LR ; APM return						 ; OBS;
													 ; OBS;
*---------------------------------------------------------;
* ReverseStr                                              ;
* Reverse the string into it's proper order               ;
*---------------------------------------------------------;
ReverseStr
	LDR R2, =RvsDecStr ; Get address of the original string
	SUB R11, R11, #1   ; Move index to last character
rvsLoop	
	CMP R11, R2         ; Is index at first character?
	BLO done            ; If last ASCII loaded we're done
	LDRB R0, [R11], #-1 ; Else Get the next ASCII And then
	STRB R0, [R8], #1   ; Store it in the proper order
	B rvsLoop
done
	MOV R2, #null ; Null-terminate the string
	STRB R2, [R8] ; Store null
	
	BX LR       ; Keil return
	;MOV PC, LR ; APM return
	
*---------------------------------------------------------;
* Output a message if input is invalid                    ;
*---------------------------------------------------------;
InvalidNum
	LDR R0, =Invalid ; Get message's address
	SWI 2            ; Print message

	B DONE ; Branch back to main's SWI 0x11

*---------------------------------------------------------;
* Data Area                                               ;
*---------------------------------------------------------;
	AREA data, DATA

TwosComp 
	DCD 0
	ALIGN
		
DecStr
	SPACE 12
	ALIGN
		
RvsDecStr 
	SPACE 11
	ALIGN
		
UserInput 
	DCB "7FFFFFFF", 0x0D
	ALIGN
		
Invalid 
	DCB "Invalid number entered.", null
	ALIGN

	END
