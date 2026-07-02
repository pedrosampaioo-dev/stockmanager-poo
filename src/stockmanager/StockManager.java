package stockmanager;

import stockmanager.exception.CategoriaInvalidaException;
import stockmanager.exception.EstoqueInsuficienteException;
import stockmanager.exception.ProdutoNaoEncontradoException;
import stockmanager.interfaces.Persistivel;
import stockmanager.interfaces.Relatorio;
import stockmanager.model.Categoria;
import stockmanager.model.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Gerenciador principal do sistema de estoque.
 * Centraliza todas as operacoes relacionadas a produtos, categorias
 * e controle de estoque. Implementa {@link Relatorio} para geracao
 * de relatorios e {@link Persistivel} para persistencia de dados em arquivo.
 *
 * @author Equipe StockManager
 * @version 1.0
 */
public class StockManager implements Relatorio, Persistivel {

    private final List<Produto> produtos;
    private final List<Categoria> categorias;
    private int proximoIdProduto;
    private int proximoIdCategoria;

    private static final String ARQUIVO_CATEGORIAS = "categorias.dat";
    private static final String ARQUIVO_PRODUTOS = "produtos.dat";

    /**
     * Cria um novo gerenciador de estoque com listas vazias de produtos e categorias.
     */
    public StockManager() {
        this.produtos = new ArrayList<>();
        this.categorias = new ArrayList<>();
        this.proximoIdProduto = 1;
        this.proximoIdCategoria = 1;
    }

    // =========================================================================
    // Gerenciamento de Categorias
    // =========================================================================

    /**
     * Cadastra uma nova categoria no sistema.
     *
     * @param nome nome da categoria (nao pode ser vazio ou nulo)
     * @return a categoria recem-criada
     * @throws IllegalArgumentException se o nome for invalido
     */
    public Categoria adicionarCategoria(String nome) {
        Categoria categoria = new Categoria(proximoIdCategoria++, nome);
        categorias.add(categoria);
        return categoria;
    }

    /**
     * Busca uma categoria pelo seu identificador unico.
     *
     * @param id identificador da categoria
     * @return a categoria encontrada
     * @throws CategoriaInvalidaException se nenhuma categoria com o ID fornecido existir
     */
    public Categoria buscarCategoria(int id) {
        return categorias.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CategoriaInvalidaException(id));
    }

    /**
     * Retorna uma lista com todas as categorias cadastradas.
     *
     * @return lista de categorias (copia defensiva)
     */
    public List<Categoria> listarCategorias() {
        return new ArrayList<>(categorias);
    }

    /**
     * Retorna o numero de categorias cadastradas no sistema.
     *
     * @return total de categorias
     */
    public int getTotalCategorias() {
        return categorias.size();
    }

    // =========================================================================
    // Gerenciamento de Produtos
    // =========================================================================

    /**
     * Cadastra um novo produto no sistema, associando-o a uma categoria existente.
     *
     * @param nome          nome do produto
     * @param codigo        codigo unico do produto
     * @param categoriaId   ID da categoria a qual o produto pertence
     * @param preco         preco unitario do produto
     * @param quantidade    quantidade inicial em estoque
     * @param estoqueMinimo quantidade minima desejada em estoque
     * @return o produto recem-criado
     * @throws CategoriaInvalidaException se o ID de categoria nao existir no sistema
     * @throws IllegalArgumentException   se algum parametro for invalido
     */
    public Produto adicionarProduto(String nome, String codigo, int categoriaId,
                                    double preco, int quantidade, int estoqueMinimo) {
        Categoria categoria = buscarCategoria(categoriaId);
        Produto produto = new Produto(proximoIdProduto++, nome, codigo, categoria,
                preco, quantidade, estoqueMinimo);
        produtos.add(produto);
        return produto;
    }

    /**
     * Busca um produto pelo seu identificador numerico.
     *
     * @param id identificador do produto
     * @return o produto encontrado
     * @throws ProdutoNaoEncontradoException se o produto nao existir
     */
    public Produto buscarProduto(int id) {
        return produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProdutoNaoEncontradoException("ID: " + id));
    }

    /**
     * Busca um produto pelo seu codigo (insensivel a maiusculas/minusculas).
     *
     * @param codigo codigo do produto
     * @return o produto encontrado
     * @throws ProdutoNaoEncontradoException se o produto nao existir
     */
    public Produto buscarProdutoPorCodigo(String codigo) {
        return produtos.stream()
                .filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Codigo: " + codigo));
    }

    /**
     * Retorna uma lista com todos os produtos cadastrados.
     *
     * @return lista de produtos (copia defensiva)
     */
    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtos);
    }

    /**
     * Retorna o numero de produtos cadastrados no sistema.
     *
     * @return total de produtos
     */
    public int getTotalProdutos() {
        return produtos.size();
    }

    // =========================================================================
    // Operacoes de Estoque
    // =========================================================================

    /**
     * Adiciona uma quantidade ao estoque de um produto identificado pelo codigo.
     *
     * @param codigo     codigo do produto
     * @param quantidade quantidade a adicionar (deve ser positiva)
     * @throws ProdutoNaoEncontradoException se o produto nao existir
     * @throws IllegalArgumentException      se a quantidade for menor ou igual a zero
     */
    public void adicionarEstoque(String codigo, int quantidade) {
        if (quantidade <= 0)
            throw new IllegalArgumentException("Quantidade a adicionar deve ser positiva.");
        Produto produto = buscarProdutoPorCodigo(codigo);
        produto.setQuantidade(produto.getQuantidade() + quantidade);
    }

    /**
     * Remove uma quantidade do estoque de um produto identificado pelo codigo.
     *
     * @param codigo     codigo do produto
     * @param quantidade quantidade a remover (deve ser positiva)
     * @throws ProdutoNaoEncontradoException  se o produto nao existir
     * @throws EstoqueInsuficienteException   se o estoque disponivel for menor que o solicitado
     * @throws IllegalArgumentException       se a quantidade for menor ou igual a zero
     */
    public void removerEstoque(String codigo, int quantidade) throws EstoqueInsuficienteException {
        if (quantidade <= 0)
            throw new IllegalArgumentException("Quantidade a remover deve ser positiva.");
        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto.getQuantidade() < quantidade)
            throw new EstoqueInsuficienteException(codigo, produto.getQuantidade(), quantidade);
        produto.setQuantidade(produto.getQuantidade() - quantidade);
    }

    /**
     * Retorna a lista de produtos cujo estoque esta abaixo ou igual ao minimo configurado.
     *
     * @return lista de produtos com estoque abaixo do minimo
     */
    public List<Produto> listarProdutosAbaixoMinimo() {
        return produtos.stream()
                .filter(Produto::estaAbaixoMinimo)
                .collect(Collectors.toList());
    }

    // =========================================================================
    // Relatorio e Persistencia
    // =========================================================================

    /**
     * Gera um relatorio completo do estoque, incluindo todos os produtos,
     * quantidades e alertas para itens abaixo do estoque minimo.
     *
     * @return String contendo o relatorio formatado
     */
    @Override
    public String gerarRelatorio() {
        String linha = "=".repeat(65);
        StringBuilder sb = new StringBuilder();
        sb.append(linha).append("\n");
        sb.append("              RELATORIO GERAL DE ESTOQUE\n");
        sb.append(linha).append("\n\n");

        sb.append(String.format("  Categorias cadastradas : %d%n", categorias.size()));
        sb.append(String.format("  Produtos cadastrados   : %d%n%n", produtos.size()));

        sb.append("  --- PRODUTOS ---\n");
        if (produtos.isEmpty()) {
            sb.append("  Nenhum produto cadastrado.\n");
        } else {
            for (Produto p : produtos) {
                sb.append("  ").append(p).append("\n");
                if (p.estaAbaixoMinimo())
                    sb.append("    [!] ESTOQUE ABAIXO DO MINIMO!\n");
            }
        }

        sb.append("\n  --- ALERTAS DE ESTOQUE MINIMO ---\n");
        List<Produto> abaixo = listarProdutosAbaixoMinimo();
        if (abaixo.isEmpty()) {
            sb.append("  Todos os produtos estao com estoque adequado.\n");
        } else {
            for (Produto p : abaixo) {
                sb.append(String.format("  [!] %-20s | Disponivel: %3d | Minimo: %3d%n",
                        p.getNome(), p.getQuantidade(), p.getEstoqueMinimo()));
            }
        }

        sb.append("\n").append(linha).append("\n");
        return sb.toString();
    }

    /**
     * Persiste todos os dados do sistema em arquivos de texto no diretorio de execucao.
     * Os arquivos {@code categorias.dat} e {@code produtos.dat} sao criados ou sobrescritos.
     *
     * @throws RuntimeException se ocorrer um erro de I/O durante a gravacao
     */
    @Override
    public void salvar() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_CATEGORIAS))) {
            for (Categoria c : categorias)
                pw.println(c.getId() + ";" + c.getNome());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar categorias: " + e.getMessage(), e);
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_PRODUTOS))) {
            for (Produto p : produtos) {
                pw.println(p.getId() + ";" + p.getNome() + ";" + p.getCodigo() + ";"
                        + p.getCategoria().getId() + ";" + p.getPrecoUnitario() + ";"
                        + p.getQuantidade() + ";" + p.getEstoqueMinimo());
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar produtos: " + e.getMessage(), e);
        }
    }

    /**
     * Carrega os dados do sistema a partir dos arquivos de persistencia.
     * As categorias sao carregadas antes dos produtos, pois produtos referenciam
     * categorias por ID. Os dados existentes em memoria sao descartados.
     *
     * @throws RuntimeException se ocorrer um erro de I/O durante a leitura
     */
    public void carregar() {
        categorias.clear();
        produtos.clear();
        proximoIdCategoria = 1;
        proximoIdProduto = 1;

        File catFile = new File(ARQUIVO_CATEGORIAS);
        if (catFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(catFile))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] partes = linha.split(";", 2);
                    if (partes.length == 2) {
                        int id = Integer.parseInt(partes[0].trim());
                        categorias.add(new Categoria(id, partes[1].trim()));
                        if (id >= proximoIdCategoria) proximoIdCategoria = id + 1;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Erro ao carregar categorias: " + e.getMessage(), e);
            }
        }

        File prodFile = new File(ARQUIVO_PRODUTOS);
        if (prodFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(prodFile))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] partes = linha.split(";", 7);
                    if (partes.length == 7) {
                        int id = Integer.parseInt(partes[0].trim());
                        Categoria cat = buscarCategoria(Integer.parseInt(partes[3].trim()));
                        Produto p = new Produto(id, partes[1].trim(), partes[2].trim(), cat,
                                Double.parseDouble(partes[4].trim()),
                                Integer.parseInt(partes[5].trim()),
                                Integer.parseInt(partes[6].trim()));
                        produtos.add(p);
                        if (id >= proximoIdProduto) proximoIdProduto = id + 1;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Erro ao carregar produtos: " + e.getMessage(), e);
            }
        }
    }
}
