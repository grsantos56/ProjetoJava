package model;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe que representa uma Venda realizada na loja.
 * Contém informações sobre o cliente, produtos vendidos, data e ID da venda.
 */
public class Venda {
    
    // Atributos privados para manter o princípio do encapsulamento
    private Integer id;
    private Cliente cliente;
    private List<Produto> produtos;
    private LocalDate dataVenda;

    // ------------------------------------------------------------------------------------
    // Construtores
    // ------------------------------------------------------------------------------------

    /**
     * Construtor padrão (vazio), permite criar o objeto sem inicializar atributos.
     * Útil para frameworks, serialização ou quando a construção for feita manualmente.
     */
    public Venda() {
    }

    /**
     * Construtor completo com parâmetros. Representa sobrecarga de construtores (polimorfismo).
     * 
     * @param id         Identificador da venda
     * @param cliente    Cliente que fez a compra
     * @param produtos   Lista de produtos comprados
     * @param dataVenda  Data em que a venda ocorreu
     */
    public Venda(Integer id, Cliente cliente, List<Produto> produtos, LocalDate dataVenda) {
        this.id = id;
        this.cliente = cliente;
        this.produtos = produtos;
        this.dataVenda = dataVenda;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    // ------------------------------------------------------------------------------------
    // Métodos úteis
    // ------------------------------------------------------------------------------------

    /**
     * Calcula o valor total da venda somando os preços dos produtos.
     * 
     * @return soma dos preços dos produtos
     */
    public double calcularTotal() {
        return produtos.stream()
                       .mapToDouble(Produto::getPreco) // pega o preço de cada produto
                       .sum(); // soma todos os preços
    }

    /**
     * Retorna uma String com a listagem dos produtos vendidos.
     * 
     * @return string formatada com informações dos produtos
     */
    public String listarProdutos() {
        StringBuilder sb = new StringBuilder();
        for (Produto p : produtos) {
            sb.append(p.toString()).append("\n"); // adiciona cada produto na string
        }
        return sb.toString();
    }

    // ------------------------------------------------------------------------------------
    // Representação textual do objeto
    // ------------------------------------------------------------------------------------

    /**
     * Retorna uma representação textual da venda para facilitar a visualização ou depuração.
     */
    @Override
    public String toString() {
        return "Venda: id=" + id + ", cliente=" + cliente + ", produtos=" + produtos;
    }
}

