import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ContCasier extends Cont {
	private int nrID;

	public ContCasier(String serieCI, String nrCI, int nrID) {
		super(serieCI, nrCI);
	}

	@Override
	public void scrieCont() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("conturi.txt"));
			pw.print(nrID + " " + getSerieCI() + " " + getNrCI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
