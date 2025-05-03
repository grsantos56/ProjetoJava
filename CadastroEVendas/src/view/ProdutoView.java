package view;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.*;

import java.util.List;

import model.Livro;
import model.Produto;
import dao.ProdutoDAO;
import dao.LivroDAO;

public class ProdutoView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtNome, txtPrecoCompra, txtPrecoVenda, txtEstoque, txtId;
    private JTextArea textAreaProdutos;
    private JTextField txtAutor;
    public ProdutoView() {
        setTitle("Gerenciar Produtos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 624);
        getContentPane().setLayout(null);

        JLabel lblTitulo = new JLabel("GERENCIAR PRODUTOS");
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 20));
        lblTitulo.setBounds(160, 10, 300, 30);
        getContentPane().add(lblTitulo);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(30, 60, 150, 20);
        getContentPane().add(lblId);

        txtId = new JTextField();
        txtId.setBounds(30, 80, 91, 25);
        getContentPane().add(txtId);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 110, 150, 20);
        getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(30, 130, 162, 25);
        getContentPane().add(txtNome);

        JLabel lblPrecoCompra = new JLabel("Preço de Compra:");
        lblPrecoCompra.setBounds(30, 160, 150, 20);
        getContentPane().add(lblPrecoCompra);

        txtPrecoCompra = new JTextField();
        txtPrecoCompra.setBounds(30, 180, 162, 25);
        getContentPane().add(txtPrecoCompra);

        JLabel lblPrecoVenda = new JLabel("Preço de Venda:");
        lblPrecoVenda.setBounds(30, 210, 150, 20);
        getContentPane().add(lblPrecoVenda);

        txtPrecoVenda = new JTextField();
        txtPrecoVenda.setBounds(30, 230, 162, 25);
        getContentPane().add(txtPrecoVenda);

        JLabel lblEstoque = new JLabel("Estoque:");
        lblEstoque.setBounds(30, 260, 150, 20);
        getContentPane().add(lblEstoque);

        txtEstoque = new JTextField();
        txtEstoque.setBounds(30, 280, 162, 25);
        getContentPane().add(txtEstoque);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(370, 77, 118, 30);
        getContentPane().add(btnCadastrar);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(370, 127, 118, 30);
        getContentPane().add(btnAtualizar);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBounds(370, 177, 118, 30);
        getContentPane().add(btnModificar);

        JButton btnExcluir = new JButton("Deletar");
        btnExcluir.setBounds(370, 227, 118, 30);
        getContentPane().add(btnExcluir);

        JButton btnListar = new JButton("Ver Produtos");
        btnListar.setBounds(370, 277, 118, 30);
        getContentPane().add(btnListar);

        JButton btnSair = new JButton("SAIR");
        btnSair.setForeground(new Color(255, 255, 255));
        btnSair.setFont(new Font("Verdana", Font.PLAIN, 12));
        btnSair.setBackground(new Color(255, 81, 81));
        btnSair.setBounds(247, 541, 74, 30);
        getContentPane().add(btnSair);

        JLabel lblAutor = new JLabel("Autor (se for livro):");
        lblAutor.setBounds(201, 110, 200, 20);
        getContentPane().add(lblAutor);

        txtAutor = new JTextField();
        txtAutor.setBounds(200, 130, 160, 25);
        getContentPane().add(txtAutor);
        
        textAreaProdutos = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textAreaProdutos);
        scrollPane.setBounds(30, 330, 520, 200);
        getContentPane().add(scrollPane);

        // --- Ações dos botões ---

        ProdutoDAO dao = new ProdutoDAO();
        LivroDAO ldao = new LivroDAO();
        
        btnCadastrar.addActionListener(e -> {
            Produto p = criarProduto();
            if (p != null) {
                dao.inserir(p);
                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso.");
                limparCampos();
            }
        });

        btnAtualizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Produto p = criarProduto();
                if (p != null) {
                    p.setId(id);
                    dao.atualizar(p);
                    JOptionPane.showMessageDialog(this, "Produto atualizado.");
                    limparCampos();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        });

        btnModificar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Produto p = dao.buscarPorId(id);
                if (p != null) {
                    txtNome.setText(p.getNome());
                    txtPrecoCompra.setText(p.getPrecoCompra().toString());
                    txtPrecoVenda.setText(p.getPrecoVenda().toString());
                    txtEstoque.setText(p.getEstoque().toString());
                    if (p instanceof Livro) {
                        txtAutor.setText(((Livro) p).getAutor());
                    } else {
                        txtAutor.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        });


        btnExcluir.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                dao.deletar(id);
                JOptionPane.showMessageDialog(this, "Produto deletado.");
                limparCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        });

        btnListar.addActionListener(e -> {
            textAreaProdutos.setText("");
                // Listar todos os produtos
                List<Produto> produtos = dao.listarOrdenado();
                for (Produto p : produtos) {
                    textAreaProdutos.append(p.toString() + "\n");
                }
        });


        setResizable(false);
        setLocationRelativeTo(null);
    }

    private Produto criarProduto() {
        try {
            String nome = txtNome.getText().toUpperCase();
            double precoCompra = Double.parseDouble(txtPrecoCompra.getText());
            double precoVenda = Double.parseDouble(txtPrecoVenda.getText());
            int estoque = Integer.parseInt(txtEstoque.getText());
			String autor = txtAutor.getText().trim().toUpperCase();

            if (!autor.isEmpty()) {
                return new Livro(null, nome, precoCompra, precoVenda, estoque, autor);
            } else {
                return new Produto(null, nome, precoCompra, precoVenda, estoque);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.");
            return null;
        }
    }


    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtPrecoCompra.setText("");
        txtPrecoVenda.setText("");
        txtEstoque.setText("");
        txtAutor.setText("");
    }

    public static void main(String[] args) {
        new ProdutoView().setVisible(true);
    }
    
}