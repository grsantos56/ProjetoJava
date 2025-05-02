package model;

import java.util.Objects;

/**
 * Classe Cliente representa uma pessoa que realiza compras na loja.
 * Contém dados pessoais como nome, CPF, telefone e endereço.
 */
public class Cliente {

    // Atributos privados respeitando o princípio do encapsulamento
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;

    // ------------------------------------------------------------------------------------
    // Construtores
    // ------------------------------------------------------------------------------------

    /**
     * Construtor padrão (vazio). Permite criar um cliente sem inicializar os campos de imediato.
     * Útil para frameworks, leitura de dados ou quando os valores forem definidos depois.
     */
    public Cliente() {
    }

    /**
     * Construtor completo com validação de todos os atributos via setters.
     *
     * @param nome     Nome do cliente (mínimo 2 letras)
     * @param cpf      CPF do cliente (11 dígitos)
     * @param telefone Telefone do cliente (11 dígitos)
     * @param endereco Endereço do cliente (mínimo 5 caracteres)
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

    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente, garantindo que tenha pelo menos 2 letras.
     *
     * @param nome nome a ser definido
     */
    public void setNome(String nome) {
        if (nome != null && nome.trim().length() >= 2) {
            this.nome = nome.trim();
        } else {
            throw new IllegalArgumentException("O nome deve conter pelo menos 2 letras.");
        }
    }

    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do cliente, exigindo exatamente 11 dígitos numéricos.
     *
     * @param cpf CPF a ser definido
     */
    public void setCpf(String cpf) {
        if (cpf != null && cpf.matches("\\d{11}")) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("O CPF deve conter exatamente 11 dígitos numéricos.");
        }
    }

    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do cliente, exigindo exatamente 11 dígitos numéricos.
     *
     * @param telefone telefone a ser definido
     */
    public void setTelefone(String telefone) {
        if (telefone != null && telefone.matches("\\d{11}")) {
            this.telefone = telefone;
        } else {
            throw new IllegalArgumentException("O telefone deve conter exatamente 11 dígitos numéricos.");
        }
    }

    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço do cliente, exigindo pelo menos 6 caracteres.
     *
     * @param endereco endereço a ser definido
     */
    public void setEndereco(String endereco) {
        if (endereco != null && endereco.trim().length() >= 5) {
            this.endereco = endereco.trim();
        } else {
            throw new IllegalArgumentException("O endereço deve conter pelo menos 5 caracteres.");
        }
    }

    // ------------------------------------------------------------------------------------
    // HashCode e Equals — para garantir unicidade com base no CPF
    // ------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    /**
     * Dois clientes são considerados iguais se tiverem o mesmo CPF.
     *
     * @param obj outro objeto a ser comparado
     * @return true se os CPFs forem iguais
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente other = (Cliente) obj;
        return Objects.equals(cpf, other.cpf);
    }

    // ------------------------------------------------------------------------------------
    // Representação textual
    // ------------------------------------------------------------------------------------

    /**
     * Retorna uma string com as principais informações do cliente.
     */
    @Override
    public String toString() {
        return "Cliente: nome=" + nome + ", cpf=" + cpf;
    }
}
