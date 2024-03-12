package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Direcao;
import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.ReadMaze;

import java.io.Serializable;

public class InGameState extends StateAdapter implements Serializable {

    /**
     * ESTADO NORMAL DO JOGO DA MÁQUINA DE ESTADOS
     * jogo em situacao normal
     */


    //ATTRIBUTES
    private static final long serialVersionUID = 1L;


    //CONSTRUCTOR
    public InGameState(Context context, Game game) {
        super(context, game);
        game.setCurrentState(2);  //indica que o estado corresponde ao 2 da enumeracao: EM_JOGO
        System.out.println("IN GAME STATE");
    }


    //METHODS
    @Override
    public boolean fim_jogo() {

        /**
         * o jogo terminou, passamos ao estado ENDSTATE ou FIM
         */

        if(game.isGanhou() == true || (game.isPerdeu() == true && game.getVidas() == 0)){
            game.necessitaAtualizarTop5();
            changeState(new EndState(context, game));
            return true;
        }
        return false;
    }

    @Override
    public boolean vulneravel() {

        /**
         * o pacman comeu a bola com poderes, passamos ao estado VULNERABLESTATE ou VULNERAVEL
         */

        if(game.isVulneravel() == true){
            changeState(new VulnerableState(context, game));
            return true;
        }
        return false;
    }

    @Override
    public boolean pausa_jogo() {

        /**
         * o utilizador deseja parar o jogo, passamos ao estado PAUSEDSTATE ou EM_PAUSA
         */

       game.setEmPausa(true);
       changeState(new PausedState(context, game));
       return true;
    }

    @Override
    public boolean reset_game() {

        /**
         * quando o pacman morre mas ainda tem vida, o jogo deve ser reiniciado
         */

        game.resetGame();
        //changeState(new BeginState(context, game));
        return true;
    }

    @Override
    public boolean evolve() {

        /**
         * função que permite o constante funcionamento do jogo
         */

        if(game.isPerdeu() == true && game.getVidas() > 0){
            reset_game();
        }

        if(game.isVulneravel() == true){
            vulneravel();
        }

        if(game.isGanhou() == true || (game.isPerdeu() == true && game.getVidas() == 0)){
            fim_jogo();
        }

        game.evolve();
        return true;
    }

    @Override
    public boolean primeiro_movimento(Direcao direcao) {

        /**
         * o utilizador pressiona uma tecla de movimento para mover o pacman
         */

        game.evolve();
        return true;
    }

    @Override
    public States getState() {
        return States.EM_JOGO;
    }

}