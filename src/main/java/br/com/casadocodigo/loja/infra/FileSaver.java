package br.com.casadocodigo.loja.infra;

import javax.servlet.http.Part;

public class FileSaver {

	private static String SERVER_PATH = "/casadocodigo";

	public String write(Part arquivo, String path) {
		String relativePath = path + "/" + arquivo.getSubmittedFileName();
		try {
			arquivo.write(SERVER_PATH + "/" + relativePath);
			return relativePath;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
