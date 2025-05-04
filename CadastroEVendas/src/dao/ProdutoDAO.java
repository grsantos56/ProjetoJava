package dao;

import db.DB;
import db.DbException;
import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar operações de CRUD (Create, Read, Update, Delete)
 * no banco de dados para a entidade Produto, incluindo informações de preço de compra e venda.
 * Esta classe implementa o padrão Data Access Object (DAO) para abstrair a camada de acesso a dados.
 */
public class ProdutoDAO {

    /**
     * Insere um novo produto no banco de dados.
     * Os dados do produto a serem inseridos são obtidos do objeto Produto fornecido.
     * Após a inserção, tenta recuperar a chave primária gerada automaticamente (ID)
     * e a define no objeto Produto. O campo 'autor' é explicitamente definido como nulo
     * na instrução SQL, indicando que este DAO é para produtos em geral e não especificamente livros.
     *
     * @param produto objeto Produto contendo os dados do produto a ser inserido (nome, preco_compra, preco_venda, estoque).
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação de inserção.
     */
    public void inserir(Produto produto) {
        // Define a string SQL para a inserção de um novo registro na tabela 'produtos'.
        // Os valores a serem inseridos são representados por placeholders '?' que serão
        // substituídos pelos atributos do objeto Produto. O campo 'autor' é definido como NULL.
        String sql = "INSERT INTO produtos (nome, preco_compra, preco_venda, estoque, autor) VALUES (?, ?, ?, ?, NULL)";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente após a execução ou em caso de exceção.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Define os valores dos placeholders na instrução SQL com os dados do objeto Produto.
            st.setString(1, produto.getNome()); // Primeiro placeholder: nome do produto.
            st.setDouble(2, produto.getPrecoCompra()); // Segundo placeholder: preço de compra.
            st.setDouble(3, produto.getPrecoVenda()); // Terceiro placeholder: preço de venda.
            st.setInt(4, produto.getEstoque()); // Quarto placeholder: quantidade em estoque.
            // O quinto valor (autor) é definido como NULL diretamente na string SQL.
            st.executeUpdate(); // Executa a instrução SQL de inserção no banco de dados.

            // Recupera as chaves primárias geradas automaticamente pela inserção (geralmente o ID).
            ResultSet rs = st.getGeneratedKeys();
            // Verifica se algum registro foi inserido e se uma chave foi gerada.
            if (rs.next()) {
                // Define o ID do produto no objeto Produto com o valor da chave gerada.
                produto.setId(rs.getInt(1));
            }
            // Fecha o ResultSet para liberar recursos.
            DB.closeResultSet(rs);
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao inserir produto: " + e.getMessage());
        }
    }

    /**
     * Busca um produto no banco de dados a partir do seu ID.
     *
     * @param id o ID do produto a ser buscado.
     * @return um objeto Produto se encontrado, ou null caso contrário.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação de busca.
     */
    public Produto buscarPorId(int id) {
        // Define a string SQL para selecionar um registro da tabela 'produtos' com base no ID.
        String sql = "SELECT * FROM produtos WHERE id = ?";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define o valor do placeholder '?' com o ID do produto a ser buscado.
            st.setInt(1, id);
            // Executa a consulta SQL e armazena o resultado em um ResultSet.
            ResultSet rs = st.executeQuery();
            // Verifica se algum registro foi encontrado.
            if (rs.next()) {
                // Cria um novo objeto Produto com os dados recuperados do ResultSet.
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco_compra"),
                        rs.getDouble("preco_venda"),
                        rs.getInt("estoque")
                );
                // Fecha o ResultSet.
                DB.closeResultSet(rs);
                // Retorna o objeto Produto encontrado.
                return p;
            }
            // Se nenhum produto com o ID especificado for encontrado, retorna null.
            return null;
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao buscar produto: " + e.getMessage());
        }
    }

    /**
     * Atualiza os dados de um produto existente no banco de dados.
     * A atualização é feita com base no ID do produto fornecido no objeto Produto.
     *
     * @param produto objeto Produto contendo os novos dados a serem atualizados (nome, preco_compra, preco_venda, estoque)
     * e o ID do produto.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação de atualização.
     */
    public void atualizar(Produto produto) {
        // Define a string SQL para atualizar um registro na tabela 'produtos' com base no ID.
        // Os novos valores para as colunas nome, preco_compra, preco_venda e estoque
        // são definidos pelos placeholders '?'.
        String sql = "UPDATE produtos SET nome=?, preco_compra=?, preco_venda=?, estoque=? WHERE id=?";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define os valores dos placeholders na instrução SQL com os dados do objeto Produto.
            st.setString(1, produto.getNome());
            st.setDouble(2, produto.getPrecoCompra());
            st.setDouble(3, produto.getPrecoVenda());
            st.setInt(4, produto.getEstoque());
            st.setInt(5, produto.getId()); // O ID é usado na cláusula WHERE para identificar o produto a ser atualizado.
            st.executeUpdate(); // Executa a instrução SQL de atualização no banco de dados.
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    /**
     * "Deleta" um produto do banco de dados, na verdade, marcando-o como inativo
     * através da atualização do campo 'ativo' para FALSE.
     *
     * @param id o ID do produto a ser marcado como inativo.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação de "deleção".
     */
    public void deletar(int id) {
        // Define a string SQL para atualizar o campo 'ativo' para FALSE no registro da tabela 'produtos'
        // correspondente ao ID fornecido.
        String sql = "UPDATE produtos SET ativo = FALSE WHERE id = ?";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define o valor do placeholder '?' com o ID do produto a ser "deletado" (inativado).
            st.setInt(1, id);
            st.executeUpdate(); // Executa a instrução SQL de atualização.
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao deletar produto: " + e.getMessage());
        }
    }

    /**
     * Retorna todos os produtos ativos cadastrados no banco de dados, ordenados por nome em ordem ascendente.
     *
     * @return uma lista de objetos Produto contendo todos os produtos ativos encontrados.
     * Retorna uma lista vazia se nenhum produto ativo for encontrado.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação de listagem.
     */
    public List<Produto> listarOrdenado() {
        // Cria uma nova lista ArrayList para armazenar os produtos encontrados.
        List<Produto> lista = new ArrayList<>();
        // Define a string SQL para selecionar todos os registros da tabela 'produtos' onde
        // o campo 'ativo' é TRUE, ordenados pelo nome em ordem crescente.
        String sql = "SELECT * FROM produtos WHERE ativo = TRUE ORDER BY nome ASC";
        // Utiliza um bloco try-with-resources para garantir que a conexão (via Statement) e o ResultSet
        // sejam fechados automaticamente.
        try (Statement st = DB.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            // Itera sobre cada linha (produto) retornado pelo ResultSet.
            while (rs.next()) {
                // Cria um novo objeto Produto com os dados de cada coluna da linha atual.
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco_compra"),
                        rs.getDouble("preco_venda"),
                        rs.getInt("estoque")
                );
                // Adiciona o objeto Produto à lista.
                lista.add(p);
            }
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao listar produtos: " + e.getMessage());
        }
        // Retorna a lista de produtos ativos encontrados, ordenados por nome.
        return lista;
    }


    /**
     * Atualiza a quantidade em estoque de um produto específico no banco de dados.
     * A atualização é feita com base no ID do produto.
     *
     * @param idProduto   o ID do produto a ter o estoque atualizado.
     * @param novoEstoque a nova quantidade em estoque para o produto.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação de atualização.
     */
    public void atualizarEstoque(int idProduto, int novoEstoque) {
        // Define a string SQL para atualizar o campo 'estoque' no registro da tabela 'produtos'
        // correspondente ao ID do produto fornecido.
        String sql = "UPDATE produtos SET estoque = ? WHERE id = ?";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define os valores dos placeholders na instrução SQL.
            st.setInt(1, novoEstoque); // Primeiro placeholder: nova quantidade em estoque.
            st.setInt(2, idProduto); // Segundo placeholder: ID do produto a ser atualizado.
            st.executeUpdate(); // Executa a instrução SQL de atualização.
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao atualizar estoque: " + e.getMessage());
        }
    }

    /**
     * Atualiza apenas os preços de compra e venda de um produto específico no banco de dados.
     * A atualização é feita com base no ID do produto.
     *
     * @param idProduto     o ID do produto a ter os preços atualizados.
     * @param novoPrecoCompra o novo preço de compra do produto.
     * @param novoPrecoVenda  o novo preço de venda do produto.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação de atualização.
     */
    public void atualizarPrecos(int idProduto, double novoPrecoCompra, double novoPrecoVenda) {
        // Define a string SQL para atualizar os campos 'preco_compra' e 'preco_venda'
        // no registro da tabela 'produtos' correspondente ao ID do produto fornecido.
        String sql = "UPDATE produtos SET preco_compra = ?, preco_venda = ? WHERE id = ?";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define os valores dos placeholders na instrução SQL.
            st.setDouble(1, novoPrecoCompra); // Primeiro placeholder: novo preço de compra.
            st.setDouble(2, novoPrecoVenda); // Segundo placeholder: novo preço de venda.
            st.setInt(3, idProduto); // Terceiro placeholder: ID do produto a ser atualizado.
            st.executeUpdate(); // Executa a instrução SQL de atualização.
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao atualizar preços: " + e.getMessage());
        }
    }
}