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

	public int getAnProductie() {
		return anProductie;
	}

	public String getNumeFilm() {
		return this.numeFilm;
	}

	public int getNrCopii() {
		return this.nrCopii;
	}

	public CategorieFilm getCategorieFilm() {
		return this.categorieFilm;
	}

	/**
	 * @return Tipul de film, <code>Dvd</code> sau <code>Caseta</code>
	 */
	public String getTipFilm() {
		return this.getClass().getName().substring(4);
	}

	@Override
	public String toString() {
		return numeFilm + " " + anProductie + " " + categorieFilm + " " + this.getClass().getName().substring(4);
	}

}
