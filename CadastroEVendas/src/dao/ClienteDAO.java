package dao;

import java.sql.*;
import java.util.*;
import db.DB;
import db.DbException;
import model.Cliente;

/**
 * Classe responsável por realizar operações de banco de dados
 * relacionadas à entidade Cliente. Esta classe implementa o padrão
 * Data Access Object (DAO) para abstrair a interação com o banco de dados.
 */
public class ClienteDAO {

    /**
     * Insere um novo cliente no banco de dados.
     * Os dados do cliente a serem inseridos são obtidos do objeto Cliente fornecido.
     *
     * @param cliente objeto Cliente com os dados a serem inseridos (cpf, nome, telefone, endereco).
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação de inserção.
     */
    public void inserir(Cliente cliente) {
        // Define a string SQL para a inserção de um novo registro na tabela 'clientes'.
        // Os valores a serem inseridos são representados por placeholders '?' que serão
        // substituídos pelos atributos do objeto Cliente.
        String sql = "INSERT INTO clientes (cpf, nome, telefone, endereco) VALUES (?, ?, ?, ?)";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente após a execução ou em caso de exceção.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define os valores dos placeholders na instrução SQL com os dados do objeto Cliente.
            st.setString(1, cliente.getCpf()); // Primeiro placeholder: CPF do cliente.
            st.setString(2, cliente.getNome()); // Segundo placeholder: nome do cliente.
            st.setString(3, cliente.getTelefone()); // Terceiro placeholder: telefone do cliente.
            st.setString(4, cliente.getEndereco()); // Quarto placeholder: endereço do cliente.
            st.executeUpdate(); // Executa a instrução SQL de inserção no banco de dados.
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    /**
     * Busca um cliente no banco de dados a partir do seu CPF.
     *
     * @param cpf CPF do cliente a ser buscado.
     * @return um objeto Cliente se encontrado, ou null caso contrário.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação de busca.
     */
    public Cliente buscarPorCpf(String cpf) {
        // Define a string SQL para selecionar um registro da tabela 'clientes' com base no CPF.
        String sql = "SELECT * FROM clientes WHERE cpf = ?";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define o valor do placeholder '?' com o CPF do cliente a ser buscado.
            st.setString(1, cpf);
            // Executa a consulta SQL e armazena o resultado em um ResultSet.
            ResultSet rs = st.executeQuery(); // executa a consulta
            // Verifica se algum registro foi encontrado.
            if (rs.next()) { // se encontrou um cliente
                // Cria um novo objeto Cliente com os dados recuperados do ResultSet.
                Cliente c = new Cliente(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("endereco")
                );
                return c; // Retorna o objeto Cliente encontrado.
            }
            return null; // cliente não encontrado
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao buscar cliente: " + e.getMessage());
        }
    }

    /**
     * Retorna todos os clientes cadastrados no banco de dados.
     *
     * @return lista de objetos Cliente contendo todos os clientes encontrados.
     * Retorna uma lista vazia se não houver clientes cadastrados.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação de listagem.
     */
    public List<Cliente> listarTodos() {
        // Cria uma nova lista ArrayList para armazenar os clientes encontrados.
        List<Cliente> lista = new ArrayList<>();
        // Define a string SQL para selecionar todos os registros da tabela 'clientes'.
        String sql = "SELECT * FROM clientes";
        // Utiliza um bloco try-with-resources para garantir que a conexão (via Statement) e o ResultSet
        // sejam fechados automaticamente.
        try (Statement st = DB.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            // Itera sobre cada linha (cliente) retornado pelo ResultSet.
            while (rs.next()) {
                // Cria um novo objeto Cliente com os dados de cada coluna da linha atual.
                Cliente c = new Cliente(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("endereco")
                );
                lista.add(c); // adiciona o cliente à lista
            }
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao listar clientes: " + e.getMessage());
        }
        return lista; // Retorna a lista de todos os clientes cadastrados.
    }

    /**
     * Atualiza os dados de um cliente existente no banco de dados com base no seu CPF.
     *
     * @param cliente objeto Cliente contendo os novos dados a serem atualizados (nome, telefone, endereco)
     * e o CPF do cliente a ser atualizado.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação de atualização.
     */
    public void atualizar(Cliente cliente) {
        // Define a string SQL para atualizar um registro na tabela 'clientes' com base no CPF.
        // Os novos valores para as colunas nome, telefone e endereco são definidos pelos placeholders '?'.
        String sql = "UPDATE clientes SET nome=?, telefone=?, endereco=? WHERE cpf=?";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define os valores dos placeholders na instrução SQL com os dados do objeto Cliente.
            st.setString(1, cliente.getNome()); // Primeiro placeholder: novo nome.
            st.setString(2, cliente.getTelefone()); // Segundo placeholder: novo telefone.
            st.setString(3, cliente.getEndereco()); // Terceiro placeholder: novo endereço.
            st.setString(4, cliente.getCpf()); // Quarto placeholder: CPF do cliente a ser atualizado (cláusula WHERE).
            st.executeUpdate(); // Executa a instrução SQL de atualização no banco de dados.
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    /**
     * Deleta um cliente do banco de dados com base no seu CPF.
     *
     * @param cpf CPF do cliente a ser removido.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação de exclusão.
     */
    public void deletar(String cpf) {
        // Define a string SQL para deletar um registro da tabela 'clientes' com base no CPF.
        String sql = "DELETE FROM clientes WHERE cpf=?";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define o valor do placeholder '?' com o CPF do cliente a ser deletado.
            st.setString(1, cpf);
            st.executeUpdate(); // Executa a instrução SQL de exclusão no banco de dados.
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao deletar cliente: " + e.getMessage());
        }
    }
}