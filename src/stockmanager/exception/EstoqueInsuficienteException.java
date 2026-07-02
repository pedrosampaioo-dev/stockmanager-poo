package stockmanager.exception;

/**
 * Excecao lancada quando uma operacao de retirada de estoque nao pode ser
 * concluida por falta de quantidade disponivel.
 * Contem informacoes detalhadas sobre o produto e as quantidades envolvidas.
 *
 * @author Equipe StockManager
 * @version 1.0
 */
public class EstoqueInsuficienteException extends RuntimeException {

    private final String codigoProduto;
    private final int quantidadeDisponivel;
    private final int quantidadeSolicitada;

    /**
     * Cria uma nova instancia com o codigo do produto e as quantidades envolvidas.
     *
     * @param codigoProduto        codigo do produto com estoque insuficiente
     * @param quantidadeDisponivel quantidade disponivel no momento da operacao
     * @param quantidadeSolicitada quantidade que foi solicitada para retirada
     */
    public EstoqueInsuficienteException(String codigoProduto, int quantidadeDisponivel, int quantidadeSolicitada) {
        super(String.format(
            "Estoque insuficiente para o produto '%s': disponivel=%d, solicitado=%d.",
            codigoProduto, quantidadeDisponivel, quantidadeSolicitada
        ));
        this.codigoProduto = codigoProduto;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.quantidadeSolicitada = quantidadeSolicitada;
    }

    /**
     * Retorna o codigo do produto que gerou a excecao.
     *
     * @return codigo do produto com estoque insuficiente
     */
    public String getCodigoProduto() {
        return codigoProduto;
    }

    /**
     * Retorna a quantidade disponivel no momento da tentativa de retirada.
     *
     * @return quantidade disponivel em estoque
     */
    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    /**
     * Retorna a quantidade que foi solicitada para retirada.
     *
     * @return quantidade solicitada
     */
    public int getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }
}
