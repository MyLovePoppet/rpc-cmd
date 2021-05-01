#pragma once

/// <summary>
/// 执行函数
/// </summary>
/// <param name="cmd">执行的命令</param>
/// <returns>执行的输出信息，需要自己手动delete</returns>
char* exec(const char* cmd);
