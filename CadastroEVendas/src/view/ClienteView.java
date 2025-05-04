package view;

import controller.ClienteController;
import model.Cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Tela para o cadastro, busca, atualização, exclusão e listagem de clientes.
 * Utiliza um ClienteController para intermediar as ações com a camada de modelo e dados.
 */
public class ClienteView extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtNome, txtCpf, txtTelefone, txtEndereco;
    private JTextArea areaClientes;
    private ClienteController controller = new ClienteController();

    /**
     * Construtor da classe ClienteView.
     * Inicializa os componentes da interface gráfica, como rótulos, campos de texto,
     * botões para as operações CRUD (Cadastrar, Buscar, Atualizar, Excluir, Listar)
     * e um JTextArea para exibir a lista de clientes. Define também os listeners
     * para os botões, associando-os aos respectivos métodos de controle.
     */
    public ClienteView() {
        setTitle("Cadastro de Clientes");
        setSize(680, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15)); // Adiciona espaçamento nas bordas
        setContentPane(contentPane);
        setResizable(false);

        JPanel painel = new JPanel(new GridLayout(8, 2, 10, 10)); // Layout de grade para organizar os campos e botões.
        contentPane.add(painel, BorderLayout.NORTH);

        painel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painel.add(txtNome);

        painel.add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        painel.add(txtCpf);

        painel.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        painel.add(txtTelefone);

        painel.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        painel.add(txtEndereco);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(this::cadastrarCliente); // Listener para o botão Cadastrar.
        painel.add(btnCadastrar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(this::buscarCliente); // Listener para o botão Buscar.
        painel.add(btnBuscar);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(this::atualizarCliente); // Listener para o botão Atualizar.
        painel.add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(this::excluirCliente); // Listener para o botão Excluir.
        painel.add(btnExcluir);

        JButton btnListar = new JButton("Listar Todos");
        btnListar.addActionListener(this::listarClientes); // Listener para o botão Listar Todos.
        painel.add(btnListar);

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> { // Listener para o botão Sair.
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Confirmar saída", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); // Fecha a tela atual.
                new TelaPrincipalView().setVisible(true); // Abre a tela principal.
            }
        });
        painel.add(btnSair);

        areaClientes = new JTextArea();
        areaClientes.setEditable(false);
        areaClientes.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaClientes); // Adiciona barra de rolagem para a área de texto.
        contentPane.add(scroll, BorderLayout.CENTER);
    }

    /**
     * Método chamado ao clicar no botão "Cadastrar".
     * Valida se todos os campos obrigatórios estão preenchidos e, em caso positivo,
     * tenta cadastrar um novo cliente utilizando o ClienteController. Exibe mensagens
     * de sucesso ou erro através de JOptionPane.
     *
     * @param e Evento de clique do botão.
     */
    private void cadastrarCliente(ActionEvent e) {
        if (txtCpf.getText().isEmpty() || txtNome.getText().isEmpty() || txtTelefone.getText().isEmpty() || txtEndereco.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira as todas informações do cliente a ser cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente cadastrar este cliente?", "Confirmar cadastro", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                controller.cadastrarCliente(
                        txtNome.getText(),
                        txtCpf.getText(),
                        txtTelefone.getText(),
                        txtEndereco.getText()
                );
                JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
                limparCampos(); // Limpa os campos após o cadastro.
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método chamado ao clicar no botão "Buscar".
     * Busca um cliente pelo CPF utilizando o ClienteController e exibe os dados
     * nos campos de texto correspondentes. Exibe mensagens caso o cliente seja
     * encontrado ou não.
     *
     * @param e Evento de clique do botão.
     */
    private void buscarCliente(ActionEvent e) {
        if (txtCpf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o CPF do cliente a ser buscado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Cliente c = controller.buscarCliente(txtCpf.getText());
            if (c != null) {
                txtNome.setText(c.getNome());
                txtTelefone.setText(c.getTelefone());
                txtEndereco.setText(c.getEndereco());
                JOptionPane.showMessageDialog(this, "Cliente encontrado.");
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método chamado ao clicar no botão "Atualizar".
     * Valida se todos os campos estão preenchidos e tenta atualizar os dados de um
     * cliente utilizando o ClienteController. A busca para atualização é feita pelo CPF.
     * Exibe mensagens de sucesso ou erro.
     *
     * @param e Evento de clique do botão.
     */
    private void atualizarCliente(ActionEvent e) {
        if (txtCpf.getText().isEmpty() || txtNome.getText().isEmpty() || txtTelefone.getText().isEmpty() || txtEndereco.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira as informações do cliente a ser atualizado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente atualizar este cliente?", "Confirmar atualização", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Cliente c = new Cliente(
                        txtNome.getText(),
                        txtCpf.getText(),
                        txtTelefone.getText(),
                        txtEndereco.getText()
                );
                controller.atualizarCliente(c);
                JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
                limparCampos(); // Limpa os campos após o cadastro.
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método chamado ao clicar no botão "Excluir".
     * Solicita confirmação do usuário e tenta excluir um cliente pelo CPF
     * utilizando o ClienteController. Exibe mensagens de sucesso ou erro.
     *
     * @param e Evento de clique do botão.
     */
    private void excluirCliente(ActionEvent e) {
        if (txtCpf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o CPF do cliente a ser excluído.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir este cliente?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                controller.excluirCliente(txtCpf.getText());
                JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
                limparCampos(); // Limpa os campos após a exclusão.
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método chamado ao clicar no botão "Listar Todos".
     * Obtém a lista de todos os clientes cadastrados utilizando o ClienteController,
     * ordena a lista por nome e exibe os dados na JTextArea.
     *
     * @param e Evento de clique do botão.
     */
    private void listarClientes(ActionEvent e) {
        try {
            List<Cliente> lista = controller.listarClientes();
            Collections.sort(lista, Comparator.comparing(Cliente::getNome)); // Ordena a lista por nome.
            areaClientes.setText(""); // Limpa a área de texto antes de exibir a lista.
            for (Cliente c : lista) {
                areaClientes.append("Cliente\n");
                areaClientes.append("nome: " + c.getNome() + "\n");
                areaClientes.append("cpf: " + c.getCpf() + "\n");
                areaClientes.append("telefone: " + c.getTelefone() + "\n");
                areaClientes.append("endereco: " + c.getEndereco() + "\n");
                areaClientes.append("--------------------------------------\n");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método utilitário para limpar os campos de texto da tela.
     */
    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtEndereco.setText("");
        areaClientes.setText("");
    }

    /**
     * Método principal para iniciar a aplicação e exibir a tela de clientes.
     * Executa a criação e exibição da janela em um thread separado para garantir
     * a responsividade da interface gráfica.
     *
     * @param args argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteView tela = new ClienteView();
            tela.setVisible(true);
        });
    }
}