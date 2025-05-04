package dao;

import db.DB;
import db.DbException;
import model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar operações de banco de dados
 * relacionadas à entidade Livro. Esta classe implementa o padrão
 * Data Access Object (DAO) para abstrair a interação com o banco de dados.
 */
public class LivroDAO {

    /**
     * Insere um novo livro no banco de dados.
     * Os dados do livro a serem inseridos são obtidos do objeto Livro fornecido.
     * Após a inserção, tenta recuperar a chave primária gerada automaticamente (ID)
     * e a define no objeto Livro.
     *
     * @param livro objeto Livro contendo os dados do livro a ser inserido.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação.
     */
    public void inserir(Livro livro) {
        // Define a string SQL para a inserção de um novo registro na tabela 'produtos'.
        // Os valores a serem inseridos são representados por placeholders '?' que serão
        // substituídos pelos atributos do objeto Livro.
        String sql = "INSERT INTO produtos (nome, preco_compra, preco_venda, estoque, autor) VALUES (?, ?, ?, ?, ?)";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente após a execução ou em caso de exceção.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Define os valores dos placeholders na instrução SQL com os dados do objeto Livro.
            st.setString(1, livro.getNome()); // Primeiro placeholder: nome do livro.
            st.setDouble(2, livro.getPrecoCompra()); // Segundo placeholder: preço de compra.
            st.setDouble(3, livro.getPrecoVenda()); // Terceiro placeholder: preço de venda.
            st.setInt(4, livro.getEstoque()); // Quarto placeholder: quantidade em estoque.
            st.setString(5, livro.getAutor()); // Quinto placeholder: nome do autor.
            st.executeUpdate(); // Executa a instrução SQL de inserção no banco de dados.

            // Recupera as chaves primárias geradas automaticamente pela inserção (geralmente o ID).
            ResultSet rs = st.getGeneratedKeys();
            // Verifica se algum registro foi inserido e se uma chave foi gerada.
            if (rs.next()) {
                // Define o ID do livro no objeto Livro com o valor da chave gerada.
                livro.setId(rs.getInt(1));
            }
            // Fecha o ResultSet para liberar recursos.
            DB.closeResultSet(rs);
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao inserir livro: " + e.getMessage());
        }
    }

    /**
     * Busca um livro no banco de dados a partir do seu ID.
     * A busca inclui filtros para garantir que o livro tenha um autor definido e esteja ativo.
     *
     * @param id o ID do livro a ser buscado.
     * @return um objeto Livro se encontrado, ou null caso contrário.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação.
     */
    public Livro buscarPorId(int id) {
        // Define a string SQL para selecionar um registro da tabela 'produtos' com base no ID,
        // garantindo que o campo 'autor' não seja nulo e que o campo 'ativo' seja verdadeiro.
        String sql = "SELECT * FROM produtos WHERE id = ? AND autor IS NOT NULL AND ativo = TRUE";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define o valor do placeholder '?' com o ID do livro a ser buscado.
            st.setInt(1, id);
            // Executa a consulta SQL e armazena o resultado em um ResultSet.
            ResultSet rs = st.executeQuery();
            // Verifica se algum registro foi encontrado.
            if (rs.next()) {
                // Cria um novo objeto Livro com os dados recuperados do ResultSet.
                Livro livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco_compra"),
                        rs.getDouble("preco_venda"),
                        rs.getInt("estoque"),
                        rs.getString("autor")
                );
                // Fecha o ResultSet.
                DB.closeResultSet(rs);
                // Retorna o objeto Livro encontrado.
                return livro;
            }
            // Se nenhum livro com o ID especificado for encontrado, retorna null.
            return null;
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao buscar livro: " + e.getMessage());
        }
    }

    /**
     * Atualiza os dados de um livro existente no banco de dados.
     * A atualização é feita com base no ID do livro fornecido no objeto Livro.
     *
     * @param livro objeto Livro contendo os novos dados a serem atualizados e o ID do livro.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação.
     */
    public void atualizar(Livro livro) {
        // Define a string SQL para atualizar um registro na tabela 'produtos' com base no ID.
        // Os novos valores para as colunas nome, preco_compra, preco_venda, estoque e autor
        // são definidos pelos placeholders '?'.
        String sql = "UPDATE produtos SET nome = ?, preco_compra = ?, preco_venda = ?, estoque = ?, autor = ? WHERE id = ?";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define os valores dos placeholders na instrução SQL com os dados do objeto Livro.
            st.setString(1, livro.getNome());
            st.setDouble(2, livro.getPrecoCompra());
            st.setDouble(3, livro.getPrecoVenda());
            st.setInt(4, livro.getEstoque());
            st.setString(5, livro.getAutor());
            st.setInt(6, livro.getId()); // O ID é usado na cláusula WHERE para identificar o livro a ser atualizado.
            st.executeUpdate(); // Executa a instrução SQL de atualização no banco de dados.
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    /**
     * "Deleta" um livro do banco de dados, na verdade, marcando-o como inativo
     * através da atualização do campo 'ativo' para FALSE.
     *
     * @param id o ID do livro a ser marcado como inativo.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação.
     */
    public void deletar(int id) {
        // Define a string SQL para atualizar o campo 'ativo' para FALSE no registro da tabela 'produtos'
        // correspondente ao ID fornecido.
        String sql = "UPDATE produtos SET ativo = FALSE WHERE id = ?";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define o valor do placeholder '?' com o ID do livro a ser deletado (inativado).
            st.setInt(1, id);
            st.executeUpdate(); // Executa a instrução SQL de atualização.
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao deletar livro: " + e.getMessage());
        }
    }

    /**
     * Lista todos os livros ativos do banco de dados, ordenados por nome em ordem ascendente
     * e que possuem um autor definido.
     *
     * @return uma lista de objetos Livro contendo todos os livros ativos encontrados.
     * Retorna uma lista vazia se nenhum livro ativo for encontrado.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação.
     */
    public List<Livro> listar() {
        // Cria uma nova lista ArrayList para armazenar os livros encontrados.
        List<Livro> lista = new ArrayList<>();
        // Define a string SQL para selecionar todos os registros da tabela 'produtos' onde
        // o campo 'ativo' é TRUE e o campo 'autor' não é nulo, ordenados pelo nome em ordem crescente.
        String sql = "SELECT * FROM produtos WHERE ativo = TRUE AND autor IS NOT NULL ORDER BY nome ASC";
        // Utiliza um bloco try-with-resources para garantir que a conexão (via Statement) e o ResultSet
        // sejam fechados automaticamente.
        try (Statement st = DB.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            // Itera sobre cada linha (livro) retornado pelo ResultSet.
            while (rs.next()) {
                // Cria um novo objeto Livro com os dados de cada coluna da linha atual.
                Livro livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco_compra"),
                        rs.getDouble("preco_venda"),
                        rs.getInt("estoque"),
                        rs.getString("autor")
                );
                // Adiciona o objeto Livro à lista.
                lista.add(livro);
            }
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao listar livros: " + e.getMessage());
        }
        // Retorna a lista de livros ativos encontrados.
        return lista;
    }

    /**
     * Atualiza a quantidade em estoque de um livro específico no banco de dados.
     * A atualização é feita com base no ID do livro.
     *
     * @param idLivro     o ID do livro a ter o estoque atualizado.
     * @param novoEstoque a nova quantidade em estoque para o livro.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação.
     */
    public void atualizarEstoque(int idLivro, int novoEstoque) {
        // Define a string SQL para atualizar o campo 'estoque' no registro da tabela 'produtos'
        // correspondente ao ID do livro fornecido.
        String sql = "UPDATE produtos SET estoque = ? WHERE id = ?";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define os valores dos placeholders na instrução SQL.
            st.setInt(1, novoEstoque); // Primeiro placeholder: nova quantidade em estoque.
            st.setInt(2, idLivro); // Segundo placeholder: ID do livro a ser atualizado.
            st.executeUpdate(); // Executa a instrução SQL de atualização.
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao atualizar estoque: " + e.getMessage());
        }
    }

    /**
     * Atualiza os preços de compra e venda de um livro específico no banco de dados.
     * A atualização é feita com base no ID do livro.
     *
     * @param idLivro         o ID do livro a ter os preços atualizados.
     * @param novoPrecoCompra o novo preço de compra do livro.
     * @param novoPrecoVenda  o novo preço de venda do livro.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação.
     */
    public void atualizarPrecos(int idLivro, double novoPrecoCompra, double novoPrecoVenda) {
        // Define a string SQL para atualizar os campos 'preco_compra' e 'preco_venda'
        // no registro da tabela 'produtos' correspondente ao ID do livro fornecido.
        String sql = "UPDATE produtos SET preco_compra = ?, preco_venda = ? WHERE id = ?";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define os valores dos placeholders na instrução SQL.
            st.setDouble(1, novoPrecoCompra); // Primeiro placeholder: novo preço de compra.
            st.setDouble(2, novoPrecoVenda); // Segundo placeholder: novo preço de venda.
            st.setInt(3, idLivro); // Terceiro placeholder: ID do livro a ser atualizado.
            st.executeUpdate(); // Executa a instrução SQL de atualização.
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao atualizar preços: " + e.getMessage());
        }
    }
}