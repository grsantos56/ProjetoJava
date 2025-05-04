package view;

import controller.ClienteController;
import model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClienteView extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtNome, txtCpf, txtTelefone, txtEndereco;
    private JTextArea areaClientes;
    private ClienteController controller = new ClienteController();

    public ClienteView() {
        setTitle("Cadastro de Clientes");
        setSize(680, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout(10, 10)); 
        setResizable(false);

        JPanel painel = new JPanel(new GridLayout(8, 2, 10, 10)); // Melhor espaçamento

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
        btnCadastrar.addActionListener(this::cadastrarCliente);
        painel.add(btnCadastrar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(this::buscarCliente);
        painel.add(btnBuscar);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(this::atualizarCliente);
        painel.add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(this::excluirCliente);
        painel.add(btnExcluir);

        JButton btnListar = new JButton("Listar Todos");
        btnListar.addActionListener(this::listarClientes);
        painel.add(btnListar);

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Confirmar saída", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new TelaPrincipalView().setVisible(true);
            }
        });
        painel.add(btnSair);

        areaClientes = new JTextArea();
        areaClientes.setEditable(false);
        areaClientes.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaClientes);

        getContentPane().add(painel, BorderLayout.NORTH);
        getContentPane().add(scroll, BorderLayout.CENTER);
    }

    private void cadastrarCliente(ActionEvent e) {
    	if(txtCpf.getText().isEmpty() || txtNome.getText().isEmpty() || txtTelefone.getText().isEmpty() || txtEndereco.getText().isEmpty()) {
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
                limparCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscarCliente(ActionEvent e) {
    	if(txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, insira o CPF do cliente a ser buscado.", "Erro", JOptionPane.ERROR_MESSAGE);}
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

    private void atualizarCliente(ActionEvent e) {
    	if(txtCpf.getText().isEmpty() || txtNome.getText().isEmpty() || txtTelefone.getText().isEmpty() || txtEndereco.getText().isEmpty()) {
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
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void excluirCliente(ActionEvent e) {
    	if(txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, insira o CPF do cliente a ser excluído.", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
        int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir este cliente?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                controller.excluirCliente(txtCpf.getText());
                JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
                limparCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void listarClientes(ActionEvent e) {
        try {
            List<Cliente> lista = controller.listarClientes();
            Collections.sort(lista, Comparator.comparing(Cliente::getNome));
            areaClientes.setText("");
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

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtEndereco.setText("");
        areaClientes.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteView tela = new ClienteView();
            tela.setVisible(true);
        });
    }
}
