package stockmanager.model;

/**
 * Representa uma categoria de produtos no sistema de estoque.
 * Cada produto pertence a exatamente uma categoria.
 *
 * @author Equipe StockManager
 * @version 1.0
 */
public class Categoria {

    private int id;
    private String nome;

    /**
     * Cria uma nova categoria com o identificador e nome fornecidos.
     *
     * @param id   identificador unico da categoria
     * @param nome nome da categoria (nao pode ser vazio ou nulo)
     * @throws IllegalArgumentException se o nome for nulo ou em branco
     */
    public Categoria(int id, String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome da categoria nao pode ser vazio.");
        this.id = id;
        this.nome = nome;
    }

    /**
     * Retorna o identificador unico da categoria.
     *
     * @return id da categoria
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna o nome da categoria.
     *
     * @return nome da categoria
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define um novo nome para a categoria.
     *
     * @param nome novo nome (nao pode ser vazio ou nulo)
     * @throws IllegalArgumentException se o nome for nulo ou em branco
     */
    public void setNome(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome da categoria nao pode ser vazio.");
        this.nome = nome;
    }

    /**
     * Retorna uma representacao textual da categoria no formato "[id] nome".
     *
     * @return String com id e nome da categoria
     */
    @Override
    public String toString() {
        return "[" + id + "] " + nome;
    }
}
