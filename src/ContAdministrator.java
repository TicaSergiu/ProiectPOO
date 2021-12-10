public class ContAdministrator {
	public void adaugaFilm(int nrCopii,String nume,int anProductie,CategorieFilm categorieFilm){
		Film filmNou=new Film(nrCopii, anProductie, nume, categorieFilm);
		actualizeazaStoc(filmNou);
	}

	private void actualizeazaStoc(Film film){

	}
}
