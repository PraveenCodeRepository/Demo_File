package com.praveen.Demo_File.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class FileUtils {

    public static byte[] compressData(byte[] data) {
        try {
            Deflater deflater = new Deflater();
            deflater.setInput(data);
            deflater.finish();

            byte[] buffer = new byte[1024];
            int compressedDataLength;
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                while (!deflater.finished()) {
                    compressedDataLength = deflater.deflate(buffer);
                    outputStream.write(buffer, 0, compressedDataLength);
                }
                return outputStream.toByteArray();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while compressing data", e);
        }
    }

    public static byte[] decompressData(byte[] data) {
        try {
            Inflater inflater = new Inflater();
            inflater.setInput(data);

            byte[] buffer = new byte[1024];
            int decompressedDataLength;
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                while (!inflater.finished()) {
                    decompressedDataLength = inflater.inflate(buffer);
                    outputStream.write(buffer, 0, decompressedDataLength);
                }
                return outputStream.toByteArray();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while decompressing data", e);
        }
    }
}

