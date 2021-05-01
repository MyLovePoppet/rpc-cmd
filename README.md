# 可远程执行cmd命令的rpc-cmd

**rpc-cmd服务器使用VS进行编译，rpc-cmd客户端使用spring-boot进行编写，前端由室友用react完成。**




1. 打开redis，命令```redis-server```。

2. **请先保存redis内的数据再进行操作**，打开客户端：https://github.com/MyLovePoppet/rpc-cmd/releases/download/rpc-cmd-1.0/rpc-cmd-client-0.0.1-SNAPSHOT.jar ，运行命令：```java -jar rpc-cmd-client-0.0.1-SNAPSHOT.jar```。

3. 打开服务器：https://github.com/MyLovePoppet/rpc-cmd/releases/download/rpc-cmd-1.0/rpc-cmd-server.exe ，直接打开即可，需要获得联网权限。

4. 浏览器打开地址：localhost:8080，填入rpc服务器的ip地址与端口号（默认为9901）进行登录。
5. 
![img.png](https://i.loli.net/2021/05/01/bi87uFTIaZLDGhk.png)

5. 登录成功后，输入cmd命令即可在远程主机上进行运行
6. 
![img.png](https://i.loli.net/2021/05/01/sHO9TWvDCZtPp4w.png)


远程主机运行截图：

![img.png](https://i.loli.net/2021/05/01/4L5nPcuipjGda8s.png)




