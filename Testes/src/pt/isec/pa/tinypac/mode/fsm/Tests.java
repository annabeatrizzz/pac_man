package pt.isec.pa.tinypac.mode.fsm;

import org.junit.Test;
import static org.junit.Assert.*;
import static pt.isec.pa.tinypac.model.fsm.States.*;
import pt.isec.pa.tinypac.model.data.Direcao;
import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.*;

import java.io.Serializable;

public class Tests implements Serializable {

    @Test
    public void BeginState_teste_primeiroMovimentoUp(){
        //TESTE para verificar se quando no estado BEGINSTATE e pressionado uma tecla de movimento resulta em mudança de estado para INGAMESTATE

        Game game = new Game(1);
        Context context = new Context();

        context.primeiro_movimento(Direcao.UP);
        States estado = context.getState();

        assertEquals(EM_JOGO, estado);
    }

    @Test
    public void BeginState_teste_pausa(){
        //TESTE para verificar se quando no estado BEGINSTATE e´ possivel colocar o jogo no estado PAUSESTATE
        //nao deve ser permitido, por isso, deve manter-se no mesmo estado

        Game game = new Game(1);
        Context context = new Context();

        game.setEmPausa(true);
        context.pausa_jogo();
        States estado = context.getState();

        assertEquals(INICIO, estado);
    }

    @Test
    public void BeginState_teste_vulneravel(){
        //TESTE para verificar se quando no estado BEGINSTATE e´ possivel colocar o jogo no estado VULNERABLESTATE
        //nao deve ser permitido, por isso, deve manter-se no mesmo estado

        Game game = new Game(1);
        Context context = new Context();

        game.setVulneravel(true);
        context.vulneravel();
        States estado = context.getState();

        assertEquals(INICIO, estado);
    }

    @Test
    public void InGameState_teste_pausa(){
        //TESTE para verificar se quando no estado INGAMESTATE e´ possivel colocar o jogo no estado PAUSESTATE

        Game game = new Game(1);
        Context context = new Context();

        context.primeiro_movimento(Direcao.UP);
        game.setEmPausa(true);
        context.pausa_jogo();
        States estado = context.getState();

        assertEquals(EM_PAUSA, estado);
    }

   @Test
    public void InGameState_teste_vulneravel(){
        //TESTE para verificar se quando no estado INGAMESTATE e´ possivel colocar o jogo no estado VULNERABLESTATE

        Game game = new Game(1);
        Context context = new Context();

        context.changeState(new InGameState(context, game));
        game.setVulneravel(true);
        context.evolve();
        States estado = context.getState();

        assertEquals(VULNERAVEL, estado);
    }

    @Test
    public void InGameState_teste_fim_jogo(){
        //TESTE para verificar se quando no estado INGAMESTATE e´ possivel colocar o jogo no estado ENDSTATE

        Game game = new Game(1);
        Context context = new Context();

        context.changeState(new InGameState(context, game));
        game.setPerdeu(true);
        game.setVidas(0);
        context.evolve();
        States estado = context.getState();

        assertEquals(FIM, estado);
    }

    @Test
    public void PausedState_teste_despausa(){
        //TESTE para verificar se quando no estado PAUSESTATE e´ possivel voltar ao estado INGAMESTATE

        Game game = new Game(1);
        Context context = new Context();

        context.primeiro_movimento(Direcao.UP);
        game.setEmPausa(true);
        context.pausa_jogo();
        game.setEmPausa(false);
        context.pausa_jogo();
        States estado = context.getState();

        assertEquals(EM_JOGO, estado);
    }

    @Test
    public void PausedState_teste_despausaVulneravel(){
        //TESTE para verificar se quando no estado PAUSESTATE e´ possivel voltar ao estado VULNERABLESTATE

        Game game = new Game(1);
        Context context = new Context();

        context.changeState(new PausedState(context, game));
        game.setEmPausa(true);
        game.setVulneravel(true);
        context.pausa_jogo();
        States estado = context.getState();

        assertEquals(VULNERAVEL, estado);
    }

    @Test
    public void VulnerableState_teste_pausa(){
        //TESTE para verificar se quando no estado VULNERABLESTATE e´ possivel ir ao estado PAUSESTATE

        Game game = new Game(1);
        Context context = new Context();

        context.changeState(new VulnerableState(context, game));
        game.setVulneravel(true);
        game.setEmPausa(true);
        context.pausa_jogo();
        States estado = context.getState();

        assertEquals(EM_PAUSA, estado);
    }

    @Test
    public void VulnerableState_teste_vulneravel(){
        //TESTE para verificar se quando no estado VULNERABLESTATE e´ possivel ir ao estado INGAMESTATE

        Game game = new Game(1);
        Context context = new Context();

        context.changeState(new VulnerableState(context, game));
        //game.setVulneravel(false);
        context.vulneravel();
        States estado = context.getState();

        assertEquals(EM_JOGO, estado);
    }

    @Test
    public void VulnerableState_teste_fim_jogo_perdeu(){
        //TESTE para verificar se quando no estado VULNERABLESTATE e´ possivel ir ao estado ENDSTATE
        //nao deve ser permitido, por isso, deve manter-se no mesmo estado. Pois é impossível perder no estado VULNERAVEL

        Game game = new Game(1);
        Context context = new Context();

        context.changeState(new VulnerableState(context, game));
        game.setPerdeu(true);
        game.setVidas(0);
        context.fim_jogo();
        States estado = context.getState();

        assertEquals(VULNERAVEL, estado);
    }

    @Test
    public void VulnerableState_teste_fim_jogo_ganhou(){
        //TESTE para verificar se quando no estado VULNERABLESTATE e´ possivel ir ao estado ENDSTATE

        Game game = new Game(1);
        Context context = new Context();

        context.changeState(new VulnerableState(context, game));
        game.setGanhou(true);
        context.fim_jogo();
        States estado = context.getState();

        assertEquals(FIM, estado);
    }

}