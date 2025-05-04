package db;

/**
 * Classe de exceção personalizada para erros relacionados à violação de integridade
 * no banco de dados. Herda de RuntimeException, indicando que não precisa ser
 * explicitamente declarada em cláusulas 'throws'. Essa exceção é útil para sinalizar
 * situações onde uma operação tentou violar regras de integridade definidas no
 * esquema do banco de dados, como restrições de chave estrangeira, unicidade,
 * ou outras constraints.
 */
public class DBIntregatyException extends RuntimeException {

    // Define um ID de serialização explícito para a classe. Isso é recomendado
    // para garantir a compatibilidade entre diferentes versões da classe se
    // instâncias dela forem serializadas e desserializadas.
    private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe DBIntregatyException que aceita uma mensagem detalhada
     * sobre a violação de integridade ocorrida. Essa mensagem é passada para o
     * construtor da superclasse RuntimeException e pode ser usada para fornecer
     * informações específicas sobre a causa da exceção (por exemplo, qual restrição
     * foi violada).
     *
     * @param msg uma string descrevendo a violação de integridade do banco de dados.
     */
    public DBIntregatyException(String msg) {
        super(msg); // Chama o construtor da superclasse RuntimeException com a mensagem.
    }
}