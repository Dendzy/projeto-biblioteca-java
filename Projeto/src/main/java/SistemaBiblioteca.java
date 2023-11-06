import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Exceptions.PaginasInvalidasException;
import Exceptions.FormatoIndefinidoException;

public class SistemaBiblioteca {

    public static void main(String [] args) throws PaginasInvalidasException, FormatoIndefinidoException {
        Biblioteca biblia = new Biblioteca();
        boolean ligado = true;

        try {
            List<String> dadosRecuperados = biblia.recuperarDados("dados_biblioteca.txt");
            biblia.carregarDadosRecuperados(dadosRecuperados);
            JOptionPane.showMessageDialog(null,"Seus Dados foram recuperados!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar os dados da biblioteca: " + e.getMessage());
        }

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
                while (ebookOrNot != 1 || ebookOrNot != 2 || ebookOrNot != 3) {
                    ebookOrNot = Integer.parseInt(JOptionPane.showInputDialog(null,"Qual o formato do seu livro? " +
                            "\n1. Ebook." +
                            "\n2. Livro Físico." +
                            "\n3. Voltar."));
                    if (ebookOrNot == 1){
                        Ebook ebook = new Ebook();
                        String nomeDoLivro = JOptionPane.showInputDialog(null,"Qual o nome do Livro?");
                        String autorDoLivro = JOptionPane.showInputDialog(null,"Qual o nome do Autor?");
                        String generoDoLivro = JOptionPane.showInputDialog(null,"Qual o gênero?");
                        int quantidadeDePaginas = Integer.parseInt(JOptionPane.showInputDialog(null,"Qual a quantidade de páginas?"));
                        String formato = JOptionPane.showInputDialog(null,"Digite o formato do Ebook.").toUpperCase();
                        try {
                            Ebook ebook1 = new Ebook(nomeDoLivro,autorDoLivro,generoDoLivro,quantidadeDePaginas,formato);
                            boolean cadastrouEbook = false;
                            cadastrouEbook = biblia.cadastrarLivro(ebook1);
                            if (cadastrouEbook) {
                                biblia.cadastrarLivro(ebook1);
                                JOptionPane.showMessageDialog(null,"Ebook Cadastrado Com Sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(null,"Esse Ebook já Existe! Tente Novamente.");
                            }
                        } catch (FormatoIndefinidoException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage() + "\nTente Novamente!");
                        } catch (PaginasInvalidasException e) {
                            JOptionPane.showMessageDialog(null,e.getMessage() + "\nTente Novamente");
                        }
                    }
                    if (ebookOrNot == 2) {
                        String nomeDoLivro = JOptionPane.showInputDialog(null,"Qual o nome do Livro?");
                        String autorDoLivro = JOptionPane.showInputDialog(null,"Qual o nome do Autor?");
                        String generoDoLivro = JOptionPane.showInputDialog(null,"Qual o gênero?");
                        int quantidadeDePaginas = Integer.parseInt(JOptionPane.showInputDialog(null,"Qual a quantidade de páginas?"));
                        try {
                            Livro livro = new Livro(nomeDoLivro,autorDoLivro,generoDoLivro,quantidadeDePaginas);
                            boolean cadastrouLivro = false;
                            cadastrouLivro = biblia.cadastrarLivro(livro);
                            if (cadastrouLivro) {
                                biblia.cadastrarLivro(livro);
                                JOptionPane.showMessageDialog(null,"Livro Cadastrado Com Sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(null,"Esse Livro já Existe!");
                            }
                            break;
                        } catch (PaginasInvalidasException e) {
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }
                    }
                    if (ebookOrNot == 3) {
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
                    JOptionPane.showMessageDialog(null,"Não existe nenhum livro com esse gênero!");
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
                boolean apagouLivro = biblia.removerLivro(livroPraApagar);
                if (apagouLivro){
                    biblia.removerLivro(livroPraApagar);
                    JOptionPane.showMessageDialog(null,"Livro removido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null,"Esse livro não existe na nossa biblioteca!");
                }
            }
            if (menu == 5) {
                try {
                    biblia.salvarDados(biblia.obterDadosParaSalvar(), "dados_biblioteca.txt");
                    JOptionPane.showMessageDialog(null, "Dados da biblioteca salvos com sucesso.");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar os dados da biblioteca: " + e.getMessage());
                }
            }
            if (menu == 6) {
                JOptionPane.showMessageDialog(null,"Obrigado Por Utilizar Nosso Sistema!");
                ligado = false;
            }
        }
    }
}
