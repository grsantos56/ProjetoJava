package controller;

import java.util.List;

import dao.LivroDAO;
import model.Livro;

public class LivroController {
	private LivroDAO dao = new LivroDAO();

    public void cadastrarProduto(String nome, Double precoCompra, Double precoVenda, Integer estoque, String autor) {
        Livro livro = new Livro(null, nome, precoCompra, precoVenda, estoque, autor);
        dao.inserir(livro);
    }

    public Livro buscarLivro(int id) {
        return dao.buscarPorId(id);
    }

    public void atualizarLivro(Livro produto) {
        dao.atualizar(produto);
    }

    public void excluirProduto(int id) {
        dao.deletar(id);
    }

    public List<Livro> listarProdutos() {
        return dao.listar();
    }

    public void atualizarEstoque(int idProduto, int novoEstoque) {
        dao.atualizarEstoque(idProduto, novoEstoque);
    }
    
    public void atualizarPrecos(int idProduto, double novoPrecoCompra, double novoPrecoVenda) {
        dao.atualizarPrecos(idProduto, novoPrecoCompra, novoPrecoVenda);
    }
}
