#include "TCPUtils.h"


char* readNBytes(SOCKET socket, int n)
{
	char* data = new char[n + 1];
	memset(data, 0, sizeof(char) * (n + 1));
	int read = 0;
	//ѭ��������
	while (read < n)
	{
		int currentRead = recv(socket, data + read, n - read, 0);
		if (currentRead > 0)
		{
			read += currentRead;
		}
		else
		{
			break;
		}
	}
	data[read] = '\0';
	return data;
}

char* read(SOCKET socket)
{
	//��ȡ����
	char* lengthByte = readNBytes(socket, 4);
	const INT32 length = *reinterpret_cast<UINT32*>(lengthByte);
	//��ʵ����
	char* data;
	if (length > 0)
	{
		data = readNBytes(socket, length);
	}
	else
	{
		data = new char[1];
		data[0] = '\0';
	}
	delete[]lengthByte;
	return data;
}

void writeNBytes(SOCKET socket, const char* data, int n)
{
	int write = 0;
	//ѭ��д������
	while (write < n)
	{
		int currentWrite = send(socket, data + write, n - write, 0);
		if (currentWrite > 0)
		{
			write += currentWrite;
		}
		else
		{
			break;
		}
	}
}

void write(SOCKET socket, const char* data, int length)
{
	INT32 len = length;
	//��д��4�ֽڳ�����Ϣ
	writeNBytes(socket, reinterpret_cast<const char*>(&len), 4);
	//��д����ʵ����
	writeNBytes(socket, data, length);
}
