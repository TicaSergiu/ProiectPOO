import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListaImprumut {
	private static ListaImprumut listaImprumuturi = null;
	private List<Imprumut> lista = null;

	private ListaImprumut() {
		lista = new ArrayList<>();
		try {
			File fisier = new File("assets\\imprumuturi.txt");
			fisier.createNewFile();
			BufferedReader bf = new BufferedReader(new FileReader(fisier));
			String linie;
			while ((linie = bf.readLine()) != null) {
				String[] valori = linie.split(" ");

				int nrAbonat = Integer.parseInt(valori[0]);
				int nrImprumut = Integer.parseInt(valori[1]);
				LocalDate dataInchiriere = LocalDate.parse(valori[2]);
				List<Film> filme = new ArrayList<>();

				while (!(linie = bf.readLine()).equals("")) {
					valori = linie.split(" ");

					String numeFilm = valori[0];
					int anProductie = Integer.parseInt(valori[1]);
					CategorieFilm categorieFilm = CategorieFilm.valueOf(valori[2]);
					String tipFilm = valori[3];

					filme.add(new Film(numeFilm, anProductie, categorieFilm, tipFilm));
				}
				if (filme.size() != 0) {
					lista.add(new Imprumut(nrAbonat, nrImprumut, filme, dataInchiriere));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ListaImprumut getListaImprumuturi() {
		if (listaImprumuturi == null) {
			listaImprumuturi = new ListaImprumut();
		}
		return listaImprumuturi;
	}

	public void adaugaImprumut(Imprumut imprumut) {
		lista.add(imprumut);
	}

	public void salveazaImprumuturi() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("assets\\imprumuturi.txt"));
			for (Imprumut imprumut : lista) {
				pw.println(imprumut.toString());
			}
			pw.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Imprumut imprumut : lista) {
			sb.append(imprumut);
		}
		return sb.toString();
	}

}
