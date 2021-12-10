public class Abonament {
	private static int nrAbonamente = 0;
	private int nrAbonat;
	private Persoana persoana;
	//8 luni sau 12 luni
	private int tipAbonament;

	public Abonament(Persoana persoana, int tipAbonament) {
		this.persoana = persoana;
		this.tipAbonament = tipAbonament;
		this.nrAbonat = nrAbonamente;
		nrAbonamente++;
	}

}
