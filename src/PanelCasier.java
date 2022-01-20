import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class PanelCasier extends JPanel {
	private final JButton bInapoi, bLogOut;
	private MyCardLayout cardLayout;
	private JFormattedTextField tSerieCIClientNou, tNrCIClientNou, tNrTelCientNou, tSerieCI;
	private JTextField tNrAbonament, tImprumutID, tNumeClientNou, tPrenumeClientNou;
	private JButton bCautaImprumut, bMain, bContinuaImprumut, bAltImprumut, bCreezaContNou, bRezRetur, bCreeazaCont;
	private JCheckBox[] cbFilmeImprumutate;
	private FereastraCard fereastraPrincipala;
	private JPanel pAranjeazaClientNou, pAranjeazaCont, pAranjeazaImprumut;
	private Ascultator ab;
	private Imprumut imprumut;

	PanelCasier(FereastraCard fereastraPrincipala) {
		super();
		ab = new Ascultator();
		bInapoi = new JButton("Inapoi");
		bLogOut = new JButton("LogOut");
		cardLayout = new MyCardLayout();
		this.fereastraPrincipala = fereastraPrincipala;

		bInapoi.addActionListener(ab);

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
			formatterSerieCI.setPlaceholderCharacter(' ');
			tSerieCIClientNou = new JFormattedTextField(formatterSerieCI);
			tSerieCIClientNou.setColumns(10);

			MaskFormatter formatterNrCI = new MaskFormatter("######");
			formatterNrCI.setPlaceholderCharacter(' ');
			tNrCIClientNou = new JFormattedTextField(formatterNrCI);
			tNrCIClientNou.setColumns(10);

			MaskFormatter formatterNrTel = new MaskFormatter("07########");
			formatterNrTel.setPlaceholderCharacter(' ');
			tNrTelCientNou = new JFormattedTextField(formatterNrTel);
			tNrTelCientNou.setColumns(10);

		} catch (ParseException e) {
			e.getStackTrace();
		}

		tNumeClientNou = new JTextField(10);
		tPrenumeClientNou = new JTextField(10);
		bCreezaContNou = new JButton("Creati cont");
		JLabel lNume = new JLabel("Nume:");
		JLabel lPrenume = new JLabel("Prenume:");
		JLabel lSerieCI = new JLabel("Serie CI:");
		JLabel lNrCI = new JLabel("Numar CI:");
		JLabel lNrTelefon = new JLabel("Numar telefon:");

		bCreezaContNou.addActionListener(ab);

		JPanel pCreeazaContClient = new JPanel(new GridLayout(5, 2));
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

		JPanel pButoane = new JPanel();
		pButoane.add(bInapoi);
		pButoane.add(bCreezaContNou);

		pAranjeazaClientNou = new JPanel(new BorderLayout());
		pAranjeazaClientNou.add(pCreeazaContClient, BorderLayout.CENTER);
		pAranjeazaClientNou.add(pButoane, BorderLayout.SOUTH);

		add(pAranjeazaClientNou, "ContNou");
	}

	private void initPanelCautaImprumut() {
		//Folosit sa mearga la fereastra cu cele 3 butoane
		bMain = new JButton("Inapoi");
		JLabel lIDAbonament = new JLabel("Introduceti id-ul abonamentului: ");
		JLabel lIDImprumut = new JLabel("Introduceti id-ul imprumutului: ");
		bCautaImprumut = new JButton("Cautati imprumutul");
		tNrAbonament = new JTextField(4);
		tImprumutID = new JTextField(4);

		bCautaImprumut.addActionListener(ab);

		JPanel pAlegeClient = new JPanel(new GridLayout(2, 2));
		pAlegeClient.add(lIDAbonament);
		pAlegeClient.add(tNrAbonament);
		pAlegeClient.add(lIDImprumut);
		pAlegeClient.add(tImprumutID);


		JPanel pButoane = new JPanel();
		pButoane.add(bMain);
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
			if (e.getSource() == bInapoi) {
				cardLayout.previous(PanelCasier.this);
				fereastraPrincipala.pack();
				return;
			}
			if (e.getSource() == bCreeazaCont) {
				cardLayout.show(PanelCasier.this, "ContNou");
				fereastraPrincipala.pack();
				return;
			}
			if (e.getSource() == bRezRetur) {
				//TODO: fa in imprumut fata de Cont
				imprumut = Cont.getClientImprumut(tNrAbonament.getText(), tImprumutID.getText());
				if (imprumut != null) {
					cardLayout.show(PanelCasier.this, "CautImprumut");
					fereastraPrincipala.pack();
				} else {
					JOptionPane.showMessageDialog(PanelCasier.this, "Imprumutnul nu s-a gasit", "Eroare",
					                              JOptionPane.ERROR_MESSAGE);
				}
				return;
			}
			if(e.getSource()==bContinuaImprumut){
				//TODO: ultima parte cu imrpumutul
			}
		}

	}

}
