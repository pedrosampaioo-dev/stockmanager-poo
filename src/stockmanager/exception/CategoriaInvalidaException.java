package stockmanager.exception;

/**
 * Excecao lancada quando uma categoria nao e encontrada no sistema
 * ou quando uma operacao e realizada com uma categoria invalida.
 *
 * @author Equipe StockManager
 * @version 1.0
 */
public class CategoriaInvalidaException extends RuntimeException {

    private final int idCategoria;

    /**
     * Cria uma nova instancia da excecao para o ID de categoria informado.
     *
     * @param idCategoria identificador da categoria nao encontrada
     */
    public CategoriaInvalidaException(int idCategoria) {
        super("Categoria com ID " + idCategoria + " nao encontrada.");
        this.idCategoria = idCategoria;
    }

    /**
     * Retorna o ID da categoria que originou a excecao.
     *
     * @return identificador da categoria nao encontrada
     */
    public int getIdCategoria() {
        return idCategoria;
    }
}
