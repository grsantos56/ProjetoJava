package controller;

import dao.VendedorDAO;

/**
 * Controlador responsável por intermediar as operações relacionadas à entidade Vendedor,
 * como autenticação, cadastro e recuperação de senha, entre a camada de apresentação
 * e a camada de acesso a dados (VendedorDAO).
 */
public class VendedorController {

    // Instância da classe VendedorDAO para realizar as operações de acesso aos dados do vendedor.
    private VendedorDAO dao = new VendedorDAO();

    /**
     * Método para autenticar um vendedor no sistema.
     * Chama o método 'autenticar' do DAO para verificar se as credenciais fornecidas
     * (nome de usuário e senha) correspondem a um vendedor cadastrado no banco de dados.
     *
     * @param usuario o nome de usuário do vendedor a ser autenticado.
     * @param senha   a senha do vendedor a ser autenticado.
     * @return {@code true} se a autenticação for bem-sucedida, {@code false} caso contrário.
     */
    public boolean autenticar(String usuario, String senha) {
        // Chama o método 'autenticar' do DAO para realizar a verificação das credenciais.
        return dao.autenticar(usuario, senha);
    }

    /**
     * Método para cadastrar um novo vendedor no sistema.
     * Chama o método 'cadastrar' do DAO para inserir um novo registro de vendedor
     * no banco de dados com o nome de usuário e a senha fornecidos.
     *
     * @param usuario o nome de usuário do novo vendedor a ser cadastrado.
     * @param senha   a senha do novo vendedor a ser cadastrado.
     * @return {@code true} se o cadastro for bem-sucedido, {@code false} em caso de falha.
     */
    public boolean cadastrar(String usuario, String senha) {
        // Chama o método 'cadastrar' do DAO para persistir os dados do novo vendedor.
        return dao.cadastrar(usuario, senha);
    }

    /**
     * Método para buscar e recuperar a senha de um vendedor com base no seu nome de usuário.
     * Chama o método 'recuperarSenha' do DAO para obter a senha do vendedor do banco de dados.
     *
     * @param usuario o nome de usuário do vendedor para o qual se deseja recuperar a senha.
     * @return a senha do vendedor correspondente ao nome de usuário, ou {@code null}
     * se nenhum vendedor com esse nome de usuário for encontrado.
     */
    public String buscarSenha(String usuario) {
        // Chama o método 'recuperarSenha' do DAO para obter a senha do vendedor.
        return dao.recuperarSenha(usuario);
    }
}