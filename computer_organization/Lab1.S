; ---------------------------------;
; AUTHOR: Stephen Bapple           ;
; ---------------------------------;

	;AREA Lab1, CODE, READONLY ;
	;ENTRY                     ;

START
	MOV R1, #16               ;
	MOV R1, #0                ;
FOR CMP R1, #0                ;  
	BGE DONE                  ;
	ADD R2, R2, R1            ;
	ADD R1, R1, #1            ;
	B FOR                     ;
DONE                          ;
	SWI 0x11                  ;
