package br.univel.main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import br.univel.download.Downloader;
import br.univel.server.Arquivo;
import br.univel.server.Cliente;
import br.univel.server.IServer;
import br.univel.server.TipoFiltro;
import br.univel.table.TableMArquivo;

public class TelaArquivos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final TipoFiltro TipoFiltro = null;
	private JPanel contentPane;
	private JTable tbArquivos;
	private TelaPrincipal tela;
	private IServer servidor;

	public TelaArquivos(IServer servidor, TelaPrincipal tela) {
		this.servidor = servidor;
		this.tela = tela;
		initComponents();
		
		try {
			tbArquivos.setModel(new TableMArquivo(servidor.procurarArquivo("query", TipoFiltro , "filtro")));
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "Erro interno ao tentar carregar arquivos disponíveis para download");
		}
	}

	private void initComponents() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		tbArquivos = new JTable();
		scrollPane.setViewportView(tbArquivos);
		
		tbArquivos.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					int selectedRow = tbArquivos.getSelectedRow();
					
					String nomeArquivoSelecionado = ((TableMArquivo)tbArquivos.getModel()).
									getNomeArquivoSelecionado(selectedRow);
					
					try {
						Cliente cli = null;
						Arquivo arq = null;
						byte[] download = servidor.baixarArquivo(cli, arq);
						tela.carregaDownload(download);
						setVisible(false);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}

				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
	}

}
