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

	@Override
	public String toString() {
		return "Id abonat: " + abonament.getNrAbonat() + "\n" + "Nume: " + abonament.getNume() + "\n" + "Prenume: " +
		       abonament.getPrenume() + "\n" + "Serie CI: " + abonament.getSerieCI() + "\n" + "Numar CI: " +
		       abonament.getNrCI() + "\n" + "Numar Telefon: " + abonament.getNrTelefon() + "\n" + "Durata abonament: " +
		       abonament.getTipAbonament() + " luni.";
	}
}
