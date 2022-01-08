import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PrelucreazaFilmeTabel {

	public static DateTabel prelucreazaFilme() {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("assets\\ListaFilme"));
			//Prima linia este numele coloanelor
			String[] numeColoane = bf.readLine().split(" ");
			for (int i = 0; i < numeColoane.length; i++) {
				numeColoane[i] = numeColoane[i].replace("_", " ");
			}
			//Urmatoarele sunt filmele
			String linie;
			int n = ListaFilme.getInstance().getNrFilme();
			Object[][] dateFilme = new Object[n][numeColoane.length];
			for (int i = 0; i < n; i++) {
				String[] film = bf.readLine().split(" ");
				//Prima coloana este cu numele filmului
				film[0] = film[0].replace("_", " ");
				dateFilme[i][0] = film[0];
				dateFilme[i][1] = film[1];
				dateFilme[i][2] = film[2];
				dateFilme[i][3] = film[3];
				dateFilme[i][4] = film[4];
			}
			return new DateTabel(dateFilme, numeColoane);
		} catch (IOException ignored) {
		}

		return null;
	}

	private static class DateTabel extends DefaultTableModel {
		DateTabel(Object[][] dateTabel, String[] numeColoane) {
			super(dateTabel, numeColoane);
		}

	}

}
