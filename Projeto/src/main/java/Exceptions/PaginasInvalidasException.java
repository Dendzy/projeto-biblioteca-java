package Exceptions;

public class PaginasInvalidasException extends Exception{
    public PaginasInvalidasException() {
        super();
    }
    public PaginasInvalidasException(String mensagem) {
        super(mensagem);
    }
}
