package view;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import dao.VendedorDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Tela de Login para vendedores do sistema.
 * Permite que um vendedor insira seu nome de usuário e senha para acessar
 * as funcionalidades do sistema. Oferece também uma opção para cadastrar
 * uma nova conta de vendedor.
 */
public class LoginView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textUsuario;
    private JPasswordField passwordSenha;

    /**
     * Método principal para iniciar a aplicação e exibir a tela de login.
     * Executa a criação e exibição da janela em um thread separado para garantir
     * a responsividade da interface gráfica.
     *
     * @param args argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginView frame = new LoginView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Construtor da classe LoginView.
     * Inicializa os componentes da interface gráfica, define o título da janela,
     * configura o comportamento ao fechar, estabelece as dimensões, impede o
     * redimensionamento, centraliza a janela na tela e adiciona os elementos visuais
     * como rótulos, campos de texto, campos de senha e botões para login e cadastro.
     * Também implementa a lógica para autenticar o usuário ao clicar no botão "Login"
     * e para abrir a tela de cadastro ao clicar no botão "Cadastrar".
     */
    public LoginView() {
        setTitle("LOGIN"); // Define o título da janela de login.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar a janela (encerra a aplicação).
        setBounds(100, 100, 504, 494); // Define as dimensões iniciais da janela.
        contentPane = new JPanel(); // Cria um novo painel para conter os elementos da tela.
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Define uma borda vazia ao redor do painel.
        setContentPane(contentPane); // Define o painel como o contentPane da JFrame.
        contentPane.setLayout(null); // Define o layout do painel como null para posicionamento manual dos componentes.

        JPanel panel = new JPanel(); // Cria um painel para a área de título.
        panel.setBounds(5, 5, 478, 67); // Define as dimensões e a posição do painel de título.
        panel.setBackground(new Color(0, 166, 41)); // Define a cor de fundo do painel de título (verde).
        contentPane.add(panel); // Adiciona o painel de título ao contentPane.
        panel.setLayout(null); // Define o layout do painel de título como null.

        JLabel lblNewLabel = new JLabel("ÁREA DE LOGIN"); // Cria um rótulo para o título da tela.
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20)); // Define a fonte do título.
        lblNewLabel.setForeground(new Color(255, 255, 255)); // Define a cor do texto do título (branco).
        lblNewLabel.setBounds(147, 21, 199, 25); // Define as dimensões e a posição do rótulo do título.
        panel.add(lblNewLabel); // Adiciona o rótulo do título ao painel de título.

        JLabel lblNewLabel_1 = new JLabel("Usuário"); // Cria um rótulo para o campo de usuário.
        lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 14)); // Define a fonte do rótulo de usuário.
        lblNewLabel_1.setBounds(15, 102, 53, 18); // Define as dimensões e a posição do rótulo de usuário.
        contentPane.add(lblNewLabel_1); // Adiciona o rótulo de usuário ao contentPane.

        textUsuario = new JTextField(); // Cria um campo de texto para o nome de usuário.
        textUsuario.setFont(new Font("Verdana", Font.PLAIN, 14)); // Define a fonte do campo de texto de usuário.
        textUsuario.setBounds(15, 126, 468, 43); // Define as dimensões e a posição do campo de texto de usuário.
        textUsuario.setColumns(10); // Define o número de colunas do campo de texto.
        contentPane.add(textUsuario); // Adiciona o campo de texto de usuário ao contentPane.

        JLabel lblNewLabel_1_1 = new JLabel("Senha"); // Cria um rótulo para o campo de senha.
        lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 14)); // Define a fonte do rótulo de senha.
        lblNewLabel_1_1.setBounds(15, 187, 53, 18); // Define as dimensões e a posição do rótulo de senha.
        contentPane.add(lblNewLabel_1_1); // Adiciona o rótulo de senha ao contentPane.

        JButton btnLogin = new JButton("Login"); // Cria um botão para realizar o login.
        btnLogin.setFont(new Font("Verdana", Font.BOLD, 16)); // Define a fonte do botão de login.
        btnLogin.setBounds(188, 288, 93, 29); // Define as dimensões e a posição do botão de login.
        btnLogin.addActionListener(new ActionListener() { // Adiciona um ActionListener para lidar com o clique no botão de login.
            public void actionPerformed(ActionEvent e) {
                String usuario = textUsuario.getText(); // Obtém o texto do campo de usuário.
                String senha = new String(passwordSenha.getPassword()); // Obtém a senha do campo de senha.
                VendedorDAO dao = new VendedorDAO(); // Cria uma instância do VendedorDAO para autenticação.
                boolean autenticado = dao.autenticar(usuario, senha); // Chama o método de autenticação no DAO.

                // Verifica se a autenticação foi bem-sucedida.
                if (autenticado) {
                    TelaPrincipalView vendas = new TelaPrincipalView(); // Cria uma nova instância da tela principal.
                    vendas.setVisible(true); // Exibe a tela principal.
                    dispose(); // Fecha a tela de login.
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnLogin); // Adiciona o botão de login ao contentPane.

        JLabel lblNewLabel_2 = new JLabel("Não tem uma conta?"); // Cria um rótulo para informar sobre a opção de cadastro.
        lblNewLabel_2.setForeground(new Color(255, 0, 0)); // Define a cor do texto do rótulo (vermelho).
        lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 14)); // Define a fonte do rótulo.
        lblNewLabel_2.setBounds(161, 335, 146, 18); // Define as dimensões e a posição do rótulo.
        contentPane.add(lblNewLabel_2); // Adiciona o rótulo ao contentPane.

        JButton btnCadastrar = new JButton("Cadastrar"); // Cria um botão para ir para a tela de cadastro.
        btnCadastrar.setFont(new Font("Verdana", Font.BOLD, 14)); // Define a fonte do botão de cadastro.
        btnCadastrar.setBounds(177, 371, 119, 27); // Define as dimensões e a posição do botão de cadastro.
        btnCadastrar.addActionListener(new ActionListener() { // Adiciona um ActionListener para lidar com o clique no botão de cadastro.
            public void actionPerformed(ActionEvent e) {
                CadastroView cadastro = new CadastroView(); // Cria uma nova instância da tela de cadastro.
                cadastro.setVisible(true); // Exibe a tela de cadastro.
                dispose(); // Fecha a tela de login.
            }
        });
        contentPane.add(btnCadastrar); // Adiciona o botão de cadastro ao contentPane.

        passwordSenha = new JPasswordField(); // Cria um campo de senha para a senha.
        passwordSenha.setFont(new Font("Verdana", Font.PLAIN, 14)); // Define a fonte do campo de senha.
        passwordSenha.setBounds(15, 211, 468, 43); // Define as dimensões e a posição do campo de senha.
        contentPane.add(passwordSenha); // Adiciona o campo de senha ao contentPane.

        setResizable(false); // Impede que o usuário redimensione a janela.
        setLocationRelativeTo(null); // Centraliza a janela na tela.
    }
}