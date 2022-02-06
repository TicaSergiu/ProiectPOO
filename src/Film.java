public class Film {
	private final double TAXA_TERMEN_DEPASIT = 1.5;
	private final double TAXA_PIERDERE_FILM = 2;
	private int pretFilm;
	private int nrCopii;
	private int anProductie;
	private String numeFilm;
	private String tipFilm;
	private CategorieFilm categorieFilm;

	Film(String numeFilm, int anProductie, int nrCopii, CategorieFilm categorieFilm, String tipFilm) {
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

	Film(String numeFilm, int anProductie, CategorieFilm categorieFilm, String tipFilm) {
		this.numeFilm = numeFilm;
		this.anProductie = anProductie;
		this.categorieFilm = categorieFilm;
		this.tipFilm = tipFilm;
		if (this.tipFilm.equals("Caseta")) {
			this.pretFilm = 1;
		} else {
			this.pretFilm = 2;
		}
	}


	public double getPretIntarziere() {
		return pretFilm * TAXA_TERMEN_DEPASIT;
	}

	public double getPretPierdereFilm() {
		return pretFilm * TAXA_PIERDERE_FILM;
	}

	public int getAnProductie() {
		return anProductie;
	}

	public String getNumeFilm() {
		return numeFilm;
	}

	public int getNrCopii() {
		return nrCopii;
	}

	public CategorieFilm getCategorieFilm() {
		return categorieFilm;
	}

	public int getPretFilm() {
		return pretFilm;
	}

	public String getTipFilm() {
		return tipFilm;
	}

	public void decrementeazaNrCopii() {
		this.nrCopii--;
	}

	public void incrementeazaNrCopii() {
		nrCopii++;
	}

	public String toStringImprumut() {
		return numeFilm.replace(" ", "_") + " " + anProductie + " " + categorieFilm + " " + tipFilm;
	}

	public String toStringFisier() {
		return numeFilm.replace(" ", "_") + " " + anProductie + " " + nrCopii + " " + categorieFilm + " " + tipFilm;
	}

	public String toStringChitanta() {
		return numeFilm + " " + " " + categorieFilm + " " + tipFilm;
	}

}
