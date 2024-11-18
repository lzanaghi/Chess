/*
 Classe abstrata das peças, guarda os atributos e metodos comuns á elas
 */
package projetoxadrez;

/**
 *
 * @author Pichau
 */
abstract class Peca {
    //atributos das peças
    private String nome; // nome da peça para ser imprimido no tabuleiro
    private char cor; // cor da peça
    private boolean noJogo; // se a peça ainda está no jogo
    private boolean primeiroMov; // cpnfere se é o primeiro movimento da peça no tabuleiro

    // metodos get e set
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getCor() {
        return cor;
    }

    public void setCor(char cor) {
        this.cor = cor;
    }

    public boolean isNoJogo() {
        return noJogo;
    }

    public void setNoJogo(boolean noJogo) {
        this.noJogo = noJogo;
    }

    public boolean isPrimeiroMov() {
        return primeiroMov;
    }

    public void setPrimeiroMov(boolean primeiroMov) {
        this.primeiroMov = primeiroMov;
    }
    // metodos abstratos
    //metodo para desenhar a peça no tabuleiro
    abstract String desenho();
    // metodo para checar o movimento se está válido ou não
    abstract boolean checarMovimento(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino);
}
