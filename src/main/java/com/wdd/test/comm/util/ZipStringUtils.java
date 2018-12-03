package com.wdd.test.comm.util;

import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipStringUtils {
	/**
	 * 压缩加密String
	 * 
	 * @param paramString
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@SuppressWarnings("restriction")
	public static final String compress(String paramString) throws IOException {
		if (!StringUtils.hasText(paramString)) {
			return null;
		}
		String compress = null;
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
			zipOutputStream.putNextEntry(new ZipEntry("0"));
			zipOutputStream.write(paramString.getBytes());
			zipOutputStream.closeEntry();
			compress = new sun.misc.BASE64Encoder().encode(byteArrayOutputStream.toByteArray());
		}
		return compress;
	}

	/**
	 * 解密解压String
	 * 
	 * @param compressed
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static final String uncompress(String compressed) throws IOException {
		if (!StringUtils.hasText(compressed)) {
			return null;
		}
		String decompressed = null;
		byte[] compBytes = new sun.misc.BASE64Decoder().decodeBuffer(compressed);
		try (ByteArrayOutputStream out = new ByteArrayOutputStream(); ByteArrayInputStream in = new ByteArrayInputStream(compBytes); ZipInputStream zin = new ZipInputStream(in)) {
			zin.getNextEntry();
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = zin.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = new String(out.toByteArray(), "UTF-8");
		}

		return decompressed;
	}
}
