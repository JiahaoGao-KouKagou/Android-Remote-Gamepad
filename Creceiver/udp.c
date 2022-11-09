#include <stdio.h>

#include <Winsock2.h>

#include <Ws2tcpip.h>
#pragma comment (lib, "ws2_32.lib")  //加载 ws2_32.dll

/* getWlanIPv4 */
#include <iphlpapi.h>
// Link with Iphlpapi.lib
#pragma comment(lib, "IPHLPAPI.lib")
#define MALLOC(x) HeapAlloc(GetProcessHeap(), 0, (x))
#define FREE(x) HeapFree(GetProcessHeap(), 0, (x))


#include "udp.h"


/*initiates use of a Windows Sockets service provider interface (SPI) by a client*/
void initSPI()
{
	WORD wVersionRequested;//套接字库版本号
	WSADATA wsaData;
	int err;

	wVersionRequested = MAKEWORD(2, 2);//定义套接字的版本号

	err = WSAStartup(wVersionRequested, &wsaData);//创建套接字
	if (err != 0) {
		return 0;
	}
	///创建套接字失败处理
	if (LOBYTE(wsaData.wVersion) != 2 ||
		HIBYTE(wsaData.wVersion) != 2)
	{
		WSACleanup();
		return 0;
	}
}

short getWlanIPv4(char* hostIPv4)
{
	int i;
	short isWLANConnected = 0;

	/* get WLAN address: using GetIpAddrTable */
	/* copy from https://docs.microsoft.com/en-us/windows/win32/api/iphlpapi/nf-iphlpapi-getipaddrtable */
	/* Variables used by GetIpAddrTable */
	PMIB_IPADDRTABLE pIPAddrTable;
	DWORD dwSize = 0;
	DWORD dwRetVal = 0;
	IN_ADDR IPAddr;

	/* Variables used to return error message */
	LPVOID lpMsgBuf;

	// Before calling AddIPAddress we use GetIpAddrTable to get
	// an adapter to which we can add the IP.
	pIPAddrTable = (MIB_IPADDRTABLE *)MALLOC(sizeof(MIB_IPADDRTABLE));

	if (pIPAddrTable) {
		// Make an initial call to GetIpAddrTable to get the
		// necessary size into the dwSize variable
		if (GetIpAddrTable(pIPAddrTable, &dwSize, 0) == ERROR_INSUFFICIENT_BUFFER) {
			FREE(pIPAddrTable);
			pIPAddrTable = (MIB_IPADDRTABLE *)MALLOC(dwSize);

		}
		if (pIPAddrTable == NULL) {
			printf("Memory allocation failed for GetIpAddrTable\n");
			exit(1);
		}
	}
	// Make a second call to GetIpAddrTable to get the
	// actual data we want
	if ((dwRetVal = GetIpAddrTable(pIPAddrTable, &dwSize, 0)) != NO_ERROR) {
		printf("GetIpAddrTable failed with error %d\n", dwRetVal);
		if (FormatMessage(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS, NULL, dwRetVal, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT),       // Default language
			(LPTSTR)&lpMsgBuf, 0, NULL)) {
			printf("\tError: %s", lpMsgBuf);
			LocalFree(lpMsgBuf);
		}
		exit(1);
	}

	// printf("\tNum Entries: %ld\n", pIPAddrTable->dwNumEntries);
	for (i = 0; i < (int)pIPAddrTable->dwNumEntries; i++) {
		/* WLAN's address is Dynamic */
		if (pIPAddrTable->table[i].wType & MIB_IPADDR_DYNAMIC) {
			isWLANConnected = 1;
			IPAddr.S_un.S_addr = (u_long)pIPAddrTable->table[i].dwAddr;
			inet_ntop(AF_INET, &IPAddr, hostIPv4, LENGTH_IPv4_ADDR);
			//printf("\tIP Address[%d]:     \t%s\n", i, hostIPv4);
		}
	}

	if (pIPAddrTable) {
		FREE(pIPAddrTable);
		pIPAddrTable = NULL;
	}

	/*close thread*/
	if (!isWLANConnected)
	{
		printf("no WLAN is connected\n");
	}

	return isWLANConnected;
}

SOCKET createSocketAndBindPort(char* hostIPv4)
{
	SOCKET SrvSock = socket(AF_INET, SOCK_DGRAM, 0);		//createSocket
	SOCKADDR_IN SrvAddr;
	u_short port = PORT_RANGE_START;

	inet_pton(AF_INET, hostIPv4, &SrvAddr.sin_addr.s_addr);//绑定服务端IP地址,vs2013版本以上使用新的函数转换IP地址,将点分十进制IP转化成网络的二进制数IP
	SrvAddr.sin_family = AF_INET;	//IPv4

	// bind port
	do
	{
		port++;
		SrvAddr.sin_port = htons(port);
	} while (0 != bind(SrvSock, (SOCKADDR*)&SrvAddr, sizeof(SOCKADDR)) && port < PORT_RANGE_END);

	printf("IPv4\t%s\nport\t%d\n", hostIPv4, port);

	return SrvSock;
}

void recvUdp(SOCKET SrvSock, char* recvBuff)
{
	SecureZeroMemory(recvBuff, SIZE_RECVBUFF);
	SOCKADDR ClistAddr;
	int len = sizeof(SOCKADDR);

	recvfrom(SrvSock, recvBuff, SIZE_RECVBUFF, 0, (SOCKADDR*)&ClistAddr, &len);//等待接收客户端的请求到来
}

void closeSocket(SOCKET SrvSock)
{
	closesocket(SrvSock);//关闭套接字
	WSACleanup();
}

//Refereance:
//————————————————
//https://blog.csdn.net/nonecode/article/details/7971682
//https://study-life.blog.csdn.net/article/details/102456232
//https://blog.csdn.net/Peerless__/article/details/101696002