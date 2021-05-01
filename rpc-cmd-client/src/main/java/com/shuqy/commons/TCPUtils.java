package com.shuqy.commons;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TCPUtils {
    public static byte[] readNBytes(InputStream inputStream, int n) throws IOException {
        if (n < 0) {
            throw new RuntimeException("Read bytes size less than 0!");
        }

        byte[] res = new byte[n];
        int read = 0;
        while (read < n) {
            int currentRead = inputStream.read(res, read, n - read);
            read += currentRead;
        }
        return res;
    }

    public static byte[] readData(InputStream inputStream) throws IOException {
        byte[] lengthBytes = readNBytes(inputStream, 4);
        int length = ByteUtils.bytes2int(lengthBytes);
        return readNBytes(inputStream, length);
    }

    public static void writeData(OutputStream outputStream, byte[] data) throws IOException {
        byte[] lengthByte = ByteUtils.int2bytes(data.length);
        outputStream.write(lengthByte);
        outputStream.write(data);
    }

}
