import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaFilme {
	private static ListaFilme listaFilme = null;
	private int nrFilmeDisponibile;
	private List<Film> lista;

	private ListaFilme() {
		nrFilmeDisponibile = 0;
		lista = new ArrayList<>();
		try {
			BufferedReader bf = new BufferedReader(new FileReader("assets\\ListaFilme"));
			String linie;
			while ((linie = bf.readLine()) != null) {
				String[] film = linie.split(" ");

				String nume = film[0].replace("_", " ");
				int anProductie = Integer.parseInt(film[1]);
				int nrCopii = Integer.parseInt(film[2]);
				if (nrCopii > 0) {
					nrFilmeDisponibile++;
				}
				CategorieFilm categorieFilm = CategorieFilm.valueOf(film[3]);
				String tipFilm = film[4];

				lista.add(new Film(nume, anProductie, nrCopii, categorieFilm, tipFilm));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ListaFilme getListaFilme() {
		if (listaFilme == null) {
			listaFilme = new ListaFilme();
		}
		return listaFilme;
	}

	public void scadeStoc(List<Film> filmeAlese) {
		for (Film film : lista) {
			for (Film filmImprumutat : filmeAlese) {
				if (film.getNumeFilm().equals(filmImprumutat.getNumeFilm()) &&
				    film.getTipFilm().equals(filmImprumutat.getTipFilm())) {
					System.out.println("Scazut");
				}
			}
		}
	}

	public Film getFilm(int index) {
		return lista.get(index);
	}

	public Film getFilm(String numeFilm, String tipFilm) {
		for (Film film : lista) {
			if (film.getNumeFilm().equals(numeFilm) && film.getTipFilm().equals(tipFilm)) {
				return film;
			}
		}
		//Nu se ajunge aici
		System.err.println("Filmul trebuia gasit");
		return null;
	}

	public int getNrFilmeDisponibile() {
		return nrFilmeDisponibile;
	}

	public int getNrFilme() {
		return lista.size();
	}


}
