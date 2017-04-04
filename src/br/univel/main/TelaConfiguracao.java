package br.univel.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.univel.server.Servidor;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaConfiguracao extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4936007049213971277L;
	private JPanel contentPane;
	private JTextField txtIp;
	private JTextField txtPort;
	private JButton btnIniciarServidor;
	private br.univel.server.Servidor newServidor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConfiguracao frame = new TelaConfiguracao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaConfiguracao getTela(){
		return this;
	}
	
	public TelaConfiguracao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 437, 88);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 305, 6, 73, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblIp = new JLabel("IP:");
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.anchor = GridBagConstraints.EAST;
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 0;
		contentPane.add(lblIp, gbc_lblIp);
		
		txtIp = new JTextField();
		GridBagConstraints gbc_txtIp = new GridBagConstraints();
		gbc_txtIp.insets = new Insets(0, 0, 5, 5);
		gbc_txtIp.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIp.gridx = 1;
		gbc_txtIp.gridy = 0;
		contentPane.add(txtIp, gbc_txtIp);
		txtIp.setColumns(10);
		
		JLabel lblPort = new JLabel("Port:");
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPort.anchor = GridBagConstraints.EAST;
		gbc_lblPort.gridx = 2;
		gbc_lblPort.gridy = 0;
		contentPane.add(lblPort, gbc_lblPort);
		
		txtPort = new JTextField();
		GridBagConstraints gbc_txtPort = new GridBagConstraints();
		gbc_txtPort.anchor = GridBagConstraints.NORTH;
		gbc_txtPort.insets = new Insets(0, 0, 5, 0);
		gbc_txtPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPort.gridx = 3;
		gbc_txtPort.gridy = 0;
		contentPane.add(txtPort, gbc_txtPort);
		txtPort.setColumns(10);
		JPanel jpane = new JPanel();
		btnIniciarServidor = new JButton("Iniciar Servidor");
		btnIniciarServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarServidor();
			}
		});
		btnIniciarServidor.setActionCommand("INICIAR");
		jpane.add(btnIniciarServidor);
		getRootPane().setDefaultButton(btnIniciarServidor);
		
		if(newServidor != null){
			btnIniciarServidor.setEnabled(false);
		}
		GridBagConstraints gbc_btnIniciarServidor = new GridBagConstraints();
		gbc_btnIniciarServidor.anchor = GridBagConstraints.EAST;
		gbc_btnIniciarServidor.gridwidth = 3;
		gbc_btnIniciarServidor.gridx = 1;
		gbc_btnIniciarServidor.gridy = 1;
		contentPane.add(btnIniciarServidor, gbc_btnIniciarServidor);
	}

	private void iniciarServidor(){
		if(!verificaParam()){
			JOptionPane.showMessageDialog(null, "Parametro invalido, tente novamente!");
			return;
		}
		
		newServidor.PORTA = Integer.parseInt(txtPort.getText());
		newServidor.nome_ap= txtIp.getText();
		newServidor = new Servidor(getTela());
	

		dispose();
	
		JOptionPane.showMessageDialog(null, "Servidor iniciado com sucesso!");
	}
	
	private boolean verificaParam() {
		return !txtIp.getText().isEmpty() && txtPort.getText().isEmpty();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
