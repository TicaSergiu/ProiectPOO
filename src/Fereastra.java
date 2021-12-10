import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class Fereastra implements ActionListener {
	private JFrame frame;
	private JPanel panelIntraCont;
	private JButton butonIntraCont;

	Fereastra() {
		frame = new JFrame("Inchiriere filme");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMaximumSize(new Dimension(200, 200));

		initPanelLogIn();

		frame.pack();
		frame.setVisible(true);
	}

	private void initPanelLogIn() {
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.CENTER);
		panelIntraCont = new JPanel(layout);

		MaskFormatter formatter;
		JFormattedTextField serieCI = null;
		JLabel labelSerieCI;
		try {
			formatter = new MaskFormatter("??");
			labelSerieCI = new JLabel("Seria CI:");
			labelSerieCI.setLabelFor(serieCI);
			serieCI = new JFormattedTextField(formatter);
			serieCI.setText("");
			panelIntraCont.add(labelSerieCI);
			panelIntraCont.add(serieCI);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}

		butonIntraCont = new JButton("apasa-ma");
		butonIntraCont.addActionListener(this);
		panelIntraCont.add(butonIntraCont);

		frame.add(panelIntraCont);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == butonIntraCont) {
			panelIntraCont.setVisible(false);
		}
	}

}
