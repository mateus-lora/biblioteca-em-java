import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    // BD em memória
    private List<Livro> acervo = new ArrayList<>(); // ArrayList<>()

    public void adicionar(Livro livro) throws Exception {
        if (livro.getTitulo() == null || livro.getTitulo().isEmpty()) {
            throw new Exception("Não é permitido cadastrar livro sem título.");
        }
        for (Livro livroAcervo : acervo) {
            if (livroAcervo.getTitulo().equalsIgnoreCase(livro.getTitulo())) {
                throw new Exception("Já existe um livro cadastrado com esse título.");
            }
        }
        if (livro.getAutor() == null || livro.getAutor().isEmpty()) {
            throw new Exception("Não é permitido cadastrar livro sem autor.");
        }
        int anoAtual = LocalDate.now().getYear();
        int anoPublicacao = livro.getAnoPublicacao();
        if (anoPublicacao < 1400 || anoPublicacao > anoAtual) {
            throw new Exception(
                    "O ano de publicação " + anoPublicacao + " não está dentro do intervalo permitido (1400 a " + anoAtual + ").");
        }
        if (livro.getnPaginas() <= 0) {
            throw new Exception("Não é permitido cadastrar livros com número de páginas negativo ou zerado.");
        }

        acervo.add(livro);
    }

    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) { // Compara
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }

    public void removerPorTitulo(String titulo) throws Exception {
        boolean encontrado = false;
        for (Livro livro : acervo) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                acervo.remove(livro);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new Exception("Não foi possível remover este livro.");
        }
    }

    public List<Livro> pesquisarTodos() {
        return this.acervo;
    }
}
