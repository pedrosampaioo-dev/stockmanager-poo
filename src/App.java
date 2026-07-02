import java.util.List;
import java.util.Scanner;
import stockmanager.StockManager;
import stockmanager.exception.CategoriaInvalidaException;
import stockmanager.exception.EstoqueInsuficienteException;
import stockmanager.exception.ProdutoNaoEncontradoException;
import stockmanager.model.Categoria;
import stockmanager.model.Produto;

/**
 * Ponto de entrada da aplicacao StockManager.
 * Apresenta um menu interativo em console para gerenciar o estoque
 *
 * @author Pedro Sampaio
 * @version 1.0
 */
public class App {

    private static final StockManager manager = new StockManager();
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Metodo principal da aplicacao. Exibe o menu e processa as
     * escolhas do usuario ate que a opcao de saida seja selecionada.
     *
     * @param args argumentos de linha de comando (nao utilizados)
     */
    public static void main(String[] args) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("   SISTEMA DE GERENCIAMENTO DE ESTOQUE - StockManager");
        System.out.println("=".repeat(60));

        boolean rodando = true;
        while (rodando) {
            exibirMenuPrincipal();
            int opcao = lerInt("Opcao: ");
            switch (opcao) {
                case 1: menuCategorias(); break;
                case 2: menuProdutos(); break;
                case 3: menuEstoque(); break;
                case 4: menuRelatorios(); break;
                case 5: salvarDados(); break;
                case 6: carregarDados(); break;
                case 7: carregarDadosDemo(); break;
                case 0: rodando = false; break;
                default: System.out.println("  Opcao invalida."); break;
            }
        }

        System.out.println("\nEncerrando o sistema. Ate logo!");
        scanner.close();
    }

    // Menus

    private static void exibirMenuPrincipal() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("  MENU PRINCIPAL");
        System.out.println("-".repeat(50));
        System.out.println("  1. Gerenciar Categorias");
        System.out.println("  2. Gerenciar Produtos");
        System.out.println("  3. Operacoes de Estoque");
        System.out.println("  4. Relatorios");
        System.out.println("  5. Salvar dados");
        System.out.println("  6. Carregar dados");
        System.out.println("  7. Carregar dados de demonstracao");
        System.out.println("  0. Sair");
        System.out.println("-".repeat(50));
    }

    private static void menuCategorias() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- CATEGORIAS ---");
            System.out.println("  1. Adicionar categoria");
            System.out.println("  2. Listar categorias");
            System.out.println("  0. Voltar");
            int opcao = lerInt("Opcao: ");
            switch (opcao) {
                case 1: adicionarCategoria(); break;
                case 2: listarCategorias(); break;
                case 0: voltar = true; break;
                default: System.out.println("  Opcao invalida."); break;
            }
        }
    }

    private static void menuProdutos() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- PRODUTOS ---");
            System.out.println("  1. Adicionar produto");
            System.out.println("  2. Listar todos os produtos");
            System.out.println("  3. Buscar produto por codigo");
            System.out.println("  0. Voltar");
            int opcao = lerInt("Opcao: ");
            switch (opcao) {
                case 1: adicionarProduto(); break;
                case 2: listarProdutos(); break;
                case 3: buscarProduto(); break;
                case 0: voltar = true; break;
                default: System.out.println("  Opcao invalida."); break;
            }
        }
    }

    private static void menuEstoque() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- OPERACOES DE ESTOQUE ---");
            System.out.println("  1. Adicionar ao estoque");
            System.out.println("  2. Retirar do estoque");
            System.out.println("  3. Listar produtos abaixo do minimo");
            System.out.println("  0. Voltar");
            int opcao = lerInt("Opcao: ");
            switch (opcao) {
                case 1: adicionarEstoque(); break;
                case 2: retirarEstoque(); break;
                case 3: listarAbaixoMinimo(); break;
                case 0: voltar = true; break;
                default: System.out.println("  Opcao invalida."); break;
            }
        }
    }

    private static void menuRelatorios() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- RELATORIOS ---");
            System.out.println("  1. Relatorio geral de estoque");
            System.out.println("  2. Produtos abaixo do estoque minimo");
            System.out.println("  0. Voltar");
            int opcao = lerInt("Opcao: ");
            switch (opcao) {
                case 1: System.out.println(manager.gerarRelatorio()); break;
                case 2: listarAbaixoMinimo(); break;
                case 0: voltar = true; break;
                default: System.out.println("  Opcao invalida."); break;
            }
        }
    }

// Operações de Categoria

    private static void adicionarCategoria() {
        System.out.print("  Nome da categoria: ");
        String nome = scanner.nextLine().trim();
        try {
            Categoria cat = manager.adicionarCategoria(nome);
            System.out.println("  Categoria adicionada: " + cat);
        } catch (IllegalArgumentException e) {
            System.out.println("  ERRO: " + e.getMessage());
        }
    }

    private static void listarCategorias() {
        List<Categoria> lista = manager.listarCategorias();
        if (lista.isEmpty()) {
            System.out.println("  Nenhuma categoria cadastrada.");
        } else {
            System.out.println("\n  Categorias cadastradas:");
            lista.forEach(c -> System.out.println("    " + c));
        }
    }

//Operações de Produto

    private static void adicionarProduto() {
        if (manager.listarCategorias().isEmpty()) {
            System.out.println("  Cadastre pelo menos uma categoria primeiro.");
            return;
        }
        try {
            System.out.print("  Nome do produto: ");
            String nome = scanner.nextLine().trim();
            System.out.print("  Codigo: ");
            String codigo = scanner.nextLine().trim();
            listarCategorias();
            int catId = lerInt("  ID da categoria: ");
            double preco = lerDouble("  Preco unitario (R$): ");
            int qtd = lerInt("  Quantidade inicial: ");
            int min = lerInt("  Estoque minimo: ");

            Produto p = manager.adicionarProduto(nome, codigo, catId, preco, qtd, min);
            System.out.println("  Produto adicionado: " + p);
        } catch (CategoriaInvalidaException | IllegalArgumentException e) {
            System.out.println("  ERRO: " + e.getMessage());
        }
    }

    private static void listarProdutos() {
        List<Produto> lista = manager.listarProdutos();
        if (lista.isEmpty()) {
            System.out.println("  Nenhum produto cadastrado.");
        } else {
            System.out.println("\n  Produtos cadastrados:");
            for (Produto p : lista) {
                System.out.println("    " + p);
                if (p.estaAbaixoMinimo())
                    System.out.println("      [!] ESTOQUE ABAIXO DO MINIMO");
            }
        }
    }

    private static void buscarProduto() {
        System.out.print("  Codigo do produto: ");
        String codigo = scanner.nextLine().trim();
        try {
            Produto p = manager.buscarProdutoPorCodigo(codigo);
            System.out.println("  " + p);
            if (p.estaAbaixoMinimo())
                System.out.println("  [!] Estoque abaixo do minimo!");
        } catch (ProdutoNaoEncontradoException e) {
            System.out.println("  ERRO: " + e.getMessage());
        }
    }

//Operações de Estoque

    private static void adicionarEstoque() {
        System.out.print("  Codigo do produto: ");
        String codigo = scanner.nextLine().trim();
        int qtd = lerInt("  Quantidade a adicionar: ");
        try {
            manager.adicionarEstoque(codigo, qtd);
            Produto p = manager.buscarProdutoPorCodigo(codigo);
            System.out.println("  Estoque atualizado. Nova quantidade: " + p.getQuantidade());
        } catch (ProdutoNaoEncontradoException | IllegalArgumentException e) {
            System.out.println("  ERRO: " + e.getMessage());
        }
    }

    private static void retirarEstoque() {
        System.out.print("  Codigo do produto: ");
        String codigo = scanner.nextLine().trim();
        int qtd = lerInt("  Quantidade a retirar: ");
        try {
            manager.removerEstoque(codigo, qtd);
            Produto p = manager.buscarProdutoPorCodigo(codigo);
            System.out.println("  Estoque atualizado. Nova quantidade: " + p.getQuantidade());
            if (p.estaAbaixoMinimo())
                System.out.println("  [!] ATENCAO: estoque abaixo do minimo!");
        } catch (EstoqueInsuficienteException e) {
            System.out.println("  ERRO: " + e.getMessage());
            System.out.println("  Disponivel: " + e.getQuantidadeDisponivel()
                    + " | Solicitado: " + e.getQuantidadeSolicitada());
        } catch (ProdutoNaoEncontradoException | IllegalArgumentException e) {
            System.out.println("  ERRO: " + e.getMessage());
        }
    }

    private static void listarAbaixoMinimo() {
        List<Produto> lista = manager.listarProdutosAbaixoMinimo();
        if (lista.isEmpty()) {
            System.out.println("  Todos os produtos estao com estoque adequado.");
        } else {
            System.out.println("\n  Produtos abaixo do estoque minimo:");
            for (Produto p : lista) {
                System.out.printf("    %-25s | Qtd: %3d | Min: %3d%n",
                        p.getNome(), p.getQuantidade(), p.getEstoqueMinimo());
            }
        }
    }

// Persistêcnia

    private static void salvarDados() {
        try {
            manager.salvar();
            System.out.println("  Dados salvos com sucesso!");
        } catch (RuntimeException e) {
            System.out.println("  ERRO ao salvar: " + e.getMessage());
        }
    }

    private static void carregarDados() {
        try {
            manager.carregar();
            System.out.println("  Dados carregados: " + manager.getTotalCategorias()
                    + " categorias, " + manager.getTotalProdutos() + " produtos.");
        } catch (RuntimeException e) {
            System.out.println("  ERRO ao carregar: " + e.getMessage());
        }
    }

    private static void carregarDadosDemo() {
        Categoria eletronicos = manager.adicionarCategoria("Eletronicos");
        Categoria alimentos   = manager.adicionarCategoria("Alimentos");
        Categoria papelaria   = manager.adicionarCategoria("Papelaria");

        manager.adicionarProduto("Teclado Mecanico", "TECLADO-01", eletronicos.getId(), 299.90, 15, 5);
        manager.adicionarProduto("Mouse Gamer",      "MOUSE-01",   eletronicos.getId(), 149.90,  3, 5);
        manager.adicionarProduto("Arroz 5kg",        "ARROZ-01",   alimentos.getId(),    29.90, 50, 10);
        manager.adicionarProduto("Feijao 1kg",       "FEIJAO-01",  alimentos.getId(),    12.50,  2,  8);
        manager.adicionarProduto("Caderno A4",       "CADERNO-01", papelaria.getId(),    19.90, 20,  5);
        manager.adicionarProduto("Caneta Azul",      "CANETA-01",  papelaria.getId(),     2.50,  1, 10);

        System.out.println("  Dados de demonstracao carregados!");
        System.out.println("  3 categorias e 6 produtos adicionados.");
        System.out.println("  (Mouse Gamer, Feijao 1kg e Caneta Azul estao abaixo do minimo)");
    }

// Utilitarios de entrada

    private static int lerInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Por favor, insira um numero inteiro valido.");
            }
        }
    }

    private static double lerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("  Por favor, insira um valor numerico valido.");
            }
        }
    }
}
