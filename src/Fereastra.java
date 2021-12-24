import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fereastra extends JFrame {
	private JLabel lSerieCI, lNrCI, lNrTel;
	private JTextField tSerieCI, tNrCI, tNrTel;
	private JButton bIntraCont, bExit;
	private JPanel pLogIn, pAlegereFilme, pChitanta;
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
		JLabel lText = new JLabel("<html>Alegeti maxim 5 filme pe care doriti sa le inchiriati <br> 1Ron/ZI pt DVD si" +
		                          " " +
		                          "2 RON/ZI pentru Caseta<br/></html>");

		String[] capTabel = {"Nr. crt.", "Nume", "An productie", "Categorie film", "Tip film", "Ales"};
//		String[][] data = {
//				{"1.", "Dune", "Actiune", "2021", "True"},
//				{"2.", "Luca", "Animatie", "2020", "False"},
//				{"3.", "Spider-man", "Aventura", "2021", "True"},
//				{"0.", "Film 1", "Actiune", "2020", "True"},
//				{"1.", "Film 2", "Actiune", "2020", "True"},
//				{"1.", "Film 2", "Actiune", "2020", "True"},
//				{"1.", "Film 2", "Actiune", "2020", "True"},
//				{"1.", "Film 2", "Actiune", "2020", "True"},
//				{"1.", "Film 2", "Actiune", "2020", "True"},
//				{"9.", "Film 10", "Actiune", "2020", "False"}
//		};

		Object[][] dateTabel = ListaFilme.getInstance().getlistaFilmeClient();
		JTable tabel = new JTable(dateTabel, capTabel);
		tabel.getTableHeader().setReorderingAllowed(false);
		tabel.getTableHeader().setResizingAllowed(false);
		tabel.setPreferredScrollableViewportSize(tabel.getPreferredSize());

		JLabel lSort = new JLabel("Sortati dupa: ");
		JRadioButton rbGen, rbAnProd;
		rbGen = new JRadioButton("Gen: ");
		rbAnProd = new JRadioButton("An productie: ");
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbGen);
		bg.add(rbAnProd);
		JPanel pButSortare = new JPanel(new GridLayout(3, 2));
		pButSortare.setPreferredSize(new Dimension(250, 150));
		JComboBox<CategorieFilm> cbGen = new JComboBox<>(CategorieFilm.values());
		pButSortare.add(lSort);
		pButSortare.add(new JLabel(""));
		pButSortare.add(rbGen);
		pButSortare.add(cbGen);
		pButSortare.add(rbAnProd);
		pButSortare.add(new JTextField(4));

		JScrollPane sp = new JScrollPane(tabel);
		sp.setPreferredSize(new Dimension(350, 200));

		JPanel pButoane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton bRenuntati = new JButton("Inchideti");
		pButoane.add(bRenuntati);
		pButoane.add(new JButton("Inchiriati"));

		getContentPane().removeAll();
		getContentPane().add(lText, BorderLayout.NORTH);
		getContentPane().add(sp, BorderLayout.CENTER);
		getContentPane().add(pButSortare, BorderLayout.EAST);
		getContentPane().add(pButoane, BorderLayout.SOUTH);

		validate();
		pack();
	}

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


	class AscultatoriButoane implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == bExit) {
				System.exit(0);
			}
			if (e.getSource() == bIntraCont) {
				initPanelAlegereFilme();

			}
		}

	}

}
