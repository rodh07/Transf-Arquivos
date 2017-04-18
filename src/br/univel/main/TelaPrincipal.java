package br.univel.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import br.univel.comum.Arquivo;
import br.univel.comum.Cliente;
import br.univel.comum.IServer;
import br.univel.comum.TipoFiltro;
import br.univel.methods.ValidadorMd5;
import br.univel.table.TableModelArquivo;

import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaPrincipal extends JFrame implements IServer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIP;
	private JButton btnConectar;
	private JLabel lblPorta;
	private JTextField txtPorta;

	// private TelaPrincipal telaServer;
	// private TableMTransferencia tmTransferencias = new TableMTransferencia();

	private JLabel lblNewLabel;
	private JTextField txtPesquisa;
	private JLabel lblNewLabel_1;
	private JLabel lblFiltros;
	private JLabel lblIpCliente;
	private JTextField txtIpCliente;
	private JLabel lblPortaCliente_1;
	private JTextField txtPortaCliente;
	private JLabel lblConexoes;
	private IServer serverS;
	private IServer clientServer;
	private Registry registryS;
	private Registry registryC;
	private Cliente cliente;
	// private TableModelArquivo tableModelArquivo;

	public static int PORTA = 1818;
	public static String nomeApresentacao = "";
	private static final String path = "./Share";
	private HashMap<String, Cliente> cliServer;
	private HashMap<Cliente, List<Arquivo>> arquivos;
	private JButton DesconectarCliente;
	private JButton btnPararServidor;
	private JPanel panel;
	private JComboBox<?> cmbFiltro;
	private JLabel lblNome;
	private JTextField txtNomeCliente;
	private JTextField txtID;
	private JLabel lblID;
	private JTextField textField;
	private Thread thread;
	private JButton btnDownload;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JTextArea textAreaServer;
	private JScrollPane scrollPane_1;
	private JTable tbDownloads;

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

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 719, 574);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 99, 33, 315, 43, 95, 78, 84, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 45, 35, 17, 153, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblIp = new JLabel("IP Server:");
		lblIp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.anchor = GridBagConstraints.EAST;
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 0;
		contentPane.add(lblIp, gbc_lblIp);

		txtIP = new JTextField();
		txtIP.setToolTipText("\r\n");
		txtIP.setText("127.0.0.1");

		GridBagConstraints gbc_txtIP = new GridBagConstraints();
		gbc_txtIP.gridwidth = 2;
		gbc_txtIP.insets = new Insets(0, 0, 5, 5);
		gbc_txtIP.fill = GridBagConstraints.BOTH;
		gbc_txtIP.gridx = 1;
		gbc_txtIP.gridy = 0;
		contentPane.add(txtIP, gbc_txtIP);
		txtIP.setColumns(10);

		lblPorta = new JLabel("Porta:");
		lblPorta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.anchor = GridBagConstraints.EAST;
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.gridx = 3;
		gbc_lblPorta.gridy = 0;
		contentPane.add(lblPorta, gbc_lblPorta);

		btnConectar = new JButton("Iniciar Servidor");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				IniciarServidor();
			}

		});

		txtPorta = new JTextField();
		txtPorta.setText("1818");
		txtPorta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					IniciarServidor();
				}

			}
		});
		GridBagConstraints gbc_txtPorta = new GridBagConstraints();
		gbc_txtPorta.gridwidth = 2;
		gbc_txtPorta.insets = new Insets(0, 0, 5, 5);
		gbc_txtPorta.fill = GridBagConstraints.BOTH;
		gbc_txtPorta.gridx = 4;
		gbc_txtPorta.gridy = 0;
		contentPane.add(txtPorta, gbc_txtPorta);
		txtPorta.setColumns(10);
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.gridwidth = 3;
		gbc_btnConectar.fill = GridBagConstraints.BOTH;
		gbc_btnConectar.insets = new Insets(0, 0, 5, 0);
		gbc_btnConectar.gridx = 6;
		gbc_btnConectar.gridy = 0;
		contentPane.add(btnConectar, gbc_btnConectar);

		lblNome = new JLabel("Nome cliente:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 1;
		contentPane.add(lblNome, gbc_lblNome);

		txtNomeCliente = new JTextField();
		txtNomeCliente.setText("Rodrigo Hajime");
		GridBagConstraints gbc_txtNomeCliente = new GridBagConstraints();
		gbc_txtNomeCliente.gridwidth = 2;
		gbc_txtNomeCliente.insets = new Insets(0, 0, 5, 5);
		gbc_txtNomeCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNomeCliente.gridx = 1;
		gbc_txtNomeCliente.gridy = 1;
		contentPane.add(txtNomeCliente, gbc_txtNomeCliente);
		txtNomeCliente.setColumns(10);

		lblID = new JLabel("ID:");
		GridBagConstraints gbc_lblID = new GridBagConstraints();
		gbc_lblID.insets = new Insets(0, 0, 5, 5);
		gbc_lblID.anchor = GridBagConstraints.EAST;
		gbc_lblID.gridx = 3;
		gbc_lblID.gridy = 1;
		contentPane.add(lblID, gbc_lblID);

		txtID = new JTextField();
		txtID.setText("1");
		GridBagConstraints gbc_txtID = new GridBagConstraints();
		gbc_txtID.gridwidth = 2;
		gbc_txtID.insets = new Insets(0, 0, 5, 5);
		gbc_txtID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtID.gridx = 4;
		gbc_txtID.gridy = 1;
		contentPane.add(txtID, gbc_txtID);
		txtID.setColumns(10);

		btnPararServidor = new JButton("Parar Server");
		btnPararServidor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPararServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pararServidor();
			}
		});
		GridBagConstraints gbc_btnPararServidor = new GridBagConstraints();
		gbc_btnPararServidor.insets = new Insets(0, 0, 5, 0);
		gbc_btnPararServidor.gridwidth = 3;
		gbc_btnPararServidor.fill = GridBagConstraints.BOTH;
		gbc_btnPararServidor.gridx = 6;
		gbc_btnPararServidor.gridy = 1;
		contentPane.add(btnPararServidor, gbc_btnPararServidor);

		lblIpCliente = new JLabel("IP Cliente:");
		lblIpCliente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblIpCliente = new GridBagConstraints();
		gbc_lblIpCliente.anchor = GridBagConstraints.EAST;
		gbc_lblIpCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblIpCliente.gridx = 0;
		gbc_lblIpCliente.gridy = 2;
		contentPane.add(lblIpCliente, gbc_lblIpCliente);

		txtIpCliente = new JTextField();
		txtIpCliente.setText("127.0.0.1");
		GridBagConstraints gbc_txtIpCliente = new GridBagConstraints();
		gbc_txtIpCliente.gridwidth = 2;
		gbc_txtIpCliente.insets = new Insets(0, 0, 5, 5);
		gbc_txtIpCliente.fill = GridBagConstraints.BOTH;
		gbc_txtIpCliente.gridx = 1;
		gbc_txtIpCliente.gridy = 2;
		contentPane.add(txtIpCliente, gbc_txtIpCliente);
		txtIpCliente.setColumns(10);

		lblPortaCliente_1 = new JLabel("Porta:");
		GridBagConstraints gbc_lblPortaCliente_1 = new GridBagConstraints();
		gbc_lblPortaCliente_1.anchor = GridBagConstraints.EAST;
		gbc_lblPortaCliente_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPortaCliente_1.gridx = 3;
		gbc_lblPortaCliente_1.gridy = 2;
		contentPane.add(lblPortaCliente_1, gbc_lblPortaCliente_1);

		txtPortaCliente = new JTextField();
		txtPortaCliente.setText("1818");
		txtPortaCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					conectarCliente();
				}

			}
		});
		GridBagConstraints gbc_txtPortaCliente = new GridBagConstraints();
		gbc_txtPortaCliente.gridwidth = 2;
		gbc_txtPortaCliente.insets = new Insets(0, 0, 5, 5);
		gbc_txtPortaCliente.fill = GridBagConstraints.BOTH;
		gbc_txtPortaCliente.gridx = 4;
		gbc_txtPortaCliente.gridy = 2;
		contentPane.add(txtPortaCliente, gbc_txtPortaCliente);
		txtPortaCliente.setColumns(10);

		btnNewButton = new JButton("Conectar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				conectarCliente();

			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 2;
		contentPane.add(btnNewButton, gbc_btnNewButton);

		lblFiltros = new JLabel("Filtros:");
		GridBagConstraints gbc_lblFiltros = new GridBagConstraints();
		gbc_lblFiltros.anchor = GridBagConstraints.EAST;
		gbc_lblFiltros.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiltros.gridx = 0;
		gbc_lblFiltros.gridy = 3;
		contentPane.add(lblFiltros, gbc_lblFiltros);

		cmbFiltro = new JComboBox(TipoFiltro.values());
		GridBagConstraints gbc_cmbFiltro = new GridBagConstraints();
		gbc_cmbFiltro.gridwidth = 2;
		gbc_cmbFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_cmbFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbFiltro.gridx = 1;
		gbc_cmbFiltro.gridy = 3;
		contentPane.add(cmbFiltro, gbc_cmbFiltro);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 5;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 3;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);

		lblConexoes = new JLabel("Conex\u00F5es ativas");
		lblConexoes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblConexoes = new GridBagConstraints();
		gbc_lblConexoes.gridwidth = 9;
		gbc_lblConexoes.insets = new Insets(0, 0, 5, 0);
		gbc_lblConexoes.gridx = 0;
		gbc_lblConexoes.gridy = 4;
		contentPane.add(lblConexoes, gbc_lblConexoes);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Servidor Ativo",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 9;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 5;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 29, 33, 340, 0, 43, 16, 75, 84, 0 };
		gbl_panel.rowHeights = new int[] { 99, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 8;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);

		textAreaServer = new JTextArea();
		scrollPane.setViewportView(textAreaServer);

		lblNewLabel = new JLabel("Transferencias");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridwidth = 9;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 6;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 2;
		gbc_scrollPane_1.gridwidth = 9;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 7;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);

		tbDownloads = new JTable();
		scrollPane_1.setViewportView(tbDownloads);

		lblNewLabel_1 = new JLabel("Pesquisa:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 9;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		txtPesquisa = new JTextField();
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {

				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					carregarBusca(txtPesquisa.getText());
				}

			}
		});
		GridBagConstraints gbc_txtPesquisa = new GridBagConstraints();
		gbc_txtPesquisa.insets = new Insets(0, 0, 5, 0);
		gbc_txtPesquisa.gridwidth = 8;
		gbc_txtPesquisa.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPesquisa.gridx = 1;
		gbc_txtPesquisa.gridy = 9;
		contentPane.add(txtPesquisa, gbc_txtPesquisa);
		txtPesquisa.setColumns(10);

		btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int linhaSelecionada = tbDownloads.getSelectedRow();

				download(linhaSelecionada);
			}
		});
		GridBagConstraints gbc_btnDownload = new GridBagConstraints();
		gbc_btnDownload.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDownload.insets = new Insets(0, 0, 0, 5);
		gbc_btnDownload.gridx = 0;
		gbc_btnDownload.gridy = 10;
		contentPane.add(btnDownload, gbc_btnDownload);

		DesconectarCliente = new JButton("Desconectar Cliente");
		DesconectarCliente.setFont(new Font("Tahoma", Font.PLAIN, 10));
		GridBagConstraints gbc_DesconectarCliente = new GridBagConstraints();
		gbc_DesconectarCliente.anchor = GridBagConstraints.EAST;
		gbc_DesconectarCliente.fill = GridBagConstraints.VERTICAL;
		gbc_DesconectarCliente.gridwidth = 3;
		gbc_DesconectarCliente.gridx = 6;
		gbc_DesconectarCliente.gridy = 10;
		contentPane.add(DesconectarCliente, gbc_DesconectarCliente);

		txtIP.requestFocusInWindow();

		criarDiretorio();
		arquivos = new HashMap<Cliente, List<Arquivo>>();

	}

	protected void carregarBusca(String pesquisa) {

		try {
			HashMap<Cliente, List<Arquivo>> busca = (HashMap<Cliente, List<Arquivo>>) procurarArquivo(pesquisa,
					(TipoFiltro) cmbFiltro.getSelectedItem());

			TableModelArquivo model = new TableModelArquivo(busca);
			tbDownloads.setModel(model);

		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private void criarDiretorio() {

		File diretorio = new File(path);
		diretorio.mkdir();

	}

	/**
	 * 
	 */
	protected void download(int linhaSelecionada) {

		Cliente cliente = new Cliente();
		Arquivo arquivo = new Arquivo();

		cliente.setNome(tbDownloads.getValueAt(linhaSelecionada, 0).toString());
		cliente.setIp(tbDownloads.getValueAt(linhaSelecionada, 1).toString());
		cliente.setPorta(Integer.valueOf(tbDownloads.getValueAt(linhaSelecionada, 2).toString()));

		arquivo.setNome(tbDownloads.getValueAt(linhaSelecionada, 3).toString());
		arquivo.setPath(tbDownloads.getValueAt(linhaSelecionada, 4).toString());
		arquivo.setExtensao(tbDownloads.getValueAt(linhaSelecionada, 5).toString());
		arquivo.setTamanho(Integer.valueOf(tbDownloads.getValueAt(linhaSelecionada, 6).toString()));
		arquivo.setMd5(tbDownloads.getValueAt(linhaSelecionada, 7).toString());

		try {
			Registry registryConDowload = LocateRegistry.getRegistry(cliente.getIp(), cliente.getPorta());
			IServer conDownload = (IServer) registryConDowload.lookup(IServer.NOME_SERVICO);

			byte[] bytes = conDownload.baixarArquivo(ClienteLocal(), arquivo);

			if (bytes == null) {
			} else {
				String nome = "cópia_de_" + arquivo.getNome().toString();

				arquivar(new File(nome), bytes);

			}

		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param file
	 * @param bytes
	 */
	public void arquivar(File arq, byte[] dados) {
		String path = "." + File.separatorChar + "share" + File.separatorChar + arq.getName();
		try {
			Files.write(Paths.get(path), dados, StandardOpenOption.CREATE);

			JOptionPane.showMessageDialog(TelaPrincipal.this, "Download realizado com sucesso!!!");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 
	 */
	protected void encontrarArquivo() {

		String nomeArquivo = txtPesquisa.getText();
		// int id = (int) tbDownloads.getValueAt(tbDownloads.getSelectedRow(),
		// 1);
		String IP = (String) tbDownloads.getValueAt(tbDownloads.getSelectedRow(), 2);
		int porta = (int) tbDownloads.getValueAt(tbDownloads.getSelectedRow(), 3);

		Arquivo arquivo = new Arquivo();
		arquivo.setNome(nomeArquivo);

		try {
			registryS = LocateRegistry.getRegistry(IP, porta);
			IServer clienteServidor = (IServer) registryS.lookup(IServer.NOME_SERVICO);
			clienteServidor.registrarCliente(cliente);

			byte[] baixarArquivo = clienteServidor.baixarArquivo(cliente, arquivo);
			escreverArquivo(new File("C:\\JShare\\" + arquivo.getNome()), baixarArquivo);

		} catch (Exception e) {
			System.err.println("Erro ao iniciar download do arquivo.");
			e.printStackTrace();
		}
	}

	private void escreverArquivo(File file, byte[] baixarArquivo) {

	}

	/**
	 * 
	 */
	protected void conectarCliente() {
		String nomeCliente = txtNomeCliente.getText();
		if (nomeCliente.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Digite um nome!");
			txtNomeCliente.requestFocus();
			return;
		}

		String ip = txtIpCliente.getText().trim();
		// String ipCliente = txtIpCliente.getText().trim();
		if (!ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
			JOptionPane.showMessageDialog(this, "Digite um endereï¿½o de IP vï¿½lido!");
			txtIpCliente.requestFocus();
			return;
		}

		// Cliente cliente = ClienteLocal();
		String porta = txtPortaCliente.getText().trim();
		// String portaCliente = txtPortaCliente.getText().trim();

		int port = Integer.parseInt(porta);
		// int intPortaCliente = Integer.parseInt(portaCliente);

		try {

			Cliente cliente = ClienteLocal();
			registryC = LocateRegistry.getRegistry(ip, port);
			clientServer = (IServer) registryC.lookup(IServer.NOME_SERVICO);
			clientServer.registrarCliente(cliente);
			thread = new Thread(new Runnable() {

				@Override
				public void run() {

					while (true) {

						try {
							Arquivar();
							// clientServer.publicarListaArquivos(cliente,
							// listarArquivos());
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});

			thread.start();

		} catch (Exception e) {
			imprimir("Erro ao conectar no servidor, por gentileza, verificar as seguintes condições: ");
			imprimir("Porta e IP/conexão do cliente");
			e.printStackTrace();
		}
	}

	public void Arquivar() {

		Cliente cliente = ClienteLocal();

		List<Arquivo> list = listarArquivos();

		try {
			clientServer.publicarListaArquivos(cliente, list);
			imprimir("Lista atualizada!!");
		} catch (Exception e) {
			e.printStackTrace();
			imprimir("Lista não atualizada, por gentileza, verificar.");
		}
	}

	/**
	 * @return
	 */
	private Cliente ClienteLocal() {
		/*
		 * String ip = ""; String nome = ""; try { ip =
		 * InetAddress.getLocalHost().getHostAddress(); nome =
		 * InetAddress.getLocalHost().getHostName(); } catch
		 * (UnknownHostException e) { e.printStackTrace(); }
		 */
		Cliente cliente = new Cliente();
		cliente.setIp(txtIpCliente.getText().trim());
		cliente.setNome(txtNomeCliente.getText().trim());
		cliente.setPorta(Integer.parseInt(txtPortaCliente.getText()));

		return cliente;
	}

	/**
	 * @return
	 */
	protected List<Arquivo> listarArquivos() {

		File dirStart = new File("." + File.separatorChar + "share" + File.separatorChar);
		List<Arquivo> listaArquivos = new ArrayList<>();
		for (File file : dirStart.listFiles()) {
			if (file.isFile()) {
				Arquivo arquivos = new Arquivo();
				int contId = 1;
				arquivos.setId(new Long(contId++));
				arquivos.setNome(file.getName());
				arquivos.setTamanho(file.length());
				String extensao = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
				arquivos.setExtensao(extensao);
				arquivos.setPath(file.getPath());
				arquivos.setDataHoraModificacao(new Date(file.lastModified()));
				arquivos.setMd5(ValidadorMd5.getMD5Checksum(arquivos.getPath()));
				listaArquivos.add(arquivos);
			}
		}
		return listaArquivos;
	}

	/**
	 * @return
	 */
	/*
	 * private List<Arquivo> listaCliente() {
	 * 
	 * File dirDown = new File("C://JShare/Uploads");
	 * 
	 * if (!dirDown.exists()) dirDown.mkdirs();
	 * 
	 * 
	 * 
	 * List<Arquivo> listaArquivos = new ArrayList<>();
	 * 
	 * for (File file : dirDown.listFiles()) { if (file.isFile()) { Arquivo arq
	 * = new Arquivo(); listaArquivos.add(arq); } else {
	 * System.out.println("Não foi encontrado nenhum arquivo"); } }
	 * 
	 * return listaArquivos; }
	 * 
	 */
	protected void pararServidor() {
		imprimir("Parando Servidor..");

		try {
			UnicastRemoteObject.unexportObject(this, true);
			UnicastRemoteObject.unexportObject(registryS, true);

			btnPararServidor.setEnabled(false);

			imprimir("Serviço encerrado.");

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param string
	 */
	private void imprimir(String string) {
		textAreaServer.append(" -> ");
		textAreaServer.append(string);
		textAreaServer.append("\n");
	}

	protected void IniciarServidor() {

		String ip = txtIP.getText().trim();
		if (!ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
			JOptionPane.showMessageDialog(this, "Digite um endereco de IP válido!");
			return;
		}

		// int Porta2 = Integer.parseInt(porta);
		if (serverS == null) {
			try {
				serverS = (IServer) UnicastRemoteObject.exportObject(TelaPrincipal.this, 0);
				registryS = LocateRegistry.createRegistry(PORTA);
				registryS.rebind(IServer.NOME_SERVICO, serverS);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// JOptionPane.showMessageDialog(null, "Servidor inicializado com
		// sucesso!");
		btnConectar.setEnabled(false);
		imprimir("Iniciando servidor..");
		imprimir("..");
		imprimir("Servidor iniciado com sucesso!");
	}

	public void registrarCliente(Cliente cliente) throws RemoteException {

		arquivos.put(cliente, new ArrayList<Arquivo>());
		imprimir(cliente.getNome() + "com o IP:" + cliente.getIp() + " estÃ¡ conectado!\n");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.univel.comum.IServer#publicarListaArquivos(br.univel.comum.Cliente,
	 * java.util.List)
	 */
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {

		arquivos.entrySet().forEach(e -> {
			if (e.getKey().equals(c)) {
				e.setValue(lista);
				imprimir("Lista de arquivos de " + e.getKey().getNome() + " foi atualizada...");
			}
		});
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String query, TipoFiltro tipoFiltro) throws RemoteException {
		Map<Cliente, List<Arquivo>> inserirResult = new HashMap<>();
		List<Arquivo> list = new ArrayList<>();

		arquivos.forEach((cliente, value) -> {
 
			value.forEach(valor -> {
				if (TipoFiltro.NOME.equals(tipoFiltro)) {
					if (valor.getNome().toLowerCase().contains(query.toLowerCase())) {
						list.add(valor);
					}

				} else if (TipoFiltro.EXTENSAO.equals(tipoFiltro)) {
					if (valor.getExtensao().toLowerCase().contains(query.toLowerCase())) {
						if (valor.getNome().toLowerCase().contains(query.toLowerCase())) {
							list.add(valor);
						}
					}

				} else if (TipoFiltro.TAMANHO_MAX.equals(tipoFiltro)) {
					if (valor.getTamanho() >= Integer.valueOf(query)) {
						if (valor.getNome().toLowerCase().contains(query.toLowerCase())) {
							list.add(valor);
						}
					}

				} else if (TipoFiltro.TAMANHO_MAX.equals(tipoFiltro)) {
					if (valor.getTamanho() <= Integer.valueOf(query)) {
						if (valor.getNome().toLowerCase().contains(query.toLowerCase())) {
							list.add(valor);
						}
					}
				}
				inserirResult.put(cliente, list);
			});
		});
		return inserirResult;
	}

	public byte[] baixarArquivo(Cliente cli, Arquivo arq) throws RemoteException {
		byte[] dados = null;
		Path path = Paths.get(arq.getPath());
		try {
			dados = Files.readAllBytes(path);
			imprimir("O usuário: " + cli.getNome() + " com o IP: " + cli.getIp() + " baixou o seu arquivo:"
					+ arq.getNome());
			return dados;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.univel.comum.IServer#desconectar(br.univel.comum.Cliente)
	 */
	public void desconectar(Cliente c) throws RemoteException {

		cliServer.remove(c);
		imprimir(c.getNome() + " saiu.");
	}

}
