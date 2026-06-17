package stockmanager.model;

public class Categoria {
    
    private int id;
    private String nome;

    public Categoria (int id, String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome da categoria não pode ser vazio.");
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome da categoria não pode ser vazio.");
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nome;
    }
}
