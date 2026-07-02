package stockmanager.exception;

/**
 * Exceção lançada quando um produto buscado não é encontrado no sistema.
 * Pode ser utilizada em buscas por ID, código ou qualquer outro identificador.
 *
 * @author Equipe StockManager
 * @version 1.0
 */
public class ProdutoNaoEncontradoException extends RuntimeException {

    private final String identificador;

    /**
     * Cria uma nova instância informando o identificador que originou a falha.
     *
     * @param identificador código, ID ou outro dado usado na busca que não retornou resultado
     */
    public ProdutoNaoEncontradoException(String identificador) {
        super("Produto não encontrado: " + identificador);
        this.identificador = identificador;
    }

    /**
     * Retorna o identificador utilizado na busca que originou a exceção.
     *
     * @return String com o identificador do produto não encontrado
     */
    public String getIdentificador() {
        return identificador;
    }
}
