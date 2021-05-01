package com.shuqy;

import com.shuqy.commons.ByteUtils;
import com.shuqy.commons.TCPUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class RpcCmdClientApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    void testSocket() {
        try {
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9901);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            TCPUtils.writeData(outputStream, "hello, world".getBytes(StandardCharsets.UTF_8));
            System.out.println(new String(TCPUtils.readData(inputStream), "GBK"));

            TCPUtils.writeData(outputStream, "hello, world, 你好世界...".getBytes("GBK"));
            System.out.println(new String(TCPUtils.readData(inputStream), "GBK"));
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRPC() {
        try {
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9901);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();


            TCPUtils.writeData(outputStream, "tasklist".getBytes("GBK"));
            System.out.println(new String(TCPUtils.readData(inputStream), "GBK"));

            TCPUtils.writeData(outputStream, "dir".getBytes("GBK"));
            System.out.println(new String(TCPUtils.readData(inputStream), "GBK"));
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
