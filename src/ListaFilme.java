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
	private List<Film> lista, filmeSelectate;

	private ListaFilme() throws IOException {
		nrFilmeDisponibile = 0;
		lista = new ArrayList<>();
		filmeSelectate = new ArrayList<>();
		BufferedReader in = new BufferedReader(new FileReader("assets\\ListaFilme"));
		String linie = in.readLine();
		//TODO: sa sterg asta ca se creeaza in tabel
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
				lista.add(new Film(nrCopii, anProductie, numeFilm, categorieFilm, st.nextToken()));
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

	public int getNrFilme() {
		return lista.size();
	}

	public void adaugaFilmSelectat(String numeFilm, String tipFilm) {
		for (Film f : lista) {
			if (numeFilm.equals(f.getNumeFilm()) && tipFilm.equals(f.getTipFilm())) {
				filmeSelectate.add(f);
				return;
			}
		}
	}

	public int getNrFilmeDisponibile() {
		return filmeSelectate.size();
	}

	public Film getFilmSelectat(int index) {
		return filmeSelectate.get(index);
	}

	public List<Film> getFilmeSelectate() {
		return filmeSelectate;
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

	public Film getFilmSelectat(String numeFilm, String tipFilm) {
		for (Film f : lista) {
			if (numeFilm.equals(f.getNumeFilm()) && tipFilm.equals(f.getTipFilm())) {
				return f;
			}
		}
		//Nu se ajunge aici
		return null;
	}

	/**
	 * Transforma lista filmelor intr-un Object[][] pentru a face un tabel din care
	 * utilizatorul alege filmele pe care le doreste
	 *
	 * @return lista filmelor pentru client
	 */
	public Object[][] getlistaFilmeClient() {
		Object[][] ret = new Object[nrFilmeDisponibile][5];
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

	public Object[][] getListaFilmeAdministrator() {
		Object[][] ret = new Object[lista.size()][6];
		for (int i = 0; i < lista.size(); i++) {
			Film f = lista.get(i);
			ret[i][0] = f.getNumeFilm();
			ret[i][1] = f.getAnProductie();
			ret[i][2] = f.getNrCopii();
			ret[i][3] = f.getCategorieFilm();
			ret[i][4] = f.getTipFilm();
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
