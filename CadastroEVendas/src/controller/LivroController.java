package controller;

import java.util.List;

import dao.LivroDAO;
import model.Livro;

/**
 * Controlador responsável por aplicar regras de negócio e intermediar operações
 * entre a camada de apresentação e a camada de acesso a dados (LivroDAO) para a entidade Livro.
 */
public class LivroController {
    // Instância da classe LivroDAO para realizar as operações de acesso aos dados do livro.
    private LivroDAO dao = new LivroDAO();

    /**
     * Método para cadastrar um novo livro no sistema.
     * Cria um objeto Livro com os dados fornecidos e o envia para a camada de acesso a dados (DAO)
     * para ser persistido no banco de dados. O ID do livro é definido como null, pois geralmente
     * é gerado automaticamente pelo banco.
     *
     * @param nome        o nome do livro a ser cadastrado.
     * @param precoCompra o preço de compra do livro.
     * @param precoVenda  o preço de venda do livro.
     * @param estoque     a quantidade em estoque do livro.
     * @param autor       o autor do livro.
     */
    public void cadastrarProduto(String nome, Double precoCompra, Double precoVenda, Integer estoque, String autor) {
        // Cria um novo objeto Livro com os dados fornecidos.
        Livro livro = new Livro(null, nome, precoCompra, precoVenda, estoque, autor);
        // Chama o método 'inserir' do DAO para persistir o novo livro no banco de dados.
        dao.inserir(livro);
    }

    /**
     * Método para buscar um livro no sistema através do seu ID.
     * Chama o método 'buscarPorId' do DAO para recuperar o livro do banco de dados.
     *
     * @param id o ID do livro a ser buscado.
     * @return o objeto Livro correspondente ao ID fornecido, ou null se não encontrado.
     */
    public Livro buscarLivro(int id) {
        // Chama o método 'buscarPorId' do DAO para buscar o livro pelo ID.
        return dao.buscarPorId(id);
    }

    /**
     * Método para atualizar os dados de um livro existente no sistema.
     * Recebe um objeto Livro com os dados atualizados e o envia para a camada de acesso a dados (DAO)
     * para ser atualizado no banco de dados. O ID do livro é usado para identificar o registro a ser atualizado.
     *
     * @param produto o objeto Livro com os dados atualizados.
     */
    public void atualizarLivro(Livro produto) {
        // Chama o método 'atualizar' do DAO para persistir as alterações do livro no banco de dados.
        dao.atualizar(produto);
    }

    /**
     * Método para excluir um livro do sistema através do seu ID.
     * Chama o método 'deletar' do DAO para marcar o livro como inativo no banco de dados.
     *
     * @param id o ID do livro a ser excluído (marcado como inativo).
     */
    public void excluirProduto(int id) {
        // Chama o método 'deletar' do DAO para marcar o livro como inativo pelo ID.
        dao.deletar(id);
    }

    /**
     * Método para listar todos os livros ativos cadastrados no sistema, ordenados por nome.
     * Chama o método 'listar' do DAO para obter uma lista de todos os livros ativos do banco de dados.
     *
     * @return uma lista de todos os objetos Livro ativos cadastrados no sistema, ordenados por nome.
     */
    public List<Livro> listarProdutos() {
        // Chama o método 'listar' do DAO para obter a lista de todos os livros ativos.
        return dao.listar();
    }

    /**
     * Método para atualizar a quantidade em estoque de um livro específico no sistema.
     * Chama o método 'atualizarEstoque' do DAO para persistir a nova quantidade no banco de dados.
     *
     * @param idProduto   o ID do livro a ter o estoque atualizado.
     * @param novoEstoque a nova quantidade em estoque.
     */
    public void atualizarEstoque(int idProduto, int novoEstoque) {
        // Chama o método 'atualizarEstoque' do DAO para atualizar o estoque do livro pelo ID.
        dao.atualizarEstoque(idProduto, novoEstoque);
    }

    /**
     * Método para atualizar os preços de compra e venda de um livro específico no sistema.
     * Chama o método 'atualizarPrecos' do DAO para persistir os novos preços no banco de dados.
     *
     * @param idProduto       o ID do livro a ter os preços atualizados.
     * @param novoPrecoCompra o novo preço de compra.
     * @param novoPrecoVenda  o novo preço de venda.
     */
    public void atualizarPrecos(int idProduto, double novoPrecoCompra, double novoPrecoVenda) {
        // Chama o método 'atualizarPrecos' do DAO para atualizar os preços do livro pelo ID.
        dao.atualizarPrecos(idProduto, novoPrecoCompra, novoPrecoVenda);
    }
}