package stockmanager.exception;

/**
 * Excecao lancada quando um produto buscado nao e encontrado no sistema.
 * Pode ser utilizada em buscas por ID, codigo ou qualquer outro identificador.
 *
 * @author Equipe StockManager
 * @version 1.0
 */
public class ProdutoNaoEncontradoException extends RuntimeException {

    private final String identificador;

    /**
     * Cria uma nova instancia informando o identificador que originou a falha.
     *
     * @param identificador codigo, ID ou outro dado usado na busca que nao retornou resultado
     */
    public ProdutoNaoEncontradoException(String identificador) {
        super("Produto nao encontrado: " + identificador);
        this.identificador = identificador;
    }

    /**
     * Retorna o identificador utilizado na busca que originou a excecao.
     *
     * @return String com o identificador do produto nao encontrado
     */
    public String getIdentificador() {
        return identificador;
    }
}
