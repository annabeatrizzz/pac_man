package pt.isec.pa.tinypac.ui.text;

import pt.isec.pa.tinypac.model.fsm.Context;
import pt.isec.pa.tinypac.model.fsm.States;

public class TextUI {
    Context fsm;
    boolean finish = false;

    public TextUI(Context fsm) {
        this.fsm = fsm;
    }

    public void start() {
        while(!finish) {
            switch(fsm.getState()) {
                case INICIO -> inicioUI();
                case EM_JOGO -> emJogoUI();
                case EM_PAUSA -> emPausaUI();
                case VULNERAVEL -> vulneravelUI();
                case FIM -> fimUI();
            }
        }
    }

    public void inicioUI() {
        System.out.println("*********************************");
        System.out.println("INICIO OPCOES (BeginState)");
        switch(PAInput.chooseOption("selecione um numero para movimentar-se", "UP", "DOWN", "RIGHT", "LEFT", "Quit")) {

            //alteracao 1 - movimento PacMan por teclas
            case 1, 2, 3, 4 -> fsm.primeiro_movimento(null);
            case 5-> {//fsm.quit();
                finish = true;}
        }
    }
    public void emJogoUI() {
        System.out.println("*********************************");
        System.out.println("EM JOGO OPCOES (InGameState)");
        switch(PAInput.chooseOption("selecione", "vitoria ou derrota", "vulneravel", "pausa","continua", "Quit")) {
            case 1-> fsm.fim_jogo();
            case 2-> fsm.vulneravel();
            case 3-> fsm.pausa_jogo();
            case 4 -> fsm.evolve();
            case 5-> {finish = true;}
        }
    }
    public void emPausaUI() {
        System.out.println("*********************************");
        System.out.println("EM PAUSA OPCOES (PausedState)");
        switch(PAInput.chooseOption("Decisoes",  "Voltar ao jogo","Quit")) {
            case 1-> {
                fsm.pausa_jogo();
            }
            case 2-> {finish = true;}
        }
    }
    public void vulneravelUI() {
        System.out.println("*********************************");
        System.out.println("VULNERAVEL OPCOES");
        switch(PAInput.chooseOption("vulneravel", "sair do vulneravel", "pausa", "continuar no vulneravel", "Quit")) {
            case 1-> fsm.vulneravel();
            case 2-> fsm.pausa_jogo();
            case 3-> fsm.evolve();
            case 4-> {finish = true;}
        }
    }
    public void fimUI() {
        System.out.println("*********************************");
        System.out.println("FIM OPCOES\nFim do jogo");
        switch(PAInput.chooseOption("Decide", "Quit")) {
            case 1-> {finish = true;}
        }
    }
}