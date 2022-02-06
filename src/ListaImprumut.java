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
	private List<Imprumut> lista;

	ListaImprumut() {
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

					String numeFilm = valori[0].replace("_", " ");
					int anProductie = Integer.parseInt(valori[1]);
					CategorieFilm categorieFilm = CategorieFilm.valueOf(valori[2]);
					String tipFilm = valori[3];

					filme.add(new Film(numeFilm, anProductie, categorieFilm, tipFilm));
				}
				if (filme.size() != 0) {
					lista.add(new Imprumut(nrAbonat, nrImprumut, filme, dataInchiriere));
				}
			}
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void adaugaImprumut(Imprumut imprumut) {
		lista.add(imprumut);
	}

	public Imprumut getImprumut(int nrAbonat, int nrImprumut) {
		for (Imprumut imprumut : lista) {
			if (imprumut.getNrImprumut() == nrImprumut && imprumut.getNrAbonat() == nrAbonat) {
				return imprumut;
			}
		}
		System.err.println("Nu s-a gasit imprumut");
		return null;
	}

	public void salveazaListaImprumut() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("imprumuturi.txt"));
			for (Imprumut imprumut : lista) {
				pw.println(imprumut.toString());
				pw.flush();
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stergeImprumut(Imprumut imprumut) {
		lista.remove(imprumut);
	}

	public void actualizeazaImprumuturi() {
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
