#pragma once

#define VK_NULL 0x00

#define INPUT_SIZE 1

#define POSITION_CTRL	0
#define POSITION_SHIFT	1

#define POSITION_W		2
#define POSITION_S		3
#define POSITION_A		4
#define POSITION_D		5

#define POSITION_UP		6
#define POSITION_DOWN	7
#define POSITION_LEFT	8
#define POSITION_RIGHT	9

#define POSITION_J		10
#define POSITION_K		11
#define POSITION_L		12
#define POSITION_U		13
#define POSITION_I		14
#define POSITION_O		15

#define POSITION_1		16
#define POSITION_2		17
#define POSITION_3		18
#define POSITION_4		19
#define POSITION_5		20
#define POSITION_6		21

#define POSITION_R		22
#define POSITION_V		23
#define POSITION_G		24

#define POSITION_8		25

void initInput();

void mouseAct();
void keyboardAct(char*);
void setInput(WORD);

int parseKeyRecv(char*);

void sendInput();
