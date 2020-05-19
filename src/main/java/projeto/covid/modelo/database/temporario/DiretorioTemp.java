package projeto.covid.modelo.database.temporario;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DiretorioTemp {

	private Path browserBinary = Paths.get(System.getProperty("java.io.tmpdir") + "TempCovid/browser/App/Firefox/firefox.exe");
	private Path browserDriver = Paths.get(System.getProperty("java.io.tmpdir") + "TempCovid/driver/geckodriver.exe");
	private Path browserDownload = Paths.get(System.getProperty("java.io.tmpdir") + "TempCovid/files");

	public void extrairParaTemp() throws IOException, URISyntaxException {

		Path path = (Paths.get(System.getProperty("java.io.tmpdir") + "TempCovid"));
		if (Files.notExists(path)) {
			Files.createDirectories(path);
		}

		final int BUFFER = 2048;
		ZipInputStream zis = new ZipInputStream(ClassLoader.getSystemResourceAsStream("driver/browser.zip"));
		ZipEntry entrada = null;
		while ((entrada = zis.getNextEntry()) != null) {
			int bytesLidos = 0;
			byte dados[] = new byte[BUFFER];
			// grava o arquivo em disco
			File ff = (path.resolve(entrada.getName())).toFile();
			if (entrada.isDirectory()) {
				ff.mkdir();
			} else {
				FileOutputStream fos = new FileOutputStream(ff);
				BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
				while ((bytesLidos = zis.read(dados, 0, BUFFER)) != -1) {
					dest.write(dados, 0, bytesLidos);
				}
				dest.flush();
				dest.close();
			}
		}
		zis.close();
	}

	public Path getBrowserBinary() {
		return this.browserBinary;
	}

	public Path getBrowserDriver() {
		return this.browserDriver;
	}

	public Path getBrowserDownload() {
		return this.browserDownload;
	}

}
