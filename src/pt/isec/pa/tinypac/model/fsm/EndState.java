package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Game;
import java.io.Serializable;

public class EndState extends StateAdapter implements Serializable {

    /**
     * ESTADO FINAL DA MÁQUINA DE ESTADOS
     */

    //ATTRIBUTES
    private static final long serialVersionUID = 1L;


    //CONSTRUCTOR
    public EndState(Context context, Game game) {
        super(context, game);
        game.setCurrentState(5);  //indica que o estado corresponde ao 5 da enumeracao: FIM
        System.out.println("END STATE");
    }


    //METHODS
    @Override
    public boolean passarDeNivel(){
        /*if(game.isGanhou() && game.getNivel()<20){
            game.setNivel(game.getNivel()+1);
            GameManager model = new GameManager();
            //Game g2 = new Game(1);
            changeState(new BeginState(model.getFsm(), model.getFsm().getGame()));
            return true;
        }
        else{*/
            return false;
        //}
    }

    @Override
    public States getState() {
        return States.FIM;
    }
}