import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ManagerConturi {
	private static final String locFisierConturi = "assets\\conturi.txt";

	/**
	 * Scrie contul de casier trimis ca perametru in fisierul {@value locFisierConturi} <p>
	 * Sub forma {@code nrID} {@code serieCI} {@code nrCI}
	 *
	 * @param contCasier contul care urmeaza sa fie scris in fisier
	 */
	public static void scrieCont(ContCasier contCasier) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(locFisierConturi, true));
			pw.print(contCasier.toString());
			pw.print("\n");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Scrie contul de utilizator trimis ca perametru in fisierul {@value locFisierConturi} <p>
	 * Sub forma {@code nrID}-{@code serieCI}-{@code nrCI}-{@code nume}-{@code prenume}-{@code nrTelefon}-{@code
	 * tipAbonament}
	 *
	 * @param contUtilizator contul care urmeaza sa fie scris in fisier
	 */
	public static void scrieCont(ContUtilizator contUtilizator) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(locFisierConturi, true));
			pw.print(contUtilizator.getAbonament().toString());
			pw.print("\n");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cauta fisierul {@code conturi.txt} pentru a determina ce panel sa afiseze programul
	 *
	 * @param serieCI seria cartii de identitate
	 * @param nrCI    numarul cartii de identitate
	 * @return ID-ul contului cu numarul si seria specificate, mai mare ca 1000 => utilizator <p>
	 * mai mic ca 1000 => casier
	 */
	public static int cautaContID(String serieCI, String nrCI) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(locFisierConturi));
			String linie;
			while ((linie = bf.readLine()) != null) {
				String[] val = linie.split(" ");
				if (val[1].equals(serieCI) && val[2].equals(nrCI)) {
					return Integer.parseInt(val[0]);
				}
			}
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static ContUtilizator getContUtilizator(int id) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(locFisierConturi));
			Abonament abonament = null;
			String linie;
			while ((linie = bf.readLine()) != null) {
				String[] cont = linie.split(" ");
				if (Integer.parseInt(cont[0]) == id) {
					int tipAbonament = Integer.parseInt(cont[6]);
					abonament = new Abonament(id, cont[1], cont[2], cont[3], cont[4], cont[5], tipAbonament);
				}
			}
			bf.close();
			return new ContUtilizator(abonament);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.err.println("Nu trebuie sa se junga aici");
		return null;
	}

	public static ContCasier getContCasier(int id) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(locFisierConturi));
			String linie;
			while ((linie = bf.readLine()) != null) {
				String[] cont = linie.split(" ");
				if (Integer.parseInt(cont[0]) == id) {
					bf.close();
					return new ContCasier(id, cont[1], cont[2]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.err.println("Nu tebuie sa se junga aici");
		return null;
	}

}
