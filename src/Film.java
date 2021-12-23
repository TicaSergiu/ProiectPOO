public class Film {
	private final double TAXA_TERMEN_DEPASIT = 1.5;
	private final double TAXA_PIERDERE_FILM = 2;
	protected int pretFilm;
	private int nrCopii;
	private int anProductie;
	private String numeFilm;
	private CategorieFilm categorieFilm;


	public Film(int nrCopii, int anProductie, String numeFilm, CategorieFilm categorieFilm) {
		this.nrCopii = nrCopii;
		this.anProductie = anProductie;
		this.numeFilm = numeFilm;
		this.categorieFilm = categorieFilm;
	}

	@Override
	public String toString() {
		return numeFilm + " " + anProductie + " " + categorieFilm + " " + this.getClass().getName();
	}

}
