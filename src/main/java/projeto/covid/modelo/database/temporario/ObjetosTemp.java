package projeto.covid.modelo.database.temporario;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

import projeto.covid.modelo.Nacao;

public class ObjetosTemp {

	public static void gravarDados(Path diretorio, Nacao nacao) throws IOException {
		FileOutputStream fos = new FileOutputStream(diretorio.resolve(nacao.getNomePais() + ".ser").toFile());
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(nacao);
		oos.flush();
		oos.close();
	}

	public static Nacao lerDados(Path diretorio, Nacao nacao) throws IOException, ClassNotFoundException {
		long inicio = System.currentTimeMillis();
		FileInputStream fis = new FileInputStream(diretorio.resolve(nacao.getNomePais() + ".ser").toFile());
		ObjectInputStream ois = new ObjectInputStream(fis);
		nacao = (Nacao) ois.readObject();
		ois.close();
		System.out.println("Tempo de execução: " + (System.currentTimeMillis() - inicio) / 1000);
		return nacao;
	}
}
