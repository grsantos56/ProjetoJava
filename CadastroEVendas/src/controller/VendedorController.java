package controller;

import dao.VendedorDAO;

public class VendedorController {

    private VendedorDAO dao = new VendedorDAO();

    public boolean autenticar(String usuario, String senha) {
        return dao.autenticar(usuario, senha);
    }

    public boolean cadastrar(String usuario, String senha) {
        return dao.cadastrar(usuario, senha);
    }

    public String buscarSenha(String usuario) {
        return dao.recuperarSenha(usuario);
    }
}
