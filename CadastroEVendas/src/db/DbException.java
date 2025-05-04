package db;

/**
 * Classe de exceção personalizada para erros relacionados ao acesso e manipulação
 * do banco de dados. Herda de RuntimeException, o que significa que não é obrigatório
 * declará-la em cláusulas 'throws' dos métodos, embora seja uma boa prática documentar
 * quando essa exceção pode ser lançada. O objetivo desta classe é fornecer um tipo
 * de exceção específico para a camada de acesso a dados (DAO) da aplicação,
 * facilitando a identificação e o tratamento de erros de banco de dados em camadas superiores.
 */
public class DbException extends RuntimeException {

    // Versão da classe para controle de serialização. É uma boa prática definir
    // explicitamente para evitar problemas de compatibilidade entre diferentes
    // versões da classe caso ela seja serializada.
    private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe DbException que recebe uma mensagem detalhada sobre o erro
     * ocorrido. Esta mensagem é passada para o construtor da superclasse RuntimeException
     * e pode ser acessada posteriormente para obter informações sobre a causa da exceção.
     *
     * @param msg uma string contendo a descrição do erro de banco de dados.
     */
    public DbException(String msg) {
        super(msg); // Chama o construtor da superclasse RuntimeException com a mensagem de erro.
    }
}