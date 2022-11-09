#include <stdio.h>

#include <process.h>

#include "udp.h"
#include "ctrl.h"
#include "act.h"
#include "state.h"


void mainCtrl();

int main()
{
	void initInput();

	/*udp*/
	_beginthread(mainCtrl, 0, NULL);

	/*bug exit*/
	_beginthread(bugExit, 0, NULL);

	/*wait*/
	while (1) {
		Sleep(ULONG_MAX);
	}

	return 0;
}


void mainCtrl()
{
	SOCKET SrvSock;

	char* recvBuff = (char*)malloc(LENGTH_UDP_PKG * sizeof(char));	/*receive buffer (min package len of EthernetII)*/
	char* hostIPv4 = (char*)malloc(LENGTH_IPv4_ADDR * sizeof(char));
	SecureZeroMemory(hostIPv4, SIZE_IPv4_ADDR);

	initSPI();

	do {
		if (getWlanIPv4(hostIPv4)) {
			system("cls");

			SrvSock = createSocketAndBindPort(hostIPv4);

			while (1) {
				recvUdp(SrvSock, recvBuff);
				//printf("%s\n", recvBuff);//把客户端发送来的buff信息打印出来

				switch (recvBuff[0]) {
					case CONNECT_SUCCEED:
						printf("Successfully Connected\n");
						break;

					case KEY_ACT:
						keyboardAct(recvBuff);
						break;

					default:
						break;
				}

			}

			closeSocket(SrvSock);
		}
	} while (restart());
}

