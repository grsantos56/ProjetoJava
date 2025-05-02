package dao;

import db.DB;
import db.DbException;
import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar operações de CRUD no banco de dados
 * para a entidade Produto, agora com preço de compra e venda.
 */
public class ProdutoDAO {

    /**
     * Insere um novo produto no banco de dados.
     */
    public void inserir(Produto produto) {
        String sql = "INSERT INTO produtos (nome, preco_compra, preco_venda, estoque, autor) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, produto.getNome());
            st.setDouble(2, produto.getPrecoCompra());
            st.setDouble(3, produto.getPrecoVenda());
            st.setInt(4, produto.getEstoque());
            st.setString(5, null); // autor nulo por padrão (usado apenas por Livro)
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                produto.setId(rs.getInt(1));
            }
            DB.closeResultSet(rs);
        } catch (SQLException e) {
            throw new DbException("Erro ao inserir produto: " + e.getMessage());
        }
    }

    /**
     * Busca um produto pelo ID.
     */
    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco_compra"),
                        rs.getDouble("preco_venda"),
                        rs.getInt("estoque")
                );
                DB.closeResultSet(rs);
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException("Erro ao buscar produto: " + e.getMessage());
        }
    }

    /**
     * Atualiza os dados de um produto existente.
     */
    public void atualizar(Produto produto) {
        String sql = "UPDATE produtos SET nome=?, preco_compra=?, preco_venda=?, estoque=? WHERE id=?";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setString(1, produto.getNome());
            st.setDouble(2, produto.getPrecoCompra());
            st.setDouble(3, produto.getPrecoVenda());
            st.setInt(4, produto.getEstoque());
            st.setInt(5, produto.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    /**
     * Deleta um produto do banco com base no ID.
     */
    public void deletar(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar produto: " + e.getMessage());
        }
    }

    /**
     * Retorna todos os produtos cadastrados.
     */
    public List<Produto> listarTodos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Statement st = DB.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco_compra"),
                        rs.getDouble("preco_venda"),
                        rs.getInt("estoque")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao listar produtos: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Atualiza o estoque de um produto específico.
     */
    public void atualizarEstoque(int idProduto, int novoEstoque) {
        String sql = "UPDATE produtos SET estoque = ? WHERE id = ?";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setInt(1, novoEstoque);
            st.setInt(2, idProduto);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar estoque: " + e.getMessage());
        }
    }
    
    /**
     * Atualiza apenas os preços de compra e venda de um produto.
     */
    public void atualizarPrecos(int idProduto, double novoPrecoCompra, double novoPrecoVenda) {
        String sql = "UPDATE produtos SET preco_compra = ?, preco_venda = ? WHERE id = ?";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setDouble(1, novoPrecoCompra);
            st.setDouble(2, novoPrecoVenda);
            st.setInt(3, idProduto);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar preços: " + e.getMessage());
        }
    }
}
