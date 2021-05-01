package com.shuqy.commons;

import java.io.UnsupportedEncodingException;

public class ByteUtils {
    public static int bytes2int(byte[] bytes) {
        int rs0 = (bytes[0] & 0xff);
        int rs1 = (bytes[1] & 0xff) << 8;
        int rs2 = (bytes[2] & 0xff) << 16;
        int rs3 = (bytes[3] & 0xff) << 24;
        return rs0 + rs1 + rs2 + rs3;
    }

    public static byte[] int2bytes(int a) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((a) & 0xff);
        bytes[1] = (byte) ((a >> 8) & 0xff);
        bytes[2] = (byte) ((a >> 16) & 0xff);
        bytes[3] = (byte) ((a >> 24) & 0xff);
        return bytes;
    }
}
