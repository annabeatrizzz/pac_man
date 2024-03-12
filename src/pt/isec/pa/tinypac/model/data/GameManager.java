package pt.isec.pa.tinypac.model.data;

import javafx.scene.input.KeyCode;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.fsm.Context;
import pt.isec.pa.tinypac.model.fsm.States;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;

public class GameManager implements Serializable {

    /**
     * Classe destinada a gerenciar o modelo de dados, permite que sejam feitas atualizações nas interfaces (sinalizando as mudanças de propriedades)
     * Apresenta o padrão observável, permitindo que sejam realizadas mudanças nas vistas de maneira assíncrona
     */

    //ATTRIBUTES
    private static final long serialVersionUID = 1L;             /**Variável destinada a serialização**/

    Context fsm;                                                 /**Referência para um objeto do tipo Context**/

    PropertyChangeSupport pcs;                                   /**Referência para um objeto do tipo Property Change Support**/

    GameEngine gameEngine;                                       /**Referência para um objeto do tipo GameEngine -> motor de jogo**/

    private boolean flagFirstTime = true;                        /**Variável booleana destinada a verificar se é a primeira vez, tem o funcionamento de uma flag para criar o GameEngine apenas 1 vez**/

    private boolean temJogoSalvo = false;                        /**Variável booleana destinada a indicar se já existe algum jogo salvo**/

    private File file = new File("bbbbb.bin");           /**Variável destinada a armazenar o nome do ficheiro onde será salvo o jogo**/


    //CONSTRUCTOR
    public GameManager() {

        /**
         * CONSTRUTOR DA CLASSE GAMEMANAGER
         * Responsável por criar uma instância de Context, PropertyChangeSupport e GameEngine
         **/

        this.flagFirstTime = true;
        fsm = new Context();
        pcs = new PropertyChangeSupport(fsm);
        gameEngine = new GameEngine();
    }


    //observer/observable management
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }


    //GETTERS AND SETTERS
    public int getContadorTempo(){
        /**
         * GETTER
         * Função que devolve o resultado da função getContadorTempo() presente na classe Context
         * **/
        return fsm.getContadorTempo();
    }

    public boolean isTemJogoSalvo() {
        /**
         * GETTER
         * Função que retorna TRUE se o ficheiro existir
         *            retorna FALSE caso contrário
         * **/
        return file.exists();
    }

    public Context getFsm() {
        /**
         * GETTER
         * Devolve a instância de Context
         * **/
        return fsm;
    }

    public void setFsm(Context fsm) {
        /**
         * SETTER
         * Atualiza a instância de Context
         * **/
        this.fsm = fsm;
    }


    //get data
    public States getState() {
        /**
         * GETTER
         * Função que devolve o resultado da função getState() presente na classe Context
         * **/
        return fsm.getState();
    }

    public char [][] getGameMaze() {
        /**
         * GETTER
         * Função que devolve o resultado da função getGameMaze() presente na classe Context
         * **/
        return fsm.getGameMaze();
    }

    public boolean isVulneravel() {
        /**
         * GETTER
         * Função que devolve o resultado da função getGameVulneravel() presente na classe Context
         * (informa se o jogo esta no estado vulnerável)
         * **/
        return fsm.getGameVulneravel();
    }

    public boolean isGanhou() {
        /**
         * GETTER
         * Função que devolve o resultado da função getGameVitoria() presente na classe Context
         * (informa se o jogo foi vencido)
         * **/
        return fsm.getGameVitoria();
    }

    public boolean isPerdeu() {
        /**
         * GETTER
         * Função que devolve o resultado da função getGameDerrota() presente na classe Context
         * (informa se o jogo foi perdido)
         * **/
        return fsm.getGameDerrota();
    }

    public boolean isEmPausa() {
        /**
         * GETTER
         * Função que devolve o resultado da função getGameEmPausa() presente na classe Context
         * (informa se o jogo esta em pausa)
         * **/
        return fsm.getGameEmPausa();
    }

    public int getVidas() {
        /**
         * GETTER
         * Função que devolve o resultado da função getGameVidas() presente na classe Context
         * (devolve o número de vidas do jogo)
         * **/
        return fsm.getGameVidas();
    }

    public int getPontos() {
        /**
         * GETTER
         * Função que devolve o resultado da função getGamePontos() presente na classe Context
         * (devolve os pontos do jogo)
         * **/
        return fsm.getGamePontos();
    }

    public Players getTop(int number){
        /**
         * GETTER
         * Função que devolve o resultado da função getTop() presente na classe Context
         * **/
        return fsm.getTop(number);
    }

    public boolean isTOP5atualizado() {
        /**
         * GETTER
         * Função que devolve o resultado da função isTOP5atualizado() presente na classe Context
         * **/
        return fsm.isTOP5atualizado();
    }

    public boolean atualizaTop5(String nomeJogador){
        /**
         * SETTER
         * Função que devolve o resultado da função atualizaTop5() presente na classe Context
         * **/
        return fsm.atualizaTop5(nomeJogador);
    }


    //METHODS
    public boolean primeiro_movimento(KeyCode keyCode){
        /**
         * Função que recebe a tecla pressionada (keyCode) e chama a função processaKeyCode() para então indica a direção atráves da enumeração.
         * Se for a primeira vez que uma tecla for pressionada deve criar um GameEngine.
         * A função também é responsável por chamar primeiro_movimento() do Context e mandar atualizações de sincronização para a vista
         * **/
        Direcao direcao = processaKeyCode(keyCode);

        if(flagFirstTime == true) {
            gameEngine.registerClient((g, t) -> this.evolve());
            gameEngine.start(500);
            flagFirstTime = false;
        }

        //Context fsm = new Context();
        boolean res = fsm.primeiro_movimento(direcao);
        pcs.firePropertyChange(null,null,null);
        System.out.print(fsm.getState());
        return res;
    }

    public boolean pausa_jogo(){
        /**
         * Função chama pausa_jogo() do Context e mandar atualizações de sincronização para a vista
         * **/
        boolean res = fsm.pausa_jogo();
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean fim_jogo(){
        /**
         * Função chama fim_jogo() do Context e mandar atualizações de sincronização para a vista
         * **/
        boolean res = fsm.fim_jogo();
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean vulneravel(){
        /**
         * Função chama vulneravel() do Context e mandar atualizações de sincronização para a vista
         * **/
        boolean res = fsm.vulneravel();
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean evolve(){
        /**
         * Função chama evolve() do Context e mandar atualizações de sincronização para a vista
         * **/
        boolean res = fsm.evolve();
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    /*public boolean passarDeNivel(){
        flagFirstTime = true;
        boolean res = fsm.passarDeNivel();
        pcs.firePropertyChange(null,null,null);
        return res;
    }*/

    public boolean saveGame(){
        /**
         * Função destinada a salvar o jogo, mais especificamente a classe Context por meio da serialização e armazenar seu conteúdo no ficheiro binário
         * **/
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(fsm);
        }catch(Exception e){
            System.err.println("Erro ao salvar o progresso do jogo");
            return false;
        }
        return true;
    }

    public boolean loadGame(){
        /**
         * Função de recuperação do jogo, mais especificamente a classe Context por meio da deserialização do ficheiro binário
         * **/
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            fsm = (Context) ois.readObject();

            if(flagFirstTime) {
                gameEngine.registerClient((g, t) -> this.evolve());
                gameEngine.start(500);
                flagFirstTime = false;
            }

            fsm.loadGame();
            pcs.firePropertyChange(null,null,null);
        }catch(Exception e){
            System.err.println("Erro em carregar o jogo salvo");
            return false;
        }
        return true;
    }

    public void deletaJogoSalvo(){
        /**
         * Função responsável por permitir que o ficheiro salvo exista apenas até a própria execução do programa.
         * **/
        if(file.exists()){
            try{
                file.delete();
            }catch(Exception e){
                System.err.println("Erro ao deletar o ficheiro com jogo salvo");
            }
        }
    }

    public String msgFinal(){
        /**
         * Função chama msgFinal() do Context, sem a necessidade de sincronização porque a decissão da mensagem é fixa
         * **/
        return fsm.msgFinal();
    }

   /*public int getNivel(){
        return fsm.getNivel();
    }*/

    public Direcao processaKeyCode(KeyCode keyCode){
        /**
         * Função destinada ao tratamento do keyCode, indicando qual a direção que o PacMan deve mover-se
         * **/
        Direcao direcao = null;

        switch(keyCode){
            case UP -> direcao = Direcao.UP;
            case RIGHT -> direcao = Direcao.RIGHT;
            case DOWN -> direcao = Direcao.DOWN;
            case LEFT -> direcao = Direcao.LEFT;
        }

        return direcao;
    }

}