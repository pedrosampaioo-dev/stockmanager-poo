package stockmanager.model;

/**
 * Representa um produto no sistema de gerenciamento de estoque.
 * Armazena informacoes como nome, codigo, categoria, preco unitario,
 * quantidade disponivel e estoque minimo configurado.
 *
 * @author Equipe StockManager
 * @version 1.0
 */
public class Produto {

    private int id;
    private String nome;
    private String codigo;
    private Categoria categoria;
    private double precoUnitario;
    private int quantidade;
    private int estoqueMinimo;

    /**
     * Cria um novo produto com todos os atributos fornecidos.
     *
     * @param id            identificador unico do produto
     * @param nome          nome do produto (nao pode ser vazio ou nulo)
     * @param codigo        codigo unico do produto (nao pode ser vazio ou nulo)
     * @param categoria     categoria a qual o produto pertence (nao pode ser nula)
     * @param precoUnitario preco unitario do produto (nao pode ser negativo)
     * @param quantidade    quantidade disponivel em estoque (nao pode ser negativa)
     * @param estoqueMinimo quantidade minima desejada em estoque (nao pode ser negativa)
     * @throws IllegalArgumentException se qualquer parametro violar as restricoes acima
     */
    public Produto(int id, String nome, String codigo, Categoria categoria,
                   double precoUnitario, int quantidade, int estoqueMinimo) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome do produto nao pode ser vazio.");
        if (codigo == null || codigo.isBlank())
            throw new IllegalArgumentException("Codigo do produto nao pode ser vazio.");
        if (categoria == null)
            throw new IllegalArgumentException("Categoria nao pode ser nula.");
        if (precoUnitario < 0)
            throw new IllegalArgumentException("Preco nao pode ser negativo.");
        if (quantidade < 0)
            throw new IllegalArgumentException("Quantidade nao pode ser negativa.");
        if (estoqueMinimo < 0)
            throw new IllegalArgumentException("Estoque minimo nao pode ser negativo.");

        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.categoria = categoria;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.estoqueMinimo = estoqueMinimo;
    }

    /**
     * Verifica se a quantidade em estoque esta abaixo ou igual ao minimo configurado.
     *
     * @return {@code true} se o estoque estiver no limite minimo ou abaixo; {@code false} caso contrario
     */
    public boolean estaAbaixoMinimo() {
        return this.quantidade <= this.estoqueMinimo;
    }

    /**
     * Retorna o identificador unico do produto.
     *
     * @return id do produto
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o codigo do produto.
     *
     * @return codigo do produto
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Retorna a categoria do produto.
     *
     * @return categoria a qual o produto pertence
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Retorna o preco unitario do produto.
     *
     * @return preco unitario em reais
     */
    public double getPrecoUnitario() {
        return precoUnitario;
    }

    /**
     * Retorna a quantidade atual em estoque.
     *
     * @return quantidade disponivel em estoque
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Retorna o estoque minimo configurado para o produto.
     *
     * @return quantidade minima desejada em estoque
     */
    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    /**
     * Define um novo nome para o produto.
     *
     * @param nome novo nome (nao pode ser vazio ou nulo)
     * @throws IllegalArgumentException se o nome for nulo ou em branco
     */
    public void setNome(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome nao pode ser vazio.");
        this.nome = nome;
    }

    /**
     * Define um novo preco unitario para o produto.
     *
     * @param preco novo preco unitario (nao pode ser negativo)
     * @throws IllegalArgumentException se o preco for negativo
     */
    public void setPrecoUnitario(double preco) {
        if (preco < 0)
            throw new IllegalArgumentException("Preco nao pode ser negativo.");
        this.precoUnitario = preco;
    }

    /**
     * Define a quantidade atual em estoque.
     *
     * @param quantidade nova quantidade (nao pode ser negativa)
     * @throws IllegalArgumentException se a quantidade for negativa
     */
    public void setQuantidade(int quantidade) {
        if (quantidade < 0)
            throw new IllegalArgumentException("Quantidade nao pode ser negativa.");
        this.quantidade = quantidade;
    }

    /**
     * Define o estoque minimo do produto.
     *
     * @param estoqueMinimo novo estoque minimo (nao pode ser negativo)
     * @throws IllegalArgumentException se o estoque minimo for negativo
     */
    public void setEstoqueMinimo(int estoqueMinimo) {
        if (estoqueMinimo < 0)
            throw new IllegalArgumentException("Estoque minimo nao pode ser negativo.");
        this.estoqueMinimo = estoqueMinimo;
    }

    /**
     * Define a categoria do produto.
     *
     * @param categoria nova categoria (nao pode ser nula)
     * @throws IllegalArgumentException se a categoria for nula
     */
    public void setCategoria(Categoria categoria) {
        if (categoria == null)
            throw new IllegalArgumentException("Categoria nao pode ser nula.");
        this.categoria = categoria;
    }

    /**
     * Retorna uma representacao textual completa do produto, incluindo
     * id, nome, codigo, categoria, quantidade, estoque minimo e preco.
     *
     * @return String formatada com todos os atributos do produto
     */
    @Override
    public String toString() {
        return String.format("[%d] %s (Cod: %s) | Categoria: %s | Qtd: %d | Min: %d | R$ %.2f",
                id, nome, codigo, categoria.getNome(), quantidade, estoqueMinimo, precoUnitario);
    }
}
