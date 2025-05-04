package model;

import java.util.Objects;

/**
 * Classe Cliente representa uma pessoa que realiza compras na loja.
 * Contém dados pessoais como nome, CPF, telefone e endereço.
 */
public class Cliente {

    // Atributos privados respeitando o princípio do encapsulamento.
    // Isso significa que o acesso direto a esses atributos é controlado por meio de métodos (getters e setters).
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;

    // ------------------------------------------------------------------------------------
    // Construtores
    // ------------------------------------------------------------------------------------

    /**
     * Construtor padrão (vazio). Permite criar uma instância da classe Cliente
     * sem a necessidade de fornecer valores iniciais para seus atributos.
     * Este construtor é útil em situações onde os dados do cliente serão
     * definidos posteriormente, como ao ler informações de um arquivo ou banco de dados,
     * ou quando frameworks como o Hibernate precisam instanciar objetos sem argumentos.
     */
    public Cliente() {
    }

    /**
     * Construtor completo que permite a criação de um objeto Cliente já com seus
     * atributos principais inicializados. Este construtor recebe o nome, CPF,
     * telefone e endereço do cliente como parâmetros e utiliza os métodos setters
     * para atribuir esses valores aos atributos da classe. O uso dos setters aqui
     * garante que as validações definidas nesses métodos sejam aplicadas durante
     * a criação do objeto, mantendo a integridade dos dados.
     *
     * @param nome     Nome do cliente (com validação de mínimo 2 letras).
     * @param cpf      CPF do cliente (com validação de 11 dígitos).
     * @param telefone Telefone do cliente (com validação de 11 dígitos).
     * @param endereco Endereço do cliente (com validação de mínimo 5 caracteres).
     */
    public Cliente(String nome, String cpf, String telefone, String endereco) {
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
        setEndereco(endereco);
    }

    // ------------------------------------------------------------------------------------
    // Getters e Setters com validações
    // ------------------------------------------------------------------------------------

    /**
     * Retorna o nome do cliente. Este método permite acessar o valor do atributo 'nome'
     * de fora da classe, respeitando o encapsulamento.
     *
     * @return o nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente, aplicando uma validação para garantir que o nome
     * fornecido não seja nulo, não contenha apenas espaços em branco e possua
     * pelo menos 2 caracteres alfabéticos. Se a validação falhar, uma exceção
     * do tipo IllegalArgumentException é lançada, indicando um erro nos dados fornecidos.
     * O nome é armazenado em letras maiúsculas após a validação e remoção de espaços
     * em branco no início e no fim.
     *
     * @param nome o nome a ser definido para o cliente.
     * @throws IllegalArgumentException se o nome for nulo ou tiver menos de 2 letras.
     */
    public void setNome(String nome) {
        if (nome != null && nome.trim().length() >= 2) {
            this.nome = nome.trim().toUpperCase();
        } else {
            throw new IllegalArgumentException("O nome deve conter pelo menos 2 letras.");
        }
    }

    /**
     * Retorna o CPF do cliente. Este método permite acessar o valor do atributo 'cpf'
     * de fora da classe, respeitando o encapsulamento.
     *
     * @return o CPF do cliente.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do cliente, aplicando uma validação para garantir que o CPF
     * fornecido não seja nulo e consista exatamente em 11 dígitos numéricos.
     * A validação utiliza uma expressão regular para verificar o formato.
     * Se a validação falhar, uma exceção do tipo IllegalArgumentException é lançada.
     *
     * @param cpf o CPF a ser definido para o cliente.
     * @throws IllegalArgumentException se o CPF for nulo ou não tiver 11 dígitos numéricos.
     */
    public void setCpf(String cpf) {
        if (cpf != null && cpf.matches("\\d{11}")) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("O CPF deve conter exatamente 11 dígitos numéricos.");
        }
    }

    /**
     * Retorna o telefone do cliente. Este método permite acessar o valor do atributo 'telefone'
     * de fora da classe, respeitando o encapsulamento.
     *
     * @return o telefone do cliente.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do cliente, aplicando uma validação para garantir que o telefone
     * fornecido não seja nulo e consista exatamente em 11 dígitos numéricos.
     * A validação utiliza uma expressão regular para verificar o formato.
     * Se a validação falhar, uma exceção do tipo IllegalArgumentException é lançada.
     *
     * @param telefone o telefone a ser definido para o cliente.
     * @throws IllegalArgumentException se o telefone for nulo ou não tiver 11 dígitos numéricos.
     */
    public void setTelefone(String telefone) {
        if (telefone != null && telefone.matches("\\d{11}")) {
            this.telefone = telefone;
        } else {
            throw new IllegalArgumentException("O telefone deve conter exatamente 11 dígitos numéricos.");
        }
    }

    /**
     * Retorna o endereço do cliente. Este método permite acessar o valor do atributo 'endereco'
     * de fora da classe, respeitando o encapsulamento.
     *
     * @return o endereço do cliente.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço do cliente, aplicando uma validação para garantir que o endereço
     * fornecido não seja nulo, não contenha apenas espaços em branco e possua
     * pelo menos 5 caracteres. Se a validação falhar, uma exceção do tipo
     * IllegalArgumentException é lançada. O endereço é armazenado em letras maiúsculas
     * após a validação e remoção de espaços em branco no início e no fim.
     *
     * @param endereco o endereço a ser definido para o cliente.
     * @throws IllegalArgumentException se o endereço for nulo ou tiver menos de 5 caracteres.
     */
    public void setEndereco(String endereco) {
        if (endereco != null && endereco.trim().length() >= 5) {
            this.endereco = endereco.trim().toUpperCase();
        } else {
            throw new IllegalArgumentException("O endereço deve conter pelo menos 5 caracteres.");
        }
    }

    // ------------------------------------------------------------------------------------
    // HashCode e Equals — para garantir unicidade com base no CPF
    // ------------------------------------------------------------------------------------

    /**
     * Implementação do método hashCode para objetos da classe Cliente.
     * Este método é sobrescrito para garantir que objetos Cliente que são considerados
     * iguais pelo método equals() também possuam o mesmo valor de hashCode.
     * A unicidade dos objetos Cliente é baseada no atributo 'cpf', portanto,
     * o hashCode é gerado utilizando o hashCode do CPF.
     *
     * @return um valor de hash code para o objeto Cliente.
     */
    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    /**
     * Sobrescrita do método equals para comparar dois objetos Cliente.
     * Dois objetos Cliente são considerados iguais se e somente se o valor do
     * atributo 'cpf' de ambos os objetos for o mesmo. A comparação é feita utilizando
     * o método Objects.equals() para evitar NullPointerExceptions.
     *
     * @param obj outro objeto a ser comparado com esta instância de Cliente.
     * @return true se os CPFs dos dois objetos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Verifica se os objetos são a mesma instância na memória.
        if (obj == null || getClass() != obj.getClass()) return false; // Verifica se o objeto não é nulo e é da mesma classe.
        Cliente other = (Cliente) obj; // Faz um cast do objeto para a classe Cliente.
        return Objects.equals(cpf, other.cpf); // Compara os CPFs dos dois objetos.
    }

    // ------------------------------------------------------------------------------------
    // Representação textual
    // ------------------------------------------------------------------------------------

    /**
     * Sobrescrita do método toString para fornecer uma representação textual útil
     * do objeto Cliente. Este método retorna uma string formatada contendo o nome,
     * CPF e endereço do cliente. É útil para logs, depuração e exibição de informações
     * do cliente de forma legível.
     *
     * @return uma string representando o objeto Cliente.
     */
    @Override
    public String toString() {
        return "Cliente: nome=" + nome + ", cpf=" + cpf + " endereco=" + endereco;
    }
}
