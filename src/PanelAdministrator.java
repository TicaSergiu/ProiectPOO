import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class PanelAdministrator extends JPanel {

	private TabelFilme tabelFilme;
	private AscultatorButoane ab;
	private MyCardLayout cl;
	private FereastraCard fereastraPrincipala;
	private JButton bAdaugaRand, bEliminaRand, bSalveazaTabel, bLogOut, bCreeazaContCasier, bModificaFilme;
	private JButton bCreeazaContNou, bInapoiCont, bInapoiTabel;
	private JPanel pAranjeazaTabel, pAranjeazaMain, pAranjeazaCasier;
	private JFormattedTextField tSerieCICasier, tNrCICasier, tNrTelCasier;
	private JTextField tNumeCasier, tPrenumeCasier;

	PanelAdministrator(FereastraCard fereastraPrincipala) {
		super();
		ab = new AscultatorButoane();
		cl = new MyCardLayout();
		this.fereastraPrincipala = fereastraPrincipala;


		setLayout(cl);

		initPanelMain();
		initPanelEditareFilme();
		initPanelCreeazaContCasier();
	}

	private void initPanelMain() {
		bLogOut = new JButton("LogOut");
		bCreeazaContCasier = new JButton("Creati un cont nou de casier");
		bModificaFilme = new JButton("Modificati filme");

		bLogOut.addActionListener(ab);
		bCreeazaContCasier.addActionListener(ab);
		bModificaFilme.addActionListener(ab);

		pAranjeazaMain = new JPanel();
		pAranjeazaMain.add(bLogOut);
		pAranjeazaMain.add(bCreeazaContCasier);
		pAranjeazaMain.add(bModificaFilme);

		add(pAranjeazaMain, "Main");
	}

	private void initPanelEditareFilme() {
		tabelFilme = new TabelFilme(true);
		JScrollPane sp = new JScrollPane(tabelFilme);
		bSalveazaTabel = new JButton("Salvati tabelul");
		bAdaugaRand = new JButton("Adaugati rand");
		bEliminaRand = new JButton("Sterge rand");
		bInapoiTabel = new JButton("Inapoi");

		bInapoiTabel.addActionListener(ab);
		bSalveazaTabel.addActionListener(ab);
		bAdaugaRand.addActionListener(ab);
		bEliminaRand.addActionListener(ab);

		JPanel pFilm = new JPanel();
		pFilm.add(sp);

		JPanel pButoaneTabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pButoaneTabel.add(bAdaugaRand);
		pButoaneTabel.add(bEliminaRand);
		pButoaneTabel.add(bSalveazaTabel);

		JPanel pButon = new JPanel();
		pButon.add(bInapoiTabel);

		pAranjeazaTabel = new JPanel(new BorderLayout());
		pAranjeazaTabel.add(pFilm, BorderLayout.CENTER);
		pAranjeazaTabel.add(pButoaneTabel, BorderLayout.EAST);
		pAranjeazaTabel.add(pButon, BorderLayout.SOUTH);

		add(pAranjeazaTabel, "Tabel");
	}

	private void initPanelCreeazaContCasier() {
		try {
			MaskFormatter formatterSerieCI = new MaskFormatter("UU");
			formatterSerieCI.setPlaceholderCharacter(' ');
			tSerieCICasier = new JFormattedTextField(formatterSerieCI);
			tSerieCICasier.setColumns(10);

			MaskFormatter formatterNrCI = new MaskFormatter("######");
			formatterNrCI.setPlaceholderCharacter(' ');
			tNrCICasier = new JFormattedTextField(formatterNrCI);
			tNrCICasier.setColumns(10);

			MaskFormatter formatterNrTel = new MaskFormatter("07########");
			formatterNrTel.setPlaceholderCharacter(' ');
			tNrTelCasier = new JFormattedTextField(formatterNrTel);
			tNrTelCasier.setColumns(10);

		} catch (ParseException e) {
			e.getStackTrace();
		}

		tNumeCasier = new JTextField(10);
		tPrenumeCasier = new JTextField(10);
		bCreeazaContNou = new JButton("Creati cont");
		bInapoiCont = new JButton("Inapoi");
		JLabel lNume = new JLabel("Nume:");
		JLabel lPrenume = new JLabel("Prenume:");
		JLabel lSerieCI = new JLabel("Serie CI:");
		JLabel lNrCI = new JLabel("Numar CI:");
		JLabel lNrTelefon = new JLabel("Numar telefon:");

		bCreeazaContNou.addActionListener(ab);
		bInapoiCont.addActionListener(ab);

		JPanel pCreeazaContClient = new JPanel(new GridLayout(5, 2));
		pCreeazaContClient.add(lNume);
		pCreeazaContClient.add(tNumeCasier);
		pCreeazaContClient.add(lPrenume);
		pCreeazaContClient.add(tPrenumeCasier);
		pCreeazaContClient.add(lSerieCI);
		pCreeazaContClient.add(tSerieCICasier);
		pCreeazaContClient.add(lNrCI);
		pCreeazaContClient.add(tNrCICasier);
		pCreeazaContClient.add(lNrTelefon);
		pCreeazaContClient.add(tNrTelCasier);

		JPanel pButoane = new JPanel();
		pButoane.add(bInapoiCont);
		pButoane.add(bCreeazaContNou);

		pAranjeazaCasier = new JPanel(new BorderLayout());
		pAranjeazaCasier.add(pCreeazaContClient, BorderLayout.CENTER);
		pAranjeazaCasier.add(pButoane, BorderLayout.SOUTH);

		add(pAranjeazaCasier, "ContNou");
	}

	class AscultatorButoane implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == bInapoiCont || e.getSource() == bInapoiTabel) {
				cl.show(PanelAdministrator.this, "Main");
				fereastraPrincipala.pack();
				return;
			}
			if (e.getSource() == bModificaFilme) {
				cl.show(PanelAdministrator.this, "Tabel");
				fereastraPrincipala.pack();
				return;
			}
			if (e.getSource() == bSalveazaTabel) {
				tabelFilme.salveazaTabel();
				return;
			}
			if (e.getSource() == bAdaugaRand) {
				tabelFilme.adaugaRand();
				return;
			}
			if (e.getSource() == bEliminaRand) {
				tabelFilme.eliminaRand();
				return;
			}
			if (e.getSource() == bCreeazaContCasier) {
				System.out.println("cont nou");
				cl.show(PanelAdministrator.this, "ContNou");
				fereastraPrincipala.pack();
				return;
			}
		}

	}

}
