import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class FereastraCard extends JFrame {
	private final AscultatorButoane ab;
	private JButton bLogIn, bExit;
	private JFormattedTextField tSerieCI, tNrCI;
	private JLabel lSerieCI, lNrCI;
	private JPanel pLogIn;

	FereastraCard(String titlu) {
		super(titlu);
		ab = new AscultatorButoane();

		initPanelLogIn();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private void initPanelLogIn() {
		try {
			// Pot fi introduse doar litere si minusculele vor fi
			// automat transformate in majuscule
			MaskFormatter formatterLitere = new MaskFormatter("UU");
			tSerieCI = new JFormattedTextField(formatterLitere);
			formatterLitere.setPlaceholderCharacter(' ');
			tSerieCI.setColumns(10);

			// Doar 6 cifre pot fi puse in textField
			MaskFormatter formatterCifre = new MaskFormatter("######");
			tNrCI = new JFormattedTextField(formatterCifre);
			formatterCifre.setPlaceholderCharacter(' ');
			tNrCI.setColumns(10);

		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}

		lSerieCI = new JLabel("Serie CI:");
		lNrCI = new JLabel("Numar CI:");

		bLogIn = new JButton("Intrati in cont");
		bLogIn.addActionListener(ab);
		bExit = new JButton("Exit");

		pLogIn = new JPanel(new GridLayout(3, 2, 5, 5));
		pLogIn.add(lSerieCI);
		pLogIn.add(tSerieCI);
		pLogIn.add(lNrCI);
		pLogIn.add(tNrCI);
		pLogIn.add(new JButton("Exit"));
		pLogIn.add(bLogIn);

		getContentPane().removeAll();
		getContentPane().add(pLogIn);

		pack();
	}

	public void logOut() {
		getContentPane().removeAll();
		getContentPane().add(pLogIn);
		pack();
	}

	private class AscultatorButoane implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Se iese din program cand se apasa pe buton
			if (e.getSource() == bExit) {
				FereastraCard.this.dispose();
				return;
			}

			// Se intra intr-un cont si se incarca urmatoarea fereastra
			if (e.getSource() == bLogIn) {
				String serie = tSerieCI.getText();
				String nrCI = tNrCI.getText();
				if (serie.equals("OV") && nrCI.equals("111111")) {
					getContentPane().removeAll();
					getContentPane().add(new PanelAdministrator(FereastraCard.this));
					pack();
				} else {
					int id = ManagerConturi.cautaContID(tSerieCI.getText(), tNrCI.getText());
					if (id > 1000) {
						ContUtilizator contUtilizator = ManagerConturi.getContUtilizator(id);
						getContentPane().removeAll();
						getContentPane().add(new PanelUtilizator(FereastraCard.this, contUtilizator));
						pack();
					} else if (id > 100) {
						//ContCasier contCasier = ManagerConturi.getContCasier(id);
						getContentPane().removeAll();
						getContentPane().add(new PanelCasier(FereastraCard.this));
						pack();
					} else {
						JOptionPane.showMessageDialog(FereastraCard.this, "Nu a fost gasit contul cu datele introduse",
						                              "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				}
				tSerieCI.setValue(null);
				tNrCI.setValue(null);
			}
		}

	}

}
