import javax.swing.JOptionPane;
import java.util.ArrayList;

public class SistemaBiblioteca {

    public static void main(String [] args) throws PaginasInvalidasException {
        Biblioteca biblia = new Biblioteca();
        boolean ligado = true;

        while(ligado) {
            int menu = Integer.parseInt(JOptionPane.showInputDialog(null,"Bem vindo ao menu, selecione a opção que deseja: " +
                    "\n1. Cadastrar novo Livro." +
                    "\n2. Pesquisar Livros pelo autor." +
                    "\n3. Pesquisar Livros pelo genero." +
                    "\n4. Apagar Livro." +
                    "\n5. Salvar Dados." +
                    "\n6. Sair."));
            if (menu == 1) {
                int ebookOrNot = 0;
                while (ebookOrNot != 1 || ebookOrNot != 2) {
                    ebookOrNot = Integer.parseInt(JOptionPane.showInputDialog(null,"Qual o formato do seu livro? " +
                            "\n1. Ebook" +
                            "\n2. Livro Físico"));
                    if (ebookOrNot == 1){
                        Ebook ebook = new Ebook();
                        String nomeDoLivro = JOptionPane.showInputDialog(null,"Qual o nome do Livro?");
                        String autorDoLivro = JOptionPane.showInputDialog(null,"Qual o nome do Autor?");
                        String generoDoLivro = JOptionPane.showInputDialog(null,"Qual o gênero?");
                        int quantidadeDePaginas = Integer.parseInt(JOptionPane.showInputDialog(null,"Qual a quantidade de páginas?"));
                        try {
                            Ebook ebook1 = new Ebook(nomeDoLivro,autorDoLivro,generoDoLivro,quantidadeDePaginas,Ebook.FORMATO_PDF);
                            biblia.cadastrarLivro(ebook1);
                        } catch (FormatoIndefinidoException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                    if (ebookOrNot == 2) {
                        String nomeDoLivro = JOptionPane.showInputDialog(null,"Qual o nome do Livro?");
                        String autorDoLivro = JOptionPane.showInputDialog(null,"Qual o nome do Autor?");
                        String generoDoLivro = JOptionPane.showInputDialog(null,"Qual o gênero?");
                        int quantidadeDePaginas = Integer.parseInt(JOptionPane.showInputDialog(null,"Qual a quantidade de páginas?"));
                        Livro livro = new Livro(nomeDoLivro,autorDoLivro,generoDoLivro,quantidadeDePaginas);
                        biblia.cadastrarLivro(livro);
                        break;
                    }
                }
            }
            if (menu == 2) {
                String nomeDoAutorPraPesquisa = JOptionPane.showInputDialog(null,"Digite o nome do autor que você deseja pesquisar.");
                ArrayList<Livro> listaDeAutoresPesquisados = new ArrayList<>();
                listaDeAutoresPesquisados = biblia.pesquisarLivroPorAutor(nomeDoAutorPraPesquisa);
                if (listaDeAutoresPesquisados.size() == 0) {
                    JOptionPane.showMessageDialog(null,"Não existe nenhum autor com esse nome!");
                } else {
                    for(Livro l: listaDeAutoresPesquisados) {
                        JOptionPane.showMessageDialog(null,l.toString());
                    }
                }
            }
            if (menu == 3) {
                String nomeDoGeneroPraPesquisa = JOptionPane.showInputDialog(null,"Digite o nome do gênero que você deseja pesquisar.");
                ArrayList<Livro> listaDeGenerosPesquisados = new ArrayList<>();
                listaDeGenerosPesquisados = biblia.pesquisarLivroPorGenero(nomeDoGeneroPraPesquisa);
                if (listaDeGenerosPesquisados.size() == 0) {
                    JOptionPane.showMessageDialog(null,"Não existe nenhum autor com esse nome!");
                } else {
                    for(Livro l: listaDeGenerosPesquisados) {
                        JOptionPane.showMessageDialog(null,l.toString());
                    }
                }
            }
            if (menu == 4) {
                String nomeDoLivroPraApagar = JOptionPane.showInputDialog(null, "Digite o nome do livro que deseja apagar.");
                String nomeDoAutorPraApagar = JOptionPane.showInputDialog(null,"Digite o nome do Autor.");
                String nomeDoGeneroPraApgar = JOptionPane.showInputDialog(null,"Digite o nome do gênero");
                Livro livroPraApagar = new Livro(nomeDoLivroPraApagar,nomeDoAutorPraApagar,nomeDoGeneroPraApgar);
                if (biblia.removerLivro(livroPraApagar)) {
                    JOptionPane.showMessageDialog(null,"Livro removido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null,"Esse livro não existe na nossa biblioteca!");
                }
            }
            if (menu == 6) {
                ligado = false;
            }
        }
    }
}
