import java.util.Random;

public class Abonament {
	private int nrAbonat;
	private String nume;
	private String prenume;
	private String serieCI;
	private String nrCI;
	private String nrTelefon;
	private int tipAbonament;

	// Abonament folosit cand se creeaza un abonament nou
	Abonament(String nume, String prenume, String serieCI, String nrCI, String nrTelefon, int tipAbonament) {
		this.nume = nume;
		this.prenume = prenume;
		this.serieCI = serieCI;
		this.nrCI = nrCI;
		this.nrTelefon = nrTelefon;
		this.tipAbonament = tipAbonament;
		this.nrAbonat = new Random().nextInt(1000, 10001);
	}

	// Abonament folosit cand se preia din fisierul conturi.txt
	Abonament(int nrAbonat, String serieCI, String nrCI, String nume, String prenume, String nrTelefon, int tipAbonament) {
		this.nrAbonat = nrAbonat;
		this.nume = nume;
		this.prenume = prenume;
		this.serieCI = serieCI;
		this.nrCI = nrCI;
		this.nrTelefon = nrTelefon;
		this.tipAbonament = tipAbonament;
	}

	public String getNume() {
		return nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public String getNrTelefon() {
		return nrTelefon;
	}

	public int getTipAbonament() {
		return tipAbonament;
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
