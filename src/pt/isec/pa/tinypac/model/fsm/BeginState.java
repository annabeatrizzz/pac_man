package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.*;
import java.io.Serializable;

public class BeginState extends StateAdapter implements Serializable {

    /**
     * ESTADO INICIAL DA MÁQUINA DE ESTADOS
     * labirinto e respectivo conteúdo no ecrã mas sem movimentos ainda
     */

    //ATTRIBUTES
    private static final long serialVersionUID = 1L;


    //CONSTRUCTOR
    public BeginState(Context context, Game game) {
        super(context, game);
        game.setCurrentState(1);    //indica que o estado corresponde ao 1 da enumeracao: INICIO
        System.out.println("BEGIN STATE");
        mostra_maze_inicial();
    }


    //METHODS
    public void mostra_maze_inicial() {
        for(int y=0; y<game.getHeight(); y++){
            for(int x=0; x<game.getWidth(); x++){
                IMazeElement elemento;
                elemento = game.maze.get(y,x);
                System.out.print(elemento.getSymbol());
            }
            System.out.println();
        }
    }

    @Override
    public boolean primeiro_movimento(Direcao direcao) {

        /**
         * o jogador pressionou alguma tecla de movimento e, portanto, passamos ao estado INGAMESTATE ou EM_JOGO
         */

        game.evolve();
        changeState(new InGameState(context, game));
        return true;
    }

    @Override
    public States getState() {
        return States.INICIO;
    }
}