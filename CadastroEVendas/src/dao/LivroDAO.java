package dao;

import db.DB;
import db.DbException;
import model.Livro;
import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    /**
     * Insere um novo livro no banco de dados.
     */
	public void inserir(Produto p) {
	    String sql = "INSERT INTO produto (nome, precoCompra, precoVenda, estoque) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement st = DB.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        st.setString(1, p.getNome());
	        st.setDouble(2, p.getPrecoCompra());
	        st.setDouble(3, p.getPrecoVenda());
	        st.setInt(4, p.getEstoque());

	        st.executeUpdate();

	        // Recuperar o ID gerado
	        ResultSet rs = st.getGeneratedKeys();
	        if (rs.next()) {
	            p.setId(rs.getInt(1));
	        }

	        // Verifica se é livro
	        if (p instanceof Livro) {
	            Livro l = (Livro) p;
	            String sqlLivro = "INSERT INTO livro (id, autor) VALUES (?, ?)";
	            try (PreparedStatement stLivro = DB.getConnection().prepareStatement(sqlLivro)) {
	                stLivro.setInt(1, l.getId());
	                stLivro.setString(2, l.getAutor());
	                stLivro.executeUpdate();
	            }
	        }

	    } catch (SQLException e) {
	        throw new DbException("Erro ao inserir produto: " + e.getMessage());
	    }
	}

    /**
     * Busca um livro pelo ID.
     */
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

    /**
     * Atualiza os dados de um livro.
     */
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

    /**
     * Deleta (logicamente) um livro.
     */
    public void deletar(int id) {
        String sql = "UPDATE produtos SET ativo = FALSE WHERE id = ?";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar livro: " + e.getMessage());
        }
    }

    /**
     * Lista todos os livros ativos.
     */
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
