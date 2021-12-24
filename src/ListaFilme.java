import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ListaFilme {
	public static ListaFilme listaFilme;
	private final int NR_COLOANE_TABEL = 6;
	private int nrFilmeDisponibile;
	private List<Film> lista;

	private ListaFilme() throws IOException {
		nrFilmeDisponibile = 0;
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
					if (nrCopii > 0) {
						nrFilmeDisponibile++;
					}
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

	public static ListaFilme getInstance() {
		if (listaFilme == null) {
			try {
				listaFilme = new ListaFilme();

			} catch (IOException e) {
				System.exit(-1);
			}
		}
		return listaFilme;
	}

	/**
	 * @return Lista filmelor ca obiect pentru a afisa un tabel cu filmele care exista
	 * in baza de date. Daca numarul de copii este 0, filmul nu va fi adaugat in lista
	 */
	public Object[][] getlistaFilmeClient() {
		Object[][] ret = new Object[nrFilmeDisponibile][NR_COLOANE_TABEL];
		int j = 0;
		for (Film f : lista) {
			if (f.getNrCopii() > 0) {
				ret[j][0] = (j + 1) + ".";
				ret[j][1] = f.getNumeFilm();
				ret[j][2] = f.getAnProductie();
				ret[j][3] = f.getCategorieFilm();
				ret[j][4] = f.getTipFilm();
				ret[j][5] = Boolean.FALSE;
				j++;
			}
		}
		return ret;
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
