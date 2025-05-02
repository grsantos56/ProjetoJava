package dao;

import java.sql.*;
import java.util.*;
import db.DB;
import db.DbException;
import model.Cliente;

public class ClienteDAO {

    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO clientes (cpf, nome, telefone, endereco) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setString(1, cliente.getCpf());
            st.setString(2, cliente.getNome());
            st.setString(3, cliente.getTelefone());
            st.setString(4, cliente.getEndereco());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    public Cliente buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM clientes WHERE cpf = ?";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setString(1, cpf);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Cliente c = new Cliente(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("endereco")
                );
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException("Erro ao buscar cliente: " + e.getMessage());
        }
    }

    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Statement st = DB.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("endereco")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao listar clientes: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome=?, telefone=?, endereco=? WHERE cpf=?";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getTelefone());
            st.setString(3, cliente.getEndereco());
            st.setString(4, cliente.getCpf());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public void deletar(String cpf) {
        String sql = "DELETE FROM clientes WHERE cpf=?";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setString(1, cpf);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar cliente: " + e.getMessage());
        }
    }
}
