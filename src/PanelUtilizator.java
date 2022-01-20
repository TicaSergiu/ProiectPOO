import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class PanelUtilizator extends JPanel {
	private JButton bInchiriazaFilmeAlese, bReturnati, bInchiriazaFilme, bPlatiti, bLogOut, bRenuntati;
	private JButton bInapoiChitanta;
	private TabelFilme tabelFilme;
	private JRadioButton rbGen, rbAnProductie;
	private JComboBox cbGen;
	private JTextField tAnProd;
	private JPanel pButoaneMain, pAranjament, pChitanta, pAranjareChitanta;
	private MyCardLayout cl;
	private AscultatorButoane ab;
	private FereastraCard fereastraPrincipala;

	PanelUtilizator(FereastraCard fereastraPrincipala) {
		super();
		ab = new AscultatorButoane();
		cl = new MyCardLayout();
		this.fereastraPrincipala = fereastraPrincipala;

		setLayout(cl);

		initPanelMain();
		initPanelAlegereFilme();
		initPanelChitanta();

	}

	private void initPanelMain() {
		bInchiriazaFilme = new JButton("Inchiriati filme");
		bReturnati = new JButton("Returnati filme");

		bInchiriazaFilme.addActionListener(ab);
		bReturnati.addActionListener(ab);

		pButoaneMain = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pButoaneMain.add(new JButton("LogOut"));
		pButoaneMain.add(bReturnati);
		pButoaneMain.add(bInchiriazaFilme);

		add(pButoaneMain, "Main");
	}

	private void initPanelAlegereFilme() {
		JLabel lText = new JLabel(
				"<html>Alegeti maxim 5 filme pe care doriti sa le inchiriati <br> 1 RON/ZI pt DVD si" +
				" 2 RON/ZI pentru Caseta</html>");
		tabelFilme = new TabelFilme(false);
		JScrollPane sp = new JScrollPane(tabelFilme);
		JLabel lSort = new JLabel("Sortati dupa: ");

		rbGen = new JRadioButton("Gen: ");
		cbGen = new JComboBox<>(CategorieFilm.values());
		tAnProd = new JTextField(5);
		rbAnProductie = new JRadioButton("An productie: ");
		bInchiriazaFilmeAlese = new JButton("Inchiriati");
		bRenuntati = new JButton("Inapoi");

		sp.setPreferredSize(new Dimension(350, 200));

		ButtonGroup grupButoaneSortare = new ButtonGroup();
		grupButoaneSortare.add(rbGen);
		grupButoaneSortare.add(rbAnProductie);

		bRenuntati.addActionListener(ab);
		cbGen.addActionListener(ab);
		rbGen.addActionListener(ab);
		rbAnProductie.addActionListener(ab);
		tAnProd.addActionListener(ab);
		bInchiriazaFilmeAlese.addActionListener(ab);

		JPanel pButSortare = new JPanel(new GridLayout(3, 2));
		pButSortare.add(lSort);
		pButSortare.add(new JLabel(""));
		pButSortare.add(rbGen);
		pButSortare.add(cbGen);
		pButSortare.add(rbAnProductie);
		pButSortare.add(tAnProd);

		JPanel pButoane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pButoane.add(bRenuntati);
		pButoane.add(bInchiriazaFilmeAlese);

		pAranjament = new JPanel(new BorderLayout());
		pAranjament.add(lText, BorderLayout.NORTH);
		pAranjament.add(sp, BorderLayout.CENTER);
		pAranjament.add(pButSortare, BorderLayout.EAST);
		pAranjament.add(pButoane, BorderLayout.SOUTH);

		add(pAranjament, "TabelFilme");
	}

	private void initPanelChitanta() {
		bPlatiti = new JButton("Platiti");
		bInapoiChitanta = new JButton("Inapoi");
		JLabel lText = new JLabel("Chitanta");

		bPlatiti.addActionListener(ab);
		bInapoiChitanta.addActionListener(ab);

		JPanel pContinua = new JPanel();
		pContinua.add(bPlatiti);

		JPanel pInapoi = new JPanel();
		pInapoi.add(bInapoiChitanta);

		Chitanta chitanta = new Chitanta();

		pAranjareChitanta = new JPanel(new BorderLayout());
		pAranjareChitanta.add(lText, BorderLayout.NORTH);
		pAranjareChitanta.add(pContinua, BorderLayout.EAST);
		pAranjareChitanta.add(pInapoi, BorderLayout.SOUTH);
		pAranjareChitanta.add(chitanta, BorderLayout.WEST);

		add(pAranjareChitanta, "Chitanta");

	}

	static class Chitanta extends JPanel {
		Chitanta() {
			super();
			setBackground(Color.WHITE);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			for (int i = 0; i < ListaFilme.getInstance().getNrFilmeDisponibile(); i++) {
				Film film = ListaFilme.getInstance().getFilmSelectat(i);
				g.drawString(film.toString(), 5, i * 20 + 20);
				g.drawString((film.getPretFilm()) + " Ron/ZI", 280, i * 20 + 20);
			}
			g.drawString("Data returnarii: " + LocalDate.now().plusDays(7), 5, 360);
			g.drawString("Total plata: " + ListaFilme.getInstance().getCostZilnicFilme() * 7 + " RON", 5, 380);
		}

		public Dimension getPreferredSize() {
			return new Dimension(330, 400);
		}

	}


	class AscultatorButoane implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == bLogOut) {
				//TODO: Sa fie inapoin logIn
			}

			if (e.getSource() == bRenuntati) {
				cl.show(PanelUtilizator.this, "Main");
				// Cand utilizatorul iese din fereastra de inchiriat filmele, se reseteaza toate filmele pe care le-a
				// ales
				for (int i = 0; i < tabelFilme.getRowCount(); i++) {
					tabelFilme.setValueAt(false, i, tabelFilme.getNR_ULTIM_COLOANA());
				}
				fereastraPrincipala.pack();
				return;
			}
			if (e.getSource() == bInapoiChitanta) {
				cl.show(PanelUtilizator.this, "TabelFilme");
				fereastraPrincipala.pack();
				return;
			}
			if (e.getSource() == bInchiriazaFilmeAlese) {
				// Se verifica daca utilizatorul a ales mai mult de 5 filme pe care
				// sa le inchirieze
				int nrFilmeAlese = 0;
				for (int i = 0; i < tabelFilme.getRowCount(); i++) {
					// Cast la boolean pentru ca ultima coloana este mereu un checkBox
					// ce este True sau False
					if ((Boolean)tabelFilme.getValueAt(i, tabelFilme.getNR_ULTIM_COLOANA())) {
						nrFilmeAlese++;
					}
				}
				if (nrFilmeAlese == 0) {
					afiseazaMesajEroare("Va rugam sa alegeti cel putin 1 film pe care doriti sa il inchiriati.");
					return;
				}
				if (nrFilmeAlese > 5) {
					afiseazaMesajEroare("Va rugam sa alegeti cel mult 5 filme pe care doriti sa le inchiriati.");
					return;
				}
				// Se creeaza o lista cu filmele selectate pentru a le folosi in chitanta
				for (int i = 0; i < tabelFilme.getRowCount(); i++) {
					if ((Boolean)tabelFilme.getValueAt(i, tabelFilme.getNR_ULTIM_COLOANA())) {
						String nume = (String)tabelFilme.getValueAt(i, 0);
						String tipFilm = (String)tabelFilme.getValueAt(i, 3);
						ListaFilme.getInstance().adaugaFilmSelectat(nume, tipFilm);
					}
				}
				cl.show(PanelUtilizator.this, "Chitanta");
				fereastraPrincipala.pack();
				return;
			}
			// Se sorteaza tabelul dupa genul filmului
			if ((e.getSource() == rbGen || e.getSource() == cbGen) && rbGen.isSelected()) {
				tabelFilme.sorteazaTabel((CategorieFilm)cbGen.getSelectedItem());
				return;
			}
			// Se sorteaza tabelul dupa anul productiei
			if ((e.getSource() == rbAnProductie || e.getSource() == tAnProd) && rbAnProductie.isSelected()) {
				tabelFilme.sorteazaTabel(tAnProd.getText());
				return;
			}
			if (e.getSource() == bInchiriazaFilme) {
				//TODO: sa preiau filmele din tabel care au fost selectate
				cl.show(PanelUtilizator.this, "TabelFilme");
				fereastraPrincipala.pack();
				return;
			}
			if (e.getSource() == bPlatiti) {
				afiseazaMesajInformare("nu a mers");
				cl.next(PanelUtilizator.this);
				fereastraPrincipala.pack();
				return;
			}
			if (e.getSource() == bReturnati) {
				afiseazaMesajInformare("Pentru a returna filmele va rugam sa vorbiti cu un casier");
				return;
			}
		}

		public void afiseazaMesajEroare(String mesaj) {
			JOptionPane.showMessageDialog(fereastraPrincipala, mesaj, "Eroare", JOptionPane.ERROR_MESSAGE);
		}

		public void afiseazaMesajInformare(String mesaj) {
			JOptionPane.showMessageDialog(fereastraPrincipala, mesaj, "Operatiune reusita",
			                              JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
