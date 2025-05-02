package dao;

import db.DB;
import db.DbException;
import java.sql.*;

public class VendedorDAO {

    public boolean autenticar(String usuario, String senha) {
        String sql = "SELECT * FROM vendedor WHERE usuario = ? AND senha = ?";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setString(1, usuario);
            st.setString(2, senha);
            ResultSet rs = st.executeQuery();
            boolean autenticado = rs.next();
            rs.close();
            return autenticado;
        } catch (SQLException e) {
            throw new DbException("Erro ao autenticar: " + e.getMessage());
        }
    }
    
    public boolean cadastrar(String usuario, String senha) {
        String sql = "INSERT INTO vendedor (usuario, senha) VALUES (?, ?)";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setString(1, usuario);
            st.setString(2, senha);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public String recuperarSenha(String usuario) {
        String sql = "SELECT senha FROM vendedor WHERE usuario = ?";
        try (PreparedStatement st = DB.getConnection().prepareStatement(sql)) {
            st.setString(1, usuario);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("senha");
            }
            return null;
        } catch (SQLException e) {
            throw new DbException("Erro ao recuperar senha: " + e.getMessage());
        }
    }
}

