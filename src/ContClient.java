import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContClient implements Serializable {
	//Transient = contCurent nu va fi scris pe fisier (serializat)
	private static transient ContClient contCurent;
	private Abonament abonament;

	ContClient(String nume, String prenume, String serieCI, String nrCI, String nrTelefon) {
		this.abonament = new Abonament(nume, prenume, serieCI, nrCI, nrTelefon, 0);
		System.out.println(abonament.getNrAbonat());
	}

	public static ContClient creeazaCont(String nume, String prenume, String serieCI, String nrCI, String nrTelefon) {
		return new ContClient(nume, prenume, serieCI, nrCI, nrTelefon);
	}

	public static int getIdCont(String serieCI, String numarCI) {
		ObjectInputStream inputStream;
		try {
			inputStream = new ObjectInputStream(new FileInputStream("assets\\conturi"));
			while (true) {
				ContClient temp = (ContClient)inputStream.readObject();
				if (temp.getAbonament().getSerieCI().equals(serieCI) && temp.getAbonament().getNrCI().equals(numarCI)) {
					contCurent = temp;
					return temp.getNrAbonament();
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static ContClient getContCurent() {
		return contCurent;
	}


	public static Imprumut getClientImprumut(String nrAbonament, String nrImprumut) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("assets\\ListaImprumuturi.txt"));
			String linie;
			while ((linie = bf.readLine()) != null) {
				String[] temp = linie.split(" ");
				if (temp[0].equals(nrAbonament) && temp[1].equals(nrImprumut)) {
					LocalDate data = LocalDate.parse(temp[2]);
					List<Film> imprumutCautat = new ArrayList<>();
					while (!(linie = bf.readLine()).equals("")) {
						String[] imprumut = linie.split(" ");
						String numeFilm = imprumut[0].replace("_", " ");
						int anProd = Integer.parseInt(imprumut[1]);
						CategorieFilm categorieFilm = CategorieFilm.valueOf(imprumut[2]);

						imprumutCautat.add(new Film(numeFilm, anProd, categorieFilm));
					}
					return new Imprumut(imprumutCautat);
				}
			}
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean existaClient(int nrAboament) {
		ObjectInputStream inputStream;
		try {
			inputStream = new ObjectInputStream(new FileInputStream("assets\\conturi"));
			while (true) {
				ContClient temp = (ContClient)inputStream.readObject();
				if (temp.getNrAbonament() == nrAboament) {
					return true;
				}
			}
		} catch (IOException | ClassNotFoundException ignored) {

		}
		return false;
	}

	public void realizeazaImprumut(List<Film> filmeInchiriate) {
		//Se adauga lista de filme inchiriate la lista de imprumuturi
		Imprumut imprumut = new Imprumut(filmeInchiriate);
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter("assets\\ListaImprumuturi.txt", true));
		} catch (IOException ignored) {
			return;
		}
		pw.println(
				contCurent.getNrAbonament() + " " + imprumut.getNrImprumut() + " " + imprumut.getInceputInchiriere());
		for (Film film : filmeInchiriate) {
			pw.println(film.toStringImprumut());
		}
		pw.println();
		pw.flush();
		pw.close();
	}

	public void scrie(ContClient cc) {

		try {
			/*
			  	Daca fisierul cu conturi exista deja se creeaza un AppendingObjectOutputStream care face posibila
			  	adaugarea unui cont nou la finalul fisierului pentru ca atunci cand se creeaza un ObjectOutputStream in
			  	isi pune pe prima linie un header care este diferit la fiecare rulare a programului nu pot fi
			  	citite toate conturile din fisier
			 */
			if (new File("assets\\conturi").isFile()) {
				AppendingObjectOutputStream outputStream = new AppendingObjectOutputStream(
						new FileOutputStream("assets\\conturi", true));
				outputStream.writeObject(cc);
				outputStream.close();
			} else {
				ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("assets\\conturi"));
				outputStream.writeObject(cc);
				outputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Abonament getAbonament() {
		return abonament;
	}

	public int getNrAbonament() {
		return abonament.getNrAbonat();
	}

	@Override
	public String toString() {
		return abonament.toString();
	}

}
