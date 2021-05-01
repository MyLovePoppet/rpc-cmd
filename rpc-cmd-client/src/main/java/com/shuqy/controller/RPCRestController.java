package com.shuqy.controller;

import com.shuqy.entity.ConnectEntity;
import com.shuqy.entity.ExecEntity;
import com.shuqy.entity.RPCSocket;
import com.shuqy.dto.ExecRes;
import com.shuqy.dto.ConnectRes;
import com.shuqy.service.RPCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

@RestController
public class RPCRestController {

    private final RPCService rpcService;
    private final Pattern ipPattern = Pattern.compile("((\\d|[1-9]\\d|1\\d{2}|2[0-5][0-5])\\.){3}(\\d|[1-9]\\d|1\\d{2}|2[0-5][0-5])");

    public RPCRestController(@Autowired RPCService rpcService) {
        this.rpcService = rpcService;
    }

    @CrossOrigin
    @RequestMapping(value = "/doConnect", method = RequestMethod.POST)
    public ConnectRes doConnect(@RequestBody ConnectEntity connectEntity) {
        String port = connectEntity.getPort();
        String host = connectEntity.getHost();
        //检查是否输入有误
        boolean isInputError = false;
        short port_short = 0;
        try {
            port_short = Short.parseShort(port);
        } catch (Exception e) {
            isInputError = true;
        }
        if (!ipPattern.matcher(host).matches()) {
            isInputError = true;
        }
        if (isInputError) {
            return new ConnectRes(100, "输入数据有误，请仔细检查...", "");
        }
        //检查缓存内是否存在对应的数据
        UUID uuid;
        if (rpcService.checkHostExist(host, port_short)) {
            uuid = rpcService.getUUIDByHost(host, port_short);
        } else {//无数据，增加缓存
            RPCSocket rpcSocket;
            try {
                rpcSocket = new RPCSocket(host, port_short);//默认超时3s
            } catch (IOException e) {
                return new ConnectRes(300, "登录失败，rpc服务器未开启...", "");
            }
            //连接成功
            uuid = UUID.randomUUID();
            rpcService.putData(uuid, rpcSocket);
        }
        return new ConnectRes(0, "登录成功！", uuid.toString());
    }

    @CrossOrigin
    @RequestMapping(value = "/doExec", method = RequestMethod.POST)
    public ExecRes doConnect(@RequestBody ExecEntity execEntity) {
        String cmd = execEntity.getCmd();
        //从token拿到uuid
        String token = execEntity.getToken();
        UUID uuid = null;
        if (token != null) {
            try {
                uuid = UUID.fromString(token);
            } catch (Exception ignored) {
            }
        }

        //如果没有uuid或者uuid已经过期或者无效uuid，要求重新登录
        if (uuid == null || !rpcService.checkUUIDExist(uuid)) {
            return new ExecRes(100, "Token数据无效，不存在或已过期，请重新登录...", "");
        }
        //有uuid数据，拿到socket，进行rpc调用
        RPCSocket rpcSocket = rpcService.getSocketByUUID(uuid);
        return rpcService.exec(rpcSocket, cmd);
    }
}
