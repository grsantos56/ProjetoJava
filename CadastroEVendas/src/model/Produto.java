package model;

import java.util.Objects;

/**
 * Classe Produto representa um item genérico vendido pela loja.
 * Contém informações essenciais como um identificador único (ID),
 * o nome do produto, o preço de compra (custo para a loja), o preço de venda
 * (preço para o cliente) e a quantidade atual em estoque.
 */
public class Produto {

    // Atributos privados para garantir o encapsulamento.
    // O acesso direto a esses atributos é controlado por meio de métodos (getters e setters).
    private Integer id;
    private String nome;
    private Double precoCompra;
    private Double precoVenda;
    private Integer estoque;

    // ------------------------------------------------------------------------------------
    // Construtores
    // ------------------------------------------------------------------------------------

    /**
     * Construtor padrão (vazio), que permite criar uma instância da classe Produto
     * sem a necessidade de fornecer valores iniciais para seus atributos.
     * Este construtor é útil em situações onde os dados do produto serão
     * definidos posteriormente, como ao ler informações de um banco de dados
     * ou ao construir o objeto passo a passo.
     */
    public Produto() {
    }

    /**
     * Construtor completo com todos os atributos do produto.
     * Permite a criação de um objeto Produto já com seus dados principais inicializados.
     * Utiliza o setter para o atributo 'estoque' para garantir que a validação seja aplicada
     * durante a criação do objeto.
     *
     * @param id          Identificador único do produto.
     * @param nome        Nome do produto.
     * @param precoCompra Preço pelo qual a loja adquire o produto.
     * @param precoVenda  Preço pelo qual o produto é vendido ao cliente.
     * @param estoque     Quantidade atual do produto em estoque.
     */
    public Produto(Integer id, String nome, Double precoCompra, Double precoVenda, Integer estoque) {
        this.id = id;
        this.nome = nome;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.setEstoque(estoque); // Usa o setter para garantir a validação do estoque.
    }

    // ------------------------------------------------------------------------------------
    // Getters e Setters
    // ------------------------------------------------------------------------------------

    /**
     * Retorna o ID do produto.
     * Este método permite acessar o valor do atributo 'id' de fora da classe.
     *
     * @return identificador único do produto.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o ID do produto.
     * Este método permite modificar o valor do atributo 'id' de fora da classe.
     *
     * @param id novo identificador do produto.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o nome do produto.
     * Este método permite acessar o valor do atributo 'nome' de fora da classe.
     *
     * @return nome do produto.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     * Este método permite modificar o valor do atributo 'nome' de fora da classe.
     *
     * @param nome novo nome do produto.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o preço de compra do produto (o custo para a loja).
     * Este método permite acessar o valor do atributo 'precoCompra' de fora da classe.
     *
     * @return preço de compra do produto.
     */
    public Double getPrecoCompra() {
        return precoCompra;
    }

    /**
     * Define o preço de compra do produto.
     * Este método permite modificar o valor do atributo 'precoCompra' de fora da classe.
     *
     * @param precoCompra novo preço de compra do produto.
     */
    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    /**
     * Retorna o preço de venda do produto (o preço para o cliente).
     * Este método permite acessar o valor do atributo 'precoVenda' de fora da classe.
     *
     * @return preço de venda do produto.
     */
    public Double getPrecoVenda() {
        return precoVenda;
    }

    /**
     * Define o preço de venda do produto.
     * Este método permite modificar o valor do atributo 'precoVenda' de fora da classe.
     *
     * @param precoVenda novo preço de venda do produto.
     */
    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    /**
     * Retorna a quantidade atual em estoque do produto.
     * Este método permite acessar o valor do atributo 'estoque' de fora da classe.
     *
     * @return quantidade de itens do produto em estoque.
     */
    public Integer getEstoque() {
        return estoque;
    }

    /**
     * Define a quantidade de itens em estoque do produto.
     * Aplica uma validação para garantir que o valor fornecido não seja negativo,
     * respeitando a lógica de que o estoque não pode ser uma quantidade negativa.
     * Se a validação falhar, uma exceção do tipo IllegalArgumentException é lançada.
     *
     * @param estoque nova quantidade em estoque do produto.
     * @throws IllegalArgumentException se o valor do estoque informado for negativo.
     */
    public void setEstoque(Integer estoque) {
        if (estoque >= 0) { // Garante que a quantidade em estoque não seja negativa.
            this.estoque = estoque;
        } else {
            throw new IllegalArgumentException("Estoque não pode ser negativo.");
        }
    }

    // ------------------------------------------------------------------------------------
    // HashCode e Equals — fundamentais para comparações e uso em coleções
    // ------------------------------------------------------------------------------------

    /**
     * Implementação do método hashCode para objetos da classe Produto.
     * Este método é sobrescrito para garantir que objetos Produto que são considerados
     * iguais pelo método equals() também possuam o mesmo valor de hashCode.
     * A unicidade dos objetos Produto é baseada no atributo 'id', portanto,
     * o hashCode é gerado utilizando o hashCode do ID.
     *
     * @return um valor de hash code para o objeto Produto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Sobrescrita do método equals para comparar dois objetos Produto.
     * Dois objetos Produto são considerados iguais se e somente se o valor do
     * atributo 'id' de ambos os objetos for o mesmo. A comparação é feita utilizando
     * o método Objects.equals() para evitar NullPointerExceptions.
     *
     * @param obj outro objeto a ser comparado com esta instância de Produto.
     * @return true se os IDs dos dois produtos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Verifica se os objetos são a mesma instância na memória.
        if (obj == null || getClass() != obj.getClass()) return false; // Verifica se o objeto não é nulo e é da mesma classe.
        Produto other = (Produto) obj; // Faz um cast do objeto para a classe Produto.
        return Objects.equals(id, other.id); // Compara os IDs dos dois produtos.
    }

    // ------------------------------------------------------------------------------------
    // Representação textual
    // ------------------------------------------------------------------------------------

    /**
     * Sobrescrita do método toString para fornecer uma representação textual útil
     * do objeto Produto. Este método retorna uma string formatada contendo o nome
     * e o preço de venda do produto. É útil para logs, depuração e exibição de informações
     * do produto de forma concisa.
     *
     * @return uma string representando o nome e o preço de venda do produto.
     */
    @Override
    public String toString() {
        return "Produto: nome=" + nome + ", preco=R$" + precoVenda;
    }
}