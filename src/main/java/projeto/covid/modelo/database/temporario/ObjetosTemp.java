package projeto.covid.modelo.database.temporario;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

import projeto.covid.modelo.Nacao;

public class ObjetosTemp {
	
	public static void gravarDados(Path diretorio, Nacao nacao) throws IOException {
		FileOutputStream fos = new FileOutputStream(diretorio.resolve("/estados.ser").toFile());
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(nacao.getEstados());
		oos.flush();
		oos.close();
	}
}
