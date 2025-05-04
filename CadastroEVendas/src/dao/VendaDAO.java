package dao;

import db.DB;
import db.DbException;
import model.Cliente;
import model.Produto;
import model.Venda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar operações de persistência (salvar e consultar)
 * relacionadas à entidade Venda no banco de dados.
 * Utiliza outros DAOs (ProdutoDAO) para realizar operações em entidades relacionadas.
 */
public class VendaDAO {

    // Instância de ProdutoDAO para interagir com a persistência de produtos.
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    /**
     * Registra uma nova venda no banco de dados, incluindo as informações do cliente,
     * a data da venda e os produtos associados. Para cada produto vendido,
     * também atualiza o estoque correspondente na tabela de produtos.
     *
     * @param venda objeto Venda contendo as informações da venda a ser registrada
     * (cliente, data da venda e lista de produtos).
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação de registro.
     */
    public void registrarVenda(Venda venda) {
        // Define a string SQL para inserir um novo registro na tabela 'vendas'
        // com o CPF do cliente e a data da venda.
        String sqlVenda = "INSERT INTO vendas (cpf_cliente, data_venda) VALUES (?, ?)";
        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // para a tabela 'vendas' sejam fechados automaticamente.
        try (PreparedStatement stVenda = DB.getConnection().prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS)) {
            // Define os valores dos placeholders na instrução SQL para a tabela 'vendas'.
            stVenda.setString(1, venda.getCliente().getCpf()); // Primeiro placeholder: CPF do cliente.
            stVenda.setDate(2, Date.valueOf(venda.getDataVenda())); // Segundo placeholder: data da venda.
            stVenda.executeUpdate(); // Executa a instrução SQL de inserção na tabela 'vendas'.

            // Recupera as chaves primárias geradas automaticamente pela inserção na tabela 'vendas' (o ID da venda).
            ResultSet rs = stVenda.getGeneratedKeys();
            // Verifica se algum registro foi inserido e se uma chave foi gerada.
            if (rs.next()) {
                int idVenda = rs.getInt(1); // Obtém o ID da venda gerado.
                venda.setId(idVenda); // Define o ID da venda no objeto Venda.

                // Inserir os produtos associados à venda na tabela 'venda_produtos'.
                String sqlItem = "INSERT INTO venda_produtos (id_venda, id_produto, quantidade) VALUES (?, ?, ?)";
                // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
                // para a tabela 'venda_produtos' sejam fechados automaticamente.
                try (PreparedStatement stItem = DB.getConnection().prepareStatement(sqlItem)) {
                    // Itera sobre a lista de produtos presentes na venda.
                    for (Produto p : venda.getProdutos()) {
                        stItem.setInt(1, idVenda); // Primeiro placeholder: ID da venda.
                        stItem.setInt(2, p.getId()); // Segundo placeholder: ID do produto.
                        stItem.setInt(3, 1); // Terceiro placeholder: quantidade vendida (definida como 1 por padrão, pode ser ajustado).
                        stItem.addBatch(); // Adiciona a instrução ao lote para execução em massa.

                        // Reduzir o estoque do produto vendido na tabela 'produtos'.
                        int novoEstoque = p.getEstoque() - 1;
                        produtoDAO.atualizarEstoque(p.getId(), novoEstoque);
                    }
                    stItem.executeBatch(); // Executa todas as inserções na tabela 'venda_produtos' em lote.
                }
            }
            // Fecha o ResultSet para liberar recursos.
            DB.closeResultSet(rs);
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao registrar venda: " + e.getMessage());
        }
    }

    /**
     * Lista todas as vendas registradas no banco de dados, recuperando informações
     * do cliente associado a cada venda através de um JOIN com a tabela 'clientes'.
     * Também busca os produtos associados a cada venda utilizando o método {@link #buscarProdutosPorVenda(int)}.
     *
     * @return uma lista de objetos Venda contendo todas as vendas registradas, com seus respectivos
     * clientes e produtos. Retorna uma lista vazia se não houver vendas registradas.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação de listagem.
     */
    public List<Venda> listarTodas() {
        // Cria uma nova lista ArrayList para armazenar as vendas encontradas.
        List<Venda> lista = new ArrayList<>();
        // Define a string SQL para selecionar informações das tabelas 'vendas' (v) e 'clientes' (c)
        // através de um INNER JOIN na coluna 'cpf_cliente'.
        String sql = "SELECT v.id, v.data_venda, c.cpf, c.nome, c.telefone, c.endereco " +
                     "FROM vendas v INNER JOIN clientes c ON v.cpf_cliente = c.cpf";

        // Utiliza um bloco try-with-resources para garantir que a conexão (via Statement) e o ResultSet
        // sejam fechados automaticamente.
        try (Statement st = DB.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            // Itera sobre cada linha (venda) retornado pelo ResultSet.
            while (rs.next()) {
                // Cria um novo objeto Cliente com os dados recuperados do ResultSet.
                Cliente cliente = new Cliente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("endereco")
                );

                // Busca a lista de produtos associados à venda atual utilizando o ID da venda.
                List<Produto> produtos = buscarProdutosPorVenda(rs.getInt("id"));

                // Cria um novo objeto Venda com os dados recuperados.
                Venda venda = new Venda(
                        rs.getInt("id"),
                        cliente,
                        produtos,
                        rs.getDate("data_venda").toLocalDate()
                );
                lista.add(venda); // Adiciona o objeto Venda à lista.
            }
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao listar vendas: " + e.getMessage());
        }

        return lista; // Retorna a lista de todas as vendas registradas com seus clientes e produtos.
    }

    /**
     * Busca os produtos associados a uma venda específica no banco de dados,
     * utilizando o ID da venda para consultar a tabela de junção 'venda_produtos'.
     * Realiza um INNER JOIN com a tabela 'produtos' para obter os detalhes de cada produto.
     *
     * @param idVenda o ID da venda para a qual se deseja buscar os produtos.
     * @return uma lista de objetos Produto associados à venda especificada.
     * Retorna uma lista vazia se nenhum produto estiver associado à venda.
     * @throws DbException se ocorrer algum erro de acesso ao banco de dados durante a operação de busca.
     */
    private List<Produto> buscarProdutosPorVenda(int idVenda) {
        // Cria uma nova lista ArrayList para armazenar os produtos encontrados.
        List<Produto> lista = new ArrayList<>();
        // Define a string SQL para selecionar informações da tabela 'produtos' (p)
        // através de um INNER JOIN com a tabela de junção 'venda_produtos' (vp)
        // com base no ID do produto, filtrando pelo ID da venda.
        String sql = "SELECT p.id, p.nome, p.preco_compra, p.preco_venda, p.estoque " +
                     "FROM produtos p " +
                     "INNER JOIN venda_produtos vp ON p.id = vp.id_produto " +
                     "WHERE vp.id_venda = ?";

        // Utiliza um bloco try-with-resources para garantir que a conexão e o PreparedStatement
        // sejam fechados automaticamente.
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            // Define o valor do placeholder '?' com o ID da venda para buscar os produtos.
            st.setInt(1, idVenda);
            // Executa a consulta SQL e armazena o resultado em um ResultSet.
            ResultSet rs = st.executeQuery();
            // Itera sobre cada linha (produto) retornado pelo ResultSet.
            while (rs.next()) {
                // Cria um novo objeto Produto com os dados recuperados do ResultSet.
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco_compra"),
                        rs.getDouble("preco_venda"),
                        rs.getInt("estoque")
                );
                lista.add(p); // Adiciona o objeto Produto à lista.
            }
            // Fecha o ResultSet para liberar recursos.
            DB.closeResultSet(rs);
        } catch (SQLException e) {
            // Em caso de erro de SQL, lança uma DbException com uma mensagem informativa.
            throw new DbException("Erro ao buscar produtos da venda: " + e.getMessage());
        }
        return lista; // Retorna a lista de produtos associados à venda especificada.
    }
}