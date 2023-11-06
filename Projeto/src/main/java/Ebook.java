import Exceptions.FormatoIndefinidoException;
import Exceptions.PaginasInvalidasException;

public class Ebook extends Livro {
    private String formato;

    public static final String FORMATO_PDF = "PDF";
    public static final String FORMATO_EPUB = "EPUB";
    public static final String FORMATO_MOBI = "MOBI";

    public Ebook(String nomeDoLivro,String nomeDoAutor, String genero, int numeroDePaginas,String formato) throws FormatoIndefinidoException, PaginasInvalidasException{
        super(nomeDoLivro,nomeDoAutor,genero,numeroDePaginas);
        if (formato.equals(FORMATO_PDF) || formato.equals(FORMATO_EPUB) || formato.equals(FORMATO_MOBI)) {
            this.formato = formato;
        } else {
            throw new FormatoIndefinidoException("Formato do Ebook inválido");
        }
    }

    public Ebook() throws PaginasInvalidasException {
        super("Sem nome","Sem Autor","Sem gênero",1);
        this.formato = "Sem formato definido";
    }

    public String getFormato() {
        return this.formato;
    }

    public void setFormato(String formato) throws FormatoIndefinidoException {
        if (formato.equals(FORMATO_PDF) || formato.equals(FORMATO_EPUB) || formato.equals(FORMATO_MOBI)) {
            this.formato = formato;
        } else {
            throw new FormatoIndefinidoException("Formato inválido");
        }
    }

    public String toString() {
        return super.toString() + "Formato: " + this.formato;
    }
}
