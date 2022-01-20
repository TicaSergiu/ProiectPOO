import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Cont {
	private String nrCI;
	private String serieCI;

	public Cont(String serieCI, String nrCI) {
		this.nrCI = nrCI;
		this.serieCI = serieCI;
	}

	public Cont() {}


	public String getNrCI() {
		return nrCI;
	}

	public String getSerieCI() {
		return serieCI;
	}

	public abstract void scrieCont();

	public void cautaCont(String serieCI, String nrCI) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("conturi.txt"));
			String linie;
			while ((linie = bf.readLine()) != null) {
				String[] valori = linie.split(" ");
				if (Integer.parseInt(valori[0])>=1000){

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public static int getIdCont(String serieCI, String numarCI) {
//		ObjectInputStream inputStream;
//		try {
//			inputStream = new ObjectInputStream(new FileInputStream("assets\\conturi"));
//			while (true) {
//				Cont temp = (Cont)inputStream.readObject();
//				if (temp.getAbonament().getSerieCI().equals(serieCI) && temp.getAbonament().getNrCI().equals(numarCI)) {
//					contCurent = temp;
//					return temp.getNrAbonament();
//				}
//			}
//		} catch (IOException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return -1;
//	}

//	public static Imprumut getClientImprumut(String nrAbonament, String nrImprumut) {
//		try {
//			BufferedReader bf = new BufferedReader(new FileReader("assets\\ListaImprumuturi.txt"));
//			String linie;
//			while ((linie = bf.readLine()) != null) {
//				String[] temp = linie.split(" ");
//				if (temp[0].equals(nrAbonament) && temp[1].equals(nrImprumut)) {
//					LocalDate data = LocalDate.parse(temp[2]);
//					List<Film> imprumutCautat = new ArrayList<>();
//					while (!(linie = bf.readLine()).equals("")) {
//						String[] imprumut = linie.split(" ");
//						String numeFilm = imprumut[0].replace("_", " ");
//						int anProd = Integer.parseInt(imprumut[1]);
//						CategorieFilm categorieFilm = CategorieFilm.valueOf(imprumut[2]);
//
//						imprumutCautat.add(new Film(numeFilm, anProd, categorieFilm));
//					}
//					return new Imprumut(imprumutCautat);
//				}
//			}
//		} catch (IOException | NumberFormatException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}
