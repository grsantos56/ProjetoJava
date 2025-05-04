package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipalView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalView frame = new TelaPrincipalView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipalView() {
		setTitle("NAVARA'S STORE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 166, 41));
		
		JLabel lblNewLabel = new JLabel("NAVARA'S STORE");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(137)
					.addComponent(lblNewLabel)
					.addContainerGap(142, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JButton btnVendas = new JButton("VENDAS");
		btnVendas.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        VendasView vendas = new VendasView();
		        vendas.setVisible(true);
		        dispose();
		    }
		});
		btnVendas.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		
		JButton btnClientes = new JButton("CLIENTES");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteView cliente = new ClienteView();
				cliente.setVisible(true);
				dispose();
		
			}
		});
		btnClientes.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		
		JButton btnProdutos = new JButton("PRODUTOS");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoView produto = new ProdutoView();
				produto.setVisible(true);
				dispose();
			}
		});
		btnProdutos.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		
		JButton btnSair = new JButton("SAIR");
		btnSair.addActionListener(e -> {
		    // Exibe uma janela de confirmação
		    int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
		    if (opcao == JOptionPane.YES_OPTION) {
		        // Se o usuário clicar em 'Sim', abre a LoginView e fecha a janela atual
		        LoginView login = new LoginView();
		        login.setVisible(true);
		        this.dispose();  // Fecha a janela atual (ProdutoView)
		    }
		});

		btnSair.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(60)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnVendas, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClientes, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
					.addGap(101)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnProdutos, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
					.addGap(65))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addGap(97)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnVendas, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnProdutos, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClientes, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(100, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		setResizable(false);
	    setLocationRelativeTo(null);
	}
}
