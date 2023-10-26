import java.util.ArrayList;

public class Biblioteca implements BibliotecaInterface{
    public ArrayList<Livro> livros = new ArrayList<>();

    public Biblioteca(ArrayList<Livro> livros) {
        this.livros = livros;
    }

    public Biblioteca() {
        this.livros = new ArrayList<>();
    }

    public boolean cadastrarLivro(Livro livro) {
        for (Livro l: this.livros){
            if (l.equals(livro)) {
                return false;
            }
        }
        livros.add(livro);
        return true;
    }

    public boolean removerLivro(Livro livro) {
        for (Livro l: this.livros) {
            if (l.equals(livro)){
                this.livros.remove(livro);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Livro> pesquisarLivroPorAutor(String autor) {
        ArrayList<Livro> livrosDoMesmoAutor = new ArrayList<>();
        for(Livro l: this.livros) {
            if(l.getNomeDoAutor().equals(autor)) {
                livrosDoMesmoAutor.add(l);
            }
        }
        return livrosDoMesmoAutor;
    }

    public ArrayList<Livro> pesquisarLivroPorGenero(String genero) {
        ArrayList<Livro> livroDoMesmoGenero = new ArrayList<>();
        for(Livro l: this.livros){
            if(l.getGeneroDoLivro().equals(genero)){
                livroDoMesmoGenero.add(l);
            }
        }
        return livroDoMesmoGenero;
    }
}
