import javax.swing.table.AbstractTableModel;

import extensions.CSVFile;

public class CSVTableModel extends AbstractTableModel {

	private CSVFile file;
	
	public CSVTableModel(CSVFile file) {
		this.file = file;
	}
	
	@Override
	public int getColumnCount() {
		return file.columnCount();
	}

	@Override
	public int getRowCount() {
		return file.rowCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return file.getCell(rowIndex, columnIndex);
	}
	

}
