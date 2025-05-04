package model;

/**
 * Classe Livro que herda da classe Produto.
 * Representa um produto específico do tipo livro, com um atributo adicional: autor.
 */
public class Livro extends Produto {

    // Atributo privado adicional da classe Livro para armazenar o nome do autor.
    private String autor;

    /**
     * Construtor padrão (sem parâmetros).
     * Este construtor permite criar uma instância da classe Livro sem fornecer
     * valores iniciais para seus atributos. É útil em situações onde os dados
     * serão definidos posteriormente ou quando frameworks precisam instanciar objetos.
     * Ele também chama o construtor padrão da superclasse Produto implicitamente.
     */
    public Livro() {
        super(); // Chama o construtor sem argumentos da classe Produto
    }

    /**
     * Construtor completo com sobrecarga, que permite inicializar todos os dados do livro,
     * incluindo os atributos herdados da classe Produto e o atributo específico 'autor'.
     * Este construtor chama explicitamente o construtor da superclasse Produto utilizando
     * a palavra-chave 'super' para inicializar os atributos comuns (id, nome, precoCompra,
     * precoVenda, estoque) e, em seguida, inicializa o atributo 'autor' específico da classe Livro.
     *
     * @param id          ID do produto (herdado de Produto).
     * @param nome        Nome do produto (livro) (herdado de Produto).
     * @param precoCompra Preço de compra do livro (herdado de Produto).
     * @param precoVenda  Preço de venda do livro (herdado de Produto).
     * @param estoque     Quantidade em estoque do livro (herdado de Produto).
     * @param autor       Nome do autor do livro.
     */
    public Livro(Integer id, String nome, Double precoCompra, Double precoVenda, Integer estoque, String autor) {
        super(id, nome, precoCompra, precoVenda, estoque); // Chama o construtor da superclasse Produto para inicializar os atributos herdados.
        this.autor = autor; // Inicializa o atributo específico da classe Livro.
    }

    // ------------------------------------------------------------------------------------
    // Getters e Setters
    // ------------------------------------------------------------------------------------

    /**
     * Retorna o nome do autor do produto, caso o produto seja um livro.
     * Este método permite acessar o valor do atributo 'autor' de fora da classe,
     * respeitando o princípio do encapsulamento.
     *
     * @return nome do autor do livro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Define o nome do autor do produto.
     * Esse campo é relevante apenas quando o produto for um livro.
     * Este método permite modificar o valor do atributo 'autor' de fora da classe.
     *
     * @param autor nome do autor a ser atribuído ao livro.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    // ------------------------------------------------------------------------------------
    // Representação textual do objeto
    // ------------------------------------------------------------------------------------

    /**
     * Sobrescrita do método toString da classe Produto para fornecer uma representação
     * textual mais completa do objeto Livro. Além das informações básicas do produto
     * (ID, nome, preços, estoque), este método também inclui o nome do autor do livro.
     * A anotação @Override indica que este método está substituindo um método da superclasse.
     *
     * @return uma string formatada contendo todas as informações relevantes do livro.
     */
    @Override
    public String toString() {
        return "Livro [ID=" + getId() +
               ", Nome=" + getNome() +
               ", Preço Compra=" + getPrecoCompra() +
               ", Preço Venda=" + getPrecoVenda() +
               ", Estoque=" + getEstoque() +
               ", Autor=" + getAutor() + "]";
    }
}