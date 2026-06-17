package stockmanager.exception;

public class EstoqueInsuficienteException extends RuntimeException {
    private final String codigoProduto;
    private final int quantidadeDisponivel;
    private final int quantidadeSolicitada;

    public EstoqueInsuficienteException(String codigoProduto, int quantidadeDisponivel, int quantidadeSolicitada){
        super(String.format(
            "Estoque insuficiente para o produto '%s': disponível=%d, solicitado=%d.", 
            codigoProduto, quantidadeDisponivel, quantidadeSolicitada
        ));
        this.codigoProduto = codigoProduto;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.quantidadeSolicitada = quantidadeSolicitada;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public int getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

}
