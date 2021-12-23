import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ListaFilme {
	private List<Film> lista;

	ListaFilme() throws IOException {
		lista = new ArrayList<>();
		BufferedReader in = new BufferedReader(new FileReader("assets\\listaFilme"));
		String linie;
		while ((linie = in.readLine()) != null) {
			//Folosesc StringTokenizer pentru a fi mai usor de inteles cum se creeaza un film,
			//din fisier, folosind variabile mai descriptive
			StringTokenizer st = new StringTokenizer(linie, " ");
			while (st.hasMoreTokens()) {
				String numeFilm = st.nextToken();
				int nrCopii, anProductie;

				try {
					anProductie = Integer.parseInt(st.nextToken());
					nrCopii = Integer.parseInt(st.nextToken());
				} catch (NumberFormatException e) {
					nrCopii = 0;
					anProductie = 0;
					System.out.println("Eroare");
				}

				CategorieFilm categorieFilm = CategorieFilm.valueOf(st.nextToken());
				if (st.nextToken().matches("Dvd")) {
					lista.add(new FilmDvd(nrCopii, anProductie, numeFilm, categorieFilm));
				} else {
					lista.add(new FilmCaseta(nrCopii, anProductie, numeFilm, categorieFilm));
				}
			}
		}

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Film film : lista) {
			sb.append(film);
			sb.append("\n");
		}
		return sb.toString();
	}

}
