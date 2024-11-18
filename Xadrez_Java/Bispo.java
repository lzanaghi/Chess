package projetoxadrez;
/*
Classe responsavel pelas caracteristicas da classe Bispo
*/
public class Bispo extends Peca {
    // construtor com base na cor da peça
    public Bispo(char cor){
        switch (cor) {
            case 'b':
                this.setNome("B_B");
                this.setCor(cor);
                break;
            case 'p':
                this.setNome("B_P");
                this.setCor(cor);
                break;
            default:
                System.out.println("Cor Inválida");
                break;
        }
        
        this.setNoJogo(true);
    }
    // sobrescrita do metodo desenho para desenhar o Bispo no tabuleiro
    @Override
    public String desenho(){
       if(this.getNome() == null){
           System.out.println("Peça não inicializada");
       }
        return this.getNome();
        
    }
    //sobrescrita do metodo checarMovimento para conferir se o movimento do bispo é válido
    @Override
    public boolean checarMovimento(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino){
       // confere o movimento na horizontal, confere a locomoção maxima  e return false se o movimento for invalido
       return (linhaDestino - linhaOrigem == ((int)colunaDestino - colunaOrigem) || linhaDestino - linhaOrigem == -((int)colunaDestino - colunaOrigem)) && (linhaDestino - linhaOrigem < 8 && linhaDestino - linhaOrigem > -8); 
    }
    
    public void comerPeca(){
        this.setNoJogo(false);
    } 
}
