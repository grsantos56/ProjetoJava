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
 * Classe responsável por salvar e consultar vendas no banco de dados.
 */
public class VendaDAO {

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    /**
     * Registra uma nova venda com os produtos associados.
     */
    public void registrarVenda(Venda venda) {
        String sqlVenda = "INSERT INTO vendas (cpf_cliente, data_venda) VALUES (?, ?)";
        try (PreparedStatement stVenda = DB.getConnection().prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS)) {
            stVenda.setString(1, venda.getCliente().getCpf());
            stVenda.setDate(2, Date.valueOf(venda.getDataVenda()));
            stVenda.executeUpdate();

            ResultSet rs = stVenda.getGeneratedKeys();
            if (rs.next()) {
                int idVenda = rs.getInt(1);
                venda.setId(idVenda);

                // Inserir os produtos associados
                String sqlItem = "INSERT INTO venda_produtos (id_venda, id_produto, quantidade) VALUES (?, ?, ?)";
                try (PreparedStatement stItem = DB.getConnection().prepareStatement(sqlItem)) {
                    for (Produto p : venda.getProdutos()) {
                        stItem.setInt(1, idVenda);
                        stItem.setInt(2, p.getId());
                        stItem.setInt(3, 1); // quantidade padrão 1 (pode ser ajustado)
                        stItem.addBatch();

                        // Reduzir estoque
                        int novoEstoque = p.getEstoque() - 1;
                        produtoDAO.atualizarEstoque(p.getId(), novoEstoque);
                    }
                    stItem.executeBatch();
                }
            }
            DB.closeResultSet(rs);
        } catch (SQLException e) {
            throw new DbException("Erro ao registrar venda: " + e.getMessage());
        }
    }

    /**
     * Lista todas as vendas registradas.
     */
    public List<Venda> listarTodas() {
        List<Venda> lista = new ArrayList<>();
        String sql = "SELECT v.id, v.data_venda, c.cpf, c.nome, c.telefone, c.endereco " +
                     "FROM vendas v INNER JOIN clientes c ON v.cpf_cliente = c.cpf";

        try (Statement st = DB.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("endereco")
                );

                List<Produto> produtos = buscarProdutosPorVenda(rs.getInt("id"));

                Venda venda = new Venda(
                        rs.getInt("id"),
                        cliente,
                        produtos,
                        rs.getDate("data_venda").toLocalDate()
                );
                lista.add(venda);
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao listar vendas: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Busca os produtos de uma venda específica.
     */
    private List<Produto> buscarProdutosPorVenda(int idVenda) {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT p.id, p.nome, p.preco, p.estoque " +
                     "FROM produtos p " +
                     "INNER JOIN venda_produtos vp ON p.id = vp.id_produto " +
                     "WHERE vp.id_venda = ?";

        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setInt(1, idVenda);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("precoCompra"),
                        rs.getDouble("precoVenda"),
                        rs.getInt("estoque")
                );
                lista.add(p);
            }
            DB.closeResultSet(rs);
        } catch (SQLException e) {
            throw new DbException("Erro ao buscar produtos da venda: " + e.getMessage());
        }
        return lista;
    }
}
