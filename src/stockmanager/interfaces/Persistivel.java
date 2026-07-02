package stockmanager.interfaces;

/**
 * Interface que define o contrato de persistencia de dados.
 * Classes que implementam esta interface devem ser capazes de
 * gravar seu estado em algum meio de armazenamento permanente,
 * como arquivos ou banco de dados.
 *
 * @author Equipe StockManager
 * @version 1.0
 */
public interface Persistivel {

    /**
     * Persiste os dados da classe em um meio de armazenamento permanente.
     * A implementacao define o formato e o destino da gravacao.
     */
    void salvar();
}
