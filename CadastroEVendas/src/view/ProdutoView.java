package view;

import controller.LivroController;
import controller.ProdutoController;
import db.DB;
import db.DbException;
import model.Produto;
import model.Livro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoView extends JFrame {
    private JTextField txtNome, txtPrecoCompra, txtPrecoVenda, txtEstoque, txtAutor;
    private JCheckBox chkLivro;
    private JTextArea txtAreaProdutos;
    private ProdutoController controller;

    public ProdutoView() {
        controller = new ProdutoController();

        setTitle("Cadastro de Produtos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de formulário
        JPanel painelForm = new JPanel(new GridLayout(7, 2, 5, 5));

        painelForm.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelForm.add(txtNome);

        painelForm.add(new JLabel("Preço de Compra:"));
        txtPrecoCompra = new JTextField();
        painelForm.add(txtPrecoCompra);

        painelForm.add(new JLabel("Preço de Venda:"));
        txtPrecoVenda = new JTextField();
        painelForm.add(txtPrecoVenda);

        painelForm.add(new JLabel("Estoque:"));
        txtEstoque = new JTextField();
        painelForm.add(txtEstoque);

        chkLivro = new JCheckBox("É um livro?");
        painelForm.add(chkLivro);
        painelForm.add(new JLabel("")); // espaço

        painelForm.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        txtAutor.setEnabled(false);
        painelForm.add(txtAutor);

        chkLivro.addActionListener(e -> txtAutor.setEnabled(chkLivro.isSelected()));

        add(painelForm, BorderLayout.NORTH);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnVisualizar = new JButton("Visualizar");

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnVisualizar);

        add(painelBotoes, BorderLayout.CENTER);

        // Área de texto para exibir produtos
        txtAreaProdutos = new JTextArea(12, 50);
        txtAreaProdutos.setEditable(false);
        add(new JScrollPane(txtAreaProdutos), BorderLayout.SOUTH);

        // Ações
        btnCadastrar.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                double precoCompra = Double.parseDouble(txtPrecoCompra.getText());
                double precoVenda = Double.parseDouble(txtPrecoVenda.getText());
                int estoque = Integer.parseInt(txtEstoque.getText());

                if (chkLivro.isSelected()) {
                    String autor = txtAutor.getText();
                    LivroController livroController = new LivroController();
                    livroController.cadastrarProduto(nome, precoCompra, precoVenda, estoque, autor);
                } else {
                    ProdutoController produtoController = new ProdutoController();
                    produtoController.cadastrarProduto(nome, precoCompra, precoVenda, estoque);
                }
                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
                limparCampos();
                listarProdutos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage());
            }
        });
        btnAtualizar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Digite o ID do produto a atualizar:");
            if (idStr != null && !idStr.isBlank()) {
                try {
                    int id = Integer.parseInt(idStr);
                    String nome = txtNome.getText();
                    double precoCompra = Double.parseDouble(txtPrecoCompra.getText());
                    double precoVenda = Double.parseDouble(txtPrecoVenda.getText());
                    int estoque = Integer.parseInt(txtEstoque.getText());

                    if (chkLivro.isSelected()) {
                        String autor = txtAutor.getText();
                        Livro livro = new Livro(id, nome, precoCompra, precoVenda, estoque, autor);
                        controller.atualizarProduto(livro); // Use o método correto para atualizar Livro
                    } else {
                        Produto produto = new Produto(id, nome, precoCompra, precoVenda, estoque);
                        controller.atualizarProduto(produto); // Use o método correto para atualizar Produto
                    }

                    JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso!");
                    limparCampos();
                    listarProdutos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
                }
            }
        });
        btnExcluir.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Digite o ID do produto a excluir:");
            if (idStr != null && !idStr.isBlank()) {
                try {
                    int id = Integer.parseInt(idStr);
                    controller.excluirProduto(id);
                    JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
                    listarProdutos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
                }
            }
        });

        btnVisualizar.addActionListener(e -> listarProdutos());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public List<Produto> listarOrdenado() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE ativo = TRUE ORDER BY nome ASC";
        try (Statement st = DB.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                String autor = rs.getString("autor");
                Produto p;
                if (autor != null) {
                    // É um livro
                    p = new Livro(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco_compra"),
                        rs.getDouble("preco_venda"),
                        rs.getInt("estoque"),
                        autor
                    );
                } else {
                    // É um produto comum
                    p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco_compra"),
                        rs.getDouble("preco_venda"),
                        rs.getInt("estoque")
                    );
                }
                lista.add(p);
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao listar produtos: " + e.getMessage());
        }
        return lista;
    }


    
    private void listarProdutos() {
        List<Produto> lista = listarOrdenado(); // usa o método local
        txtAreaProdutos.setText("");

        for (Produto p : lista) {
            if (p instanceof Livro) {
                Livro l = (Livro) p;
                txtAreaProdutos.append(
                    "Livro\nID: " + l.getId() +
                    "\nNome: " + l.getNome() +
                    "\nPreço de Compra: " + l.getPrecoCompra() +
                    "\nPreço de Venda: " + l.getPrecoVenda() +
                    "\nEstoque: " + l.getEstoque() +
                    "\nAutor: " + l.getAutor() +
                    "\n---------------------------\n"
                );
            } else {
                txtAreaProdutos.append(
                    "Produto\nID: " + p.getId() +
                    "\nNome: " + p.getNome() +
                    "\nPreço de Compra: " + p.getPrecoCompra() +
                    "\nPreço de Venda: " + p.getPrecoVenda() +
                    "\nEstoque: " + p.getEstoque() +
                    "\n---------------------------\n"
                );
            }
        }
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtPrecoCompra.setText("");
        txtPrecoVenda.setText("");
        txtEstoque.setText("");
        txtAutor.setText("");
        chkLivro.setSelected(false);
        txtAutor.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProdutoView::new);
    }
}