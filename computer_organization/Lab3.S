; ---------------------------------;
; AUTHOR: Stephen Bapple           ;
; ---------------------------------;
	
	AREA	Lab1, CODE
	;EXPORT city
	;EXPORT sales
	;EXPORT total
	ENTRY			; mark first instruction
		
	LDR R1, =0x55555555	; [R1] <- 0x55555555
	ADR R2, sales           ; move the addr of sales to R2
	BL calculate            ; Call subroutine calculate
	ADR R2, total           ; R2 gets addr of total
	STR R3, [R2]            ; Store the total sales in addr of total
	SWI 0x11
	
	LTORG

calculate			; Calculate subroutine
	MOV R3, R1  		; R3 gets 0x55555555 which was in R1
	MOV R4, #0  		; set sales index to zero
while	CMP R4, #16 		; while condition
	BGT done		; exit while loop on false
				;
	LDR R5, [R2, R4]             ; Load the current sale to R5 
	ADD R4, R4, #4   			; Increment sales index 
	ADD R3, R3, R5    			; Add all sales to R3
        B while                                 ;
done
	MOV PC, LR				; Return	
	
	;AREA, data, DATA

city	DCB "Greenwood Village", 0
sales   DCD 28, 39, 34, 26, 50
total	DCD 0
queue	% 5000

	END
