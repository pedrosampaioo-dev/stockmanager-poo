package stockmanager.model;

public class Produto {

    private int id;
    private String nome;
    private String codigo;
    private Categoria categoria;
    private double precoUnitario;
    private int quantidade;
    private int estoqueMinimo;

    public Produto(int id, String nome, String codigo, Categoria categoria,
                double precoUnitario, int quantidade, int estoqueMinimo) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome do produto não pode ser vazio.");
        if (codigo == null || codigo.isBlank())
            throw new IllegalArgumentException("Código do produto não pode ser vazio.");
        if (categoria == null)
            throw new IllegalArgumentException("Categoria não pode ser nula.");
        if (precoUnitario < 0)
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        if (quantidade < 0)
            throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        if (estoqueMinimo < 0)
            throw new IllegalArgumentException("Estoque mínimo não pode ser negativo.");

        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.categoria = categoria;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.estoqueMinimo = estoqueMinimo;
    }

    public boolean estaAbaixoMinimo() {
        return this.quantidade <= this.estoqueMinimo;
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getCodigo() {
        return codigo;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public double getPrecoUnitario() {
        return precoUnitario;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        this.nome = nome;
    }

    public void setPrecoUnitario(double preco) {
        if (preco < 0)
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        this.precoUnitario = preco;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0)
            throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        this.quantidade = quantidade;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        if (estoqueMinimo < 0)
            throw new IllegalArgumentException("Estoque mínimo não pode ser negativo.");
        this.estoqueMinimo = estoqueMinimo;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria == null)
            throw new IllegalArgumentException("Categoria não pode ser nula.");
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (Cód: %s) | Categoria: %s | Qtd: %d | Mín: %d | R$ %.2f",
                id, nome, codigo, categoria.getNome(), quantidade, estoqueMinimo, precoUnitario);
    }
}
