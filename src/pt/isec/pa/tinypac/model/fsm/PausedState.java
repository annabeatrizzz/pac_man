package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Game;
import java.io.Serializable;

public class PausedState extends StateAdapter implements Serializable {

    /**
     * ESTADO PAUSE DA MÁQUINA DE ESTADOS
     * com o jogo em pausa é possível salvar o progresso do jogo, voltar ao jogo e sair do jogo
     */

    //ATTRIBUTES
    private static final long serialVersionUID = 1L;

    //CONSTRUCTOR
    public PausedState(Context context, Game game) {
        super(context, game);
        game.setCurrentState(3);  //indica que o estado corresponde ao 3 da enumeracao: EM_PAUSA
        System.out.println("PAUSED STATE");
    }

    //METHODS
    @Override
    public boolean pausa_jogo(){

        /**
         * o jogador deseja voltar ao jogo e, portanto, passamos ao estado INGAMESTATE ou EM_JOGO OU ao estado VULNERABLESTATE ou VULNERAVEL
         */

        if(game.isEmPausa() && game.isVulneravel() == false){
            game.setEmPausa(false);
            changeState(new InGameState(context, game));
            return true;
       }
        else if(game.isEmPausa() && game.isVulneravel()){
            game.setEmPausa(false);
            changeState(new VulnerableState(context, game));
            return true;
        }
        return false;
    }

    @Override
    public States getState() {
        return States.EM_PAUSA;
    }
}