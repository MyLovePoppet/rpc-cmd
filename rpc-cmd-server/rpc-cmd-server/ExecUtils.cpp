#include "ExecUtils.h"
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <vector>
const int maxSize = 1024;

char* exec(const char* cmd)
{
	FILE* fp;

	char* pRetMsg = new char[maxSize];
	if (cmd == NULL || pRetMsg == NULL)
	{
		sprintf_s(pRetMsg, maxSize, "Param Error!");
		return pRetMsg;
	}
	//打开管道
	if ((fp = _popen(cmd, "r")) == NULL)
	{
		sprintf_s(pRetMsg, maxSize, "Popen Error!");
		return pRetMsg;
	}

	memset(pRetMsg, 0, maxSize * sizeof(char));
	//get lastest result
	int read = 0;
	char* buffer = new char[maxSize];
	std::vector<char> res;
	memset(buffer, 0, sizeof(char) * maxSize);
	//循环读取数据
	while (fgets(buffer, maxSize, fp) != NULL)
	{
		//strcat_s(pRetMsg, maxSize, buffer);
		//加到vector内
		res.insert(res.end(), buffer, buffer + strlen(buffer));
		//长度
		read += strlen(buffer);
		memset(buffer, 0, sizeof(char) * maxSize);
	}
	delete[]buffer;
	_pclose(fp);
	pRetMsg = static_cast<char*>(realloc(pRetMsg, (res.size() + 1) * sizeof(char)));
	memcpy(pRetMsg, res.data(), res.size() * sizeof(char));
	pRetMsg[res.size()] = '\0';
	return pRetMsg;
}
