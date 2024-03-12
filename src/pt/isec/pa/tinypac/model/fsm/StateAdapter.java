package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Direcao;
import pt.isec.pa.tinypac.model.data.Game;

abstract class StateAdapter implements IState {

    /**
     * default implementation
     */


    //ATTRIBUTES
    protected Game game;
    protected Context context;


    //CONSTRUCTOR
    public StateAdapter(Context context, Game game){
        this.context = context;
        this.game = game;
    }


    //METHODS
    protected void changeState(IState novoEstado) {
        context.changeState(novoEstado);
    }

    @Override
    public boolean primeiro_movimento(Direcao direcao) {
        return false;
    }

    @Override
    public boolean pausa_jogo() {
        return false;
    }

    @Override
    public boolean fim_jogo() {
        return false;
    }

    @Override
    public boolean vulneravel() {
        return false;
    }

    @Override
    public boolean evolve() {
        return false;
    }

    @Override
    public boolean reset_game() {
        return false;
    }

    @Override
    public boolean passarDeNivel(){
        return false;
    }
}