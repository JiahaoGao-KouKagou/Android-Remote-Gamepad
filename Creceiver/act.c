#include <stdio.h>

#include <Ws2tcpip.h>
#pragma comment (lib, "ws2_32.lib")  //╪сть ws2_32.dll

#include <WinUser.h>
#pragma comment (lib, "user32.lib")  //а╢╫с user32.dll

#include <stdlib.h>

#include "state.h"
#include "act.h"


boolean isShiftPressed = FALSE;

INPUT input[INPUT_SIZE];

void initInput()
{
	SecureZeroMemory(input, INPUT_SIZE * sizeof(INPUT));
}

void mouseAct()
{

}

void keyboardAct(char* recvBuff)
{
	WORD key = parseKeyRecv(recvBuff);
	 printf("key: %X\n", key);

	setInput(key);
	if (VK_SHIFT == key) {
		INVERSE(isShiftPressed);
	}
	if (ACT_UP == recvBuff[4]) {
		input[0].ki.dwFlags = KEYEVENTF_KEYUP;
	}

	if (VK_SHIFT != key && isShiftPressed) {
		sendInput();
		setInput(VK_SHIFT);
	}

	sendInput();
}

void setInput(WORD key)
{
	input[0].type = INPUT_KEYBOARD;
	input[0].ki.wVk = key;
	input[0].ki.wScan = MapVirtualKey(input[0].ki.wVk, MAPVK_VK_TO_VSC);
}

void sendInput()
{
	UINT uSent = SendInput(INPUT_SIZE, input, sizeof(INPUT));
	SecureZeroMemory(input, INPUT_SIZE * sizeof(INPUT));
	//if (uSent != INPUT_SIZE) {
	//	/* printf("SendInput failed: 0x%x\n", HRESULT_FROM_WIN32(GetLastError())); */
	//	printf("SendInput failed: \n inputs %d \n uSent %d \n\n", INPUT_SIZE, uSent);
	//}
}


int parseKeyRecv(char* recvBuff)
{
	char keyStr[3] = { 0 };

	keyStr[0] = recvBuff[1];
	keyStr[1] = recvBuff[2];
	keyStr[2] = recvBuff[3];

	return atoi(keyStr);
}

