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
	private final String[] numeColoaneAdmin = new String[]{"Nume", "An Productie", "Nr Copii", "Gen", "Tip"};
	private final String[] numeColoaneUtilizator = new String[]{"Nume", "An Productie", "Tip", "Gen", "Ales"};
	private List<RowSorter.SortKey> valoriFiltrate;
	private int nrIndexColoana;
	private boolean administrator;
	private TableRowSorter<TableModel> filtru;
	private ListaFilme listaFilme;

	TabelFilme(boolean administrator, ListaFilme listaFilme) {
		super();
		this.listaFilme = listaFilme;

		this.administrator = administrator;
		nrIndexColoana = numeColoaneUtilizator.length - 1;

		prelucreazaFilme();

		if (this.administrator) {
			creeazaComboBox();
		} else {
			creeazaFiltruFilme();
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

	public void salveazaTabel() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("assets\\listaFilme.txt"));
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

	public void reseteazaFiltru() {
		filtru.setRowFilter(null);
		filtru.sort();
		System.out.println(getRowCount());
		for (int i = 0; i < getRowCount(); i++) {
			setValueAt(false, i, nrIndexColoana);
		}
	}

	/**
	 * Modifica coloanele cu TipFilm si Genul filmului sa fie modificate prin ComboBox cand tabelul trebuie aratat
	 * administratorului
	 */
	private void creeazaComboBox() {
		JComboBox<String> cbTipFilm = new JComboBox<>();
		cbTipFilm.addItem("Dvd");
		cbTipFilm.addItem("Caseta");
		getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cbTipFilm));

		JComboBox<CategorieFilm> cbCategorii = new JComboBox<>(CategorieFilm.values());
		getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cbCategorii));
	}

	private void creeazaFiltruFilme() {
		filtru = new TableRowSorter<>(this.getModel());
		this.setRowSorter(filtru);
		valoriFiltrate = new ArrayList<>();
		int coloanaSortata = 0;
		valoriFiltrate.add(new RowSorter.SortKey(coloanaSortata, SortOrder.ASCENDING));
		filtru.setSortKeys(valoriFiltrate);
		filtru.sort();
	}

	private void prelucreazaFilme() {
		String[] numeColoane;
		Object[][] dateFilme;
		if (administrator) {
			numeColoane = numeColoaneAdmin;
			dateFilme = new Object[listaFilme.getNumarFilme()][numeColoane.length];
			for (int i = 0; i < listaFilme.getNumarFilme(); i++) {
				dateFilme[i][0] = listaFilme.getFilm(i).getNumeFilm();
				dateFilme[i][1] = listaFilme.getFilm(i).getAnProductie();
				dateFilme[i][2] = listaFilme.getFilm(i).getNrCopii();
				dateFilme[i][3] = listaFilme.getFilm(i).getCategorieFilm();
				dateFilme[i][4] = listaFilme.getFilm(i).getTipFilm();
			}
		} else {
			numeColoane = numeColoaneUtilizator;
			dateFilme = new Object[listaFilme.getNrFilmeDisponibile()][numeColoane.length];
			int j = 0;
			// Trebuie verificate toate filmele din fisier dar doar cele cu cel putin o copie trebuie afisata
			// utilizatorului
			for (int i = 0; i < listaFilme.getNumarFilme(); i++) {
				if (listaFilme.getFilm(i).getNrCopii() > 0) {
					dateFilme[j][0] = listaFilme.getFilm(i).getNumeFilm();
					dateFilme[j][1] = listaFilme.getFilm(i).getAnProductie();
					dateFilme[j][2] = listaFilme.getFilm(i).getTipFilm();
					dateFilme[j][3] = listaFilme.getFilm(i).getCategorieFilm();
					//Ultima coloana a utilizatorului este un checkbox care are valori boolean
					dateFilme[j][4] = false;
					j++;
				}
			}
		}
		setModel(new DefaultTableModel(dateFilme, numeColoane));
	}

	public int getNrIndexUltimaColoana() {
		return nrIndexColoana;
	}

	public void actulizeazaDateTabel() {
		String[] numeColoane = numeColoaneUtilizator;
		Object[][] dateFilme = new Object[listaFilme.getNrFilmeDisponibile()][numeColoane.length];
		int j = 0;
		// Trebuie verificate toate filmele din fisier dar doar cele cu cel putin o copie trebuie afisata
		// utilizatorului
		for (int i = 0; i < listaFilme.getNumarFilme(); i++) {
			if (listaFilme.getFilm(i).getNrCopii() > 0) {
				dateFilme[j][0] = listaFilme.getFilm(i).getNumeFilm();
				dateFilme[j][1] = listaFilme.getFilm(i).getAnProductie();
				dateFilme[j][2] = listaFilme.getFilm(i).getTipFilm();
				dateFilme[j][3] = listaFilme.getFilm(i).getCategorieFilm();
				//Ultima coloana a utilizatorului este un checkbox care are valori boolean
				dateFilme[j][4] = false;
				j++;
			}
		}
		setModel(new DefaultTableModel(dateFilme, numeColoane));
	}

	public void sorteazaTabel(CategorieFilm categorieFilm) {
		try {
			filtru.setRowFilter(RowFilter.regexFilter(categorieFilm.name()));
			filtru.sort();
		} catch (PatternSyntaxException ignored) {
			//Nu este o problema daca se ajunge aici
		}
	}

	public void sorteazaTabel(String anProductie) {
		try {
			filtru.setRowFilter(RowFilter.regexFilter((anProductie)));
		} catch (PatternSyntaxException ignored) {
			//Nu este o problema daca se ajunge aici
		}
	}


	@Override
	public boolean isCellEditable(int row, int column) {
		//Doar ultima coloana poate fi editata de catre utilizator pentru ca este un checkBox
		if (administrator) {
			return true;
		} else {
			return column == getColumnCount() - 1;
		}

	}

	@Override
	public Class<?> getColumnClass(int column) {
		if (administrator) {
			return super.getColumnClass(column);
		} else {

			//Ultima coloana trebuie afisata ca un checkBox daca tabelul trebuie afisat utulizatorului
			return column == getColumnCount() - 1 ? Boolean.class : super.getColumnClass(column);
		}

	}

}
