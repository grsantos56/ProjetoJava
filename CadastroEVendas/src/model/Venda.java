package model;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe que representa uma Venda realizada na loja.
 * Contém informações sobre o cliente que realizou a compra, a lista de produtos
 * que foram vendidos, a data em que a venda ocorreu e um identificador único para a venda.
 */
public class Venda {

    // Atributos privados para manter o princípio do encapsulamento.
    // O acesso a esses atributos é controlado por meio de métodos getters e setters.

    /** Identificador único da venda. Geralmente gerado pelo sistema ou banco de dados. */
    private Integer id;

    /** Objeto Cliente que representa a pessoa que realizou a compra. */
    private Cliente cliente;

    /** Lista de objetos Produto que foram comprados nesta venda. */
    private List<Produto> produtos;

    /** Objeto LocalDate que representa a data em que a venda foi realizada. */
    private LocalDate dataVenda;

    // ------------------------------------------------------------------------------------
    // Construtores
    // ------------------------------------------------------------------------------------

    /**
     * Construtor padrão (vazio), permite criar uma instância da classe Venda
     * sem a necessidade de fornecer valores iniciais para seus atributos.
     * Este construtor é útil em situações onde os dados da venda serão
     * definidos posteriormente, como ao ler informações de um banco de dados
     * ou ao construir o objeto passo a passo.
     */
    public Venda() {
    }

    /**
     * Construtor completo com parâmetros. Representa sobrecarga de construtores (polimorfismo),
     * permitindo a criação de um objeto Venda já com seus atributos principais inicializados.
     *
     * @param id        Identificador único da venda.
     * @param cliente   Objeto Cliente que realizou a compra.
     * @param produtos  Lista de objetos Produto que foram comprados.
     * @param dataVenda Objeto LocalDate que representa a data da venda.
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

    /**
     * Obtém o identificador único da venda.
     * Este método permite acessar o valor do atributo 'id' de fora da classe.
     *
     * @return ID da venda.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador único da venda.
     * Este método permite modificar o valor do atributo 'id' de fora da classe.
     *
     * @param id novo ID da venda.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o cliente associado a esta venda.
     * Este método permite acessar o objeto Cliente que realizou a compra.
     *
     * @return cliente que realizou a compra.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente que realizou a venda.
     * Este método permite associar um objeto Cliente a esta venda.
     *
     * @param cliente cliente que será associado à venda.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Retorna a lista de produtos vendidos nesta venda.
     * Este método permite acessar a coleção de objetos Produto que foram comprados.
     *
     * @return lista de produtos desta venda.
     */
    public List<Produto> getProdutos() {
        return produtos;
    }

    /**
     * Define a lista de produtos vendidos.
     * Este método permite substituir a lista de produtos associada a esta venda.
     *
     * @param produtos nova lista de produtos que compõem a venda.
     */
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    /**
     * Retorna a data em que a venda foi realizada.
     * Este método permite acessar o objeto LocalDate que representa a data da venda.
     *
     * @return data da venda.
     */
    public LocalDate getDataVenda() {
        return dataVenda;
    }

    /**
     * Define a data da venda.
     * Este método permite modificar a data em que a venda foi registrada.
     *
     * @param dataVenda nova data para a venda.
     */
    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    // ------------------------------------------------------------------------------------
    // Métodos úteis
    // ------------------------------------------------------------------------------------

    /**
     * Calcula o valor total da venda somando o preço de venda de todos os produtos
     * presentes na lista de produtos desta venda. Utiliza a API de Streams do Java
     * para realizar essa operação de forma concisa e funcional.
     *
     * @return a soma dos preços de venda de todos os produtos da venda.
     */
    public double calcularTotal() {
        return produtos.stream()
                       .mapToDouble(Produto::getPrecoVenda) // Transforma cada Produto em seu preço de venda (Double).
                       .sum(); // Soma todos os preços de venda resultantes.
    }

    /**
     * Retorna uma String formatada contendo a descrição de cada produto vendido
     * nesta venda. Utiliza um StringBuilder para construir a string de forma eficiente,
     * iterando sobre a lista de produtos e adicionando a representação textual de cada um
     * (obtida através do método toString da classe Produto) seguida de uma nova linha.
     *
     * @return uma string formatada com as informações de cada produto vendido.
     */
    public String listarProdutos() {
        StringBuilder sb = new StringBuilder();
        for (Produto p : produtos) {
            sb.append(p.toString()).append("\n"); // Adiciona a representação textual do produto e uma nova linha.
        }
        return sb.toString();
    }

    // ------------------------------------------------------------------------------------
    // Representação textual do objeto
    // ------------------------------------------------------------------------------------

    /**
     * Retorna uma representação textual da venda para facilitar a visualização ou depuração.
     * Este método exibe o ID da venda, o cliente associado e a lista de produtos vendidos.
     * A representação dos produtos dependerá da implementação do método toString na classe Produto.
     * A anotação @Override indica que este método está sobrescrevendo o método toString da classe Object.
     *
     * @return uma string contendo informações básicas sobre a venda.
     */
    @Override
    public String toString() {
        return "Venda: id=" + id + ", cliente=" + cliente + ", produtos=" + produtos;
    }
}