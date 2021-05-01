#pragma once
#include <winsock.h>
#pragma comment(lib, "ws2_32.lib")
/// <summary>
/// ��Socket��ȡn���ֽڷ��أ�������س���Ϊ0,��ʾ�Է�socket�Ѿ��˳�
/// </summary>
/// <param name="socket">socket</param>
/// <param name="n">n���ֽ�</param>
/// <returns>��ȡ������,��Ҫ�Լ��ֶ�delete</returns>
char* readNBytes(SOCKET socket, int n);

/// <summary>
/// ��socket��ȡһ�����ݣ��ȶ�4�ֽڳ��ȣ��ٶ���Ӧ���ȵ�����
/// </summary>
/// <param name="socket">socket</param>
/// <returns>�������,��Ҫ�Լ��ֶ�delete</returns>
char* read(SOCKET socket);

/// <summary>
/// ��socketд��n�ֽ�����
/// </summary>
/// <param name="socket">socket</param>
/// <param name="data">��Ҫд�������</param>
/// <param name="n">д����ֽ���</param>
void writeNBytes(SOCKET socket, const char* data, int n);

/// <summary>
/// ��socketд��һ�����ݣ���д��4�ֽڳ��ȣ���д����ʵ����
/// </summary>
/// <param name="socket">socket</param>
/// <param name="data">����</param>
/// <param name="length">���ݵĳ���</param>
void write(SOCKET socket, const char* data, int length);
