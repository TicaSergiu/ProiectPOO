import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ContUtilizator extends Cont {
	private Abonament abonament;

	public ContUtilizator(String serieCI, String nrCI, String nume, String prenume, String nrTelefon, int tipAbonament) {
		this.abonament = new Abonament(nume, prenume, serieCI, nrCI, nrTelefon, tipAbonament);
	}

	@Override
	public void scrieCont() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("conturi.txt"));
			pw.print(abonament.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
