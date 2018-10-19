*-------------------------------------------------------------------------------
* TEA_Decryption
*-------------------------------------------------------------------------------
	AREA decryptionProg, CODE
	ENTRY
	EXPORT main
		
main
	; Move Cypher text from previous program into memory
	; This block of code will only work if TEA_Encrypt was ran first.
	;LDR R0, =LTwo
	;STR R10, [R0]
	;LDR R0, =RTwo
	;STR R11, [R0]

	; Load values
	LDR R2, =DeltaOne
	LDR R2, [R2]
	LDR R3, =DeltaTwo
	LDR R3, [R3]
	LDR R4, =KZero
	LDR R4, [R4]
	LDR R5, =KOne
	LDR R5, [R5]
	LDR R6, =KTwo
	LDR R6, [R6]
	LDR R7, =KThree
	LDR R7, [R7]
	LDR R8, =LTwo
	LDR R8, [R8]
	LDR R9, =RTwo
	LDR R9, [R9]
	LDR R10, =LZero
	LDR R11, =RZero
	
	; Decrypt RZero
	LSL R0, R8, #4   ; LSL LTwo by 4
	ADD R0, R0, R6  ; Add LSL'd LTwo with KTwo and store in R0
	
	ADD R1, R8, R3  ; Add LTwo and DeltaTwo and store in R1
	EOR R0, R0, R1  ; Perform 1st exclusive-or
	
	LSR R1, R8, #5   ; LSR LTwo by 5
	ADD R1, R1, R7  ; Add LSR'd LTwo with KThree
	EOR R0, R0, R1  ; Perform 2nd exclusive-or
	
	SUB R9, R9, R0  ; Subtract the result from RTwo to get RZero
	
	; Decrypt LZero
	LSL R0, R9, #4   ; LSL RZero by 4
	ADD R0, R0, R4  ; Add LSL'd RZero with KZero and store in R0
	
	ADD R1, R9, R2  ; Add RZero with DeltaOne and store in R1
	EOR R0, R0, R1  ; Perform 1st exclusive-or
	
	LSR R1, R9, #5   ; LSR RZero by 5
	ADD R1, R1, R5  ; Add LSR'd RZero with KOne
	EOR R0, R0, R1  ; Perform 2nd exclusive-or
	
	SUB R8, R8, R0  ; Subtract the result from LTwo to get LZero
	
	; Store the found values
	LDR R0, =LZero
	LDR R1, =RZero
	STR R8, [R0]
	STR R9, [R1]
	
    SVC 0x11; Quit program

*-------------------------------------------------------------------------------
* Data area
*-------------------------------------------------------------------------------
	AREA data, DATA
	
	EXPORT adrLZero
	EXPORT adrRZero

adrLZero
	DCD LZero
		
adrRZero
	DCD RZero

DeltaOne
	DCD 0x11111111
	ALIGN

DeltaTwo
	DCD 0x22222222
	ALIGN
		
KZero
	DCD 0x90001C55
	ALIGN
		
KOne
	DCD 0x1234ABCD
	ALIGN
		
KTwo
	DCD 0xFEDCBA98
	ALIGN
		
KThree
	DCD 0xE2468AC0
	ALIGN
		
LZero
	DCD 0x00000000
	ALIGN
		
RZero
	DCB 0x00000000
	ALIGN
		
LTwo
	DCD 0xB72599B2
	ALIGN
		
RTwo
	DCD 0xCF8E5A4C
	ALIGN
		
	END