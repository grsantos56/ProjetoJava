package model;

import java.util.Objects;

/**
 * Classe Produto representa um item genérico vendido pela loja.
 * Contém informações como ID, nome, preço e estoque.
 */
public class Produto {

    // Atributos privados respeitando o encapsulamento
    private Integer id;
    private String nome;
    private Double precoCompra;
    private Double precoVenda;
    private Integer estoque;

    // ------------------------------------------------------------------------------------
    // Construtores
    // ------------------------------------------------------------------------------------

    /**
     * Construtor padrão (vazio), necessário para frameworks ou instâncias parciais.
     */
    public Produto() {
    }

    /**
     * Construtor completo com todos os atributos do produto.
     *
     * @param id       Identificador do produto
     * @param nome     Nome do produto
     * @param preco    Preço do produto
     * @param estoque  Quantidade em estoque
     */
    public Produto(Integer id, String nome, Double precoCompra, Double precoVenda, Integer estoque) {
        this.id = id;
        this.nome = nome;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.setEstoque(estoque); // usa o setter para aplicar a validação
    }

    // ------------------------------------------------------------------------------------
    // Getters e Setters
    // ------------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }
    
    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Integer getEstoque() {
        return estoque;
    }

    /**
     * Define o valor do estoque, com validação para garantir que não seja negativo.
     *
     * @param estoque quantidade em estoque
     */
    public void setEstoque(Integer estoque) {
        if (estoque >= 0) {
            this.estoque = estoque;
        } else {
            throw new IllegalArgumentException("Estoque não pode ser negativo.");
        }
    }

    // ------------------------------------------------------------------------------------
    // HashCode e Equals — fundamentais para comparações e uso em coleções
    // ------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Dois produtos são considerados iguais se possuem o mesmo ID.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Produto other = (Produto) obj;
        return Objects.equals(id, other.id);
    }

    // ------------------------------------------------------------------------------------
    // Representação textual
    // ------------------------------------------------------------------------------------

    /**
     * Retorna uma string com os dados principais do produto.
     */
    @Override
    public String toString() {
        return "Produto: id=" + id + ", nome=" + nome + ", preco de compra=R$" + precoCompra + ", preco de venda=R$" + precoVenda + ", estoque=" + estoque;
    }
}