package org.japs.basic.fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public final class Main {
	public static void main(String[] args) throws IOException {
		new FileInputStreamTest().execute();
	}
}

class FileInputStreamTest {
	public void execute() throws IOException {
		try (InputStream is = new FileInputStream(
				new File("./src/main/resources/sample.txt"))) {
//		try (InputStream is = this.getClass()
//				.getResourceAsStream("/sample.txt")) {
//		try (InputStream is = this.getClass()
//				.getClassLoader().getResourceAsStream("sample.txt")) {
			
			byte[] bytes = new byte[3];
			while (is.read(bytes) != -1) {
				System.out.println(new String(bytes, Charset.forName("UTF-8")));
			}
		}
		/*
		 * あ
		 * い
		 * う
		 * え
		 * お
		 */
	}
}