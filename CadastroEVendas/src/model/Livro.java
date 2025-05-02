package model;

/**
 * Classe Livro que herda da classe Produto.
 * Representa um produto específico do tipo livro, com um atributo adicional: autor.
 */
public class Livro extends Produto {

    // Atributo privado adicional da classe Livro
    private String autor;

    /**
     * Construtor padrão (sem parâmetros).
     * Necessário para instanciar objetos sem fornecer dados imediatamente.
     */
    public Livro() {
    }

    /**
     * Construtor completo com sobrecarga, que permite inicializar todos os dados do livro.
     *
     * @param id       ID do produto
     * @param nome     Nome do produto (livro)
     * @param preco    Preço do livro
     * @param estoque  Quantidade em estoque
     * @param autor    Nome do autor do livro
     */
    public Livro(Integer id, String nome, Double preco, Integer estoque, String autor) {
        super(id, nome, preco, estoque); // chama o construtor da superclasse Produto
        this.autor = autor;
    }

    // ------------------------------------------------------------------------------------
    // Getters e Setters
    // ------------------------------------------------------------------------------------

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    // ------------------------------------------------------------------------------------
    // Representação textual do objeto
    // ------------------------------------------------------------------------------------

    /**
     * Retorna uma string com as informações do livro.
     * Pode ser sobrescrita para incluir mais detalhes, se necessário.
     */
    @Override
    public String toString() {
        return super.toString() + ", autor=" + autor;
    }
}