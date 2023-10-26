import javax.swing.JOptionPane;
import java.util.ArrayList;

public class SistemaBiblioteca {
    public static void main(String [] args) throws PaginasInvalidasException {
        Biblioteca biblia = new Biblioteca();
        boolean ligado = true;

        while(ligado) {
            int menu = Integer.parseInt(JOptionPane.showInputDialog(null,"Bem vindo ao menu, selecione a opção que deseja: " +
                    "\n1. Cadastrar novo Livro." +
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

                    }
                    if (ebookOrNot == 2) {
                        String nomeDoLivro = JOptionPane.showInputDialog(null,"Qual o nome do Livro?");
                        String autorDoLivro = JOptionPane.showInputDialog(null,"Qual o nome do Autor?");
                        String generoDoLivro = JOptionPane.showInputDialog(null,"Qual o gênero?");
                        int quantidadeDePaginas = Integer.parseInt(JOptionPane.showInputDialog(null,"Qual a quantidade de páginas?"));
                        Livro livro = new Livro(nomeDoLivro,autorDoLivro,generoDoLivro,quantidadeDePaginas);
                        biblia.cadastrarLivro(livro);
                    }
                }
            }

            if (menu == 6) {
                ligado = false;
            }
        }
    }
}
