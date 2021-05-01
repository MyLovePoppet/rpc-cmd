#include "TCPUtils.h"
#include "ExecUtils.h"
#include <iostream>

#define MY_PORT 9901

int main()
{
	SOCKET listen_sock;
	struct sockaddr_in my_addr;
	int dummy = sizeof(my_addr);
	WSADATA wsaData;

	WSAStartup(MAKEWORD(1, 1), &wsaData);

	listen_sock = socket(AF_INET, SOCK_STREAM, 0);

	my_addr.sin_family = AF_INET;
	my_addr.sin_port = htons(MY_PORT);
	my_addr.sin_addr.s_addr = INADDR_ANY;

	//绑定端口
	bind(listen_sock, (struct sockaddr*)&my_addr, sizeof(struct sockaddr));
	//监听
	listen(listen_sock, 5);


	while (true)
	{
		//来了新的请求
		struct sockaddr_in new_addr;
		const SOCKET new_sock = accept(listen_sock, (struct sockaddr*)&new_addr, &dummy);
		std::cout << "Connect -->" << inet_ntoa(new_addr.sin_addr) << ":" << new_addr.sin_port << "..." << std::endl;
		while (true)
		{
			//从socket读取待执行命令
			const char* data = read(new_sock);
			//长度为0表示对方socket已经退出
			if (strlen(data) == 0)
			{
				std::cout << "Socket exit..." << std::endl;
				delete[]data;
				break;
			}
			std::cout << "Receive -->" << data << "..." << std::endl;
			//执行结果
			char* res = exec(data);
			delete[]data;
			//向socket写入运行结果
			write(new_sock, res, strlen(res));
			delete[]res;
		}
		closesocket(new_sock);
	}
	closesocket(listen_sock);
	WSACleanup();
	return 0;
}
