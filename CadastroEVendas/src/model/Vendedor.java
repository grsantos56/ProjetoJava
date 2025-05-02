package model;

public class Vendedor {
    private Integer id;
    private String usuario;
    private String senha;

    public Vendedor() {}

    public Vendedor(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public Integer getId() { 
    	return id; 
    }
    public void setId(Integer id) { 
    	this.id = id; 
    }

    public String getUsuario() { 
    	return usuario; 
    }
    public void setUsuario(String usuario) { 
    	this.usuario = usuario;
    }

    public String getSenha() { 
    	return senha; 
    }
    public void setSenha(String senha) {
    	this.senha = senha; 
    }
}

