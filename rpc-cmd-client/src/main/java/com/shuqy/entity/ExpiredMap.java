package com.shuqy.entity;


import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class ExpiredMap {

    private final BiMap<UUID, RPCSocket> cache;

    private final RedisTemplate<String, String> redisTemplate;
    private final int expire_min;

    public ExpiredMap(@Value("${cache.expire_min}") int expire_min,
                      @Autowired RedisTemplate<String, String> redisTemplate) {
        cache = HashBiMap.create();
        this.redisTemplate = redisTemplate;
        this.expire_min = expire_min;
        //开始时先将redis内所有的键值给它删掉
        redisTemplate.delete(redisTemplate.keys("*"));
    }

    //检查是否存在对应的uuid
    public boolean containsUUID(UUID uuid) {
        boolean res = redisTemplate.hasKey(uuid.toString());
        //如果redis内没有数据，则检查自己的哈希表有没有对应的数据，如果还有就把它删除
        if (!res && cache.containsKey(uuid)) {
            cache.get(uuid).destroy();
            cache.remove(uuid);
        }
        return res;
    }

    //检查是否存在对应的socket
    public boolean containsRPCSocket(String host, short port) {
        RPCSocket rpcSocket = new RPCSocket();
        rpcSocket.setHost(host);
        rpcSocket.setPort(port);
        //先检查redis内部还有没有数据
        boolean res = redisTemplate.hasKey(rpcSocket.toString());
        if (!res && cache.containsValue(rpcSocket)) {//如果redis内已经没有数据了，但是cache内还有数据，说明数据已经过期了，需要重新进行登录操作
            cache.get(cache.inverse().get(rpcSocket)).destroy();
            cache.remove(cache.inverse().get(rpcSocket));
        }

        return res;
    }

    //通过ip+端口获得socket
    public RPCSocket getSocketByHost(String host, short port) {
        RPCSocket rpcSocket = new RPCSocket();
        rpcSocket.setHost(host);
        rpcSocket.setPort(port);
        return cache.get(cache.inverse().get(rpcSocket));
    }

    //通过ip+端口获得socket，再获得uuid
    public UUID getUUIDByHost(String host, short port) {
        RPCSocket rpcSocket = new RPCSocket();
        rpcSocket.setHost(host);
        rpcSocket.setPort(port);
        return cache.inverse().get(rpcSocket);
    }

    //通过uuid获得socket
    public RPCSocket getSocketByUUID(UUID uuid) {
        return cache.get(uuid);
    }

    //存入数据
    public void putData(UUID uuid, RPCSocket rpcSocket) {
        redisTemplate.boundValueOps(uuid.toString()).set(rpcSocket.toString());
        redisTemplate.expire(uuid.toString(), expire_min, TimeUnit.MINUTES);
        redisTemplate.boundValueOps(rpcSocket.toString()).set(uuid.toString());
        redisTemplate.expire(rpcSocket.toString(), expire_min, TimeUnit.MINUTES);

        cache.put(uuid, rpcSocket);
    }
}
