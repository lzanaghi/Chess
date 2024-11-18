
package projetoxadrez;
/*
Classe responsalver por guardas as informações do jogador
*/
public class Jogador {
    private String nome;  // atributo nome do jogador
    private char cor;     //atributo cor que o jogador escolheu
    private Peca peca[] = new Peca[16];

    //metodos get e set
    public String getNome() {
        return nome;
    }

    public char getCor() {
        return cor;
    }

    public Peca getPeca(int indice) {
        return peca[indice];
    }
    
    
    //construtor para inicialuzar jogando com Nome e cor
    public Jogador( String nome, char cor ){
        this.nome = nome;
        this.cor = cor;
        // vetor com as peças do jogador que estão no jogo
        if(cor == 'b' || cor == 'p'){
            this.peca[0] =  new Rainha(cor);
            this.peca[1] =  new Rei(cor);
            this.peca[2] =  new Cavalo(cor);
            this.peca[3] =  new Cavalo(cor);
            this.peca[4] =  new Torre(cor);
            this.peca[5] =  new Torre(cor);
            this.peca[6] =  new Bispo(cor);
            this.peca[7] =  new Bispo(cor);
            this.peca[8] =  new Peao(cor);
            this.peca[9] =  new Peao(cor);
            this.peca[10] = new Peao(cor);
            this.peca[11] = new Peao(cor);
            this.peca[12] = new Peao(cor);
            this.peca[13] = new Peao(cor);
            this.peca[14] = new Peao(cor);
            this.peca[15] = new Peao(cor);  
        }else{
            System.out.println("Cor invalida");
        }
    }
    
    
}
