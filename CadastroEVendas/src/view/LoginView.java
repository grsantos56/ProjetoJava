package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.VendedorDAO;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 478, 67);
		panel.setBackground(new Color(0, 166, 41));
		
		textUsuario = new JTextField();
		textUsuario.setFont(new Font("Verdana", Font.PLAIN, 14));
		textUsuario.setBounds(15, 126, 468, 43);
		textUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setBounds(15, 102, 53, 18);
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1_1 = new JLabel("Senha");
		lblNewLabel_1_1.setBounds(15, 187, 53, 18);
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(188, 288, 93, 29);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.setFont(new Font("Verdana", Font.BOLD, 16));
		
		JLabel lblNewLabel_2 = new JLabel("Não tem uma conta?");
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setBounds(161, 335, 146, 18);
		lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(177, 371, 119, 27);
		btnCadastrar.setFont(new Font("Verdana", Font.BOLD, 14));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		passwordSenha = new JPasswordField();
		passwordSenha.setFont(new Font("Verdana", Font.PLAIN, 14));
		passwordSenha.setBounds(15, 211, 468, 43);
		
		JLabel lblNewLabel = new JLabel("ÁREA DE LOGIN");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(147, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(143))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(21, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(20))
		);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = textUsuario.getText();
				String senha = new String(passwordSenha.getPassword());
				VendedorDAO dao = new VendedorDAO();
				boolean autenticado = dao.autenticar(usuario, senha);

				if (autenticado) {
					TelaPrincipalView vendas = new TelaPrincipalView();
					vendas.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
				}
			}
		});


		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroView cadastro = new CadastroView();
				cadastro.setVisible(true);
				dispose(); // Fecha a tela de login
			}
		});

		panel.setLayout(gl_panel);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel_1_1);
		contentPane.add(panel);
		contentPane.add(textUsuario);
		contentPane.add(passwordSenha);
		contentPane.add(btnLogin);
		contentPane.add(lblNewLabel_2);
		contentPane.add(btnCadastrar);
		setResizable(false);
	    setLocationRelativeTo(null);
	}
}