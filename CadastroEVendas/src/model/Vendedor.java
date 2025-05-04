package model;

/**
 * Classe que representa um Vendedor no sistema.
 * Contém os dados de identificação e credenciais de acesso para que um usuário
 * possa se autenticar como vendedor e realizar operações no sistema.
 */
public class Vendedor {

    // ------------------------------------------------------------------------------------
    // Atributos privados da classe
    // ------------------------------------------------------------------------------------

    /**
     * Identificador único do vendedor.
     * Este atributo geralmente corresponde à chave primária no banco de dados
     * e é usado para identificar exclusivamente cada vendedor no sistema.
     */
    private Integer id;

    /**
     * Nome de usuário (login) do vendedor.
     * Este atributo é utilizado como identificador durante o processo de
     * autenticação para que o vendedor possa acessar as funcionalidades do sistema.
     * Espera-se que seja único para cada vendedor.
     */
    private String usuario;

    /**
     * Senha associada ao nome de usuário do vendedor.
     * Esta credencial é utilizada em conjunto com o nome de usuário para
     * verificar a identidade do vendedor durante a autenticação, garantindo
     * o acesso seguro ao sistema. Recomenda-se que seja armazenada de forma
     * segura (e.g., utilizando hash).
     */
    private String senha;

    // ------------------------------------------------------------------------------------
    // Construtores
    // ------------------------------------------------------------------------------------

    /**
     * Construtor padrão sem argumentos.
     * Este construtor permite criar uma instância da classe Vendedor sem
     * inicializar seus atributos imediatamente. É útil em cenários onde
     * a instanciação é necessária, mas os dados serão fornecidos posteriormente,
     * como ao recuperar informações de um banco de dados ou utilizar frameworks
     * que exigem um construtor sem argumentos.
     */
    public Vendedor() {}

    /**
     * Construtor que inicializa o vendedor com nome de usuário e senha.
     * Este construtor é útil para criar rapidamente objetos Vendedor,
     * especialmente durante processos de login ou cadastro, onde essas
     * informações são primordiais. O ID pode ser definido posteriormente
     * ou gerado pelo sistema.
     *
     * @param usuario Nome de usuário do vendedor para acesso ao sistema.
     * @param senha   Senha do vendedor para autenticação.
     */
    public Vendedor(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    // ------------------------------------------------------------------------------------
    // Getters e Setters
    // ------------------------------------------------------------------------------------

    /**
     * Obtém o ID único do vendedor.
     * Este método permite acessar o valor do atributo 'id' de fora da classe,
     * respeitando o princípio do encapsulamento.
     *
     * @return ID do vendedor.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o ID único do vendedor.
     * Este método permite modificar o valor do atributo 'id' de fora da classe.
     *
     * @param id novo valor para o ID do vendedor.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtém o nome de usuário (login) do vendedor.
     * Este método permite acessar o valor do atributo 'usuario' de fora da classe.
     *
     * @return nome de usuário do vendedor.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define o nome de usuário (login) do vendedor.
     * Este método permite modificar o valor do atributo 'usuario' de fora da classe.
     *
     * @param usuario novo nome de usuário para o vendedor.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtém a senha do vendedor.
     * Este método permite acessar o valor do atributo 'senha' de fora da classe.
     * É importante considerar a segurança ao manipular a senha.
     *
     * @return senha do vendedor.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do vendedor.
     * Este método permite modificar o valor do atributo 'senha' de fora da classe.
     * Em aplicações reais, a senha geralmente seria armazenada de forma criptografada
     * (e.g., utilizando hash) antes de ser persistida.
     *
     * @param senha nova senha para o vendedor.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
}