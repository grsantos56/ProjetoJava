package controller;

import dao.ClienteDAO;
import model.Cliente;

import java.util.List;

/**
 * Camada intermediária entre a interface gráfica (ou outra camada de apresentação)
 * e o banco de dados para operações relacionadas à entidade Cliente.
 * Esta classe contém a lógica de controle e delega as operações de persistência
 * para a classe ClienteDAO.
 */
public class ClienteController {

    // Instância da classe ClienteDAO para realizar as operações de acesso aos dados do cliente.
    private ClienteDAO dao = new ClienteDAO();

    /**
     * Método para cadastrar um novo cliente no sistema.
     * Cria um objeto Cliente com os dados fornecidos e o envia para a camada de acesso a dados (DAO)
     * para ser persistido no banco de dados.
     *
     * @param nome     o nome do cliente a ser cadastrado.
     * @param cpf      o CPF do cliente a ser cadastrado.
     * @param telefone o telefone do cliente a ser cadastrado.
     * @param endereco o endereço do cliente a ser cadastrado.
     */
    public void cadastrarCliente(String nome, String cpf, String telefone, String endereco) {
        // Cria um novo objeto Cliente com os dados fornecidos. O ID é geralmente gerado pelo banco.
        Cliente cliente = new Cliente(nome, cpf, telefone, endereco);
        // Chama o método 'inserir' do DAO para persistir o novo cliente no banco de dados.
        dao.inserir(cliente);
    }

    /**
     * Método para buscar um cliente no sistema através do seu CPF.
     * Chama o método 'buscarPorCpf' do DAO para recuperar o cliente do banco de dados.
     *
     * @param cpf o CPF do cliente a ser buscado.
     * @return o objeto Cliente correspondente ao CPF fornecido, ou null se não encontrado.
     */
    public Cliente buscarCliente(String cpf) {
        // Chama o método 'buscarPorCpf' do DAO para buscar o cliente pelo CPF.
        return dao.buscarPorCpf(cpf);
    }

    /**
     * Método para atualizar os dados de um cliente existente no sistema.
     * Recebe um objeto Cliente com os dados atualizados e o envia para a camada de acesso a dados (DAO)
     * para ser atualizado no banco de dados.
     *
     * @param cliente o objeto Cliente com os dados atualizados. O CPF é geralmente usado para identificar
     * o cliente a ser atualizado.
     */
    public void atualizarCliente(Cliente cliente) {
        // Chama o método 'atualizar' do DAO para persistir as alterações do cliente no banco de dados.
        dao.atualizar(cliente);
    }

    /**
     * Método para excluir um cliente do sistema através do seu CPF.
     * Chama o método 'deletar' do DAO para remover o cliente do banco de dados.
     *
     * @param cpf o CPF do cliente a ser excluído.
     */
    public void excluirCliente(String cpf) {
        // Chama o método 'deletar' do DAO para remover o cliente pelo CPF.
        dao.deletar(cpf);
    }

    /**
     * Método para listar todos os clientes cadastrados no sistema.
     * Chama o método 'listarTodos' do DAO para obter uma lista de todos os clientes do banco de dados.
     *
     * @return uma lista de todos os objetos Cliente cadastrados no sistema.
     */
    public List<Cliente> listarClientes() {
        // Chama o método 'listarTodos' do DAO para obter a lista de todos os clientes.
        return dao.listarTodos();
    }
}