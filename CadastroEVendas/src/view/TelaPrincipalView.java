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
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Tela principal da aplicação Navara's Store.
 * Oferece botões de acesso para as funcionalidades de vendas, clientes, produtos, ajuda e para sair do sistema,
 * retornando à tela de login.
 */

public class TelaPrincipalView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Método principal para iniciar a aplicação e exibir a tela principal.
	 * Executa a criação e exibição da janela em um thread separado para garantir
	 * a responsividade da interface gráfica.
	 *
	 * @param args argumentos da linha de comando (não utilizados).
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
	 * Construtor da classe TelaPrincipalView.
	 * Inicializa os componentes da interface gráfica, como o título da janela,
	 * o painel principal, o rótulo do título e os botões para as diversas funcionalidades.
	 * Define também os listeners para cada botão, permitindo a navegação entre as telas
	 * de vendas, clientes, produtos, ajuda e para sair do sistema, voltando ao login.
	 */
	public TelaPrincipalView() {
		setTitle("NAVARA'S STORE"); // Define o título da janela principal.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar a janela (encerra a aplicação).
		setBounds(100, 100, 504, 494); // Define as dimensões iniciais da janela.
		contentPane = new JPanel(); // Cria um novo painel para conter os elementos da tela.
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Define uma borda vazia ao redor do painel.
		setContentPane(contentPane); // Define o painel como o contentPane da JFrame.
		
		JPanel panel = new JPanel(); // Cria um painel para a área de título.
		panel.setBackground(new Color(0, 166, 41)); // Define a cor de fundo do painel de título (verde).
		
		JLabel lblNewLabel = new JLabel("NAVARA'S STORE"); // Cria um rótulo com o título da aplicação.
		lblNewLabel.setForeground(new Color(255, 255, 255)); // Define a cor do texto do título (branco).
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20)); // Define a fonte do título (Verdana, negrito, tamanho 20).
		GroupLayout gl_panel = new GroupLayout(panel); // Cria um layout para o painel de título.
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
		panel.setLayout(gl_panel); // Define o layout do painel de título.
		
		JButton btnVendas = new JButton("VENDAS"); // Cria um botão para acessar a tela de vendas.
		btnVendas.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		        VendasView vendas = new VendasView(); // Cria uma nova instância da tela de vendas.
		        vendas.setVisible(true); // Torna a tela de vendas visível.
		        dispose(); // Fecha a janela atual (TelaPrincipalView).
		    }
		});
		btnVendas.setFont(new Font("Trebuchet MS", Font.BOLD, 18)); // Define a fonte do botão de vendas (Trebuchet MS, negrito, tamanho 18).
		
		JButton btnClientes = new JButton("CLIENTES"); // Cria um botão para acessar a tela de clientes.
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteView cliente = new ClienteView(); // Cria uma nova instância da tela de clientes.
				cliente.setVisible(true); // Torna a tela de clientes visível.
				dispose(); // Fecha a janela atual (TelaPrincipalView).
		
			}
		});
		btnClientes.setFont(new Font("Trebuchet MS", Font.BOLD, 18)); // Define a fonte do botão de clientes (Trebuchet MS, negrito, tamanho 18).
		
		JButton btnProdutos = new JButton("PRODUTOS"); // Cria um botão para acessar a tela de produtos.
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoView produto = new ProdutoView(); // Cria uma nova instância da tela de produtos.
				produto.setVisible(true); // Torna a tela de produtos visível.
				dispose(); // Fecha a janela atual (TelaPrincipalView).
			}
		});
		btnProdutos.setFont(new Font("Trebuchet MS", Font.BOLD, 18)); // Define a fonte do botão de produtos (Trebuchet MS, negrito, tamanho 18).
		
		JButton btnSair = new JButton("SAIR"); // Cria um botão para sair do sistema.
		btnSair.addActionListener(e -> {
		    // Exibe uma janela de confirmação
		    int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION); 
		    if (opcao == JOptionPane.YES_OPTION) {
		        // Se o usuário clicar em 'Sim', abre a LoginView e fecha a janela atual
		        LoginView login = new LoginView(); 	// Cria uma nova instância da tela de login.
		        login.setVisible(true); // Torna a tela de login visível.
		        this.dispose();  // Fecha a janela atual
		    }
		});

		btnSair.setFont(new Font("Trebuchet MS", Font.BOLD, 18)); // Define a fonte do botão de sair (Trebuchet MS, negrito, tamanho 18).
		
		JButton btnAjuda = new JButton("AJUDA"); // Cria um botão para acessar a tela de ajuda.
		btnAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjudaView ajuda = new AjudaView(); // Cria uma nova instância da tela de ajuda.
				ajuda.setVisible(true); // Torna a tela de ajuda visível.
				dispose(); // Fecha a janela atual (TelaPrincipalView).
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane); // Cria um layout para o painel principal.
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
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup() 
					.addContainerGap() 
					.addComponent(btnAjuda)) 
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAjuda)
					.addGap(68)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnVendas, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnProdutos, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClientes, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(100, Short.MAX_VALUE)) 
		);
		contentPane.setLayout(gl_contentPane); // Define o layout do painel principal.
		setResizable(false); // Impede que o usuário redimensione a janela.
	    setLocationRelativeTo(null); // Centraliza a janela na tela.
	}
}