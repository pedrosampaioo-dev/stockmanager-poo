package stockmanager.interfaces;

/**
 * Interface que define o contrato para geracao de relatorios.
 * Classes que implementam esta interface podem produzir um relatorio
 * textual formatado sobre seu estado atual.
 *
 * @author Equipe StockManager
 * @version 1.0
 */
public interface Relatorio {

    /**
     * Gera e retorna um relatorio formatado com informacoes relevantes
     * sobre o estado atual do objeto.
     *
     * @return String contendo o relatorio formatado
     */
    String gerarRelatorio();
}
