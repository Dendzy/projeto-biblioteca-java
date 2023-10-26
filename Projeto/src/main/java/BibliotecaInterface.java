import java.util.ArrayList;

public interface BibliotecaInterface {
    boolean cadastrarLivro(Livro livro);
    boolean removerLivro(Livro livro);
    ArrayList<Livro> pesquisarLivroPorAutor(String autor);
    ArrayList<Livro> pesquisarLivroPorGenero(String genero);
}
