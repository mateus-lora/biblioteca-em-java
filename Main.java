import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Biblioteca biblio = new Biblioteca();
    static Scanner input = new Scanner(System.in);

    private static void clear() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    private static int inputNumerico(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.print(mensagem);
        do {
            String valorStr = input.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (Exception e) {
                System.out.println("Erro. Por favor informe um número inteiro");
            }
        } while (!entradaValida);
        return valor;
    }

    private static void listar() {
        var livros = biblio.pesquisarTodos();
        if (livros.isEmpty()) {
            System.out.println("A lista de livros está vazia.");
        } else {
            livros.sort(Comparator.comparing(Livro::getTitulo)); // Referência de método
            System.out.println("-------- Lista de Livros --------\n");
            for (Livro livro : livros) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Ano: " + livro.getAnoPublicacao());
                System.out.println("N. Páginas: " + livro.getnPaginas());
                System.out.println("\n---------------------------------\n");
            }
        }
        input.nextLine();
    }

    private static void adicionar() {
        Livro novoLivro = new Livro();
        System.out.println("-------- ADICIONANDO NOVO LIVRO --------");
        System.out.print("Informe o título do livro: ");

        String titulo = input.nextLine();
        novoLivro.setTitulo(titulo);

        System.out.print("Informe o nome do autor: ");
        novoLivro.setAutor(input.nextLine());

        novoLivro.setAnoPublicacao(inputNumerico("Informe o ano de publicação: "));

        novoLivro.setnPaginas(inputNumerico("Informe o número de páginas: "));

        try {
            biblio.adicionar(novoLivro);
            System.out.println("Livro adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("\nERRO: " + e.getMessage());
        }

        input.nextLine();
    }

    private static void pesquisar() {
        if (biblio.pesquisarTodos().isEmpty()) {
            System.out.println("Não existem livros a serem pesquisados.");
        } else {
            System.out.print("Insira o título do livro: ");
            String titulo = input.nextLine();
            clear();
            var lista = biblio.pesquisarPorTitulo(titulo);
            if (lista.isEmpty()) {
                System.out.println("Não existem livros correspondente a essa busca.");
    
            } else {
                System.out.println("-------- Lista de Livros --------\n");
                for (Livro livro : lista) {
                    System.out.println("Título: " + livro.getTitulo());
                    System.out.println("Autor: " + livro.getAutor());
                    System.out.println("Ano: " + livro.getAnoPublicacao());
                    System.out.println("N. Páginas: " + livro.getnPaginas());
                    System.out.println("\n---------------------------------\n");
                }
            }
        }
        input.nextLine();
    }

    private static void remover() {
        clear();
        var livros = biblio.pesquisarTodos();
        listar();
        if (!livros.isEmpty()) {
            System.out.println("\nInsira o título completo ao qual deseja remover: ");
            String titulo = input.nextLine();
            clear();
            try {
                biblio.removerPorTitulo(titulo);
                System.out.println("Livro removido com sucesso!");
            } catch (Exception e) {
                System.out.println("ERRO: " + e.getMessage());
            }
            input.nextLine();
        }

    }

    public static void main(String[] args) {

        Livro novoLivro = new Livro();
        String menu = """
                SISTEMA DE GERENCIAMENTO DE BIBLIOTECA
                Escolha uma das opções:
                1 - Adicionar novo livro
                2 - Listar todos os livros
                3 - Pesquisar livro
                4 - Remover livro
                0 - SAIR
                """;
        int opcao;
        do {
            clear();
            opcao = inputNumerico(menu);
            switch (opcao) {
                case 0:
                    clear();
                    System.out.println("Volte sempre!!!");
                    break;
                case 1:
                    clear();
                    adicionar();

                    break;
                case 2:
                    clear();
                    listar();

                    break;
                case 3:
                    clear();
                    pesquisar();
                    break;
                case 4:
                    clear();
                    remover();
                    break;
                default:
                    clear();
                    System.out.println("Opção Inválida!!!");
                    input.nextLine();
                    break;
            }
        } while (opcao != 0);

    }
}
