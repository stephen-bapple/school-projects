*------------------------------------------------------;
* AUTHOR: Stephen Bapple                               ;
*                                                      ;
* This program takes two strings in memory and mixes   ;
* them into a new string, which is then stored in a    ;
* memory location labeled MixStr                       ;
* Program doesn't take input, only uses data in memory ;
*------------------------------------------------------;
* Symbol Declarations                                  ;
*------------------------------------------------------;
null    EQU 0    ; String terminator
MAX_LEN EQU 0x64 ; 100 in dec
	
	AREA	homework4part2, CODE
	ENTRY
	EXPORT main
	
main
	LDR R0, =StrOne ; [R0] <- addr of StrOne
	LDR R1, =StrTwo ; [R1] <- addr of StrTwo
	LDR R8, =MixStr ; [R8] <- addr of MixStr, the result
	BL mixStrings

DONE	SWI 0x11   ;terminate program

*------------------------------------------------------;
* This subroutine mixes the two strings together       ;
* [R2] holds one char temporarily                      ;
* [R3] holds the address of the last byte modified in  ;
* MixStr                                               ;
*------------------------------------------------------;

mixStrings 
	MOV R3, #0         ; [R3] <-- 0 Clear register
loop
	CMP R3, R8         ; Test if MixStr was modified 
	BEQ doneMixing     ; If not, both strings are done
	MOV R3, R8         ; If so store the new last addr
	
	; Mix in a byte from String One
	LDRB R2, [R0]      ; Get one ascii code from StrOne
	CMP R2, #null      ; Test if string is terminated and... 
	BEQ doneStr1       ; Branch if yes
	ADD R0, R0, #1     ; Increment StrOne index
	STRB R2, [R8], #1  ; add ascii to mixed string
doneStr1

	; Mix in a byte from String Two
	LDRB R2, [R1]     ; Get one ascii code from StrOne
	CMP R2, #null     ; Test if string is terminated and...
	BEQ doneStr2      ; Branch if yes
	ADD R1, R1, #1    ; Increment StrTwo index
	STRB R2, [R8], #1 ; Add ascii to mixed string
doneStr2
	B loop
	
doneMixing
	MOV R2, #null
	STRB R2, [R8]
	BX LR
	
*------------------------------------------------------;
* Data storage area                                    ;
* Holds two strings and the resulting mixed string     ;
*------------------------------------------------------;

	AREA data, DATA
StrOne  DCB 'H'
	DCB 'e'
	DCB 'l'
	DCB 'l'
	DCB 'o'
	DCB ' '
	DCB 'M'
	DCB 'e'
	DCB 't'
	DCB 'r'
	DCB 'o'
	DCB ' '
	DCB 'S'
	DCB 't'
	DCB 'a'
	DCB 't'
	DCB 'e'
	DCB '!'
	DCB null
	ALIGN
		
StrTwo  DCB 'I'
	DCB ' '
	DCB 'L'
	DCB 'i'
	DCB 'k'
	DCB 'e'
	DCB ' '
	DCB 'a'
	DCB 's'
	DCB 's' 
	DCB 'e' 
	DCB 'm'
	DCB 'b'
	DCB 'l'
	DCB 'y'
	DCB ' '
	DCB 'p'
	DCB 'r'
	DCB 'o'
	DCB 'g'
	DCB 'a'
	DCB 'm'
	DCB 'm'
	DCB 'i'
	DCB 'n'
	DCB 'g'
    DCB null
	ALIGN

MixStr	SPACE MAX_LEN + 1

	END
