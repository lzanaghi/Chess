package projetoxadrez;
/*
Classe que representa as caracteristicas da peça da Rainha
*/
public class Rainha extends Peca {
// construtor com base da cor da peça 
    public Rainha(char cor){
        switch (cor) {
            case 'b':
                this.setNome("Q_B");
                this.setCor(cor);
                break;
            case 'p':
                this.setNome("Q_P");
                this.setCor(cor);
                break;
            default:
                System.out.println("Cor Inválida");
                break;
        }
        
        this.setNoJogo(true);
    }
    // sobrescrita do metodo desenho para desenhar a Rainha no tabuleiro
    @Override
    public String desenho(){
       if(this.getNome() == null){
           System.out.println("Peça não inicializada");
       }
        return this.getNome();
        
    }
    //sobrescrita do metodo checarMovimento para conferir se o movimento da rainha é válido
    @Override
    public boolean checarMovimento(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino){
        // confere movimentação na horizontal e confere a locomoção maxima
        if(linhaDestino != linhaOrigem && (-8 < linhaDestino - linhaOrigem && linhaDestino - linhaOrigem < 8) && colunaDestino == colunaOrigem){
            return true;
        // confere a movimentação na vertical e confere sua locomoção maxima
        }else if((linhaDestino == linhaOrigem && colunaDestino != colunaOrigem && (((int )colunaDestino - colunaOrigem) < 8 || ((int )colunaDestino - colunaOrigem) > -8))){
           return true;
           //confere a locomoção na diagonal e confere sua locomoção maxima, caso return false se movimento invalido
        }else {
            return ((linhaDestino - linhaOrigem == ((int)colunaDestino - colunaOrigem) || linhaDestino - linhaOrigem == -((int)colunaDestino - colunaOrigem)) && (linhaDestino - linhaOrigem < 8 && linhaDestino - linhaOrigem > -8)); 
        }
    }
    // metodo para comer a peca
    public void comerPeca(){
        this.setNoJogo(false);
    }
        
    
}
