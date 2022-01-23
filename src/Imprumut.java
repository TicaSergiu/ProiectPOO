import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Imprumut {
	private List<Film> filmeImprumutate;
	private LocalDate inceputInchiriere;
	private int nrImprumut;
	private int nrAbonat;

	/**
	 * Imprumutul acesta se creeaza cand trebuie creat un nou imprumut si adaugat la lista de imprumuturi
	 *
	 * @param nrAbonat         numarul abonatului
	 * @param filmeImprumutate lista de filme alese de client care urmeaza sa fie imprumutate
	 */
	Imprumut(int nrAbonat, int nrImprumut, List<Film> filmeImprumutate) {
		this.filmeImprumutate = new ArrayList<>(filmeImprumutate);
		this.inceputInchiriere = LocalDate.now();
		this.nrAbonat = nrAbonat;
		this.nrImprumut = nrImprumut;
	}

	/**
	 * Imprumutul care se preia din fisier si este folosit in lista de imprumuturi<p>
	 *
	 * @param nrAbonat          numarul abonatului
	 * @param nrImprumut        numarul imprumutului
	 * @param filmeImprumutate  lista cu filmele imprumutate
	 * @param inceputInchiriere data la care a fost efectuat imprumutul
	 */
	Imprumut(int nrAbonat, int nrImprumut, List<Film> filmeImprumutate, LocalDate inceputInchiriere) {
		this.filmeImprumutate = new ArrayList<>(filmeImprumutate);
		this.nrAbonat = nrAbonat;
		this.nrImprumut = nrImprumut;
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
		StringBuilder sb = new StringBuilder();
		sb.append(nrAbonat);
		sb.append(" ");
		sb.append(nrImprumut);
		sb.append(" ");
		sb.append(inceputInchiriere.toString());
		sb.append("\n");
		for (Film f : filmeImprumutate) {
			sb.append(f.toStringImprumut());
			sb.append("\n");
		}
		return sb.toString();
	}

}
