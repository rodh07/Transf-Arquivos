<<<<<<< HEAD
package br.univel.table;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import br.univel.server.IServer;

public class TableMConexao extends AbstractTableModel{

	private List<IServer> conexoes = new ArrayList<IServer>();

	public TableMConexao() {
	}
	
	public TableMConexao(List<IServer> conexoes) {
		this.conexoes = conexoes;
	}
	
	private String [] columns = {"Conexões"};
	
	public int getRowCount() {
		return conexoes.size();
	}

	public int getColumnCount() {
		return 1;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {	
	return conexoes.get(rowIndex);	
	}

	public void addServidor(IServer conexao){
		conexoes.add(conexao);
		fireTableDataChanged();
	}
	
	public IServer getConexao(int row){
		return conexoes.get(row);
	}
	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	public List<IServer> getConexoes() {
		return conexoes;
	}
=======
/**
 * 
 */
package br.univel.table;

/**
 * @author Seven
 *
 */
public class TableMConexao {

>>>>>>> branch 'master' of https://github.com/rodh07/Transf-Arquivos-JShared.git
}
