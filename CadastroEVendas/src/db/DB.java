package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Classe utilitária responsável por gerenciar a conexão com o banco de dados.
 * Implementa o padrão Singleton para garantir que exista apenas uma instância
 * da conexão durante a execução da aplicação. Também fornece métodos para
 * carregar as configurações de acesso ao banco de dados e fechar recursos
 * como conexões, statements e result sets.
 */
public class DB {

    // Variável estática para armazenar a única instância da conexão com o banco de dados.
    private static Connection con = null;

    /**
     * Retorna uma instância da conexão com o banco de dados.
     * Se a conexão ainda não foi estabelecida (con == null), este método
     * carrega as propriedades de configuração do arquivo 'db.properties',
     * obtém a URL de conexão e estabelece a conexão com o banco de dados
     * utilizando o DriverManager. Se a conexão já existir, retorna a instância existente,
     * garantindo o padrão Singleton.
     *
     * @return um objeto Connection representando a conexão com o banco de dados.
     * @throws DbException se ocorrer algum erro de SQL durante a tentativa de conexão.
     */
    public static Connection getConnection() {
        try {
            if (con == null) {
                Properties prop = carregarProperties();
                String url = prop.getProperty("dburl");
                con = DriverManager.getConnection(url, prop);
            }
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com a mensagem de erro original.
            throw new DbException(e.getMessage());
        }
        return con;
    }

    /**
     * Fecha a conexão com o banco de dados, se ela estiver aberta (não for null).
     * Este método deve ser chamado quando a conexão não for mais necessária,
     * geralmente ao final da execução da aplicação, para liberar recursos do banco de dados.
     *
     * @return a conexão fechada (que será null após o fechamento bem-sucedido),
     * ou a conexão existente se já estiver fechada ou for null.
     * @throws DbException se ocorrer algum erro de SQL durante a tentativa de fechar a conexão.
     */
    public static Connection closeConnection() {
        if (con != null) {
            try {
                con.close();
                con = null; // Define a conexão como null após o fechamento.
            } catch (SQLException e) {
                // Em caso de erro de SQL ao fechar a conexão, lança uma DbException.
                throw new DbException(e.getMessage());
            }
        }
        return con;
    }

    /**
     * Carrega as propriedades de configuração do banco de dados a partir do arquivo
     * 'db.properties' localizado na raiz do classpath da aplicação.
     * Este arquivo deve conter informações como a URL de conexão, nome de usuário e senha
     * do banco de dados.
     *
     * @return um objeto Properties contendo as configurações do banco de dados.
     * @throws DbException se ocorrer algum erro de I/O durante a leitura do arquivo de propriedades.
     */
    public static Properties carregarProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(fs);
            return prop;
        } catch (IOException e) {
            // Em caso de erro de I/O ao ler o arquivo, lança uma DbException.
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Fecha um objeto Statement (usado para executar comandos SQL) se ele não for null.
     * É importante fechar os Statements após o uso para liberar recursos associados
     * à execução de comandos no banco de dados.
     *
     * @param st o objeto Statement a ser fechado.
     * @throws DbException se ocorrer algum erro de SQL ao tentar fechar o Statement.
     */
    public static void closeStatment(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                // Em caso de erro de SQL ao fechar o Statement, lança uma DbException.
                throw new DbException(e.getMessage());
            }
        }
    }

    /**
     * Fecha um objeto ResultSet (usado para armazenar os resultados de uma consulta SQL)
     * se ele não for null. É fundamental fechar os ResultSets após processar os dados
     * para liberar recursos e evitar vazamentos de memória ou conexões pendentes.
     *
     * @param rs o objeto ResultSet a ser fechado.
     * @throws DbException se ocorrer algum erro de SQL ao tentar fechar o ResultSet.
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // Em caso de erro de SQL ao fechar o ResultSet, lança uma DbException.
                throw new DbException(e.getMessage());
            }
        }
    }
}