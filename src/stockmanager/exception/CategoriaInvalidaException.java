package stockmanager.exception;

/**
 * Exceção lançada quando uma categoria não é encontrada no sistema
 * ou quando uma operação é realizada com uma categoria inválida.
 *
 * @author Equipe StockManager
 * @version 1.0
 */
public class CategoriaInvalidaException extends RuntimeException {

    private final int idCategoria;

    /**
     * Cria uma nova instância da exceção para o ID de categoria informado.
     *
     * @param idCategoria identificador da categoria não encontrada
     */
    public CategoriaInvalidaException(int idCategoria) {
        super("Categoria com ID " + idCategoria + " não encontrada.");
        this.idCategoria = idCategoria;
    }

    /**
     * Retorna o ID da categoria que originou a exceção.
     *
     * @return identificador da categoria não encontrada
     */
    public int getIdCategoria() {
        return idCategoria;
    }
}
