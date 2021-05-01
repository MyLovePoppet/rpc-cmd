#pragma once
#include <winsock.h>
#pragma comment(lib, "ws2_32.lib")
/// <summary>
/// 从Socket读取n个字节返回，如果返回长度为0,表示对方socket已经退出
/// </summary>
/// <param name="socket">socket</param>
/// <param name="n">n个字节</param>
/// <returns>读取的数据,需要自己手动delete</returns>
char* readNBytes(SOCKET socket, int n);

/// <summary>
/// 从socket读取一段数据，先读4字节长度，再读对应长度的数据
/// </summary>
/// <param name="socket">socket</param>
/// <returns>这段数据,需要自己手动delete</returns>
char* read(SOCKET socket);

/// <summary>
/// 向socket写入n字节数据
/// </summary>
/// <param name="socket">socket</param>
/// <param name="data">需要写入的数据</param>
/// <param name="n">写入的字节数</param>
void writeNBytes(SOCKET socket, const char* data, int n);

/// <summary>
/// 向socket写入一段数据，先写入4字节长度，再写入真实数据
/// </summary>
/// <param name="socket">socket</param>
/// <param name="data">数据</param>
/// <param name="length">数据的长度</param>
void write(SOCKET socket, const char* data, int length);
