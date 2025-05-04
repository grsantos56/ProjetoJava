package view;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tela de Cadastro de novos vendedores no sistema.
 * Permite que um novo usuário insira um nome de usuário e senha,
 * confirme a senha e, ao clicar em "Cadastrar", tenta criar uma nova
 * conta de vendedor no sistema. A tela também oferece um botão "Voltar"
 * para retornar à tela de login.
 */
public class CadastroView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textUsuario;
    private JPasswordField passwordSenha;
    private JPasswordField passwordField;

    /**
     * Método principal para iniciar a aplicação e exibir a tela de cadastro.
     * Executa a criação e exibição da janela em um thread separado para garantir
     * a responsividade da interface gráfica.
     *
     * @param args argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CadastroView frame = new CadastroView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Construtor da classe CadastroView.
     * Inicializa os componentes da interface gráfica, define o título da janela,
     * configura o comportamento ao fechar, estabelece as dimensões, impede o
     * redimensionamento, centraliza a janela na tela e adiciona os elementos visuais
     * como rótulos, campos de texto, campos de senha e botões para cadastro e voltar.
     * Também implementa a lógica para o botão de cadastro, validando os campos
     * e tentando cadastrar um novo vendedor através da camada DAO.
     */
    public CadastroView() {
        setTitle("CADASTRO"); // Define o título da janela de cadastro.
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

        JLabel lblNewLabel = new JLabel("ÁREA DE CADASTRO"); // Cria um rótulo para o título da tela.
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 19)); // Define a fonte do título.
        lblNewLabel.setForeground(new Color(255, 255, 255)); // Define a cor do texto do título (branco).
        lblNewLabel.setBounds(132, 19, 220, 25); // Define as dimensões e a posição do rótulo do título.
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

        passwordSenha = new JPasswordField(); // Cria um campo de senha para a senha.
        passwordSenha.setFont(new Font("Verdana", Font.PLAIN, 14)); // Define a fonte do campo de senha.
        passwordSenha.setBounds(15, 211, 468, 43); // Define as dimensões e a posição do campo de senha.
        contentPane.add(passwordSenha); // Adiciona o campo de senha ao contentPane.

        JLabel lblNewLabel_1_1_1 = new JLabel("Confirmar Senha"); // Cria um rótulo para o campo de confirmação de senha.
        lblNewLabel_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 14)); // Define a fonte do rótulo de confirmação de senha.
        lblNewLabel_1_1_1.setBounds(15, 279, 150, 18); // Define as dimensões e a posição do rótulo de confirmação de senha.
        contentPane.add(lblNewLabel_1_1_1); // Adiciona o rótulo de confirmação de senha ao contentPane.

        passwordField = new JPasswordField(); // Cria um campo de senha para confirmar a senha.
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 14)); // Define a fonte do campo de confirmação de senha.
        passwordField.setBounds(15, 302, 468, 43); // Define as dimensões e a posição do campo de confirmação de senha.
        contentPane.add(passwordField); // Adiciona o campo de confirmação de senha ao contentPane.

        JButton btnCadastrar = new JButton("Cadastrar"); // Cria um botão para realizar o cadastro.
        btnCadastrar.setFont(new Font("Verdana", Font.BOLD, 14)); // Define a fonte do botão de cadastro.
        btnCadastrar.setBounds(274, 371, 119, 27); // Define as dimensões e a posição do botão de cadastro.
        btnCadastrar.addActionListener(new ActionListener() { // Adiciona um ActionListener para lidar com o clique no botão de cadastro.
            public void actionPerformed(ActionEvent e) {
                String usuario = textUsuario.getText().trim(); // Obtém o texto do campo de usuário e remove espaços em branco.
                String senha = new String(passwordSenha.getPassword()); // Obtém a senha do campo de senha.
                String confirmarSenha = new String(passwordField.getPassword()); // Obtém a senha do campo de confirmação de senha.

                // Valida se todos os campos foram preenchidos.
                if (usuario.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                    return; // Sai do método se algum campo estiver vazio.
                }

                // Valida se as senhas coincidem.
                if (!senha.equals(confirmarSenha)) {
                    JOptionPane.showMessageDialog(null, "As senhas não coincidem!");
                    return; // Sai do método se as senhas não forem iguais.
                }

                // Cria uma instância do VendedorDAO para realizar a operação de cadastro.
                dao.VendedorDAO dao = new dao.VendedorDAO();
                boolean sucesso = dao.cadastrar(usuario, senha); // Chama o método de cadastro no DAO.

                // Verifica se o cadastro foi bem-sucedido.
                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Vendedor cadastrado com sucesso!");
                    // Limpa os campos após o cadastro bem-sucedido.
                    textUsuario.setText("");
                    passwordSenha.setText("");
                    passwordField.setText("");

                    // Redireciona para a tela de login após o cadastro.
                    LoginView login = new LoginView();
                    login.setVisible(true);
                    dispose(); // Fecha a tela de cadastro.
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar. Tente novamente.");
                }
            }
        });
        contentPane.add(btnCadastrar); // Adiciona o botão de cadastro ao contentPane.

        JButton btnVoltar = new JButton("VOLTAR"); // Cria um botão para voltar à tela anterior.
        btnVoltar.setFont(new Font("Verdana", Font.BOLD, 14)); // Define a fonte do botão voltar.
        btnVoltar.setBounds(72, 371, 119, 27); // Define as dimensões e a posição do botão voltar.
        btnVoltar.addActionListener(new ActionListener() { // Adiciona um ActionListener para lidar com o clique no botão voltar.
            public void actionPerformed(ActionEvent e) {
                LoginView login2 = new LoginView(); // Cria uma nova instância da tela de login.
                login2.setVisible(true); // Exibe a tela de login.
                dispose(); // Fecha a tela de cadastro.
            }
        });
        contentPane.add(btnVoltar); // Adiciona o botão voltar ao contentPane.

        setResizable(false); // Impede que o usuário redimensione a janela.
        setLocationRelativeTo(null); // Centraliza a janela na tela.
    }
}