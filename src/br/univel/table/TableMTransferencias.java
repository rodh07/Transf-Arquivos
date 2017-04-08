/**
 * 
 */
package br.univel.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.download.Downloader;

/**
 * @author Seven
 *
 */
public class TableMTransferencias extends AbstractTableModel {

	private List<Downloader> downloads = new ArrayList<Downloader>();
	private String [] columns = {"De","Arquivo", "Status"};
	
	public int getRowCount() {
		return downloads.size();
	}

	public int getColumnCount() {
		return columns.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Downloader download = downloads.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return download.getDown();
		case 1:
			return download.getNomeArquivo();
		case 2:
			return download.getStatus();

		default:
			return "erro";
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	/**
	 * @param download
	 */
	public void addDownload(byte[] download) {
		downloads.addAll(downloads);
		fireTableDataChanged();
	}
}
