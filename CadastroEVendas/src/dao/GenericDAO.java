package dao;

import java.util.List;

/**
 * Interface genérica para operações básicas de persistência (CRUD - Create, Read, Update, Delete).
 * <br>
 * Define métodos comuns que devem ser implementados por qualquer DAO (Data Access Object) específico,
 * garantindo consistência e reutilização de código para diferentes entidades do sistema.
 * <br>
 * O uso de Generics (<T>) permite que esta interface seja utilizada com qualquer tipo de objeto
 * que necessite ser persistido no banco de dados.
 *
 * @param <T> o tipo de objeto que será manipulado (por exemplo: Produto, Cliente, Livro, etc.)
 */
public interface GenericDAO<T> {

    /**
     * Insere um novo objeto do tipo T no banco de dados.
     * Este método é responsável por persistir os dados do objeto na tabela correspondente.
     *
     * @param obj o objeto do tipo T a ser inserido. Os atributos do objeto contêm os valores
     * que serão gravados nas colunas da tabela.
     */
    void inserir(T obj);

    /**
     * Atualiza os dados de um objeto do tipo T já existente no banco de dados.
     * A identificação do objeto a ser atualizado geralmente é feita através de um ID presente no objeto.
     *
     * @param obj o objeto do tipo T com os dados atualizados. Os atributos deste objeto
     * representam os novos valores a serem persistidos na tabela.
     */
    void atualizar(T obj);

    /**
     * Remove um objeto do tipo T do banco de dados com base no seu identificador único (ID).
     *
     * @param id identificador único do objeto do tipo T a ser deletado. Este ID corresponde
     * geralmente à chave primária da tabela no banco de dados.
     */
    void deletar(int id);

    /**
     * Busca e retorna um objeto do tipo T a partir do seu identificador único (ID).
     *
     * @param id identificador único do objeto do tipo T a ser buscado. Este ID corresponde
     * geralmente à chave primária da tabela no banco de dados.
     * @return o objeto do tipo T correspondente ao ID fornecido, ou {@code null} se nenhum
     * objeto com esse ID for encontrado no banco de dados.
     */
    T buscarPorId(int id);

    /**
     * Retorna uma lista contendo todos os objetos do tipo T cadastrados no banco de dados.
     *
     * @return uma {@code List} de objetos do tipo T. Se não houver nenhum objeto cadastrado,
     * retorna uma lista vazia.
     */
    List<T> listarTodos();
}