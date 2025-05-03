package dao;

import db.DB;
import db.DbException;
import model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void inserir(Livro livro) {
        String sql = "INSERT INTO produtos (nome, preco_compra, preco_venda, estoque, autor) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, livro.getNome());
            st.setDouble(2, livro.getPrecoCompra());
            st.setDouble(3, livro.getPrecoVenda());
            st.setInt(4, livro.getEstoque());
            st.setString(5, livro.getAutor());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                livro.setId(rs.getInt(1));
            }
            DB.closeResultSet(rs);
        } catch (SQLException e) {
            throw new DbException("Erro ao inserir livro: " + e.getMessage());
        }
    }

    public Livro buscarPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ? AND autor IS NOT NULL AND ativo = TRUE";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Livro livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco_compra"),
                        rs.getDouble("preco_venda"),
                        rs.getInt("estoque"),
                        rs.getString("autor")
                );
                DB.closeResultSet(rs);
                return livro;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException("Erro ao buscar livro: " + e.getMessage());
        }
    }

    public void atualizar(Livro livro) {
        String sql = "UPDATE produtos SET nome = ?, preco_compra = ?, preco_venda = ?, estoque = ?, autor = ? WHERE id = ?";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setString(1, livro.getNome());
            st.setDouble(2, livro.getPrecoCompra());
            st.setDouble(3, livro.getPrecoVenda());
            st.setInt(4, livro.getEstoque());
            st.setString(5, livro.getAutor());
            st.setInt(6, livro.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "UPDATE produtos SET ativo = FALSE WHERE id = ?";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar livro: " + e.getMessage());
        }
    }

    public List<Livro> listar() {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE ativo = TRUE AND autor IS NOT NULL ORDER BY nome ASC";
        try (Statement st = DB.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco_compra"),
                        rs.getDouble("preco_venda"),
                        rs.getInt("estoque"),
                        rs.getString("autor")
                );
                lista.add(livro);
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao listar livros: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarEstoque(int idLivro, int novoEstoque) {
        String sql = "UPDATE produtos SET estoque = ? WHERE id = ?";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setInt(1, novoEstoque);
            st.setInt(2, idLivro);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar estoque: " + e.getMessage());
        }
    }

    public void atualizarPrecos(int idLivro, double novoPrecoCompra, double novoPrecoVenda) {
        String sql = "UPDATE produtos SET preco_compra = ?, preco_venda = ? WHERE id = ?";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setDouble(1, novoPrecoCompra);
            st.setDouble(2, novoPrecoVenda);
            st.setInt(3, idLivro);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar preços: " + e.getMessage());
        }
    }
}
