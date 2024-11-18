
package projetoxadrez;
/*
classe que representa as caracteristicas da peça Rei
*/

public class Rei extends Peca {
    // construtor com base na cor da peça
    public Rei(char cor){
        switch (cor) {
            case 'b':
                this.setNome("K_B");
                this.setCor(cor);
                break;
            case 'p':
                this.setNome("K_P");
                this.setCor(cor);
                break;
            default:
                System.out.println("Cor Inválida");
                break;
        }
        // seta a peça no jogo
        this.setNoJogo(true);
    }
    // sobrescrita do metodo desenho para desenhar o rei no tabuleiro
    @Override
    public String desenho(){
       if(this.getNome() == null){
           System.out.println("Peça não inicializada");
       }
        return this.getNome();
        
    }
    // sobrescrita do metodo checarMovimento, para conferir se o movimento está valido para a peça do rei
    @Override
    public boolean checarMovimento(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino){
        // confere o movimento na horizontal
        if(linhaDestino - linhaOrigem == -1  || linhaDestino - linhaOrigem == 1 && colunaDestino == colunaOrigem ){
            return true;
        // confere o movimento na vertical
        }else if( (int)colunaDestino - colunaOrigem == -1 || (int)colunaDestino - colunaOrigem == 1 && linhaDestino == linhaOrigem){
            return true;
        // confere o movimento na diagonal
        }else{
            // return true se o movimento é na diagona e return false se o movimento é invalido
            return (((int)colunaDestino - colunaOrigem == -1 || (int)colunaDestino - colunaOrigem == 1) && (linhaDestino - linhaOrigem == -1 || linhaDestino - linhaOrigem == 1));
        } 
        
    }
    // metodo q set a peça como falso no jogo
    public void comerPeca(){
        this.setNoJogo(false);
    }
        
    
}
