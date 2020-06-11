package projeto.covid.modelo.database.temporario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DiretorioTemp {

	private Path diretorioFiles = Paths.get(System.getProperty("java.io.tmpdir") + "TempCovid/files");

	public void extrairParaTemp() throws IOException {

		Path path = (Paths.get(System.getProperty("java.io.tmpdir") + "TempCovid"));
		if (Files.notExists(path)) {
			Files.createDirectories(path);
		}
		if(Files.notExists(diretorioFiles)) {
			Files.createDirectories(diretorioFiles);
		}
	}

	public Path getDiretorioFiles() {
		return this.diretorioFiles;
	}

}
