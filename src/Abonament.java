import java.io.Serializable;
import java.util.Random;

public class Abonament implements Serializable {
	private int nrAbonat;
	private String nume;
	private String prenume;
	private String serieCI;
	private String nrCI;
	private String nrTelefon;
	// 8 luni sau 12 luni
	private int tipAbonament;

	Abonament(String nume, String prenume, String serieCI, String nrCI, String nrTelefon, int tipAbonament) {
		this.nume = nume;
		this.prenume = prenume;
		this.serieCI = serieCI;
		this.nrCI = nrCI;
		this.nrTelefon = nrTelefon;
		this.tipAbonament = tipAbonament;
		this.nrAbonat = new Random().nextInt(1000, 10001);
	}

	public int getNrAbonat() {
		return nrAbonat;
	}

	public String getSerieCI() {
		return serieCI;
	}

	public String getNrCI() {
		return nrCI;
	}

	@Override
	public String toString() {
		return nrAbonat + " " + serieCI + " " + nrCI + " " + nume + " " + prenume + " " + nrTelefon + " " +
		       tipAbonament;
	}

}
