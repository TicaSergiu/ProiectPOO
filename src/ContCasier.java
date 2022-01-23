public class ContCasier {
	private String serieCI;
	private String nrCI;
	private int nrID;

	public ContCasier(int nrID, String serieCI, String nrCI) {
		this.serieCI = serieCI;
		this.nrCI = nrCI;
		this.nrID = nrID;
	}

	@Override
	public String toString() {
		return nrID + " " + serieCI + " " + nrCI;
	}


}
