#pragma once

#include <Winsock2.h>

#define LENGTH_UDP_PKG 64
#define LENGTH_IPv4_ADDR 17

#define SIZE_RECVBUFF LENGTH_UDP_PKG*sizeof(char)
#define SIZE_IPv4_ADDR LENGTH_IPv4_ADDR*sizeof(char)

#define PORT_RANGE_START 49152
#define PORT_RANGE_END 65535

/*initiates use of a Windows Sockets service provider interface (SPI) by a client*/
void initSPI();

short getWlanIPv4(char*);

SOCKET createSocketAndBindPort(char*);
void recvUdp(SOCKET, char*);
void closeSocket(SOCKET);
