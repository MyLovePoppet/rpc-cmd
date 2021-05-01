package com.shuqy.service;

import com.shuqy.entity.ExpiredMap;
import com.shuqy.entity.RPCSocket;
import com.shuqy.dto.ExecRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
public class RPCService {
    private final ExpiredMap expiredMap;

    private final Lock lock = new ReentrantLock();

    public RPCService(@Autowired ExpiredMap expiredMap) {
        this.expiredMap = expiredMap;
    }

    public boolean checkHostExist(String host, short port) {
        return expiredMap.containsRPCSocket(host, port);
    }

    public boolean checkUUIDExist(UUID uuid) {
        return expiredMap.containsUUID(uuid);
    }

    public UUID getUUIDByHost(String host, short port) {
        return expiredMap.getUUIDByHost(host, port);
    }

    public RPCSocket getSocketByUUID(UUID uuid) {
        return expiredMap.getSocketByUUID(uuid);
    }

    public void putData(UUID uuid, RPCSocket rpcSocket) {
        expiredMap.putData(uuid, rpcSocket);
    }

    public ExecRes exec(RPCSocket rpcSocket, String cmd) {
        lock.lock();
        if (!rpcSocket.checkSocketOpen()) {
            return new ExecRes(300, "rpc服务器已经关闭...", "");
        }
        try {
            return new ExecRes(0, "调用成功...", rpcSocket.exec(cmd));
        } catch (Exception e) {
            log.info("引发异常-->" + e.getMessage());
            return new ExecRes(200, "中间件引发错误[" + e.getMessage() + "]", "");
        } finally {
            lock.unlock();
        }
    }
}
