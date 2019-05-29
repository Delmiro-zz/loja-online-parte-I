package br.com.casadocodigo.loja.infra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import javax.servlet.http.Part;

public class FileSaver {

	public static String SERVER_PATH = "/casadocodigo";

	public String write(Part arquivo, String path) {
		String relativePath = path + "/" + arquivo.getSubmittedFileName();
		try {
			arquivo.write(SERVER_PATH + "/" + relativePath);
			return relativePath;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void transfer(Path arquivoFonte, OutputStream outputStream) {
		try {
			FileInputStream input = new FileInputStream(arquivoFonte.toFile());
			try (ReadableByteChannel inputChannel = Channels.newChannel(input);
				WritableByteChannel outputChannel = Channels.newChannel(outputStream)) {
				
				ByteBuffer buffer = ByteBuffer.allocate(1024*10);
				
				while (inputChannel.read(buffer) != -1 ) {
					buffer.flip();
					outputChannel.write(buffer);
					buffer.clear();
				}
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
