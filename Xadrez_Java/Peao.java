
package projetoxadrez;
/*
Classe responsavel pelas caracteriscas da peça peão
*/
public class Peao extends Peca {
   // construtor com base na cor da peça
    public Peao(char cor){
        switch (cor) {
            case 'b':
                this.setNome("P_B");
                this.setCor(cor);
                break;
            case 'p':
                this.setNome("P_P");
                this.setCor(cor);
                break;
            default:
                System.out.println("Cor Inválida");
                break;
        }
        this.setPrimeiroMov(true);
        this.setNoJogo(true);
    }

   
    
    // sobrescrita do metodo desenho para desenhar o Peao no tabuleiro
    
    @Override
    public String desenho(){
       if(this.getNome() == null){
           System.out.println("Peça não inicializada");
       }
        return this.getNome();
        
    }
    // sobrescrita do metodo checarMovimento de acordo com as caracteristicas do peao
    @Override
    public boolean checarMovimento(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino){
        // confere se é o primeiro movimento do peao no tabuleiro, se for pode andar ate 2 casas verticalmente
        if(this.isPrimeiroMov()){
            if(this.getCor() == 'b'){ // como o peao so anda para "frente" como as cores dos lados das peças sao fixas os peao so podem andar em uma direção especifica dependendo da cor da peça
                return( (linhaDestino - linhaOrigem == 1  || linhaDestino - linhaOrigem == 2 )  && (colunaDestino == colunaOrigem) );
            }else{
                return( (linhaDestino - linhaOrigem == -1  || linhaDestino - linhaOrigem == -2 )  && (colunaDestino == colunaOrigem) );                     
            }
        }else{ // depois de ja ter realizado o primeiro movimento
        // confere o movimento na vertical do peao
            if(this.getCor() == 'b'){
                return( (linhaDestino - linhaOrigem == 1)  && (colunaDestino == colunaOrigem) );
            }else{
                return( (linhaDestino - linhaOrigem == -1)  && (colunaDestino == colunaOrigem) );                     
            }
        }
    }
    
    public void comerPeca(){
        this.setNoJogo(false);
    }
      
}
