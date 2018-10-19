*-------------------------------------------------------------------------------
* TEA_Encryption
*-------------------------------------------------------------------------------
	AREA encryptionProg, CODE
	ENTRY
	EXPORT main
	;IMPORT decrypt
		
main
	; ---------------------- Load values ------------------------------
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
	LDR R8, =LZero
	LDR R8, [R8]
	LDR R9, =RZero
	LDR R9, [R9]

	; Perform the rounds
	;-------------------------- Round 1 -------------------------------
	LSL R0, R9, #4  ; LSL RZero by 4
	ADD R0, R0, R4  ; Add LSL'd RZero with KZero and store in R0
	
	LSR R1, R9, #5  ; LSR RZero by 5
	ADD R1, R1, R5  ; Add LSR'd RZero with KOne and store in R1
	EOR R0, R0, R1  ; Perform 1st exclusive-or
	
	ADD R1, R9, R2  ; Add RZero with DeltaOne and store in R1
	EOR R0, R0, R1  ; Perform 2nd exclusive-or
	
	ADD R9, R0, R8  ; Add result with LZero and store in R's register
	LDR R8, =RZero  ; Put RZero in L's register
	LDR R8, [R8]
	
	;-------------------------- Round 2 -------------------------------
	; At this point, R8 is LOne and R9 is ROne
	LDR R0, =LTwo
	STR R9, [R0]    ; We've found LTwo!
	;MOV R10, R9     ; Only used for convenience
	
	LSL R0, R9, #4  ; LSL ROne by 4
	ADD R0, R0, R6  ; Add LSL'd ROne with KTwo and store in R0
	
	LSR R1, R9, #5  ; LSR ROne by 5
	ADD R1, R1, R7  ; Add LSR'd ROne and with KThree and store in R1
	EOR R0, R0, R1  ; Perform 1st exclusive-or
	
	ADD R1, R9, R3  ; Add ROne with DeltaTwo and store in R1
	EOR R0, R0, R1  ; Perform 2nd exclusive-or
	ADD R9, R0, R8  ; Add result with LOne and store in R's register
	
	LDR R0, =RTwo
	STR R9, [R0]    ; We've found RTwo!
	;MOV R11, R9   ; Convenience!!!
	;B decrypt       ; Perform the decryption, for ease of use in KEILuVision
    
	SVC 0x11; Quit program

*-------------------------------------------------------------------------------
* Data area
*-------------------------------------------------------------------------------
	AREA data, DATA

	EXPORT	adrLTwo
	EXPORT	adrRTwo

adrLTwo
	DCD	LTwo
		
adrRTwo
	DCD	RTwo
	

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
	DCD 0xA0000009
	ALIGN
		
RZero
	DCD 0x8000006B
	ALIGN
		
LTwo
	DCD 0x0
	ALIGN
		
RTwo
	DCD 0x0
	ALIGN
		
	END