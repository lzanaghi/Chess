package projetoxadrez;
/*
Classe responsavel pelas caracteristicas da peça Cavalo
*/
public class Cavalo extends Peca{ 
    // construtor com base na cor da peça
    public Cavalo(char cor){
        switch (cor) {
            case 'b':
                this.setNome("C_B");
                this.setCor(cor);
                break;
            case 'p':
                this.setNome("C_P");
                this.setCor(cor);
                break;
            default:
                System.out.println("Cor Inválida");
                break;
        }
        
        this.setNoJogo(true);
    }
    // sobrescrita do metodo desenho para desenhar o Cavalo no tabuleiro
    @Override
    public String desenho(){
       if(this.getNome() == null){
           System.out.println("Peça não inicializada");
       }
        return this.getNome();
        
    }
    //sobrescrita do metodo checarMovimento para conferir se o movimento é válido para o cavalo
    @Override
    public boolean checarMovimento(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino){
        //confere o movimento em L na horizontal
        if((linhaDestino - linhaOrigem == 2 || linhaDestino - linhaOrigem == -2) && (((int) colunaDestino - colunaOrigem) == 1 || ((int) colunaDestino - colunaOrigem) == -1)){
        return true;
        // confere o movimento em L na vertical se não return false, movimento invalido
       }else return (linhaDestino - linhaOrigem == 1 || linhaDestino - linhaOrigem == -1) && (((int) colunaDestino - colunaOrigem) == 2 || ((int) colunaDestino - colunaOrigem) == -2);
    }
    public void comerPeca(){
        this.setNoJogo(false);
    } 
}

