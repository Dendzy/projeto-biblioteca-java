import java.util.Objects;
import Exceptions.PaginasInvalidasException;

public class Livro {
    private String nomeDoLivro;
    private String nomeDoAutor;
    private String generoDoLivro;
    private int numeroDePaginas;

    public Livro(String nomeDoLivro,String nomeAutor, String genero, int numeroDePaginas) throws PaginasInvalidasException {
        this.nomeDoLivro = nomeDoLivro;
        this.nomeDoAutor = nomeAutor;
        this.generoDoLivro = genero;
        if (numeroDePaginas >= 1) {
            this.numeroDePaginas = numeroDePaginas;
        } else {
            throw new PaginasInvalidasException("O número de páginas deve ser maior ou igual a 1");
        }
    }

    public Livro(String nomeDoLivro, String nomeAutor,String genero) {
        this.nomeDoLivro = nomeDoLivro;
        this.nomeDoAutor = nomeAutor;
        this.generoDoLivro = genero;
        this.numeroDePaginas = 0;
    }

    public Livro() throws PaginasInvalidasException {
        this("Sem nome","Sem Autor","Sem gênero",1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Livro livro = (Livro) o;

        if (!Objects.equals(nomeDoLivro, livro.nomeDoLivro)) return false;
        if (!Objects.equals(nomeDoAutor, livro.nomeDoAutor)) return false;
        return Objects.equals(generoDoLivro, livro.generoDoLivro);
    }

    @Override
    public int hashCode() {
        int result = nomeDoLivro != null ? nomeDoLivro.hashCode() : 0;
        result = 31 * result + (nomeDoAutor != null ? nomeDoAutor.hashCode() : 0);
        result = 31 * result + (generoDoLivro != null ? generoDoLivro.hashCode() : 0);
        return result;
    }

    public String getNomeDoLivro() {
        return this.nomeDoLivro;
    }

    public void setNomeDoLivro(String nomeDoLivro) {
        this.nomeDoLivro = nomeDoLivro;
    }

    public String getNomeDoAutor() {
        return this.nomeDoAutor;
    }

    public void setNomeDoAutor(String nomeDoAutor) {
        this.nomeDoAutor = nomeDoAutor;
    }

    public String getGeneroDoLivro() {
        return this.generoDoLivro;
    }

    public void setGeneroDoLivro(String genero) {
        this.generoDoLivro = genero;
    }

    public int getNumeroDePaginas() {
        return this.numeroDePaginas;
    }

    public void setNumeroDePaginas(int numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public String toString() {
            return  "Nome do Livro: " + this.nomeDoLivro + "\n" +
                    "Nome do Autor: " + this.nomeDoAutor + "\n" +
                    "Gênero do Livro: " + this.generoDoLivro + "\n" +
                    "Quantidade de Páginas: " + this.numeroDePaginas + "\n";
    }
}
