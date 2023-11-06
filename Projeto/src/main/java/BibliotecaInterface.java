import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface BibliotecaInterface {
    boolean cadastrarLivro(Livro livro);
    boolean removerLivro(Livro livro);
    ArrayList<Livro> pesquisarLivroPorAutor(String autor);
    ArrayList<Livro> pesquisarLivroPorGenero(String genero);
    List<String> recuperarDados(String nomeArquivo) throws IOException;
    void salvarDados(List<String> lista, String nomeArquivo) throws IOException;
}
