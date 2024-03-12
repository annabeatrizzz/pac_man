package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.mazeElements.MazeElementFruta;
import java.io.*;
import static pt.isec.pa.tinypac.model.data.Direcao.*;

public class Game implements Serializable {

    /**
     * Classe destinada ao modelo de dados, tem as principais informações sobre o jogo
     */

    //ATTRIBUTES
    private static final long serialVersionUID = 1L;    /**Variável destinada a serialização**/

    private int currentState;                           /**Variável destinada ao estado atual**/

    private int height, width;                          /**Variáveis destinadas a altura e largura do tabuleiro**/

    private boolean vulneravel = false;                 /**Variável booleana destinada a identificar se o jogo esta no modo vulnerável**/

    private boolean ganhou = false;                     /**Variável booleana destinada a identificar se o jogo terminou com vitória**/

    private boolean perdeu = false;                     /**Variável booleana destinada a identificar se o jogo terminou com derrota**/

    private boolean emPausa = false;                    /**Variável booleana destinada a identificar se o jogo está em pausa**/

    private boolean resetGame = false;                  /**Variável booleana destinada a identificar se o jogo deve ser restado (quando o pacman encontra um fantasma mas tem um número de vidas superior a 0)**/

    private boolean TOP5atualizado = true;              /**Variável booleana destinada a verificar se o top5 precisa ser atualizado**/

    private Top5Players top5Players;                    /**Referência para um objeto do tipo Top5Players**/

    public Maze maze;                                   /**Referência para um objeto do tipo Maze**/

    private int pontos = 0, vidas = 3;                  /**Variáveis destinadas aos pontos acumulados e número de vidas do pacman**/

    static private int contadorTempo = 0;               /**Variável destinada a contar o tempo que se passou (para de contar no estado de pausa)**/

    static private int contadorTotalTempo = 0;          /**Variável destinada a contar o tempo que se passou total**/

    private int xFruta, yFruta;                         /**Variáveis destinadas a guardar as coordenadas x e y da Fruta para saber onde colocar a restauração da fruta**/

    private int tempoVulneravel = 40;                   /**Variável destinada a indicar a duração do estado vulnerável**/

    private int quantidadeFantasmasNaPosicaoInicial = 0;/**Variável destinada a contar quantos fantasmas voltaram para a posição inicial dos fantasmas quando eles estão em modo vulnerável**/

    private int quantidadeBolas;                        /**Variável destinada a indicar a quantidade de bolas presentes no maze**/

    private int nivel = 1;                              /**Variável destinada a indicar o nível do jogo**/

    private boolean isUp = false, isRight = false;      /**Variáveis booleanas destinada a indicar a direção do movimento do PacMan (UP e RIGHT)**/

    private boolean isDown = false, isLeft = false;     /**Variáveis booleanas destinada a indicar a direção do movimento do PacMan (DOWN e LEFT)**/



    //CONSTRUCTOR
    public Game(int currentState) {

        /**
         * CONSTRUTOR DA CLASSE GAME
         * Chama a função sizeOfMaze() para obter as dimensões do tabuleiro (height e width)
         * Chama a função fillMaze() para preencher o tabuleiro de acordo com o ficheiro de texto
         **/

        this.currentState = currentState;
        //ReadMaze.selectLevel(this);
        ReadMaze.sizeOfMaze(this);
        this.maze = new Maze(height, width);
        ReadMaze.fillMaze(this);
        top5Players = new Top5Players();
    }


    //GETTERS AND SETTERS
    public int getCurrentState() {
        /**
         * GETTER
         * Função que retorna um inteiro o qual indica o estado atual em que o jogo se encontra
         * **/
        return currentState;
    }

    public void setCurrentState(int currentState) {
        /**
         * SETTER
         * Função que atualiza o estado em que o jogo se encontra
         * **/
        this.currentState = currentState;
    }

    public int getHeight() {
        /**
         * GETTER
         * Função que retorna um inteiro o qual indica a altura do tabuleiro
         * **/
        return height;
    }

    public void setHeight(int height) {
        /**
         * SETTER
         * Função que atualiza a altura do tabuleiro
         * **/
        this.height = height;
    }

    public int getWidth() {
        /**
         * GETTER
         * Função que retorna um inteiro o qual indica a largura do tabuleiro
         * **/
        return width;
    }

    public void setWidth(int width) {
        /**
         * SETTER
         * Função que atualiza a largura do tabuleiro
         * **/
        this.width = width;
    }

    public boolean isVulneravel() {
        /**
         * GETTER
         * Função que retorna TRUE se o jogo estiver no estado vulnerável
         *            retorna FALSE se o jogo não estiver no estado vulnerável
         * **/
        return vulneravel;
    }

    public void setVulneravel(boolean vulneravel) {
        /**
         * SETTER
         * Função que atualiza a variável booleana "vulneravel"
         * **/
        this.vulneravel = vulneravel;
    }

    public boolean isGanhou() {
        /**
         * GETTER
         * Função que retorna TRUE se o nível do jogo for ganho
         *            retorna FALSE se o nível do jogo não estiver ganho
         * **/
        return ganhou;
    }

    public void setGanhou(boolean ganhou) {
        /**
         * SETTER
         * Função que atualiza a variável booleana "ganhou"
         * **/
        this.ganhou = ganhou;
    }

    public boolean isPerdeu() {
        /**
         * GETTER
         * Função que retorna TRUE se o pacman foi apanhado por um fantasma
         *            retorna FALSE caso contrário
         * **/
        return perdeu;
    }

    public void setPerdeu(boolean perdeu) {
        /**
         * SETTER
         * Função que atualiza a variável booleana "perdeu"
         * **/
        this.perdeu = perdeu;
    }

    public boolean isEmPausa() {
        /**
         * GETTER
         * Função que retorna TRUE se o jogo estiver no estado em pausa
         *            retorna FALSE se o jogo não estiver no estado em pausa
         * **/
        return emPausa;
    }

    public void setEmPausa(boolean emPausa) {
        /**
         * SETTER
         * Função que atualiza a variável booleana "emPausa"
         * **/
        this.emPausa = emPausa;
    }

    public boolean isResetGame(){
        /**
         * GETTER
         * Função que retorna TRUE se o jogo precisa ser resetado
         *            retorna FALSE se o jogo não precisa ser resetado
         * **/
        return resetGame;
    }

    public void setResetGame(boolean resetGame){
        /**
         * SETTER
         * Função que atualiza a variável booleana "resetGame"
         * **/
        this.resetGame = resetGame;
    }

    public int getPontos() {
        /**
         * GETTER
         * Função que retorna inteiro o qual representa a quantidade de pontos do jogo
         * **/
        return pontos;
    }

    public void setPontos(int pontos) {
        /**
         * SETTER
         * Função que atualiza a quantidade de pontos do jogo
         * **/
        this.pontos = pontos;
    }

    public int getVidas() {
        /**
         * GETTER
         * Função que retorna inteiro o qual representa a quantidade de vidas do PacMan
         * **/
        return vidas;
    }

    public void setVidas(int vidas) {
        /**
         * SETTER
         * Função que atualiza a quantidade de vidas do PacMan
         * **/
        this.vidas = vidas;
    }

    public Top5Players getTop5Players() {
        /**
         * GETTER
         * Função que retorna uma referência para Top5Players
         * **/
        return top5Players;
    }

    public Players getTop(int number){
        /**
         * GETTER
         * Função que recebe "number" e retorna as informações referentes ao player da posição "number"
         * **/
        return top5Players.getTop(number);
    }

    public boolean isTOP5atualizado() {
        /**
         * GETTER
         * Função que retorna TRUE se o TOP5 do jogo estiver atualizado
         *            retorna FALSE se o TOP5 jogo não estiver atualizado
         * **/
        return TOP5atualizado;
    }

    public void setTOP5atualizado(boolean TOP5atualizado) {
        /**
         * SETTER
         * Função que atualiza a variável "top5atualizado"
         * **/
        this.TOP5atualizado = TOP5atualizado;
    }

    public int getContadorTempo(){
        /**
         * GETTER
         * Função que retorna inteiro o qual representa o contadorTempo (quanto tempo se passou do jogo)
         * **/
        return contadorTempo;
    }

    public int getQuantidadeFantasmasNaPosicaoInicial() {
        /**
         * GETTER
         * Função que retorna inteiro o qual representa a quantidade de fantasmas na posição inicial
         * **/
        return quantidadeFantasmasNaPosicaoInicial;
    }

    public void setQuantidadeFantasmasNaPosicaoInicial(int quantidadeFantasmasNaPosicaoInicial) {
        /**
         * SETTER
         * Função que atualiza a quantidade de fantasmas na posição inicial
         * **/
        this.quantidadeFantasmasNaPosicaoInicial = quantidadeFantasmasNaPosicaoInicial;
    }

    public int getQuantidadeBolas() {
        /**
         * GETTER
         * Função que retorna inteiro o qual representa a quantidade de bolas presentes no jogo
         * **/
        return quantidadeBolas;
    }

    public void setQuantidadeBolas(int quantidadeBolas) {
        /**
         * SETTER
         * Função que atualiza a quantidade de bolas presentes no jogo
         * **/
        this.quantidadeBolas = quantidadeBolas;
    }

    public int getNivel() {
        /**
         * GETTER
         * Função que retorna inteiro o qual representa o nível do jogo
         * **/
        return nivel;
    }

    public void setNivel(int nivel) {
        /**
         * SETTER
         * Função que atualiza o nível do jogo
         * **/
        this.nivel = nivel;
    }

    public boolean isUp() {
        /**
         * GETTER
         * Função que retorna TRUE se a tecla pressionada pelo utilizador for a seta para cima
         *            retorna FALSE caso contrário
         * **/
        return isUp;
    }

    public void setUp(boolean up) {
        /**
         * SETTER
         * Função que atualiza se o movimento do PacMan deve ir para cima
         * **/
        isUp = up;
    }

    public boolean isRight() {
        /**
         * GETTER
         * Função que retorna TRUE se a tecla pressionada pelo utilizador for a seta para direita
         *            retorna FALSE caso contrário
         * **/
        return isRight;
    }

    public void setRight(boolean right) {
        /**
         * SETTER
         * Função que atualiza se o movimento do PacMan deve ir para direita
         * **/
        isRight = right;
    }

    public boolean isDown() {
        /**
         * GETTER
         * Função que retorna TRUE se a tecla pressionada pelo utilizador for a seta para baixo
         *            retorna FALSE caso contrário
         * **/
        return isDown;
    }

    public void setDown(boolean down) {
        /**
         * SETTER
         * Função que atualiza se o movimento do PacMan deve ir para baixo
         * **/
        isDown = down;
    }

    public boolean isLeft() {
        /**
         * GETTER
         * Função que retorna TRUE se a tecla pressionada pelo utilizador for a seta para esquerda
         *            retorna FALSE caso contrário
         * **/
        return isLeft;
    }

    public void setLeft(boolean left) {
        /**
         * SETTER
         * Função que atualiza se o movimento do PacMan deve ir para esquerda
         * **/
        isLeft = left;
    }

    public char [][] getGameMaze() {
        /**
         * GETTER
         * Função que devolve um char [][] que representa o tabuleiro
         * **/
        return maze.getMaze();
    }


    //METHODS
    public boolean evolve() {
        /**
         * Função que permite o movimento dos fantasmas e do PacMan, contar o tempo passado, verificar se a fruta tem de ser reposta,
         * verificar se o jogo foi ganho, administra o tempo do jogo no estado vulnerável
         * **/

        boolean oneMovimentPacman = true;
        boolean oneMovimentBlinky = true;
        boolean oneMovimentClyde = true;
        boolean oneMovimentPinky = true;
        boolean oneMovimentInky = true;
        boolean frutaPresente = false;

        contadorTotalTempo++;

        ModelLog.getInstance().getLog().forEach(System.out::println);
        ModelLog.getInstance().reset();

        if(isGanhou() || isPerdeu() || isEmPausa())
            return false;

        if(getQuantidadeBolas() == 0){
            setGanhou(true);
        }

        if(isVulneravel()){
            tempoVulneravel--;
            if(tempoVulneravel == 0){
                setVulneravel(false);
                tempoVulneravel = 10;
            }
        }

        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){

                IMazeElement element = maze.get(y, x);

                if(element instanceof MazeElementFruta){
                    xFruta = x;
                    yFruta = y;
                    if(contadorTotalTempo % 20 == 0){
                        frutaPresente = true;
                    }
                }

                if(element instanceof Agents agent) {
                    if (element instanceof PacMan) {
                        if(oneMovimentPacman){
                            if(vulneravel == true){
                                agent.evolveVulneravel(y, x);
                            }
                            else{
                                agent.evolve(y, x);
                            }
                            oneMovimentPacman = false;
                        }
                    }
                    else if(element instanceof Blinky){
                        if(oneMovimentBlinky){
                            if(vulneravel == true){
                                agent.evolveVulneravel(y, x);
                            }
                            else {
                                agent.evolve(y, x);
                            }
                            oneMovimentBlinky = false;
                        }
                    }
                    else if(element instanceof Clyde){
                        if(oneMovimentClyde){
                            if(vulneravel == true){
                                agent.evolveVulneravel(y, x);
                            }
                            else {
                                agent.evolve(y, x);
                            }
                            oneMovimentClyde = false;
                        }
                    }
                    else if(element instanceof Pinky){
                        if(oneMovimentPinky){
                            if(vulneravel == true){
                                agent.evolveVulneravel(y, x);
                            }
                            else {
                                agent.evolve(y, x);
                            }
                            oneMovimentPinky = false;
                        }
                    }
                    else if(element instanceof Inky){
                        if(oneMovimentInky){
                            if(vulneravel == true){
                                agent.evolveVulneravel(y, x);
                            }
                            else {
                                agent.evolve(y, x);
                            }
                            oneMovimentInky = false;
                        }
                    }
                    else {
                        agent.evolve(y, x);
                    }
                }
            }
        }

        if(frutaPresente == false && contadorTotalTempo % 20 == 0){
            maze.set(yFruta, xFruta, new MazeElementFruta());
        }

        mostra_maze();

        if(!isEmPausa()) {
            contadorTempo++;
        }

        return true;
    }

    public void mostra_maze() {
        /**
         * Função que imprime na console o tabuleiro
         * **/
        System.out.println();
        char [][] tab = maze.getMaze();
        for(int y=0; y<tab.length; y++){
            for(int x=0; x<tab[0].length ; x++){
                System.out.print(tab[y][x]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public void movimentoPacman(Direcao direcao){
        /**
         * Função que atualiza a direção do movimento do PacMan
         * **/

        if(direcao == UP){
            setUp(true);
            setRight(false);
            setDown(false);
            setLeft(false);
        }
        else if(direcao == RIGHT){
            setUp(false);
            setRight(true);
            setDown(false);
            setLeft(false);
        }
        else if(direcao == DOWN){
            setUp(false);
            setRight(false);
            setDown(true);
            setLeft(false);
        }
        else if(direcao == LEFT){
            setUp(false);
            setRight(false);
            setDown(false);
            setLeft(true);
        }
    }

    public void necessitaAtualizarTop5(){
        /**
         * Função que percorre o TOP5 e verifica se o novo jogo é maior que um deles. Se for maior, muda o valor booleano da variável "TOP5atualizado" para false
         * **/
        for(int i=0; i<5; i++) {
            if (pontos > top5Players.getTop(i).getPontuacao()){
                setTOP5atualizado(false);
            }
        }
    }

    public boolean atualizaTop5(String nomeJogador){
        /**
         * Função que determina qual posição do TOP5 o novo jogador deve ocupar e por fim, atualiza o TOP5
         * **/
        for(int i=0; i<5; i++) {
            if (pontos > top5Players.getTop(i).getPontuacao()){
                top5Players.addPlayerToTop5(new Players(nomeJogador,pontos), i);
                setTOP5atualizado(true);
                return true;
            }
        }
        return false;
    }

    public void resetGame(){
        /**
         * Função que da reset ao jogo quando o PacMan morre mais ainda tem vidas
         * **/
        pontos = 0;
        contadorTempo = 0;
        contadorTotalTempo = 0;
        ReadMaze.sizeOfMaze(this);
        this.maze = new Maze(height, width);
        ReadMaze.fillMaze(this);
    }

    public String msgFinal(){
        /**
         * Função que representa o status de "VITÓRIA" ou de "DERROTA" ao fim de um jogo
         * **/
        if(ganhou){
            return "VITÓRIA";
        }
        else
            return "DERROTA";
    }
}