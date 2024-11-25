package com.praveen.Demo_File.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class FileUtil {
	
	public static byte[] compressData(byte[] data) throws IOException {
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		GZIPOutputStream gZip = new GZIPOutputStream(bos);
		
		gZip.write(data);
		gZip.close();
		  
		byte[] compressedData =  bos.toByteArray();
		
		return compressedData;
		
	}
	
	public static byte[] decompressData(byte[] data) throws IOException {
		
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		GZIPInputStream gZip = new GZIPInputStream(bis);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		
		int count;
		while( (count = gZip.read(buffer) ) != -1) {
			bos.write(buffer, 0, count);
		}
		
		bos.close();
		gZip.close();
		
		byte[] decompressData = bos.toByteArray();
		
		return decompressData;
		
	}

}
