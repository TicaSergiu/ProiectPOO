import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class PanelCasier extends JPanel {
	private final JButton bLogOut;
	private MyCardLayout cardLayout;
	private JFormattedTextField tSerieCIClientNou, tNrCIClientNou, tNrTelCientNou, tSerieCI;
	private JTextField tNrAbonament, tImprumutID, tNumeClientNou, tPrenumeClientNou;
	private JButton bCautaImprumut, bInapoiContNou, bInapoiRezRetur, bContinuaImprumut, bAltImprumut, bCreezaContNou, bRezRetur, bCreeazaCont;
	private JCheckBox[] cbFilmeImprumutate;
	private JComboBox<String> cmbTipAbonament;
	private FereastraCard fereastraPrincipala;
	private JPanel pAranjeazaClientNou, pAranjeazaCont, pAranjeazaImprumut;
	private Ascultator ab;
	private Imprumut imprumut;

	PanelCasier(FereastraCard fereastraPrincipala) {
		super();
		ab = new Ascultator();
		bLogOut = new JButton("LogOut");
		cardLayout = new MyCardLayout();
		this.fereastraPrincipala = fereastraPrincipala;

		bLogOut.addActionListener(ab);

		setLayout(cardLayout);

		initPanelContCasier();
		initPanelCreeazaContClient();
		initPanelCautaImprumut();
		//initPanelRezolvaRetur();

	}

	private void initPanelContCasier() {
		bRezRetur = new JButton("Rezolvati un retur");
		bCreeazaCont = new JButton("Creati un cont");

		JPanel pContCasier = new JPanel();
		pContCasier.add(bLogOut);
		pContCasier.add(bRezRetur);
		pContCasier.add(bCreeazaCont);

		bRezRetur.addActionListener(ab);
		bCreeazaCont.addActionListener(ab);

		pAranjeazaCont = new JPanel();
		pAranjeazaCont.add(pContCasier);

		add(pAranjeazaCont, "Principal");
	}

	private void initPanelCreeazaContClient() {
		try {
			MaskFormatter formatterSerieCI = new MaskFormatter("UU");
			tSerieCIClientNou = new JFormattedTextField(formatterSerieCI);
			tSerieCIClientNou.setColumns(10);

			MaskFormatter formatterNrCI = new MaskFormatter("######");
			tNrCIClientNou = new JFormattedTextField(formatterNrCI);
			tNrCIClientNou.setColumns(10);

			MaskFormatter formatterNrTel = new MaskFormatter("07########");
			tNrTelCientNou = new JFormattedTextField(formatterNrTel);
			tNrTelCientNou.setColumns(10);

		} catch (ParseException e) {
			e.getStackTrace();
		}

		tNumeClientNou = new JTextField(10);
		tPrenumeClientNou = new JTextField(10);
		bCreezaContNou = new JButton("Creati cont");
		cmbTipAbonament = new JComboBox<>();
		JLabel lNume = new JLabel("Nume:");
		JLabel lPrenume = new JLabel("Prenume:");
		JLabel lSerieCI = new JLabel("Serie CI:");
		JLabel lNrCI = new JLabel("Numar CI:");
		JLabel lNrTelefon = new JLabel("Numar telefon:");
		JLabel lTipAbonament = new JLabel("Durata abonament:");

		bInapoiContNou = new JButton("Inapoi");

		cmbTipAbonament.addItem("8 luni");
		cmbTipAbonament.addItem("12 luni");

		bInapoiContNou.addActionListener(ab);
		bCreezaContNou.addActionListener(ab);

		JPanel pCreeazaContClient = new JPanel(new GridLayout(6, 2));
		pCreeazaContClient.add(lNume);
		pCreeazaContClient.add(tNumeClientNou);
		pCreeazaContClient.add(lPrenume);
		pCreeazaContClient.add(tPrenumeClientNou);
		pCreeazaContClient.add(lSerieCI);
		pCreeazaContClient.add(tSerieCIClientNou);
		pCreeazaContClient.add(lNrCI);
		pCreeazaContClient.add(tNrCIClientNou);
		pCreeazaContClient.add(lNrTelefon);
		pCreeazaContClient.add(tNrTelCientNou);
		pCreeazaContClient.add(lTipAbonament);
		pCreeazaContClient.add(cmbTipAbonament);

		JPanel pButoane = new JPanel();
		pButoane.add(bInapoiContNou);
		pButoane.add(bCreezaContNou);

		pAranjeazaClientNou = new JPanel(new BorderLayout());
		pAranjeazaClientNou.add(pCreeazaContClient, BorderLayout.CENTER);
		pAranjeazaClientNou.add(pButoane, BorderLayout.SOUTH);

		add(pAranjeazaClientNou, "ContNou");
	}

	private void initPanelCautaImprumut() {
		//Folosit sa mearga la fereastra cu cele 3 butoane
		JLabel lIDAbonament = new JLabel("Introduceti id-ul abonamentului: ");
		JLabel lIDImprumut = new JLabel("Introduceti id-ul imprumutului: ");
		bCautaImprumut = new JButton("Cautati imprumutul");
		bInapoiRezRetur = new JButton("Inapoi");
		tNrAbonament = new JTextField(4);
		tImprumutID = new JTextField(4);

		bInapoiRezRetur.addActionListener(ab);
		bCautaImprumut.addActionListener(ab);

		JPanel pAlegeClient = new JPanel(new GridLayout(2, 2));
		pAlegeClient.add(lIDAbonament);
		pAlegeClient.add(tNrAbonament);
		pAlegeClient.add(lIDImprumut);
		pAlegeClient.add(tImprumutID);


		JPanel pButoane = new JPanel();
		pButoane.add(bInapoiRezRetur);
		pButoane.add(bCautaImprumut);

		pAranjeazaImprumut = new JPanel(new BorderLayout());
		pAranjeazaImprumut.add(pAlegeClient, BorderLayout.CENTER);
		pAranjeazaImprumut.add(pButoane, BorderLayout.SOUTH);

		add(pAranjeazaImprumut, "CautImprumut");
	}

	private void initPanelRezolvaRetur() {
		int n = imprumut.getNrFilme();

		JLabel lText = new JLabel("Selectati filmele care au fost returnate de catre client:");
		bContinuaImprumut = new JButton("Contiunua");
		bAltImprumut = new JButton("Cautati alt imprumut");
		cbFilmeImprumutate = new JCheckBox[n];

		bAltImprumut.addActionListener(ab);
		bContinuaImprumut.addActionListener(ab);


		JPanel pFilmeImprumutate = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pFilmeImprumutate.setPreferredSize(new Dimension(200, 150));
		for (int i = 0; i < n; i++) {
			cbFilmeImprumutate[i] = new JCheckBox(imprumut.getNumeFilmTip(i));
			pFilmeImprumutate.add(cbFilmeImprumutate[i]);
		}

		JPanel pButoane = new JPanel();
		pButoane.add(bAltImprumut);
		pButoane.add(bContinuaImprumut);


	}

	private void initPanelTerminaRetur() {

	}


	class Ascultator implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == bLogOut) {
				fereastraPrincipala.logOut();
			}
			if (e.getSource() == bInapoiContNou || e.getSource() == bInapoiRezRetur) {
				cardLayout.show(PanelCasier.this, "Principal");
				fereastraPrincipala.pack();
				return;
			}
			if (e.getSource() == bCreeazaCont) {
				cardLayout.show(PanelCasier.this, "ContNou");
				fereastraPrincipala.pack();
				return;
			}
			if (e.getSource() == bCreezaContNou) {
				if (tNumeClientNou.getText().matches("[A-Z][a-z]*")) {
					// Prenumele trebuie sa fie de forma:
					// [A-Z]                    - trebuie sa inceapa cu litera mare
					// [a-z]*                   - orice numar de litere
					// ?-(\\p{Upper}[a-z]*)?    - optional, urmatorul prenume trebuie sa inceapa cu litera mare si
					//                          - intre cele doua sa existe cratima
					if (tPrenumeClientNou.getText().matches("[A-Z][a-z]*?(-[A-Z][a-z]*)?")) {
						// Campurile cu serieCI, numarCI si nrTelefon trebuie sa fie completate
						// Se completeaza automat cu spatiu cand sunt create cu MaskFormatter
						if (!tSerieCIClientNou.getText().equals("") && !tNrCIClientNou.getText().equals("") &&
						    tNrTelCientNou.getText().matches("07[0-9]*")) {
							int durataAbonament = 0;
							if (cmbTipAbonament.getSelectedIndex() == 0) {
								durataAbonament = 8;
							} else {
								durataAbonament = 12;
							}
							ContUtilizator temp = new ContUtilizator(tSerieCIClientNou.getText(),
							                                         tNrCIClientNou.getText(), tNumeClientNou.getText(),
							                                         tPrenumeClientNou.getText(),
							                                         tNrTelCientNou.getText(), durataAbonament);
							ManagerConturi.scrieCont(temp);
							afiseazaMesajInformare(temp.toString());

							tNumeClientNou.setText("");
							tPrenumeClientNou.setText("");
							tSerieCIClientNou.setValue(null);
							tNrCIClientNou.setValue(null);
							tNrTelCientNou.setValue(null);

							cardLayout.show(PanelCasier.this, "Principal");
							fereastraPrincipala.pack();
						} else {
							afiseazaMesajEroare("Toate campurile trebuie completate");
						}
					} else {
						afiseazaMesajEroare("Prenumele trebuie introdus sub forma:\n Marian-George");
					}
				} else {
					afiseazaMesajEroare("Numele trebuie introdus sub forma:\nPopescu");
				}
			}
			if (e.getSource() == bRezRetur) {
				cardLayout.show(PanelCasier.this, "CautImprumut");
				fereastraPrincipala.pack();
			}
			if (e.getSource() == bContinuaImprumut) {
				//TODO: ultima parte cu imrpumutul
			}
		}

		private void afiseazaMesajInformare(String mesaj) {
			JOptionPane.showMessageDialog(PanelCasier.this, mesaj, "Contul a fost creat",
			                              JOptionPane.INFORMATION_MESSAGE);
		}

		private void afiseazaMesajEroare(String mesaj) {
			JOptionPane.showMessageDialog(PanelCasier.this, mesaj, "Eroare", JOptionPane.ERROR_MESSAGE);
		}

	}

}
