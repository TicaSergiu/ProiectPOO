import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Imprumut {
	private List<Film> filmeImprumutate;
	private LocalDate inceputInchiriere;
	private int nrImprumut;

	Imprumut(List<Film> filmeImprumutate) {
		this.filmeImprumutate = new ArrayList<>(filmeImprumutate);
		this.inceputInchiriere = LocalDate.now();
		this.nrImprumut = new Random().nextInt(1, 1000);
	}

	Imprumut(List<Film> filmeImprumutate, LocalDate inceputInchiriere) {
		this.filmeImprumutate = new ArrayList<>(filmeImprumutate);
		this.inceputInchiriere = inceputInchiriere;
	}

	public int getNrImprumut() {
		return nrImprumut;
	}

	public LocalDate getInceputInchiriere() {
		return inceputInchiriere;
	}

	public int getNrFilme() {
		return filmeImprumutate.size();
	}

	public Film getFilm(int index) {
		return filmeImprumutate.get(index);
	}

	public String getNumeFilmTip(int index) {
		Film temp = filmeImprumutate.get(index);
		System.out.println(temp.getTipFilm());
		return temp.getNumeFilm() + "-" + temp.getTipFilm();

	}

	@Override
	public String toString() {
		return nrImprumut + " " + inceputInchiriere.toString();
	}

}
