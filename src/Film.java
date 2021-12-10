public class Film {
	private final double TAXA_TERMEN_DEPASIT = 1.5;
	private final double TAXA_PIERDERE_FILM = 2;
	protected int pretFilm;
	private int nrCopii;
	private int anProductie;
	private String nume;
	private CategorieFilm categorieFilm;


	public Film(int nrCopii, int anProductie, String nume, CategorieFilm categorieFilm) {
		this.nrCopii = nrCopii;
		this.anProductie = anProductie;
		this.nume = nume;
		this.categorieFilm = categorieFilm;
	}

}
