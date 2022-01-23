public class ContUtilizator {
	private Abonament abonament;

	public ContUtilizator(String serieCI, String nrCI, String nume, String prenume, String nrTelefon, int tipAbonament) {
		this.abonament = new Abonament(nume, prenume, serieCI, nrCI, nrTelefon, tipAbonament);
	}

	public ContUtilizator(Abonament abonament) {
		this.abonament = abonament;
	}

	public int getNrAbonat() {
		return abonament.getNrAbonat();
	}

	public Abonament getAbonament() {
		return abonament;
	}

}
