package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Direcao;
import pt.isec.pa.tinypac.model.data.Game;
import java.io.Serializable;

public class VulnerableState extends StateAdapter implements Serializable {

    /**
     * ESTADO VULNERÁVEL DA MÁQUINA DE ESTADOS
     * pacman pode comer os fantasmas
     */


    //ATTRIBUTES
    private static final long serialVersionUID = 1L;


    //CONSTRUCTOR
    public VulnerableState(Context context, Game game) {
        super(context, game);
        game.setCurrentState(4);  //indica que o estado corresponde ao 4 da enumeracao: VULNERAVEL
        System.out.println("VULNERABLE STATE");
    }


    //METHODS
    @Override
    public boolean vulneravel() {

        /**
         * o jogo não tem mais o comportamento de vulnerável, passamos ao estado normal INGAMESTATE ou EM_JOGO
         */


        changeState(new InGameState(context, game));
            return true;
    }

    @Override
    public boolean pausa_jogo() {

        /**
         * o utilizador deseja pausar o jogo, passamos ao estado PAUSEDSTATE ou EM_PAUSA
         */

        game.setEmPausa(true);
        changeState(new PausedState(context, game));
        return true;
    }

    @Override
    public boolean evolve() {

        /**
         * função que permite o constante funcionamento do jogo
         */

        game.evolve();

        if(!game.isVulneravel()){
            vulneravel();
        }

        if(game.isGanhou()){
            fim_jogo();
        }

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
    public boolean fim_jogo() {

        /**
         * o jogo terminou, passamos ao estado ENDSTATE ou FIM
         */

        if(game.isGanhou() == true){
            changeState(new EndState(context, game));
            return true;
        }
        return false;
    }

    @Override
    public States getState() {
        return States.VULNERAVEL;
    }
}