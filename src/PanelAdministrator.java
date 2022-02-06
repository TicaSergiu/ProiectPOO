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
	private FereastraPrincipala fereastraPrincipala;
	private JButton bAdaugaRand, bEliminaRand, bSalveazaTabel, bLogOut, bCreeazaContCasier, bModificaFilme;
	private JButton bCreeazaContNou, bInapoiCont, bInapoiTabel;
	private JPanel pAranjeazaTabel, pAranjeazaMain, pAranjeazaCasier;
	private JFormattedTextField tSerieCICasier, tNrCICasier, tIdCasier;

	PanelAdministrator(FereastraPrincipala fereastraPrincipala) {
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

		add(pAranjeazaMain, "Principal");
	}

	private void initPanelEditareFilme() {
		tabelFilme = new TabelFilme(true, new ListaFilme());
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
			tSerieCICasier = new JFormattedTextField(formatterSerieCI);
			tSerieCICasier.setColumns(5);

			MaskFormatter formatterNrCI = new MaskFormatter("######");
			tNrCICasier = new JFormattedTextField(formatterNrCI);
			tNrCICasier.setColumns(5);

			MaskFormatter idCasier = new MaskFormatter("###");
			tIdCasier = new JFormattedTextField(idCasier);
			tIdCasier.setColumns(5);

		} catch (ParseException e) {
			e.getStackTrace();
		}

		JLabel lSerieCI = new JLabel("Serie CI:");
		JLabel lNrCI = new JLabel("Numar CI:");
		JLabel lIdCasier = new JLabel("ID casier:");
		bCreeazaContNou = new JButton("Creati cont");
		bInapoiCont = new JButton("Inapoi");

		bCreeazaContNou.addActionListener(ab);
		bInapoiCont.addActionListener(ab);

		JPanel pCreeazaContCasier = new JPanel(new GridLayout(3, 2));
		pCreeazaContCasier.add(lSerieCI);
		pCreeazaContCasier.add(tSerieCICasier);
		pCreeazaContCasier.add(lNrCI);
		pCreeazaContCasier.add(tNrCICasier);
		pCreeazaContCasier.add(lIdCasier);
		pCreeazaContCasier.add(tIdCasier);

		JPanel pButoane = new JPanel();
		pButoane.add(bInapoiCont);
		pButoane.add(bCreeazaContNou);

		pAranjeazaCasier = new JPanel(new BorderLayout());
		pAranjeazaCasier.add(pCreeazaContCasier, BorderLayout.CENTER);
		pAranjeazaCasier.add(pButoane, BorderLayout.SOUTH);

		add(pAranjeazaCasier, "ContNou");
	}

	class AscultatorButoane implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == bLogOut) {
				fereastraPrincipala.iesiDinContCurent();
			}
			if (e.getSource() == bInapoiCont || e.getSource() == bInapoiTabel) {
				cl.show(PanelAdministrator.this, "Principal");
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
				cl.show(PanelAdministrator.this, "ContNou");
				fereastraPrincipala.pack();
				return;
			}
			if (e.getSource() == bCreeazaContNou) {
				if (tSerieCICasier.getText().matches("[A-Z][A-Z]") && tNrCICasier.getText().matches("[1-9][0-9]{5}") &&
				    tIdCasier.getText().matches("[0-9]{3}")) {

					int nrID = Integer.parseInt(tIdCasier.getText());
					String serieCI = tSerieCICasier.getText();
					String nrCI = tNrCICasier.getText();
					ManagerConturi.scrieCont(new ContCasier(nrID, serieCI, nrCI));
					JOptionPane.showMessageDialog(PanelAdministrator.this, "Contul a fost creat", "Operatiune reusita",
					                              JOptionPane.INFORMATION_MESSAGE);

					tSerieCICasier.setValue(null);
					tIdCasier.setValue(null);
					tNrCICasier.setValue(null);

					cl.show(PanelAdministrator.this, "Principal");
					fereastraPrincipala.pack();
				} else {
					JOptionPane.showMessageDialog(PanelAdministrator.this, "Informatiile introduse sunt gresit",
					                              "Eroare creare cont", JOptionPane.ERROR_MESSAGE);
				}
			}
		}


	}

}
