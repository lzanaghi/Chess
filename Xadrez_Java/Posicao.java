package projetoxadrez;

public  class Posicao {
    private char cor;  // cor da atual posição na tabuleiro
    private int linha;  //linha pertecente no tabuleiro
    private char coluna;    //coluna pertecente no tabuleiro
    private Peca peca;    // string peca
    private boolean ocup;   // se esta ocupado ou não
    private boolean sobAtaqueBranco; // mostra se aquela posição pode ser atacada por uma peça branca
    private boolean sobAtaquePreto;// mostra se aquela posição pode ser atacada por uma peça preta

    //metodos get e set
    public char getCor() {
        return cor;
    }

    private void setCor(char cor) {
        this.cor = cor;
    }

    public int getLinha() {
        return linha;
    }

    private void setLinha(int linha) {
        this.linha = linha;
    }

    public char getColuna() {
        return coluna;
    }

    private void setColuna(char coluna) {
        this.coluna = coluna;
    }

    public Peca getPeca() {
        return this.peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
        this.setOcup(true);
    }

    public boolean isOcup() {
        return ocup;
    }

    public void setOcup(boolean ocup) {
        this.ocup = ocup;
    }

    public boolean isSobAtaqueBranco() {
        return sobAtaqueBranco;
    }

    public void setSobAtaqueBranco(boolean sobAtaqueBranco) {
        this.sobAtaqueBranco = sobAtaqueBranco;
    }

    public boolean isSobAtaquePreto() {
        return sobAtaquePreto;
    }

    public void setSobAtaquePreto(boolean sobAtaquePreto) {
        this.sobAtaquePreto = sobAtaquePreto;
    }
     
    //construtor sem peca
    public Posicao(int linha, char coluna , char cor){
        if(linha < 0 || linha > 8 || coluna < 'a' || coluna > 'h' || (cor != 'b' && cor != 'p')){
                System.out.println("Posição Inválida");
        }else{ 
            this.setLinha(linha);
            this.setCor(cor);
            this.setColuna(coluna);
            this.setOcup(false);
            this.setSobAtaqueBranco(false);
            this.setSobAtaquePreto(false);
        }
    }
    
}
