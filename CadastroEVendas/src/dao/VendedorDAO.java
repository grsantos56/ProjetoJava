package dao;

import db.DB;
import db.DbException;
import java.sql.*;

/**
 * Classe responsável por realizar operações de acesso a dados relacionadas
 * à entidade Vendedor, como autenticação, cadastro e recuperação de senha.
 */
public class VendedorDAO {

    /**
     * Autentica um vendedor no sistema verificando se o nome de usuário e a senha
     * fornecidos correspondem a um registro na tabela 'vendedor'.
     *
     * @param usuario o nome de usuário do vendedor a ser autenticado.
     * @param senha   a senha do vendedor a ser autenticado.
     * @return {@code true} se a autenticação for bem-sucedida (usuário e senha correspondem),
     * {@code false} caso contrário.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a autenticação.
     */
    public boolean autenticar(String usuario, String senha) {
        // Define a string SQL para selecionar todos os registros da tabela 'vendedor'
        // onde a coluna 'usuario' corresponde ao valor fornecido e a coluna 'senha'
        // corresponde ao valor fornecido.
        String sql = "SELECT * FROM vendedor WHERE usuario = ? AND senha = ?";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente após a execução ou em caso de exceção.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define os valores dos placeholders na instrução SQL com o usuário e a senha fornecidos.
            st.setString(1, usuario); // Primeiro placeholder: nome de usuário.
            st.setString(2, senha);   // Segundo placeholder: senha.
            // Executa a consulta SQL e armazena o resultado em um ResultSet.
            ResultSet rs = st.executeQuery();
            // Verifica se algum registro foi encontrado. Se 'rs.next()' retornar true,
            // significa que um vendedor com as credenciais fornecidas existe no banco.
            boolean autenticado = rs.next();
            // Fecha o ResultSet para liberar recursos.
            rs.close();
            // Retorna o resultado da autenticação.
            return autenticado;
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao autenticar: " + e.getMessage());
        }
    }

    /**
     * Cadastra um novo vendedor no sistema, inserindo um novo registro na tabela 'vendedor'
     * com o nome de usuário e a senha fornecidos.
     *
     * @param usuario o nome de usuário do novo vendedor a ser cadastrado.
     * @param senha   a senha do novo vendedor a ser cadastrado.
     * @return {@code true} se o cadastro for bem-sucedido, {@code false} em caso de erro
     * (por exemplo, violação de chave única, erro de conexão).
     */
    public boolean cadastrar(String usuario, String senha) {
        // Define a string SQL para inserir um novo registro na tabela 'vendedor'
        // com o nome de usuário e a senha fornecidos.
        String sql = "INSERT INTO vendedor (usuario, senha) VALUES (?, ?)";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente após a execução ou em caso de exceção.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define os valores dos placeholders na instrução SQL com o usuário e a senha fornecidos.
            st.setString(1, usuario); // Primeiro placeholder: nome de usuário.
            st.setString(2, senha);   // Segundo placeholder: senha.
            // Executa a instrução SQL de inserção no banco de dados.
            st.executeUpdate();
            // Se a execução da atualização ocorrer sem exceção, o cadastro é considerado bem-sucedido.
            return true;
        } catch (SQLException e) {
            // Em caso de erro de SQL, retorna false indicando que o cadastro falhou.
            // A exceção pode ocorrer devido a várias razões, como violação de chave única
            // (se o nome de usuário já existir) ou problemas de conexão com o banco.
            return false;
        }
    }

    /**
     * Recupera a senha de um vendedor com base no nome de usuário fornecido.
     *
     * @param usuario o nome de usuário do vendedor para o qual se deseja recuperar a senha.
     * @return a senha do vendedor correspondente ao nome de usuário, ou {@code null}
     * se nenhum vendedor com esse nome de usuário for encontrado.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a recuperação da senha.
     */
    public String recuperarSenha(String usuario) {
        // Define a string SQL para selecionar a coluna 'senha' da tabela 'vendedor'
        // onde a coluna 'usuario' corresponde ao valor fornecido.
        String sql = "SELECT senha FROM vendedor WHERE usuario = ?";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente após a execução ou em caso de exceção.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define o valor do placeholder na instrução SQL com o nome de usuário fornecido.
            st.setString(1, usuario); // Primeiro placeholder: nome de usuário.
            // Executa a consulta SQL e armazena o resultado em um ResultSet.
            ResultSet rs = st.executeQuery();
            // Verifica se algum registro foi encontrado.
            if (rs.next()) {
                // Retorna o valor da coluna 'senha' do primeiro (e único) registro encontrado.
                return rs.getString("senha");
            }
            // Se nenhum vendedor com o nome de usuário especificado for encontrado, retorna null.
            return null;
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao recuperar senha: " + e.getMessage());
        }
    }
}