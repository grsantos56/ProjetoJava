package controller;

import dao.ProdutoDAO;
import model.Produto;

import java.util.List;

/**
 * Controlador responsável por aplicar regras de negócio e intermediar operações
 * entre a camada de apresentação e a camada de acesso a dados (ProdutoDAO) para a entidade Produto.
 */
public class ProdutoController {

    // Instância da classe ProdutoDAO para realizar as operações de acesso aos dados do produto.
    private ProdutoDAO dao = new ProdutoDAO();

    /**
     * Método para cadastrar um novo produto no sistema.
     * Cria um objeto Produto com os dados fornecidos e o envia para a camada de acesso a dados (DAO)
     * para ser persistido no banco de dados. O ID do produto é definido como null, pois geralmente
     * é gerado automaticamente pelo banco.
     *
     * @param nome        o nome do produto a ser cadastrado.
     * @param precoCompra o preço de compra do produto.
     * @param precoVenda  o preço de venda do produto.
     * @param estoque     a quantidade em estoque do produto.
     */
    public void cadastrarProduto(String nome, Double precoCompra, Double precoVenda, Integer estoque) {
        // Cria um novo objeto Produto com os dados fornecidos.
        Produto produto = new Produto(null, nome, precoCompra, precoVenda, estoque);
        // Chama o método 'inserir' do DAO para persistir o novo produto no banco de dados.
        dao.inserir(produto);
    }

    /**
     * Método para buscar um produto no sistema através do seu ID.
     * Chama o método 'buscarPorId' do DAO para recuperar o produto do banco de dados.
     *
     * @param id o ID do produto a ser buscado.
     * @return o objeto Produto correspondente ao ID fornecido, ou null se não encontrado.
     */
    public Produto buscarProduto(int id) {
        // Chama o método 'buscarPorId' do DAO para buscar o produto pelo ID.
        return dao.buscarPorId(id);
    }

    /**
     * Método para atualizar os dados de um produto existente no sistema.
     * Recebe um objeto Produto com os dados atualizados e o envia para a camada de acesso a dados (DAO)
     * para ser atualizado no banco de dados. O ID do produto é usado para identificar o registro a ser atualizado.
     *
     * @param produto o objeto Produto com os dados atualizados.
     */
    public void atualizarProduto(Produto produto) {
        // Chama o método 'atualizar' do DAO para persistir as alterações do produto no banco de dados.
        dao.atualizar(produto);
    }

    /**
     * Método para excluir um produto do sistema através do seu ID.
     * Chama o método 'deletar' do DAO para marcar o produto como inativo no banco de dados.
     *
     * @param id o ID do produto a ser excluído (marcado como inativo).
     */
    public void excluirProduto(int id) {
        // Chama o método 'deletar' do DAO para marcar o produto como inativo pelo ID.
        dao.deletar(id);
    }

    /**
     * Método para listar todos os produtos ativos cadastrados no sistema, ordenados por nome.
     * Chama o método 'listarOrdenado' do DAO para obter uma lista de todos os produtos ativos do banco de dados.
     *
     * @return uma lista de todos os objetos Produto ativos cadastrados no sistema, ordenados por nome.
     */
    public List<Produto> listarProdutos() {
        // Chama o método 'listarOrdenado' do DAO para obter a lista de todos os produtos ativos ordenados.
        return dao.listarOrdenado();
    }

    /**
     * Método para atualizar a quantidade em estoque de um produto específico no sistema.
     * Chama o método 'atualizarEstoque' do DAO para persistir a nova quantidade no banco de dados.
     *
     * @param idProduto   o ID do produto a ter o estoque atualizado.
     * @param novoEstoque a nova quantidade em estoque.
     */
    public void atualizarEstoque(int idProduto, int novoEstoque) {
        // Chama o método 'atualizarEstoque' do DAO para atualizar o estoque do produto pelo ID.
        dao.atualizarEstoque(idProduto, novoEstoque);
    }

    /**
     * Método para atualizar os preços de compra e venda de um produto específico no sistema.
     * Chama o método 'atualizarPrecos' do DAO para persistir os novos preços no banco de dados.
     *
     * @param idProduto       o ID do produto a ter os preços atualizados.
     * @param novoPrecoCompra o novo preço de compra.
     * @param novoPrecoVenda  o novo preço de venda.
     */
    public void atualizarPrecos(int idProduto, double novoPrecoCompra, double novoPrecoVenda) {
        // Chama o método 'atualizarPrecos' do DAO para atualizar os preços do produto pelo ID.
        dao.atualizarPrecos(idProduto, novoPrecoCompra, novoPrecoVenda);
    }
}