import Exceptions.FormatoIndefinidoException;
import Exceptions.PaginasInvalidasException;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Biblioteca implements BibliotecaInterface {
    public ArrayList<Livro> livros = new ArrayList<>();

    public Biblioteca(ArrayList<Livro> livros) {
        this.livros = livros;
    }

    public Biblioteca() {
        this.livros = new ArrayList<>();
    }

    public boolean cadastrarLivro(Livro livro) {
        for (Livro l : this.livros) {
            if (l.equals(livro)) {
                return false;
            }
        }
        livros.add(livro);
        return true;
    }

    public boolean removerLivro(Livro livro) {
        boolean removido = livros.remove(livro);
        if (removido) {
            try {
                this.livros.remove(livro);
                List<String> dadosParaSalvar = obterDadosParaSalvar(); // Obtém todos os dados da biblioteca
                salvarDados(dadosParaSalvar, "dados_biblioteca.txt"); // Reescreve o arquivo de dados
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar o arquivo de dados: " + e.getMessage());
            }
        }
        return removido;
    }

    public ArrayList<Livro> pesquisarLivroPorAutor(String autor) {
        ArrayList<Livro> livrosDoMesmoAutor = new ArrayList<>();
        for (Livro l : this.livros) {
            if (l.getNomeDoAutor().equals(autor)) {
                livrosDoMesmoAutor.add(l);
            }
        }
        return livrosDoMesmoAutor;
    }

    public ArrayList<Livro> pesquisarLivroPorGenero(String genero) {
        ArrayList<Livro> livroDoMesmoGenero = new ArrayList<>();
        for (Livro l : this.livros) {
            if (l.getGeneroDoLivro().equals(genero)) {
                livroDoMesmoGenero.add(l);
            }
        }
        return livroDoMesmoGenero;
    }

    @Override
    public List<String> recuperarDados(String nomeArquivo) throws IOException {
        List<String> dadosLidos = new ArrayList<>();
        BufferedReader leitor = null;
        try {
            leitor = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = leitor.readLine()) != null) {
                dadosLidos.add(linha);
            }
        } finally {
            if (leitor != null) {
                leitor.close();
            }
        }
        return dadosLidos;
    }

    public void salvarDados(List<String> dados, String nomeArquivo) throws IOException {
        BufferedWriter gravador = null;
        try {
            gravador = new BufferedWriter(new FileWriter(nomeArquivo));
            for (String linha : dados) {
                gravador.write(linha + "\n");
            }
        } finally {
            if (gravador != null) {
                gravador.close();
            }
        }
    }

    public List<String> obterDadosParaSalvar() {
        List<String> dados = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro instanceof Ebook) {
                Ebook ebook = (Ebook) livro;
                String linha = ebook.getNomeDoLivro() + ", " + ebook.getNomeDoAutor() + ", " + ebook.getGeneroDoLivro() + ", " + ebook.getNumeroDePaginas() + ", " + ebook.getFormato();
                dados.add(linha);
            } else {
                String linha = livro.getNomeDoLivro() + ", " + livro.getNomeDoAutor() + ", " + livro.getGeneroDoLivro() + ", " + livro.getNumeroDePaginas() + ", Livro Físico";
                dados.add(linha);
            }
        }
        return dados;
    }

    public void carregarDadosRecuperados(List<String> dados) {
        livros.clear();
        for (String linha : dados) {
            String[] partes = linha.split(", ");
            if (partes.length == 5) {
                String nomeDoLivro = partes[0];
                String nomeDoAutor = partes[1];
                String genero = partes[2];
                int numeroDePaginas = Integer.parseInt(partes[3]);
                String formato = partes[4];
                if (formato.equals("PDF") || formato.equals("MOBI") || formato.equals("EPUB")) {
                    try {
                        livros.add(new Ebook(nomeDoLivro, nomeDoAutor, genero, numeroDePaginas, formato));
                    } catch (FormatoIndefinidoException e) {
                        throw new RuntimeException(e);
                    } catch (PaginasInvalidasException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        livros.add(new Livro(nomeDoLivro, nomeDoAutor, genero, numeroDePaginas));
                    } catch (PaginasInvalidasException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}