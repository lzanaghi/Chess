package projetoxadrez;
/*
Classe responsavel pela criação e manutenção do tabuleiro, alem de checar os movimentos.
*/
public class Tabuleiro {
    
    private Posicao[][] tab = new Posicao[8][8]; //atributo com 64 posições separados em linha e coluna
    private String comeu; // atributo para controle de quando comer uma peça
   
    // metodos get e set dos atributos
    public Posicao getTab(int linha, int coluna) {
        return tab[linha][coluna];
    }

    public void setTab(Posicao tab, int linha, int coluna) {
        this.tab[linha][coluna] = tab;
    }

    public String getComeu() {
        return comeu;
    }

    public void setComeu(String comeu) {
        this.comeu = comeu;
    }
    
    // construtor do tabuleiro
    public Tabuleiro(){
        
        char a = 0;
        //inicializa as 64 posições
        for(int i = 7; i >= 0; i--){           
            for(int j = 0; j < 8; j++){
                // switch para converter a coluna de int para char
                    switch(j){
                        case 0: 
                            a = 'a';
                            break;
                        case 1: 
                            a = 'b';
                            break;
                        case 2: 
                            a = 'c';
                            break;
                        case 3: 
                            a = 'd';
                            break;
                        case 4: 
                            a = 'e';
                            break;
                        case 5: 
                            a = 'f';
                            break;
                        case 6: 
                            a = 'g';
                            break;
                        case 7: 
                            a = 'h';
                            break; 
                    }     
                    // inicializando as o tabuleiro com as determinadas cores das posições
                if(i%2 == 0 && j%2 == 0){
                    Posicao aux = new Posicao(i, a, 'p');
                    tab[i][j] = aux;
                }else if(i%2 != 0 && j%2 != 0){
                    Posicao aux = new Posicao(i, a, 'p');
                    tab[i][j] = aux;
                }else{
                    Posicao aux = new Posicao(i, a, 'b');
                    tab[i][j] = aux;
                }
            }
            
        }
        this.inserirPecas(); // inserindo as peças no tabuleiro
        this.comeu = "nao comeu"; // inicializando a variavel
    }
    // metodo para inserir as peças(as peças sempre são colocadas numa ordem predeterminada)
    private void inserirPecas(){
        //inserção das peças em duas determinadas posições no começo do jogo
    this.getTab(0, 0).setPeca(new Torre('b'));
    this.getTab(0, 1).setPeca(new Cavalo('b'));
    this.getTab(0, 2).setPeca(new Bispo('b'));
    this.getTab(0, 3).setPeca(new Rei('b'));
    this.getTab(0, 4).setPeca(new Rainha('b'));
    this.getTab(0, 5).setPeca(new Bispo('b'));
    this.getTab(0, 6).setPeca(new Cavalo('b'));
    this.getTab(0, 7).setPeca(new Torre('b'));
    this.getTab(1, 0).setPeca(new Peao('b'));
    this.getTab(1, 1).setPeca(new Peao('b'));
    this.getTab(1, 2).setPeca(new Peao('b'));
    this.getTab(1, 3).setPeca(new Peao('b'));
    this.getTab(1, 4).setPeca(new Peao('b'));
    this.getTab(1, 5).setPeca(new Peao('b'));
    this.getTab(1, 6).setPeca(new Peao('b'));
    this.getTab(1, 7).setPeca(new Peao('b'));
    
    this.getTab(7, 0).setPeca(new Torre('p'));
    this.getTab(7, 1).setPeca(new Cavalo('p'));
    this.getTab(7, 2).setPeca(new Bispo('p'));
    this.getTab(7, 3).setPeca(new Rei('p'));
    this.getTab(7, 4).setPeca(new Rainha('p'));
    this.getTab(7, 5).setPeca(new Bispo('p'));
    this.getTab(7, 6).setPeca(new Cavalo('p'));
    this.getTab(7, 7).setPeca(new Torre('p'));
    this.getTab(6, 0).setPeca(new Peao('p'));
    this.getTab(6, 1).setPeca(new Peao('p'));
    this.getTab(6, 2).setPeca(new Peao('p'));
    this.getTab(6, 3).setPeca(new Peao('p'));
    this.getTab(6, 4).setPeca(new Peao('p'));
    this.getTab(6, 5).setPeca(new Peao('p'));
    this.getTab(6, 6).setPeca(new Peao('p'));
    this.getTab(6, 7).setPeca(new Peao('p'));
    
    
    }
   
    // metodo em que ocorrem a checagem do movimento de acordo com o tabuleiro atual
    public boolean checkMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        // verifica se as coordenadas estao dentro do escopo do tabuleiro
        if(  linhaDestino < 0 || linhaDestino > 7 || colunaDestino < 'a' || colunaDestino > 'h' || linhaOrigem < 0 || linhaOrigem > 7 || colunaOrigem < 'a' || colunaOrigem > 'h' ){
            return false;
        }
        // verifica se a posição origem possui uma peça para movimentar
        if(!this.tab[linhaOrigem][(int)(colunaOrigem - 97)].isOcup()){
            return false;
        }
        // verificação das peças
        if("P_B".equals(this.tab[linhaOrigem][(int)(colunaOrigem - 97)].getPeca().getNome())){ // verifica se é um peao
               // verifica se tem uma peça na diagonal para o peão conseguir comer, ou movimento inválido
                if((((int)(colunaDestino - colunaOrigem)) == 1 || ((int)(colunaDestino - colunaOrigem)) == -1) && (linhaDestino - linhaOrigem == 1)){
                    if(!this.tab[linhaDestino][(int)(colunaDestino - 97)].isOcup()){
                        return false;
                    }else{  
                        //se a peça for da mesma cor, movimento inválido
                        if(this.tab[linhaDestino][(int)(colunaDestino - 97)].getPeca().getCor() == 'b'){
                            return false;
                        }
                    }
                }else{ // se n for um movimento para a diagonal, realiza a checagem padrao do movimento do peão
                    if(!this.getTab(linhaOrigem, (int) (colunaOrigem - 97)).getPeca().checarMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino)){
                        return false;
                    }
                    if(this.tab[linhaDestino][(int)(colunaDestino - 97)].isOcup()){ // se tiver qualquer peça no movimento vertical, movimento inválido
                        return false;
                    }
                }    
        }else if("P_P".equals(this.tab[linhaOrigem][(int)(colunaOrigem - 97)].getPeca().getNome())){ // mesma verificação para o  peão preto
            if((((int)(colunaDestino - colunaOrigem)) == 1 || ((int)(colunaDestino - colunaOrigem)) == -1) && (linhaDestino - linhaOrigem == -1)){
                    if(!this.tab[linhaDestino][(int)(colunaDestino - 97)].isOcup()){
                        return false;
                    }else{
                        if(this.tab[linhaDestino][(int)(colunaDestino - 97)].getPeca().getCor() == 'p'){
                            return false;
                        }
                    }
                }else{
                    if(!this.getTab(linhaOrigem, (int) (colunaOrigem - 97)).getPeca().checarMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino)){
                        return false;
                    }
                    if(this.tab[linhaDestino][(int)(colunaDestino - 97)].isOcup()){
                        return false;
                    }
                }  
        }// se não for um peão realiza a checagem do movimento caracteristica da peça
        else if(!this.getTab(linhaOrigem, (int) (colunaOrigem - 97)).getPeca().checarMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino)){
            return false;
        }
        // verifica a disponibilidade do caminho ate o destino
        if(!(this.checaCaminhoLivre(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino))){
            return false;
        }
        // se a coordenada origem for igual a coordenada destino o movimento é inválido    
        if(linhaOrigem == linhaDestino && colunaOrigem == colunaDestino){
            return false;
        }
        // se a peça no destino for de cor igual a peça da origem,movimento inválido
        if(this.tab[linhaDestino][(int)colunaDestino - 97].isOcup()){
            if(this.tab[linhaDestino][(int)colunaDestino - 97].getPeca().getCor() == this.tab[linhaOrigem][(int)colunaOrigem - 97].getPeca().getCor()){
                return false;
            }
        }
        // faz a verificação do rei, só pode se movimentor para a casa que não entre em xeque    
        if("K_P".equals(this.tab[linhaOrigem][(int)(colunaOrigem - 97)].getPeca().getNome())){
            if(this.tab[linhaDestino][(char)(colunaDestino - 97)].isSobAtaquePreto()){
                return false;
            }
        }
        // mesma verificação para o rei Branco
        if("K_B".equals(this.tab[linhaOrigem][(int)(colunaOrigem - 97)].getPeca().getNome())){
            if(this.tab[linhaDestino][(char)(colunaDestino - 97)].isSobAtaqueBranco()){
                return false;
            }
        }
            return true;
        
    }
    
    
    // metodo para verificar se o caminho está livre ate o destino
    private boolean checaCaminhoLivre(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        // atributos auxiliares para descobrir o tipo de movimento
        int distanciaVertical = linhaDestino - linhaOrigem;
        int distanciaHorizontal = (int) colunaDestino - (int) colunaOrigem;
        
        // se a peça for um cavalo, ela pode passar por cima das outras peças
        if("C_P".equals(this.tab[linhaOrigem][(char)(colunaOrigem - 97)].getPeca().getNome())|| "C_B".equals(this.tab[linhaOrigem][(char)(colunaOrigem - 97)].getPeca().getNome())){
            return true;
        } 
        // movimento na vertical
        if(distanciaHorizontal == 0){
            // movimento para a peça branca
            if(linhaOrigem < linhaDestino){    
                for(int i = linhaOrigem + 1; i < linhaDestino; i++){ // verifica se não tem nenhuma pela verticamente até o destino
                    if(this.tab[i][(int) (colunaOrigem - 97)].isOcup()){
                    return false;
                    }
                }
            }else{ // movimento para a peça preta
               for(int i = linhaOrigem - 1; i > linhaDestino; i--){
                    if(this.tab[i][(int) (colunaOrigem - 97)].isOcup()){
                    return false;
                    }
                } 
            }    
        }else if(distanciaVertical == 0){  // movimento horizontal
            if(colunaOrigem < colunaDestino){   // movimento para as peças brancas  
                for(int i = (int) (colunaOrigem - 97) + 1; i < (int) (colunaDestino - 97); i++){ // verifica horizontalmente se não há nenhuma peça no caminho
                    if(this.tab[linhaOrigem][i].isOcup()){
                        return false;
                }
            }
            }else{ // movimento para as pelas pretas
               for(int i = (int) (colunaOrigem - 97) - 1; i > (int) (colunaDestino - 97); i--){
                    if(this.tab[linhaOrigem][i].isOcup()){
                        return false;
                    }
                }
            }
        }else{ // movimentos na diagonal
                int i,j;
                if(linhaOrigem < linhaDestino && colunaOrigem < colunaDestino){     // movimento das peças brancas
                    for(i = linhaOrigem + 1,j = (int) (colunaOrigem - 97) + 1; i < linhaDestino && j < (int)(colunaDestino - 97) ; i++, j++){ // verifica o movimento para a diagonal nordeste
                        if(this.tab[i][j].isOcup()){
                            return false;
                        }
                    }
                }else if(linhaOrigem > linhaDestino && colunaOrigem > colunaDestino){
                    for(i = linhaOrigem - 1,j = (int) (colunaOrigem - 97) - 1 ; i > linhaDestino && j > (int)(colunaDestino - 97) ; i--, j--){ // verifica o movimento para a diagonal sudoeste
                        if(this.tab[i][j].isOcup()){
                            return false;
                        }
                    }
                }else if(linhaOrigem < linhaDestino && colunaOrigem > colunaDestino) {
                for(i = linhaOrigem + 1,j = (int) (colunaOrigem - 97) - 1; i < linhaDestino && j > (int)(colunaDestino - 97) ; i++, j--){ // verifica o movimenta para a diagonal noroeste
                        if(this.tab[i][j].isOcup()){
                            return false;
                        }
                    }
                }else{
                    for(i = linhaOrigem - 1,j = (int) (colunaOrigem - 97) + 1; i > linhaDestino && j < (int)(colunaDestino - 97) ; i--, j++){ // verifica o movimento para a diagonal sudeste
                        if(this.tab[i][j].isOcup()){
                            return false;
                        }
                    }
                }
        }
        return true;
    }
    
    // calcula em cada posição se alguma peça consegue chegar ate ela
    private void sobAtaque(){
        for(int i = 7; i >= 0; i--){
            for(int j = 0; j < 8 ; j++){ // pega posição por posição no tabuleiro
                // atributos auxiliares para ajudar a saber se alguma peça pode chegar naquela posição
                int contadorPreto = 0;
                int contadorBranco = 0;
                for(int x = 7; x >= 0; x --){ // verifica para cada posição, o tabuleiro inteiro
                    for(int y = 0; y < 8; y++){
                        if(this.tab[x][y].isOcup()){ // verifica se tem uma peça nessa posição do tabuleiro
                            if(this.checkMovimento(x, (char) (y + 97), i, (char) (j + 97))){ // caso possuir uma peça, verifica se a peça consegue chegar até o local destino(posição referencia)
                                if(this.tab[x][y].getPeca().getCor() == 'p' && this.tab[x][y].getPeca().isNoJogo()){ // se a peça for da cor preta deixa o atributo sobAtaquePreto da posição referencia verdadeiro
                                    this.tab[i][j].setSobAtaquePreto(true);
                                    contadorPreto++;
                                }
                                if(this.tab[x][y].getPeca().getCor() == 'b' && this.tab[x][y].getPeca().isNoJogo()){ // se a peça for da cor branca deixa o atributo sobAtaqueBranco da posição referencia como verdadeiro   
                                    this.tab[i][j].setSobAtaqueBranco(true);
                                    contadorBranco++;
                                }
                            }
                        }
                    }
                }
                    if(contadorBranco == 0){ // caso não encontrar nenhuma peça, é colocado false para os atributos de ataque
                        this.tab[i][j].setSobAtaqueBranco(false);
                    }
                    if(contadorPreto == 0){
                        this.tab[i][j].setSobAtaquePreto(false);
                    }
                
            }
        }          
    }
    // metodo para movimentar as peças no tabuleiro
    public void movimentar(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        //verifica se é primeiro movimento do determinado peão da peça branca
        if("P_B".equals(this.tab[linhaOrigem][(int)(colunaOrigem - 97)].getPeca().getNome())){
            if(this.tab[linhaOrigem][(int)(colunaOrigem - 97)].getPeca().isPrimeiroMov()){  // se for o primeiro movimento  
                this.tab[linhaOrigem][(int)(colunaOrigem - 97)].getPeca().setPrimeiroMov(false); // seta o atributo primeiroMov como falso
            }
        }
        //verifica se é primeiro movimento do determinado peão da peça preta
        if("P_P".equals(this.tab[linhaOrigem][(char)(colunaOrigem - 97)].getPeca().getNome())){
            if(this.tab[linhaOrigem][(char)(colunaOrigem - 97)].getPeca().isPrimeiroMov()){    // se for o primeiro movimento
                this.tab[linhaOrigem][(char)(colunaOrigem - 97)].getPeca().setPrimeiroMov(false); // seta o atributo primeiroMov como falso
            }
        }
        // movimenta a pela no tabuleiro
        if(this.tab[linhaDestino][(int)colunaDestino - 97].isOcup()){
            // tem uma peça inimiga no destino
            this.tab[linhaDestino][(int) (colunaDestino - 97)].getPeca().setNoJogo(false); // seta a peça inimiga como fora do jogo
            this.setComeu(this.tab[linhaDestino][(int) (colunaDestino - 97)].getPeca().getNome()); // coloca o nome da peça no atributo comeu
            this.getTab(linhaDestino, (int) (colunaDestino - 97)).setPeca(this.getTab(linhaOrigem, (int) (colunaOrigem - 97)).getPeca()); // coloca a peça da posição origem na posição destino
            this.getTab(linhaDestino, (int) (colunaDestino - 97)).setOcup(true); // seta a ocupação da posição destino como true 
            this.getTab(linhaOrigem, (int) (colunaOrigem - 97)).setOcup(false); // seta a ocupação da posição origem como falso
        }else{                             
            this.getTab(linhaDestino, (int) (colunaDestino - 97)).setPeca(this.getTab(linhaOrigem, (int) (colunaOrigem - 97)).getPeca());// coloca a peça da posição origem na posição destino
            this.getTab(linhaDestino, (int) (colunaDestino - 97)).setOcup(true);// seta a ocupação da posição destino como true 
            this.getTab(linhaOrigem, (int) (colunaOrigem - 97)).setOcup(false);// seta a ocupação da posição origem como falso
        } 
        this.sobAtaque(); // recalcula toda as posições para saber se a posição está sobre ataque de alguma peça
    }
    // desenha o tabuleiro no terminal
    public void printarTabuleiro(){
        System.out.println("Tabuleiro Atual: ");
        for(int i = 7; i >= 0; i--){
            System.out.print(i + " "); // insere o numeros das linhas
            for(int j = 0; j < 8; j++){
                if(!this.tab[i][j].isOcup()){ // insere o nome da peça nas posições ocupadas ou 000 nas desocupadas
                    System.out.print("000 ");
                }else{  
                    System.out.print(this.tab[i][j].getPeca().getNome() + " ");
                   
                }
            }
                System.out.println("");
        }
        System.out.println("   a   b   c   d   e   f   g   h");
    }
    
}
