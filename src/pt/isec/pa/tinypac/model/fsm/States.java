package pt.isec.pa.tinypac.model.fsm;

import java.io.Serializable;

public enum States implements Serializable {

    /**
     * ENUMERAÇÃO DOS ESTADOS
     * permite a identificacao de todos os estados
     * **/

    INICIO, EM_JOGO, EM_PAUSA, VULNERAVEL, FIM;
}