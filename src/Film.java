public class Film {
	private final double TAXA_TERMEN_DEPASIT = 1.5;
	private final double TAXA_PIERDERE_FILM = 2;
	private int pretFilm;
	private int nrCopii;
	private int anProductie;
	private String numeFilm;
	private String tipFilm;
	private CategorieFilm categorieFilm;

	public Film(int nrCopii, int anProductie, String numeFilm, CategorieFilm categorieFilm, String tipFilm) {
		this.nrCopii = nrCopii;
		this.anProductie = anProductie;
		this.numeFilm = numeFilm;
		this.categorieFilm = categorieFilm;
		this.tipFilm = tipFilm;
		if (this.tipFilm.equals("Caseta")) {
			this.pretFilm = 1;
		} else {
			this.pretFilm = 2;
		}
	}

	public Film(String numeFilm, int anProductie, CategorieFilm categorieFilm) {
		this.numeFilm = numeFilm;
		this.anProductie = anProductie;
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

	public void scadeNrCopii() {
		this.nrCopii--;
	}

	public int getPretFilm() {
		return this.pretFilm;
	}

	public void setNumeActualizare() {
		numeFilm = numeFilm.replace(" ", "_");
	}

	/**
	 * @return Tipul de film, <code>Dvd</code> sau <code>Caseta</code>
	 */
	public String getTipFilm() {
		return tipFilm;
	}

	public String toStringFisier() {
		return numeFilm + " " + anProductie + " " + nrCopii + " " + categorieFilm + " " + tipFilm;
	}

	public String toStringImprumut() {
		return numeFilm.replace(" ", "_") + " " + anProductie + " " + categorieFilm + " " + tipFilm;
	}

	@Override
	public String toString() {
		return numeFilm + " " + anProductie + " " + categorieFilm + " " + tipFilm;
	}

}
