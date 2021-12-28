import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class TabelFilme extends JTable {
	private final int NR_ULTIM_COLOANA;
	private RowFilter<Object, Object> filtru;
	private List<RowSorter.SortKey> sortKeys;
	private TableRowSorter<TableModel> sorter;

	TabelFilme(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		//1. Transform lista numelor coloanelor in Array de String
		//2. Le fac intr-un tablou cu split
		//3. Aflu numarul de coloane dupa marimea tabloului
		//4. Scad unu pentru a putea folosi ultima coloana
		NR_ULTIM_COLOANA = Arrays.toString(columnNames).split(",").length - 1;

		this.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setResizingAllowed(false);

		sorter = new TableRowSorter<>(this.getModel());
		this.setRowSorter(sorter);
		sortKeys = new ArrayList<>();
		int coloanaSortata = 0;
		sortKeys.add(new RowSorter.SortKey(coloanaSortata, SortOrder.ASCENDING));

		sorter.setSortKeys(sortKeys);
		sorter.sort();

	}

	public void sorteazaTabel(CategorieFilm categorieFilm) {
		try {
			sorter.setRowFilter(RowFilter.regexFilter(categorieFilm.name()));
			sorter.sort();
		} catch (PatternSyntaxException ignored) {
			//Nu este o problema daca se ajunge aici
		}
	}

	public Film getFilmSelectat(int nrLinie){

		return null;
	}

	public void sorteazaTabel(String anProductie) {
		try {
			sorter.setRowFilter(RowFilter.regexFilter((anProductie)));
		} catch (PatternSyntaxException ignored) {
			//Nu este o problema daca se ajunge aici
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		//Doar ultima coloana poate fi editata pentru ca este un checkBox
		return column == NR_ULTIM_COLOANA;
	}

	@Override
	public Class<?> getColumnClass(int column) {
		//Ultima coloana trebuie afisata ca un checkBox
		return column == NR_ULTIM_COLOANA ? Boolean.class : super.getColumnClass(column);
	}

	public int getNR_ULTIM_COLOANA() {
		return NR_ULTIM_COLOANA;
	}

}
