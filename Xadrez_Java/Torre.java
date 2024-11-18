package projetoxadrez;
/*
Classe que representa as caracteriscas da peça Torre
*/
public class Torre extends Peca {
    
    // construtor que insere o nome de acordo com a cor 
    public Torre(char cor){
        switch (cor) {
            case 'b':
                this.setNome("T_B");
                this.setCor(cor);
                break;
            case 'p':
                this.setNome("T_P");
                this.setCor(cor);
                break;
            default:
                System.out.println("Cor Inválida");
                break;
        }
        
        this.setNoJogo(true);
    }
    //metodo que sobrescreve o metodo Desenho para mostrar a peça no Tabuleiro
    @Override
    public String desenho(){
       if(this.getNome() == null){
           System.out.println("Peça não inicializada");
       }
        return this.getNome();
        
    }
    //metodo que sobrescreve checarMovimento de peça para checar o movimento da Torre
    @Override
    public boolean checarMovimento(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino){
        // confere o movimento na horizontal
        if(linhaDestino != linhaOrigem && (-8 < linhaDestino - linhaOrigem && linhaDestino - linhaOrigem < 8) && colunaDestino == colunaOrigem){
            return true;
        }else{
            // confere o movimento na vertical retornando true, se não return false se o movimento for invalido
            return (linhaDestino == linhaOrigem && colunaDestino != colunaOrigem && (((int )colunaDestino - colunaOrigem) < 8 || ((int )colunaDestino - colunaOrigem) > -8));
        }
    }
    // metodo para caso a peça seja comida
    public void comerPeca(){
        this.setNoJogo(false);
    }
        
    
}

