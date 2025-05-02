package controller;

import dao.ClienteDAO;
import model.Cliente;

import java.util.List;

/**
 * Camada intermediária entre a interface gráfica e o banco de dados
 * para operações relacionadas a clientes.
 */
public class ClienteController {

    private ClienteDAO dao = new ClienteDAO();

    public void cadastrarCliente(String nome, String cpf, String telefone, String endereco) {
        Cliente cliente = new Cliente(nome, cpf, telefone, endereco);
        dao.inserir(cliente);
    }

    public Cliente buscarCliente(String cpf) {
        return dao.buscarPorCpf(cpf);
    }

    public void atualizarCliente(Cliente cliente) {
        dao.atualizar(cliente);
    }

    public void excluirCliente(String cpf) {
        dao.deletar(cpf);
    }

    public List<Cliente> listarClientes() {
        return dao.listarTodos();
    }
}

