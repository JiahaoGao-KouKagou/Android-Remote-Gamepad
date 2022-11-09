#pragma once

#define CONNECT_SUCCEED '1'
#define MOUSE_MOVE '2'
#define MOUSE_BUTTON '4'
#define KEY_ACT '8'

#define ACT_DOWN '1'
#define ACT_UP '2'

#define TRUE 1
#define FALSE 0
#define INVERSE(VER) VER=VER?FALSE:TRUE

#define MODE_ADD 1
#define MODE_REMOVE 2
#define MODE_NEITHER 3
typedef unsigned short input_mode;


#ifndef small
typedef unsigned char byte;
typedef byte cs_byte;
typedef unsigned char boolean;
#define small char
#endif