import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class FereastraPrincipala extends JFrame {
	private final AscultatorButoane ab;
	private JButton bLogIn, bExit;
	private JFormattedTextField tSerieCI, tNrCI;
	private JLabel lSerieCI, lNrCI;
	private JPanel pLogIn;

	FereastraPrincipala() {
		super("Inchiriere filme");
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
		bExit = new JButton("Exit");

		bLogIn.addActionListener(ab);
		bExit.addActionListener(ab);

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

	public void iesiDinContCurent() {
		getContentPane().removeAll();
		getContentPane().add(pLogIn);
		pack();
	}

	private class AscultatorButoane implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Se iese din program cand se apasa pe buton
			// Nu merge, dar nu stiu de ce
			if (e.getSource() == bExit) {
				System.out.println("afara");
				FereastraPrincipala.this.dispose();
				return;
			}

			// Se intra intr-un cont si se incarca urmatoarea fereastra
			if (e.getSource() == bLogIn) {
				String serie = tSerieCI.getText();
				String nrCI = tNrCI.getText();
				if (serie.equals("OV") && nrCI.equals("111111")) {
					getContentPane().removeAll();
					getContentPane().add(new PanelAdministrator(FereastraPrincipala.this));
					pack();
				} else {
					int id = ManagerConturi.cautaContID(tSerieCI.getText(), tNrCI.getText());
					if (id > 1000) {
						ContUtilizator contUtilizator = ManagerConturi.getContUtilizator(id);
						getContentPane().removeAll();
						getContentPane().add(new PanelUtilizator(FereastraPrincipala.this, contUtilizator));
						pack();
					} else if (id > 100) {
						//ContCasier contCasier = ManagerConturi.getContCasier(id);
						getContentPane().removeAll();
						getContentPane().add(new PanelCasier(FereastraPrincipala.this));
						pack();
					} else {
						JOptionPane.showMessageDialog(FereastraPrincipala.this, "Nu a fost gasit contul cu datele introduse",
						                              "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				}
				tSerieCI.setValue(null);
				tNrCI.setValue(null);
			}
		}

	}

}
