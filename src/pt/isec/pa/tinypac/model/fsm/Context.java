package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Direcao;
import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.Players;
import pt.isec.pa.tinypac.model.data.Top5Players;
import java.io.*;

public class Context implements Serializable {

    /**
     * Classe destinada a permitir as modificações no modelo de dados ocorram apenas no contexto dos métodos relacionados as transições de estado
     */

    //ATTRIBUTES
    private static final long serialVersionUID = 1L;        /**Variável destinada a serialização**/

    private IState state;                                   /**Referência para um objeto do tipo IState**/

    private Game game;                                      /**Referência para um objeto do tipo Game**/


    //CONSTRUCTOR
    public Context(){

        /**
         * CONSTRUTOR DA CLASSE CONTEXT
         * Inicializa o jogo (Game) no estado 1: INICIO
         **/

        game = new Game(1); //jogo comeca em 1: INICIO
        state = new BeginState(this, game);
    }


    //GETTERS AND SETTERS
    public States getState() {
        /**
         * GETTER
         * Função que retorna o estado atual em que o jogo se encontra
         * **/
        return state.getState();
    }

    public void changeState(IState newState) {
        /**
         * SETTER
         * Função que permite atualiza/mudar o estado em que o jogo se encontra
         * **/
        this.state = newState;
    }

    public char [][] getGameMaze(){
        /**
         * GETTER
         * Função que devolve o resultado da função getGameMaze() presente na classe Game
         * **/
        return game.getGameMaze();
    }

    public boolean getGameVulneravel() {
        /**
         * GETTER
         * Função que devolve o resultado da função isVulneravel() presente na classe Game
         * **/
        return game.isVulneravel();
    }

    public boolean getGameVitoria() {
        /**
         * GETTER
         * Função que devolve o resultado da função isGanhou() presente na classe Game
         * **/
        return game.isGanhou();
    }

    public boolean getGameDerrota() {
        /**
         * GETTER
         * Função que devolve o resultado da função isPerdeu() presente na classe Game
         * **/
        return game.isPerdeu();
    }

    public boolean getGameEmPausa(){
        /**
         * GETTER
         * Função que devolve o resultado da função isEmPausa() presente na classe Game
         * **/
        return game.isEmPausa();
    }

    public int getGameVidas() {
        /**
         * GETTER
         * Função que devolve o resultado da função getVidas() presente na classe Game
         * **/
        return game.getVidas();
    }

    public int getGamePontos() {
        /**
         * GETTER
         * Função que devolve o resultado da função getPontos() presente na classe Game
         * **/
        return game.getPontos();
    }

    public Players getTop(int number){
        /**
         * GETTER
         * Função que devolve o resultado da função getTop(number) presente na classe Game
         * **/
        return game.getTop(number);
    }

    public boolean isTOP5atualizado() {
        /**
         * GETTER
         * Função que devolve o resultado da função isTOP5atualizado() presente na classe Game
         * **/
        return game.isTOP5atualizado();
    }

    public Top5Players getTop5Players() {
        /**
         * GETTER
         * Função que devolve o resultado da função getTop5Players() presente na classe Game
         * **/
        return game.getTop5Players();
    }

    public int getContadorTempo(){
        /**
         * GETTER
         * Função que devolve o resultado da função getContadorTempo() presente na classe Game
         * **/
        return game.getContadorTempo();
    }

    /*public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getNivel(){
        return game.getNivel();
    }*/


    //METHODS
    public boolean primeiro_movimento(Direcao direcao) {
        /**
         * Função que chama a função movimentoPacman() da classe Game e também a função primeiro_movimento() do seu respectivo estado
         * **/
        game.movimentoPacman(direcao);
        return state.primeiro_movimento(direcao);
    }

    public boolean pausa_jogo() {
        /**
         * Função que chama a função pausa_jogo() do seu respectivo estado
         * **/
        return state.pausa_jogo();
    }

    public boolean fim_jogo() {
        /**
         * Função que chama a função fim_jogo() do seu respectivo estado
         * **/
        return state.fim_jogo();
    }

    public boolean vulneravel() {
        /**
         * Função que chama a função vulneravel() do seu respectivo estado
         * **/
        return state.vulneravel();
    }

    public boolean evolve() {
        /**
         * Função que chama a função evolve() do seu respectivo estado
         * **/
        return state.evolve();
    }

    /*public boolean passarDeNivel() {
        return state.passarDeNivel();
    }*/

    public void loadGame(){
        /**
         * Função que muda o estado para PAUSEDSTATE
         * **/
        changeState(new PausedState(this, game));
    }

    public boolean atualizaTop5(String nomeJogador){
        /**
         * GETTER
         * Função que devolve o resultado da função atualizaTop5() presente na classe Game
         * **/
        return game.atualizaTop5(nomeJogador);
    }

    public String msgFinal(){
        /**
         * GETTER
         * Função que devolve o resultado da função msgFinal() presente na classe Game
         * **/
        return game.msgFinal();
    }

}