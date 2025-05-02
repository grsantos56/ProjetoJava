package controller;

import dao.ProdutoDAO;
import model.Produto;

import java.util.List;

/**
 * Controlador responsável por aplicar regras e intermediar operações com produtos.
 */
public class ProdutoController {

    private ProdutoDAO dao = new ProdutoDAO();

    public void cadastrarProduto(String nome, double preco, int estoque) {
        Produto produto = new Produto(null, nome, preco, estoque);
        dao.inserir(produto);
    }

    public Produto buscarProduto(int id) {
        return dao.buscarPorId(id);
    }

    public void atualizarProduto(Produto produto) {
        dao.atualizar(produto);
    }

    public void excluirProduto(int id) {
        dao.deletar(id);
    }

    public List<Produto> listarProdutos() {
        return dao.listarTodos();
    }

    public void atualizarEstoque(int idProduto, int novoEstoque) {
        dao.atualizarEstoque(idProduto, novoEstoque);
    }
}
