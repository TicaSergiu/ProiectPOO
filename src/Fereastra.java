import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fereastra extends JFrame {
	private JLabel lSerieCI, lNrCI, lNrTel;
	private JTextField tSerieCI, tNrCI, tNrTel, tAnProd;
	private JButton bIntraCont, bExit, bInchiriati, bInapoi, bPlatiti;
	private JPanel pLogIn, pAlegereFilme, pChitanta;
	private JRadioButton rbGen, rbAnProd;
	private JComboBox<CategorieFilm> cbGen;
	private TabelFilme tabelFilme;
	private AscultatoriButoane ab;

	Fereastra(String titlu) {
		super(titlu);
		ab = new AscultatoriButoane();

		initPanelLogIn();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private void initPanelLogIn() {
		//	private void initPanelLogIn() {
//		MaskFormatter formatter;
//		JFormattedTextField serieCI = null;
//		JLabel labelSerieCI;
//		try {
//			formatter = new MaskFormatter("??");
//			labelSerieCI = new JLabel("Seria CI:");
//			labelSerieCI.setLabelFor(serieCI);
//			serieCI = new JFormattedTextField(formatter);
//			serieCI.setText("");
//			panelIntraCont.add(labelSerieCI);
//			panelIntraCont.add(serieCI);
//		} catch (ParseException e) {
//			System.out.println(e.getMessage());
//		}
//
//	}
		lSerieCI = new JLabel("Serie CI:");
		lNrCI = new JLabel("Numar CI:");
		lNrTel = new JLabel("Numar telefon:");

		tSerieCI = new JTextField(10);
		tNrCI = new JTextField(10);
		tNrTel = new JTextField(10);

		bExit = new JButton("Exit");
		bIntraCont = new JButton("Intrati in cont");
		bExit.addActionListener(ab);
		bIntraCont.addActionListener(ab);

		pLogIn = new JPanel(new GridLayout(4, 2, 5, 5));
		pLogIn.add(lSerieCI);
		pLogIn.add(tSerieCI);
		pLogIn.add(lNrCI);
		pLogIn.add(tNrCI);
		pLogIn.add(lNrTel);
		pLogIn.add(tNrTel);
		pLogIn.add(bExit);
		pLogIn.add(bIntraCont);

		getContentPane().add(pLogIn);

	}

	private void initPanelAlegereFilme() {
		JLabel lText = new JLabel(
				"<html>Alegeti maxim 5 filme pe care doriti sa le inchiriati <br> 1 RON/ZI pt DVD si" +
				"2 RON/ZI pentru Caseta<br/></html>");

		String[] numeColoane = ListaFilme.getInstance().getNumeColoane();
		Object[][] dateTabel = ListaFilme.getInstance().getlistaFilmeClient();
		tabelFilme = new TabelFilme(dateTabel, numeColoane);
		JScrollPane sp = new JScrollPane(tabelFilme);
		sp.setPreferredSize(new Dimension(350, 200));

		JLabel lSort = new JLabel("Sortati dupa: ");
		rbGen = new JRadioButton("Gen: ");
		cbGen = new JComboBox<>(CategorieFilm.values());
		rbAnProd = new JRadioButton("An productie: ");
		tAnProd = new JTextField(5);
		tAnProd.setSize(new Dimension(10, 10));
		cbGen.addActionListener(ab);
		rbGen.addActionListener(ab);
		rbAnProd.addActionListener(ab);
		tAnProd.addActionListener(ab);

		ButtonGroup grupButoaneSortare = new ButtonGroup();
		grupButoaneSortare.add(rbGen);
		grupButoaneSortare.add(rbAnProd);

		JPanel pButSortare = new JPanel(new GridLayout(3, 2, 0, 55));
		pButSortare.add(lSort);
		pButSortare.add(new JLabel(""));
		pButSortare.add(rbGen);
		pButSortare.add(cbGen);
		pButSortare.add(rbAnProd);
		pButSortare.add(tAnProd);

		JPanel pButoane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton bRenuntati = new JButton("Inchideti");
		bInchiriati = new JButton("Inchiriati");
		bInchiriati.addActionListener(ab);
		pButoane.add(bRenuntati);
		pButoane.add(bInchiriati);

		getContentPane().removeAll();
		getContentPane().add(lText, BorderLayout.NORTH);
		getContentPane().add(sp, BorderLayout.CENTER);
		getContentPane().add(pButSortare, BorderLayout.EAST);
		getContentPane().add(pButoane, BorderLayout.SOUTH);

		validate();
		pack();
	}

	private void initPanelChitanta() {
		bInapoi = new JButton("Inapoi");
		bInapoi.addActionListener(ab);
		bPlatiti = new JButton("Platiti");
		bPlatiti.addActionListener(ab);

		JPanel pContinua = new JPanel();
		pContinua.add(bPlatiti);

		JPanel pInapoi = new JPanel();
		pInapoi.add(bInapoi);

		PanelChitanta pc = new PanelChitanta();

		getContentPane().removeAll();
		getContentPane().add(new JLabel("Chitanta:"), BorderLayout.NORTH);
		getContentPane().add(pc, BorderLayout.WEST);
		getContentPane().add(pContinua, BorderLayout.EAST);
		getContentPane().add(pInapoi, BorderLayout.SOUTH);

		pack();
	}

	public Fereastra getFereastra() {
		return Fereastra.this;
	}

	class PanelChitanta extends JPanel {
		PanelChitanta() {
			super();
			setBackground(Color.WHITE);
		}

		public Dimension getPreferredSize() {
			return new Dimension(330, 400);
		}


		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			for (int i = 0; i < ListaFilme.getInstance().getNrFilmeDisponibile(); i++) {
				Film film = ListaFilme.getInstance().getFilm(i);
				g.drawString(film.toString(), 5, i * 20 + 20);
				g.drawString((film.pretFilm) + " Ron/ZI", 280, i * 20 + 20);
			}
			g.drawString("Total plata: " + ListaFilme.getInstance().getCostZilnicFilme() + " RON/ZI", 5, 380);
		}

	}


	class AscultatoriButoane implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == bExit) {
				System.exit(0);
			}
			if (e.getSource() == bIntraCont) {
				initPanelAlegereFilme();

			}
			if (e.getSource() == bInchiriati) {
				//Se verifica daca utilizatorul a ales mai mult de 5 filme pe care
				//sa le inchirieze
				int nrFilmeAlese = 0;
				for (int i = 0; i < tabelFilme.getRowCount(); i++) {
					//Cast la boolean pentru ca ultima coloana este mereu un checkBox
					//ce este True sau False
					if ((Boolean)tabelFilme.getValueAt(i, tabelFilme.getNR_ULTIM_COLOANA())) {
						nrFilmeAlese++;
					}
				}
				if (nrFilmeAlese == 0) {
					JOptionPane.showMessageDialog(getFereastra(),
					                              "Va rugam sa alegeti cel putin 1 film pe care sa il inchiriati.",
					                              "Numar invalid de filme alese", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (nrFilmeAlese > 5) {
					JOptionPane.showMessageDialog(getFereastra(),
					                              "Va rugam sa alegeti cel mult 5 filme pe care sa le inchiriati.",
					                              "Numar invalid de filme alese", JOptionPane.ERROR_MESSAGE);
					return;
				}

				for (int i = 0; i < tabelFilme.getRowCount(); i++) {
					if ((Boolean)tabelFilme.getValueAt(i, tabelFilme.getNR_ULTIM_COLOANA())) {
						String nume = (String)tabelFilme.getValueAt(i, 0);
						String tipFilm = (String)tabelFilme.getValueAt(i, 3);
						ListaFilme.getInstance().adaugaFilmSelectat(nume, tipFilm);
					}
				}
				initPanelChitanta();
			}
			if ((e.getSource() == rbGen || e.getSource() == cbGen) && rbGen.isSelected()) {
				tabelFilme.sorteazaTabel(cbGen.getItemAt(cbGen.getSelectedIndex()));
				return;
			}
			if ((e.getSource() == rbAnProd || e.getSource() == tAnProd) && rbAnProd.isSelected()) {
				tabelFilme.sorteazaTabel(tAnProd.getText());
				return;
			}
			if (e.getSource() == bInapoi) {
				initPanelAlegereFilme();
			}
			if (e.getSource() == bPlatiti) {
				ListaFilme.getInstance().actualizeazaStoc();
			}
		}

	}

}
