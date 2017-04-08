/**
 * 
 */
package br.univel.table;

<<<<<<< HEAD
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * @author Seven
 *
 */
public class TableMArquivo extends AbstractTableModel{
	
	
	private List<String> nomes;

	private String [] columns = {"Arquivos"};
	
	public TableMArquivo(List<String> nomes) {
		this.nomes = nomes;
	}
	
	public int getRowCount() {
		return nomes.size();
	}

	public int getColumnCount() {
		return 1;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return nomes.get(rowIndex);
	}

	public String getNomeArquivoSelecionado(int row){
		return nomes.get(row);
	}
	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
=======
/**
 * @author Seven
 *
 */
public class TableMArquivo {

>>>>>>> branch 'master' of https://github.com/rodh07/Transf-Arquivos-JShared.git
}
