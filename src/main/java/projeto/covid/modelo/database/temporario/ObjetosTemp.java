package projeto.covid.modelo.database.temporario;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

import projeto.covid.modelo.GrupoEstado;

public class ObjetosTemp {
	
	public static void gravarDados(Path diretorio, GrupoEstado grupoEstados) throws IOException {
		FileOutputStream fos = new FileOutputStream(diretorio.resolve("/estados.ser").toFile());
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(grupoEstados);
		oos.flush();
		oos.close();
	}
}
