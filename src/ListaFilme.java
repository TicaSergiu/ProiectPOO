import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ListaFilme {
	private static ListaFilme listaFilme;
	private final int NR_COLOANE_TABEL = 6;
	private int nrFilmeDisponibile;
	private String[] numeColoane;
	private List<Film> lista, filmeSelectate;

	private ListaFilme() throws IOException {
		nrFilmeDisponibile = 0;
		lista = new ArrayList<>();
		filmeSelectate = new ArrayList<>();
		numeColoane = new String[]{"Nume", "An productie", "Categorie film", "Tip film", "Ales"};
		BufferedReader in = new BufferedReader(new FileReader("assets\\listaFilme"));
		String linie;
		while ((linie = in.readLine()) != null) {
			//Folosesc StringTokenizer pentru a fi mai usor de inteles cum se creeaza un film,
			//din fisier, folosind variabile mai descriptive
			StringTokenizer st = new StringTokenizer(linie, " ");
			while (st.hasMoreTokens()) {
				//Filmele cu _ in fisier vor fi inlocuite cu spatiu
				String numeFilm = st.nextToken().replace("_", " ");
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

	public void adaugaFilmSelectat(String numeFilm, String tipFilm) {
		for (Film f : lista) {
			if (numeFilm.equals(f.getNumeFilm()) && tipFilm.equals(f.getTipFilm())) {
				filmeSelectate.add(f);
			}
		}
	}

	public int getNrFilmeDisponibile() {
		return filmeSelectate.size();
	}

	public Film getFilm(int index) {
		return filmeSelectate.get(index);
	}

	public void actualizeazaStoc() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter("assets\\listaFilme"));
		} catch (IOException e) {
			System.exit(-1);
		}
		for (Film f : filmeSelectate) {
			f.scadeNrCopii();
		}
		for (Film f : lista) {
			f.setNumeActualizare();
			pw.println(f.toStringFisier());
		}
		pw.close();
	}

	public int getCostZilnicFilme() {
		int suma = 0;
		for (Film f : filmeSelectate) {
			suma += f.getPretFilm();
		}
		return suma;
	}

	public Film getFilm(String numeFilm, String tipFilm) {
		for (Film f : lista) {
			if (numeFilm.equals(f.getNumeFilm()) && tipFilm.equals(f.getTipFilm())) {
				return f;
			}
		}
		//Nu se ajunge aici
		return null;
	}

	public String[] getNumeColoane() {
		return numeColoane;
	}

	/**
	 * Transforma lista filmelor intr-un Object[][] pentru a face un tabel din care
	 * utilizatorul alege filmele pe care le doreste
	 *
	 * @return lista filmelor pentru client
	 */
	public Object[][] getlistaFilmeClient() {
		Object[][] ret = new Object[nrFilmeDisponibile][NR_COLOANE_TABEL];
		int j = 0;
		for (Film f : lista) {
			if (f.getNrCopii() > 0) {
				ret[j][0] = f.getNumeFilm();
				ret[j][1] = f.getAnProductie();
				ret[j][2] = f.getCategorieFilm();
				ret[j][3] = f.getTipFilm();
				ret[j][4] = Boolean.FALSE;
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
