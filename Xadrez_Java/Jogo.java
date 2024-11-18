package projetoxadrez;
import java.util.Scanner;
/*
classe responsavel por controlar o jogo, guardar informações do tabuleiro(no estado atual) e dos jogadores
*/

public class Jogo {
     private Jogador p1; // jogador das peças brancas
     private Jogador p2;// jogador das peças pretas
     private Tabuleiro tab; // tabuleiro
     private int countJogadas; // contador do número de jogadas

    //metodos get e set
    public Jogador getP1() {
        return p1;
    }

    private void setP1(Jogador p1) {
        this.p1 = p1;
    }

    public Jogador getP2() {
        return p2;
    }

    private void setP2(Jogador p2) {
        this.p2 = p2;
    }

    public Tabuleiro getTab() {
        return tab;
    }

    private void setTab(Tabuleiro tab) {
        this.tab = tab;
    }

    public int getCountJogadas() {
        return countJogadas;
    }

    public void setCountJogadas(int countJogadas) {
        this.countJogadas = countJogadas;
    }
     
    //metodo construtor
    public Jogo(Jogador p1, Jogador p2, Tabuleiro tab){
        this.setP1(p1);
        this.setP2(p2);
        this.setTab(tab);
        this.setCountJogadas(0);
    } 
    // metodo no qual é realizado o jogo
    public void jogar(){
        // atributos das posiçoes origem e destino
        int linhaDestino;
        int linhaOrigem;
        char colunaDestino;
        char colunaOrigem;
        Scanner ler = new Scanner(System.in);
        while(this.p1.getPeca(1).isNoJogo() && this.p2.getPeca(1).isNoJogo()){ // o jogo continua ate o xequeMate
            System.out.println("Rodada " + (this.getCountJogadas() + 1)+"º");
            if(this.getCountJogadas()%2 == 0){ // mostra de qual jogador é a vez
                System.out.println("Vez do Jogador das Peças Brancas");
            }else{
                System.out.println("Vez do jogador das Peças Pretas ");
            }
            if(this.xeque(this.getTab())){ // entra se algum rei estiver em xeque
                while(this.xeque(this.getTab())){ // Verifica se o Jogador atual não está em xeque
                    if(this.getCountJogadas()%2 == 0){
                        System.out.println("");
                        System.out.println("-------------------");
                        System.out.println("Rei Branco em Xeque");
                        System.out.println("-------------------");
                        System.out.println("");
                    }else{
                        System.out.println("");
                        System.out.println("-------------------");
                        System.out.println("Rei Preto em Xeque");
                        System.out.println("-------------------");
                        System.out.println("");
                    }
                    // leitura da coordenadas origem e destino
                    this.tab.printarTabuleiro();
                    System.out.println("Digite a coordenada Origem: ");
                    linhaOrigem = ler.nextInt();
                    colunaOrigem = ler.next().charAt(0);
                    System.out.println("Digite a coordenada Destino: ");
                    linhaDestino = ler.nextInt();
                    colunaDestino = ler.next().charAt(0);
                    // confere se as coordenadas sao validas
                    while((!this.tab.checkMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) || (! this.corValida(linhaOrigem, colunaOrigem)) ){
                        System.out.println("Movimento Inválido");
                        System.out.println("Digite a coordenada Origem: ");
                        linhaOrigem = ler.nextInt();
                        colunaOrigem = ler.next().charAt(0);
                        System.out.println("Digite a coordenada Destino: ");
                        linhaDestino = ler.nextInt();
                        colunaDestino = ler.next().charAt(0);
                    }
                    
                    // verifica se esse movimento retira o rei de xeque
                    if(this.checarXeque(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)){
                        System.out.println("O Rei ainda está em xeque, Movimento inválido"); // se nao retira, manda mensagem na tela, e volta para o loop
                    }else{ // se retirar do cheque, realiza o movimento
                        this.tab.movimentar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
                        if(!"nao comeu".equals(this.tab.getComeu())){ // confere se alguma peça foi comida no movimento
                        int i;
                        if(this.getCountJogadas()%2 == 0){ // atualiza na lista de peças do jogador se alguma peça deles foram comidas
                            for(i = 0; i < 16; i++){
                                if((this.p2.getPeca(i).getNome().equals(this.tab.getComeu())) && (this.p2.getPeca(i).isNoJogo()) ){
                                    this.p2.getPeca(i).setNoJogo(false);
                                }
                            }
                        }else{
                            for(i = 0; i < 16; i++){
                                if((this.p1.getPeca(i).getNome().equals(this.tab.getComeu())) && (this.p1.getPeca(i).isNoJogo()) ){
                                    this.p1.getPeca(i).setNoJogo(false);
                                }
                            }
                        }
                
                        }
                    }
                    
                }
            }else{
                //le as coordenadas origem e destino
                this.tab.printarTabuleiro();
                System.out.println("Digite a coordenada Origem: ");
                linhaOrigem = ler.nextInt();
                colunaOrigem = ler.next().charAt(0);
                System.out.println("Digite a coordenada Destino: ");
                linhaDestino = ler.nextInt();
                colunaDestino = ler.next().charAt(0);
                // verifica se são validas
                while((!this.tab.checkMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) || (! this.corValida(linhaOrigem, colunaOrigem)) ){
                    System.out.println("Movimento Inválido");
                    System.out.println("Digite a coordenada Origem: ");
                    linhaOrigem = ler.nextInt();
                    colunaOrigem = ler.next().charAt(0);
                    System.out.println("Digite a coordenada Destino: ");
                    linhaDestino = ler.nextInt();
                    colunaDestino = ler.next().charAt(0);
                }
                // realiza o movimento
                this.tab.movimentar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
                if(!"nao comeu".equals(this.tab.getComeu())){ // verifica se alguma peça foi comida, para atualizar na lista do jogador
                    int i;
                    if(this.getCountJogadas()%2 == 0){ // utiliza a vez do jogador para saber de quem foi a peça que foi comida
                        for(i = 0; i < 16; i++){
                            if((this.p2.getPeca(i).getNome().equals(this.tab.getComeu())) && (this.p2.getPeca(i).isNoJogo()) ){
                                this.p2.getPeca(i).setNoJogo(false);
                            }
                        }
                    }else{
                        for(i = 0; i < 16; i++){
                            if((this.p1.getPeca(i).getNome().equals(this.tab.getComeu())) && (this.p1.getPeca(i).isNoJogo()) ){
                                this.p1.getPeca(i).setNoJogo(false);
                            }
                        }
                    }
                
                }
            }
            this.tab.printarTabuleiro(); // printa o tabuleiro no final do movimento
            if(this.xequeMate()){
                if(this.getCountJogadas()%2 == 0){
                    this.p2.getPeca(1).setNoJogo(false);
                }else{
                    this.p1.getPeca(1).setNoJogo(false);
                }                
            }
            this.setCountJogadas(this.getCountJogadas() + 1); // soma no numero de rodada
        }
    }
    
    private boolean corValida(int linhaOrigem,char colunaOrigem){
        if(this.getCountJogadas()%2 == 0){
                if(this.tab.getTab(linhaOrigem, (int) (colunaOrigem - 97)).getPeca().getCor() 
                ==  this.p1.getCor()){
                    return true;
                }
            }else{
                if(this.tab.getTab(linhaOrigem, (int) (colunaOrigem - 97)).getPeca().getCor() 
                ==  this.p2.getCor()){
                    return true;
                }
        }
        return false;
    }
    
    public boolean xeque(Tabuleiro Tab){
        for(int i = 7; i >= 0; i--){
            for(int j = 0; j < 8 ; j++){
                if(this.tab.getTab(i, j).isOcup()){
                    if("K_P".equals(Tab.getTab(i, j).getPeca().getNome())){
                        if(Tab.getTab(i, j).isSobAtaqueBranco()){
                            return true;
                        }
                    }
                    if("K_B".equals(Tab.getTab(i, j).getPeca().getNome())){
                        if(Tab.getTab(i, j).isSobAtaquePreto()){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean xequeMate(){
        boolean auxiliarPreto = true;
        boolean auxiliarBranco = true;
        for(int i = 7; i >= 0; i--){
            for(int j = 0; j < 8 ; j++){
                if(this.tab.getTab(i, j).isOcup()){
                    if("K_P".equals(this.getTab().getTab(i, j).getPeca().getNome())){
                        if(this.getTab().getTab(i, j).isSobAtaqueBranco()){
                            auxiliarPreto = false;
                            if(this.getTab().checkMovimento(i,(char) (j+97), i+1, (char) (j+97)) ){ // norte       
                                return false;
                            }
                            if(this.getTab().checkMovimento(i,(char) (j+97), i, (char) (j+98))){ // leste
                                return false;
                            }
                            if(this.getTab().checkMovimento(i,(char) (j+97), i+1, (char) (j+98))){ // nordeste
                                return false;
                            }
                            if(this.getTab().checkMovimento(i,(char) (j+97), i-1, (char) (j+97))){ // sul
                                return false;
                            }
                            if(this.getTab().checkMovimento(i,(char) (j+97), i+1, (char) (j+96))){ // oeste
                                return false;
                            }
                            if(this.getTab().checkMovimento(i,(char) (j+97), i-1, (char) (j+96))){ // sudoeste
                                return false;
                            }
                            if(this.getTab().checkMovimento(i,(char) (j+97), i-1, (char) (j+98))){ // sudeste
                                return false;
                            }
                            if(this.getTab().checkMovimento(i,(char) (j+97), i+1, (char) (j+96))){ // noroeste
                                return false;
                            }
                        }
                    
                    }
           
                    if("K_B".equals(this.getTab().getTab(i, j).getPeca().getNome())){
                        if(this.getTab().getTab(i, j).isSobAtaquePreto()){
                            auxiliarBranco = false;
                            if(this.getTab().checkMovimento(i,(char) (j+97), i+1, (char) (j+97))){ // norte
                                return false;
                            }
                            if(this.getTab().checkMovimento(i,(char) (j+97), i, (char) (j+98))){ // leste
                                return false;
                            }
                            if(this.getTab().checkMovimento(i,(char) (j+97), i+1, (char) (j+98))){ // nordeste
                                return false;
                            }
                            if(this.getTab().checkMovimento(i,(char) (j+97), i-1, (char) (j+97))){ // sul
                                return false;
                            }
                            if(this.getTab().checkMovimento(i,(char) (j+97), i+1, (char) (j+96))){ // oeste
                                return false;
                            }
                            if(this.getTab().checkMovimento(i,(char) (j+97), i-1, (char) (j+96))){ // sudoeste
                                return false;
                            }
                            if(this.getTab().checkMovimento(i,(char) (j+97), i-1, (char) (j+98))){ // sudeste
                                return false;
                            }
                            if(this.getTab().checkMovimento(i,(char) (j+97), i+1, (char) (j+96))){ // noroeste
                                return false;
                            } 
                        }
                    }
                }
            }
        }
        return !(auxiliarBranco && auxiliarPreto);
    }
    
    private boolean checarXeque(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        
        boolean auxiliar;
        
        if(  linhaDestino < 0 || linhaDestino > 7 || colunaDestino < 'a' || colunaDestino > 'h' || linhaOrigem < 0 || linhaOrigem > 7 || colunaOrigem < 'a' || colunaOrigem > 'h' ){
            return false;
        }
        Posicao pos1 = new Posicao(linhaOrigem,colunaOrigem,this.getTab().getTab(linhaOrigem, (int) colunaOrigem - 97).getCor());
        pos1.setOcup(this.getTab().getTab(linhaOrigem, (int) colunaOrigem - 97).isOcup());
        pos1.setSobAtaqueBranco(this.getTab().getTab(linhaOrigem, (int) colunaOrigem - 97).isSobAtaqueBranco());
        pos1.setSobAtaquePreto(this.getTab().getTab(linhaOrigem, (int) colunaOrigem - 97).isSobAtaquePreto());
        pos1.setPeca(this.getTab().getTab(linhaOrigem, (int) colunaOrigem - 97).getPeca());
        pos1.setOcup(this.getTab().getTab(linhaOrigem, (int) colunaOrigem - 97).isOcup());
                
        Posicao pos2 = new Posicao(linhaDestino,colunaDestino,this.getTab().getTab(linhaDestino, (int) colunaDestino - 97).getCor());
        pos2.setOcup(this.getTab().getTab(linhaDestino, (int) colunaDestino - 97).isOcup());
        pos2.setSobAtaqueBranco(this.getTab().getTab(linhaDestino, (int) colunaDestino - 97).isSobAtaqueBranco());
        pos2.setSobAtaquePreto(this.getTab().getTab(linhaDestino, (int) colunaDestino - 97).isSobAtaquePreto());
        pos2.setPeca(this.getTab().getTab(linhaDestino, (int) colunaDestino - 97).getPeca());
        pos2.setOcup(this.getTab().getTab(linhaDestino, (int) colunaDestino - 97).isOcup());
                
        
        this.getTab().movimentar(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
        
        auxiliar = this.xeque(this.getTab());
        
        this.getTab().setTab(pos1, linhaOrigem, (int) colunaOrigem - 97);
        this.getTab().setTab(pos2, linhaDestino, (int)colunaDestino - 97); 
        
        return auxiliar;
        
    }
    
    
    private boolean checkXequeMate(int linha, char coluna, char cor){
        
        boolean auxiliar;
        
        Posicao pos1 = new Posicao(linha,coluna,this.getTab().getTab(linha, (int) coluna - 97).getCor());
        pos1.setOcup(this.getTab().getTab(linha, (int) coluna - 97).isOcup());
        pos1.setSobAtaqueBranco(this.getTab().getTab(linha, (int) coluna - 97).isSobAtaqueBranco());
        pos1.setSobAtaquePreto(this.getTab().getTab(linha, (int) coluna - 97).isSobAtaquePreto());
        pos1.setPeca(this.getTab().getTab(linha, (int) coluna - 97).getPeca());
        pos1.setOcup(this.getTab().getTab(linha, (int) coluna - 97).isOcup());
        
        this.getTab().getTab(linha,(int) coluna - 97).setPeca(new Peao(cor));
        this.getTab().getTab(linha,(int) coluna - 97).setOcup(true);
        
        this.getTab().sobAtaque();
        
        auxiliar = this.xeque(this.getTab());
        
        
        this.getTab().setTab(pos1, linha, (int) coluna - 97);
        return auxiliar;
            
    }

}


