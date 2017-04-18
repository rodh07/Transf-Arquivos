package br.univel.table;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

import br.univel.comum.Arquivo;
import br.univel.comum.Cliente;

public class TableModelArquivo extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private Object[][] matriz;
	private int linhas;

	public TableModelArquivo(Map<Cliente, List<Arquivo>> mapa) {
		linhas = 0;
		for (Entry<Cliente, List<Arquivo>> e : mapa.entrySet()) {
			linhas += e.getValue().size();
		}

		matriz = new Object[linhas][10];

		int linha = 0;

		for (Entry<Cliente, List<Arquivo>> e : mapa.entrySet()) {
			for (Arquivo arq : e.getValue()) {
				matriz[linha][0] = e.getKey().getNome();
				matriz[linha][1] = e.getKey().getIp();
				matriz[linha][2] = e.getKey().getPorta();
				matriz[linha][3] = arq.getNome();
				matriz[linha][4] = arq.getPath();
				matriz[linha][5] = arq.getExtensao();
				matriz[linha][6] = arq.getTamanho();
				matriz[linha][7] = arq.getMd5();
				matriz[linha][8] = arq;
				matriz[linha][9] = e;
				linha++;
			}
		}
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public int getRowCount() {
		return linhas;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return matriz[rowIndex][columnIndex];
	}

	@Override
	public String getColumnName(int col) {

		switch (col) {
		case 0:
			return "Cliente";
		case 1:
			return "IP";
		case 2:
			return "Porta";

		case 3:
			return "Nome Arquivo";
		case 4:
			return "Path";
		case 5:
			return "Extensão";
		case 6:
			return "Tamanho";
		case 7:
			return "MD5";

		default:
			return "Erro";
		}

	}
}
