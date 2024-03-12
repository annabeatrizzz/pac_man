package pt.isec.pa.tinypac.model.fsm;

import javafx.scene.input.KeyCode;
import pt.isec.pa.tinypac.model.data.Direcao;

import java.io.File;
import java.io.Serializable;

public interface IState extends Serializable {

    /**
     * interface com os metodos que representam todas as transições
     * **/

    boolean primeiro_movimento(Direcao direcao);
    boolean pausa_jogo();
    boolean fim_jogo();
    boolean vulneravel();
    boolean evolve();
    boolean reset_game();
    boolean passarDeNivel();
    States getState();
}