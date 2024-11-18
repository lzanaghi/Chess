package projetoxadrez;
import java.util.Scanner;
/*
Classe responsavel por iniciar o jogo
*/
public class Gerenciador {


    public static void main(String[] args) {
            Tabuleiro tab = new Tabuleiro();
            Scanner ler = new Scanner(System.in);
            String s;
            //le os nomes dos jogadores
            System.out.println("Digite o nome do Jogador das Peças Brancas");
            s = ler.next();
            Jogador j1 = new Jogador(s,'b');
            System.out.println("Digite o nome do JOgador das Peças Pretas");
            Jogador j2 = new Jogador(s,'p');
            System.out.println("-----------------");
            System.out.println("Iniciando o jogo");
            System.out.println("-----------------");
            // inicia o jogo
            Jogo jog = new Jogo(j1,j2,tab);
            
            jog.jogar();
            // declara o vencedor do jogo
            if(jog.getCountJogadas()%2 == 0){
                System.out.println("Vitoria de : " + j2.getNome());
            }else{
                System.out.println("Vitoria de : " + j1.getNome());
            }
            
    }
    
}
