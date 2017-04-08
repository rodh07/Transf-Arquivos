package br.univel.main;

import java.awt.BorderLayout;
<<<<<<< HEAD
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.univel.server.Arquivo;
import br.univel.server.Arquivo.PathDir;
import br.univel.server.Cliente;
import br.univel.server.IServer;
import br.univel.server.Servidor;
import br.univel.table.TableMConexao;
import br.univel.table.TableMTransferencias;
import br.univel.server.TipoFiltro;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Font;

public class TelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtPort;
	private JTable tbConexoes;
	private JTable tbDownloads;
	private Cliente client;
	private TableMConexao tConexoes = new TableMConexao();
	private TableMTransferencias tableTransferencias = new TableMTransferencias();
	private Servidor server;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		setBackground(Color.WHITE);
		initComponents();

	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 43, 359, 21, 60, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 73, 0, 112, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		contentPane.add(lblNome, gbc_lblNome);

		txtNome = new JTextField();
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.insets = new Insets(0, 0, 5, 5);
		gbc_txtNome.gridx = 1;
		gbc_txtNome.gridy = 0;
		contentPane.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);

		JLabel lblPort = new JLabel("Port:");
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPort.anchor = GridBagConstraints.EAST;
		gbc_lblPort.gridx = 2;
		gbc_lblPort.gridy = 0;
		contentPane.add(lblPort, gbc_lblPort);

		txtPort = new JTextField();
		GridBagConstraints gbc_txtPort = new GridBagConstraints();
		gbc_txtPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPort.insets = new Insets(0, 0, 5, 0);
		gbc_txtPort.gridx = 3;
		gbc_txtPort.gridy = 0;
		contentPane.add(txtPort, gbc_txtPort);
		txtPort.setColumns(10);

		JButton btnConfigurar = new JButton("Configurar Servidor");
		btnConfigurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaConfiguracao().setVisible(true);
			}
		});
		GridBagConstraints gbc_btnConfigurar = new GridBagConstraints();
		gbc_btnConfigurar.anchor = GridBagConstraints.EAST;
		gbc_btnConfigurar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConfigurar.gridx = 1;
		gbc_btnConfigurar.gridy = 1;
		contentPane.add(btnConfigurar, gbc_btnConfigurar);

		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!verificarParametrosConexao()) {
					JOptionPane.showMessageDialog(null, "Servidor iniciado com sucesso");
					return;
				}

				conectar();

			}
		});
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.gridwidth = 2;
		gbc_btnConectar.insets = new Insets(0, 0, 5, 0);
		gbc_btnConectar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConectar.gridx = 2;
		gbc_btnConectar.gridy = 1;
		contentPane.add(btnConectar, gbc_btnConectar);

		JLabel lblFiltro = new JLabel("Filtro:");
		GridBagConstraints gbc_lblFiltro = new GridBagConstraints();
		gbc_lblFiltro.fill = GridBagConstraints.VERTICAL;
		gbc_lblFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiltro.anchor = GridBagConstraints.EAST;
		gbc_lblFiltro.gridx = 0;
		gbc_lblFiltro.gridy = 2;
		contentPane.add(lblFiltro, gbc_lblFiltro);

		final JComboBox cmbFiltro = new JComboBox(TipoFiltro.values());
		cmbFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		GridBagConstraints gbc_cmbFiltro = new GridBagConstraints();
		gbc_cmbFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbFiltro.gridwidth = 3;
		gbc_cmbFiltro.insets = new Insets(0, 0, 5, 0);
		gbc_cmbFiltro.gridx = 1;
		gbc_cmbFiltro.gridy = 2;
		contentPane.add(cmbFiltro, gbc_cmbFiltro);

		JLabel lblServer = new JLabel("Servidor Ativo");
		lblServer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblServer = new GridBagConstraints();
		gbc_lblServer.gridwidth = 4;
		gbc_lblServer.insets = new Insets(0, 0, 5, 0);
		gbc_lblServer.gridx = 0;
		gbc_lblServer.gridy = 3;
		contentPane.add(lblServer, gbc_lblServer);

		JScrollPane scrlServer = new JScrollPane();
		GridBagConstraints gbc_scrlServer = new GridBagConstraints();
		gbc_scrlServer.insets = new Insets(0, 0, 5, 0);
		gbc_scrlServer.gridwidth = 4;
		gbc_scrlServer.fill = GridBagConstraints.BOTH;
		gbc_scrlServer.gridx = 0;
		gbc_scrlServer.gridy = 4;
		contentPane.add(scrlServer, gbc_scrlServer);

		JLabel lblTransfers = new JLabel("Transferências");
		lblTransfers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTransfers = new GridBagConstraints();
		gbc_lblTransfers.insets = new Insets(0, 0, 5, 0);
		gbc_lblTransfers.gridwidth = 4;
		gbc_lblTransfers.gridx = 0;
		gbc_lblTransfers.gridy = 5;
		contentPane.add(lblTransfers, gbc_lblTransfers);

		JScrollPane ScrlTransfers = new JScrollPane();
		GridBagConstraints gbc_ScrlTransfers = new GridBagConstraints();
		gbc_ScrlTransfers.gridwidth = 4;
		gbc_ScrlTransfers.fill = GridBagConstraints.BOTH;
		gbc_ScrlTransfers.gridx = 0;
		gbc_ScrlTransfers.gridy = 6;
		contentPane.add(ScrlTransfers, gbc_ScrlTransfers);

		tbDownloads = new JTable();
		tbDownloads.setModel(tableTransferencias);
		ScrlTransfers.setViewportView(tbDownloads);

		txtNome.requestFocusInWindow();
	}

	/**
	 * Inicia o servidor local
	 */
	private void abreTelaArquivos() {
		int selectedRow = tbConexoes.getSelectedRow();
		new TelaArquivos(tConexoes.getConexao(selectedRow), this).setVisible(true);
	}

	private boolean verificarParametrosConexao() {
		return server != null && txtNome.getText().matches("[\\d\\.]+") && txtPort.getText().matches("\\d+");
	}

	/**
	 * Conecta a um servidor conforme parametros passados
	 */
	private void conectar() {
		Registry registry;
		try {
			String ip = txtNome.getText();
			int porta = Integer.parseInt(txtPort.getText());

			registry = LocateRegistry.getRegistry(ip, porta);

			IServer servidor = (IServer) registry.lookup(IServer.NOME_SERVICO);

			servidor.registrarCliente(client);
			adicionaNovaConexao(servidor);

			novo();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel conectar ao endereço e porta informados");
		}
	}

	private void novo() {
		txtNome.setText("");
		txtNome.requestFocusInWindow();
		txtPort.setText("");
	}

	/**
	 * Cria o diretório para os arquivos que serão disponibilizados
	 */
	private void criarDiretorioDowns() {
		try {
			File diretorio = new File(PathDir.ARQUIVOS.toString());
			diretorio.mkdir();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Não foi possível criar o diretório para disponibilização de arquivos");
		}
	}

	/**
	 * Tela para configuração do servidor local
	 */
	private class TelaConfiguracao extends JDialog {

		private final JPanel contentPanel = new JPanel();
		private JTextField txtPort;
		private JTextField txtNomeServidor;

		public TelaConfiguracao() {
			setBounds(100, 100, 426, 114);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			GridBagLayout gbl_contentPanel = new GridBagLayout();
			gbl_contentPanel.columnWidths = new int[] { 0, 264, 0, 0, 0 };
			gbl_contentPanel.rowHeights = new int[] { 0, 0 };
			gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
			gbl_contentPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
			contentPanel.setLayout(gbl_contentPanel);
			{
				JLabel lblNome = new JLabel("Nome");
				GridBagConstraints gbc_lblNome = new GridBagConstraints();
				gbc_lblNome.anchor = GridBagConstraints.EAST;
				gbc_lblNome.insets = new Insets(0, 0, 0, 5);
				gbc_lblNome.gridx = 0;
				gbc_lblNome.gridy = 0;
				contentPanel.add(lblNome, gbc_lblNome);
			}
			{
				txtNomeServidor = new JTextField();
				txtNomeServidor.setColumns(10);
				txtNomeServidor.setText(Servidor.nome_ap);
				GridBagConstraints gbc_txfNomeServidor = new GridBagConstraints();
				gbc_txfNomeServidor.insets = new Insets(0, 0, 0, 5);
				gbc_txfNomeServidor.fill = GridBagConstraints.HORIZONTAL;
				gbc_txfNomeServidor.gridx = 1;
				gbc_txfNomeServidor.gridy = 0;
				contentPanel.add(txtNomeServidor, gbc_txfNomeServidor);
			}
			{
				JLabel lblPortaServidor = new JLabel("Porta");
				lblPortaServidor.setFont(new Font("Tahoma", Font.PLAIN, 13));
				GridBagConstraints gbc_lblPortaServidor = new GridBagConstraints();
				gbc_lblPortaServidor.insets = new Insets(0, 0, 0, 5);
				gbc_lblPortaServidor.anchor = GridBagConstraints.EAST;
				gbc_lblPortaServidor.gridx = 2;
				gbc_lblPortaServidor.gridy = 0;
				contentPanel.add(lblPortaServidor, gbc_lblPortaServidor);
			}
			{
				txtPort = new JTextField();
				GridBagConstraints gbc_txfPorta = new GridBagConstraints();
				gbc_txfPorta.fill = GridBagConstraints.HORIZONTAL;
				gbc_txfPorta.gridx = 3;
				gbc_txfPorta.gridy = 0;
				contentPanel.add(txtPort, gbc_txfPorta);
				txtPort.setColumns(10);

				txtPort.setText(String.valueOf(Servidor.PORTA));
			}

			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					JButton okButton = new JButton("Iniciar Servidor");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							iniciarServidor();
						}
					});
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);

					if (server != null) {
						okButton.setEnabled(false);
					}
				}
				{
					JButton cancelButton = new JButton("Fechar");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});

					buttonPane.add(cancelButton);
				}
			}
		}

		/**
		 * @param download
		 */

		private void iniciarServidor() {
			if (!verificaParametrosParaIniciarServidor()) {
				JOptionPane.showMessageDialog(null, "Parametros informados para iniciar servidor inválidos");
				return;
			}

			Servidor.PORTA = Integer.parseInt(txtPort.getText());
			Servidor.nome_ap = txtNomeServidor.getText();

			server = new Servidor(getTela());
			server.start();

			dispose();
			JOptionPane.showMessageDialog(null, "Servidor iniciado com sucesso");
		}

		private boolean verificaParametrosParaIniciarServidor() {
			return !txtNomeServidor.getText().isEmpty() && txtPort.getText().matches("\\d+");
		}

	}

	public void carregaDownload(byte[] download) {
		String path = new StringBuilder().append(PathDir.ARQUIVOS).append('/').append(download.getNomeArquivo())
				.toString();

		tableTransferencias.addDownload(download);

		File file = new File(path);
		BufferedOutputStream bos;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(file));
			bos.write(download.getDadosDown());
			bos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao baixar arquivo.");
			return;
		}

		JOptionPane.showMessageDialog(null, "Arquivo transferido com sucesso.");
	}

	public void adicionaNovaConexao(IServer servidor) {
		tConexoes.addServidor(servidor);
	}

	public TelaPrincipal getTela() {
		return this;
	}

	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			for (IServer conexao : tConexoes.getConexoes()) {
				try {
					conexao.desconectar(client);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		}

		super.processWindowEvent(e);
	}

	public void removerConexao(IServer servico) {
		tConexoes.getConexoes().remove(servico);
		tConexoes.fireTableDataChanged();
=======
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.univel.server.TipoFiltro;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.Font;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{30, 359, 21, 60, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 73, 0, 112, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblIp = new JLabel("Nome:");
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.anchor = GridBagConstraints.EAST;
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 0;
		contentPane.add(lblIp, gbc_lblIp);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblPort = new JLabel("Port:");
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPort.anchor = GridBagConstraints.EAST;
		gbc_lblPort.gridx = 2;
		gbc_lblPort.gridy = 0;
		contentPane.add(lblPort, gbc_lblPort);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 0;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Configurar Servidor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaConfiguracao().setVisible(true);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.gridwidth = 2;
		gbc_btnConectar.insets = new Insets(0, 0, 5, 0);
		gbc_btnConectar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConectar.gridx = 2;
		gbc_btnConectar.gridy = 1;
		contentPane.add(btnConectar, gbc_btnConectar);
		
		JLabel lblFiltro = new JLabel("Filtro:");
		GridBagConstraints gbc_lblFiltro = new GridBagConstraints();
		gbc_lblFiltro.fill = GridBagConstraints.VERTICAL;
		gbc_lblFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiltro.anchor = GridBagConstraints.EAST;
		gbc_lblFiltro.gridx = 0;
		gbc_lblFiltro.gridy = 2;
		contentPane.add(lblFiltro, gbc_lblFiltro);
		
		final JComboBox cmbFiltro = new JComboBox(TipoFiltro.values());
		cmbFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	             
	                	            
			}
		});
		GridBagConstraints gbc_cmbFiltro = new GridBagConstraints();
		gbc_cmbFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbFiltro.gridwidth = 3;
		gbc_cmbFiltro.insets = new Insets(0, 0, 5, 0);
		gbc_cmbFiltro.gridx = 1;
		gbc_cmbFiltro.gridy = 2;
		contentPane.add(cmbFiltro, gbc_cmbFiltro);
		
		JLabel lblNewLabel_1 = new JLabel("Servidor Ativo");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 4;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		JLabel lblNewLabel = new JLabel("Transferências");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 5;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 4;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 6;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);
>>>>>>> branch 'master' of https://github.com/rodh07/Transf-Arquivos-JShared.git
	}

}
