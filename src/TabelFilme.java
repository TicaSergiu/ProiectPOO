import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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
	private DefaultTableModel dateTabel;
	private TableRowSorter<TableModel> sorter;

	TabelFilme(DefaultTableModel dateTabel, boolean administrator) {
		super(dateTabel);
		//1. Transform lista numelor coloanelor in Array de String
		//2. Le fac intr-un tablou cu split
		//3. Aflu numarul de coloane dupa marimea tabloului
		//4. Scad unu pentru a putea folosi ultima coloana
		NR_ULTIM_COLOANA = dateTabel.getColumnCount();

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

		creeazaComboBox();

		this.dateTabel = dateTabel;
		this.administrator = administrator;
	}

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
			for (int i = 0; i < dateTabel.getColumnCount(); i++) {
				pw.print(dateTabel.getColumnName(i).replace(" ", "_") + " ");
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

	public void adaugaLinie() {
		dateTabel.addRow(new Object[]{"", "", "", "", ""});
	}

	public void stergeLinie() {
		dateTabel.removeRow(getSelectedRow());
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
