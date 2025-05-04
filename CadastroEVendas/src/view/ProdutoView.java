package view;

import controller.LivroController;
import controller.ProdutoController;
import db.DB;
import db.DbException;
import model.Produto;
import model.Livro;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Tela para o cadastro, atualização, exclusão e visualização de produtos (com distinção entre produtos comuns e livros).
 * Utiliza ProdutoController e LivroController para intermediar as ações com a camada de modelo e dados.
 */
public class ProdutoView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtNome, txtPrecoCompra, txtPrecoVenda, txtEstoque, txtAutor;
    private JCheckBox chkLivro;
    private JTextArea txtAreaProdutos;
    private ProdutoController controller;

    /**
     * Construtor da classe ProdutoView.
     * Inicializa os componentes da interface gráfica, como rótulos, campos de texto,
     * um checkbox para indicar se o produto é um livro (ativando o campo Autor),
     * botões para as operações (Cadastrar, Atualizar, Excluir, Visualizar, Sair)
     * e uma JTextArea para exibir a lista de produtos. Define também os listeners
     * para os botões e o checkbox.
     */
    public ProdutoView() {
        controller = new ProdutoController();

        setTitle("Cadastro de Produtos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout(10, 10)); // Espaçamento entre as áreas do layout.
        setResizable(false);

        // Painel para o formulário de entrada de dados do produto.
        JPanel painelForm = new JPanel(new GridLayout(7, 2, 10, 10)); // Layout de grade com espaçamento.
        painelForm.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Margem interna.

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
        painelForm.add(new JLabel("")); // Espaço vazio para alinhamento.

        painelForm.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        txtAutor.setEnabled(false); // Campo Autor inicialmente desabilitado.
        painelForm.add(txtAutor);

        // Listener para o checkbox de livro, habilitando/desabilitando o campo Autor.
        chkLivro.addActionListener(e -> txtAutor.setEnabled(chkLivro.isSelected()));

        getContentPane().add(painelForm, BorderLayout.NORTH);

        // Painel para os botões de ação.
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnVisualizar = new JButton("Visualizar");
        JButton btnSair = new JButton("Sair");

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnVisualizar);
        painelBotoes.add(btnSair);

        getContentPane().add(painelBotoes, BorderLayout.CENTER);

        // Área de texto para exibir a lista de produtos.
        txtAreaProdutos = new JTextArea(12, 50);
        txtAreaProdutos.setEditable(false);
        txtAreaProdutos.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtAreaProdutos);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        getContentPane().add(scrollPane, BorderLayout.SOUTH);

        // Ação para o botão Cadastrar.
        btnCadastrar.addActionListener(e -> {
            if (txtNome.getText().isBlank() || txtPrecoCompra.getText().isBlank() || txtPrecoVenda.getText().isBlank() || txtEstoque.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.");
                return;
            }
            int confirmacao = JOptionPane.showConfirmDialog(this, "Você tem certeza que deseja cadastrar este produto?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                try {
                    String nome = txtNome.getText();
                    double precoCompra = Double.parseDouble(txtPrecoCompra.getText());
                    double precoVenda = Double.parseDouble(txtPrecoVenda.getText());
                    int estoque = Integer.parseInt(txtEstoque.getText());

                    if (chkLivro.isSelected()) {
                        if (txtAutor.getText().isBlank()) {
                            JOptionPane.showMessageDialog(this, "Preencha o campo Autor.");
                            return;
                        }
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
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Erro de formato nos campos numéricos.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage());
                }
            }
        });

        // Ação para o botão Atualizar.
        btnAtualizar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Digite o ID do produto a atualizar:");
            if (idStr != null && !idStr.isBlank()) {
                int confirmacao = JOptionPane.showConfirmDialog(this, "Você tem certeza que deseja atualizar este produto?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirmacao == JOptionPane.YES_OPTION) {
                    try {
                        int id = Integer.parseInt(idStr);
                        String nome = txtNome.getText();
                        double precoCompra = Double.parseDouble(txtPrecoCompra.getText());
                        double precoVenda = Double.parseDouble(txtPrecoVenda.getText());
                        int estoque = Integer.parseInt(txtEstoque.getText());

                        if (chkLivro.isSelected()) {
                            String autor = txtAutor.getText();
                            Livro livro = new Livro(id, nome, precoCompra, precoVenda, estoque, autor);
                            controller.atualizarProduto(livro);
                        } else {
                            Produto produto = new Produto(id, nome, precoCompra, precoVenda, estoque);
                            controller.atualizarProduto(produto);
                        }

                        JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso!");
                        limparCampos();
                        listarProdutos();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Erro de formato no ID ou nos campos numéricos.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
                    }
                }
            }
        });

        // Ação para o botão Excluir.
        btnExcluir.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Digite o ID do produto a excluir:");
            if (idStr != null && !idStr.isBlank()) {
                int confirmacao = JOptionPane.showConfirmDialog(this, "Você tem certeza que deseja excluir este produto?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirmacao == JOptionPane.YES_OPTION) {
                    try {
                        int id = Integer.parseInt(idStr);
                        controller.excluirProduto(id);
                        JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
                        listarProdutos();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Erro de formato no ID.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
                    }
                }
            }
        });

        // Ação para o botão Visualizar.
        btnVisualizar.addActionListener(e -> listarProdutos());

        // Ação para o botão Sair.
        btnSair.addActionListener(e -> {
            int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Confirmação",
                    JOptionPane.YES_NO_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                TelaPrincipalView tela = new TelaPrincipalView();
                tela.setVisible(true);
                this.dispose(); // Fecha a janela atual
            }
        });

        pack(); // Ajusta o tamanho da janela aos seus componentes.
        setLocationRelativeTo(null); // Centraliza a janela na tela.
        setVisible(true);
    }

    /**
     * Lista todos os produtos ativos do banco de dados e exibe na área de texto,
     * formatando a saída de acordo com se o produto é um livro ou não.
     */
    private void listarProdutos() {
        List<Produto> lista = listarOrdenado();
        txtAreaProdutos.setText("");

        for (Produto p : lista) {
            if (p instanceof Livro) {
                Livro l = (Livro) p;
                txtAreaProdutos.append(
                        "Livro\nID: " + l.getId() +
                                "\nNome: " + l.getNome() +
                                "\nPreço de Compra: " + String.format("%.2f", l.getPrecoCompra()) +
                                "\nPreço de Venda: " + String.format("%.2f", l.getPrecoVenda()) +
                                "\nEstoque: " + l.getEstoque() +
                                "\nAutor: " + l.getAutor() +
                                "\n---------------------------\n"
                );
            } else {
                txtAreaProdutos.append(
                        "Produto\nID: " + p.getId() +
                                "\nNome: " + p.getNome() +
                                "\nPreço de Compra: " + String.format("%.2f", p.getPrecoCompra()) +
                                "\nPreço de Venda: " + String.format("%.2f", p.getPrecoVenda()) +
                                "\nEstoque: " + p.getEstoque() +
                                "\n---------------------------\n"
                );
            }
        }
    }

    /**
     * Busca todos os produtos ativos no banco de dados e retorna uma lista ordenada por nome.
     * Distingue entre produtos comuns e livros com base na presença do campo 'autor' no banco de dados.
     *
     * @return Uma lista de objetos Produto (podendo ser instâncias de Livro).
     * @throws DbException Se ocorrer algum erro ao acessar o banco de dados.
     */
    public List<Produto> listarOrdenado() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE ativo = TRUE ORDER BY nome ASC";
        try (Statement st = DB.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                String autor = rs.getString("autor");
                Produto p;
                if (autor != null) {
                    p = new Livro(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDouble("preco_compra"),
                            rs.getDouble("preco_venda"),
                            rs.getInt("estoque"),
                            autor
                    );
                } else {
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

    /**
     * Limpa os campos de entrada de dados do formulário.
     */
    private void limparCampos() {
        txtNome.setText("");
        txtPrecoCompra.setText("");
        txtPrecoVenda.setText("");
        txtEstoque.setText("");
        txtAutor.setText("");
        chkLivro.setSelected(false);
        txtAutor.setEnabled(false);
    }

    /**
     * Método principal para iniciar a aplicação e exibir a tela de produtos.
     * Executa a criação e exibição da janela em um thread separado para garantir
     * a responsividade da interface gráfica.
     *
     * @param args argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProdutoView::new);
    }
}