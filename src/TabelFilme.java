import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class TabelFilme extends JTable {
	private final int NR_ULTIM_COLOANA;
	private boolean administrator;
	private RowFilter<Object, Object> filtru;
	private List<RowSorter.SortKey> sortKeys;
	private TableRowSorter<TableModel> sorter;

	TabelFilme(boolean administrator) {
		super();
		this.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setResizingAllowed(false);

		if (!administrator) {
			sorter = new TableRowSorter<>(this.getModel());
			this.setRowSorter(sorter);
			sortKeys = new ArrayList<>();
			int coloanaSortata = 0;
			sortKeys.add(new RowSorter.SortKey(coloanaSortata, SortOrder.ASCENDING));
			sorter.setSortKeys(sortKeys);
			sorter.sort();
		}

		prelucreazaFilme();
		creeazaComboBox();

		NR_ULTIM_COLOANA = getModel().getColumnCount() - 1;
		this.administrator = administrator;
	}

	private void prelucreazaFilme() {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("assets\\ListaFilme"));
			//Prima linia este numele coloanelor
			String[] numeColoane = null;
			numeColoane = bf.readLine().split(" ");
			if (!administrator) {
				List<String> nColoane = new ArrayList<>(List.of(numeColoane));
				nColoane.add("Ales");
				numeColoane = nColoane.toArray(new String[0]);

			}
			for (int i = 0; i < numeColoane.length; i++) {
				numeColoane[i] = numeColoane[i].replace("_", " ");
			}
			//Urmatoarele sunt filmele
			String linie;
			int n = ListaFilme.getInstance().getNrFilme();
			Object[][] dateFilme = new Object[n][numeColoane.length];
			for (int i = 0; i < n; i++) {
				String[] film = bf.readLine().split(" ");
				//Prima coloana este cu numele filmului
				film[0] = film[0].replace("_", " ");
				dateFilme[i][0] = film[0];
				dateFilme[i][1] = film[1];
				dateFilme[i][2] = film[2];
				dateFilme[i][3] = film[3];
				dateFilme[i][4] = film[4];
				if (!administrator) {
					dateFilme[i][5] = false;
				}
			}
			// In spatele tabelului este un "model" care contine informatii despre randuri si despre coloane, si imi
			// setez modelul cu filmele din fisier, si asa pot sa adaug si sa elimin randuri la rulare
			setModel(new DefaultTableModel(dateFilme, numeColoane));
		} catch (IOException ignored) {
		}
	}

	// Face posibila
	private void creeazaComboBox() {
		JComboBox<String> cbTipFilm = new JComboBox<>();
		cbTipFilm.addItem("Dvd");
		cbTipFilm.addItem("Caseta");
		getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cbTipFilm));

		JComboBox<CategorieFilm> cbCategorii = new JComboBox<>(CategorieFilm.values());
		getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cbCategorii));
	}

	public void salveazaTabel() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("assets\\ListaFilme"));
			for (int i = 0; i < getModel().getColumnCount(); i++) {
				pw.print(getModel().getColumnName(i).replace(" ", "_") + " ");
			}
			pw.println();
			for (int i = 0; i < dataModel.getRowCount(); i++) {
				for (int j = 0; j < 5; j++) {
					try {
						String t = (String)dataModel.getValueAt(i, j);
						t = t.replace(" ", "_");
						pw.print(t + " ");
					} catch (ClassCastException eInt) {
						try {
							int t = (Integer)dataModel.getValueAt(i, j);
							pw.print(t + " ");

						} catch (ClassCastException eCatFilm) {
							CategorieFilm cf = (CategorieFilm)dataModel.getValueAt(i, j);
							pw.print(cf + " ");
						}
					}
				}
				pw.println();
				pw.flush();
			}
			pw.close();
		} catch (IOException ignored) {
		}
	}

	public void sorteazaTabel(CategorieFilm categorieFilm) {
		try {
			sorter.setRowFilter(RowFilter.regexFilter(categorieFilm.name()));
			sorter.sort();
		} catch (PatternSyntaxException ignored) {
			//Nu este o problema daca se ajunge aici
		}
	}

	public void sorteazaTabel(String anProductie) {
		try {
			sorter.setRowFilter(RowFilter.regexFilter((anProductie)));
		} catch (PatternSyntaxException ignored) {
			//Nu este o problema daca se ajunge aici
		}
	}

	public void adaugaRand() {
		DefaultTableModel tableModel = (DefaultTableModel)getModel();
		tableModel.addRow(new Object[]{"", "", "", "", ""});
	}

	public void eliminaRand() {
		DefaultTableModel tableModel = (DefaultTableModel)getModel();
		tableModel.removeRow(getSelectedRow());
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		//Doar ultima coloana poate fi editata de catre utilizator pentru ca este un checkBox
		if (!administrator) {
			return column == NR_ULTIM_COLOANA;
		}
		return true;
	}

	@Override
	public Class<?> getColumnClass(int column) {
		//Ultima coloana trebuie afisata ca un checkBox daca tabelul trebuie afisat utulizatorului
		if (!administrator) {
			return column == NR_ULTIM_COLOANA ? Boolean.class : super.getColumnClass(column);
		}
		return super.getColumnClass(column);
	}

	public int getNR_ULTIM_COLOANA() {
		return NR_ULTIM_COLOANA;
	}

}
