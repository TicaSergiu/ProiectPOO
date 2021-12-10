import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaFilme {
	private static ListaFilme instanta;
	private List<Film> lista;

	private ListaFilme() throws IOException {
		lista = new ArrayList<>();
		BufferedReader in = new BufferedReader(new FileReader("listaFilme.txt"));
		String linie;
		while ((linie = in.readLine()) != null) {

		}
	}

	public static ListaFilme creeareListaFilme() {
		if (instanta == null) {
			try {
				instanta = new ListaFilme();

			} catch (IOException e) {
				//TODO eroare cand nu exista fisier
			}
		}
		return instanta;
	}

}
