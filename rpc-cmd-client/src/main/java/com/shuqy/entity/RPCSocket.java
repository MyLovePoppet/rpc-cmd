package com.shuqy.entity;

import com.shuqy.commons.TCPUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Objects;

@Slf4j
public class RPCSocket {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    private String host;
    private short port;

    public RPCSocket(String host, short port) throws IOException {
        this.host = host;
        this.port = port;
        this.socket = new Socket();
        socket.connect(new InetSocketAddress(host, port), 3000);
        this.inputStream = this.socket.getInputStream();
        this.outputStream = this.socket.getOutputStream();
    }

    public RPCSocket() {

    }

    public String getHost() {
        return host;
    }

    public short getPort() {
        return port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(short port) {
        this.port = port;
    }

    public void initSocket() throws IOException {
        this.socket = new Socket(host, port);
        this.inputStream = this.socket.getInputStream();
        this.outputStream = this.socket.getOutputStream();
    }

    public String exec(String cmd) throws IOException {
        TCPUtils.writeData(outputStream, cmd.getBytes("GBK"));
        return new String(TCPUtils.readData(inputStream), "GBK");
    }

    public boolean checkSocketOpen() {
        return this.socket != null && !this.socket.isClosed();
    }

    public void destroy() {
        log.info("由Cache关闭socket[" + toString() + "]...");
        try {
            socket.close();
        } catch (IOException e) {
            log.warn("引发IO异常-->" + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "RPCSocket-->" + host + ":" + port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RPCSocket rpcSocket = (RPCSocket) o;
        return port == rpcSocket.port && host.equals(rpcSocket.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }
}
