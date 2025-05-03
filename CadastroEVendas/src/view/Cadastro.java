package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Cadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordSenha;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro frame = new Cadastro();
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
	public Cadastro() {
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
		
		JLabel lblNewLabel = new JLabel("ÁREA DE CADASTRO");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(132, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(115))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel_1_1);
		contentPane.add(panel);
		contentPane.add(textUsuario);
		contentPane.add(passwordSenha);
		contentPane.add(btnCadastrar);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Confirmar Senha");
		lblNewLabel_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(15, 279, 150, 18);
		contentPane.add(lblNewLabel_1_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Verdana", Font.PLAIN, 14));
		passwordField.setBounds(15, 302, 468, 43);
		contentPane.add(passwordField);
		setResizable(false);
	    setLocationRelativeTo(null);
	}
}