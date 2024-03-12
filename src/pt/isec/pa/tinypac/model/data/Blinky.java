package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.Agents;
import pt.isec.pa.tinypac.model.data.mazeElements.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Blinky extends Agents {

    /**
     * Classe destinada ao fantasma Blinky
     */

    //ATTRIBUTES
    private static final char SYMBOL = 'B';
    private IMazeElement elementoAnterior, elementoProximo = null;
    private boolean primeiroMovimento = true;
    private boolean movimentoAutomatico = false;
    private int numAnterior = 0;
    private ArrayList<Integer> lista = new ArrayList<Integer>();
    private int contadorInicio = 5;
    private record Position(int y, int x) {}
    private List<Position> listaPosicoes = new ArrayList<>();
    private boolean movimentoPermitido = true;
    private int numero;
    private Random random;


    //CONSTRUCTORS
    public Blinky(Game game) {
        super(game);
        random = new Random();
    }


    //METHODS
    @Override
    public char getSymbol() {
        return SYMBOL;
    }

    @Override
    public void evolve(int y, int x) {
        int posX = x, posY = y;

        if(primeiroMovimento){
            contadorInicio--;
            if(contadorInicio > 0){
                return;
            }

            for (int linha = 0; linha < game.getHeight(); linha++) {
                for (int coluna = 0; coluna < game.getWidth(); coluna++) {
                    if (game.maze.get(linha, coluna) instanceof MazeElementPosicaoInicialFantasmas) {
                        posX = coluna;
                        posY = linha;
                        primeiroMovimento = false;
                        elementoAnterior = new MazeElementCaverna();
                        elementoProximo = game.maze.get(posY, posX);
                        game.maze.set(posY, posX, this);
                        game.maze.set(y, x, elementoAnterior);
                        elementoAnterior = elementoProximo;
                        elementoProximo = null;
                        numero = 0;
                        listaPosicoes.add(new Position(y, x));
                        return;
                    }
                }
            }
        }

        do{

            if(!movimentoPermitido){
                numero = random.nextInt(4);
                movimentoPermitido = true;
            }

            switch (numero) {
                case 0 -> posY = y - 1;     //up
                case 1 -> posX = x + 1;     //right
                case 2 -> posY = y + 1;     //down
                case 3 -> posX = x - 1;     //left
            }

            elementoProximo = game.maze.get(posY, posX);

            if (elementoProximo instanceof MazeElementParede
            || elementoProximo instanceof MazeElementCaverna) {
                posX = x;
                posY = y;
                movimentoPermitido = false;
            }
        }while(!movimentoPermitido);

        movimentoPermitido = true;
        game.maze.set(y,x, elementoAnterior);
        //posX = x;
        //posY = y + 1;
        //elementoProximo = game.maze.get(posY, posX);
        game.maze.set(posY,posX,this);
        listaPosicoes.add(new Position(y, x));
        elementoAnterior = elementoProximo;
    }

    @Override
    public void evolveVulneravel(int y, int x){
        int posXAnterior, posYAnterior;

        if(elementoAnterior instanceof MazeElementPosicaoInicialFantasmas){
            listaPosicoes.clear();
            game.setQuantidadeFantasmasNaPosicaoInicial(game.getQuantidadeFantasmasNaPosicaoInicial() + 1);
        }

        if(listaPosicoes.isEmpty()){
            return;
        }

        posXAnterior = listaPosicoes.get(listaPosicoes.size()-1).x;
        posYAnterior = listaPosicoes.get(listaPosicoes.size()-1).y;

        elementoProximo = game.maze.get(posYAnterior, posXAnterior);
        game.maze.set(posYAnterior, posXAnterior, this);
        listaPosicoes.remove(listaPosicoes.size()-1);
        game.maze.set(y, x, elementoAnterior);

        elementoAnterior = elementoProximo;
        elementoProximo = null;
    }
}

    /*
    @Override
    public void evolve(int y, int x) {
        //Blinky define uma direção e anda sempre em frente
        int numAleatorio = 0;

        if(primeiroMovimento){
            contadorInicio--;

            if(contadorInicio > 0){
                return;
            }

            //iniciar o movimento dele no Y
            for(int linha=0; linha<game.getHeight(); linha++){
                for(int coluna = 0; coluna < game.getWidth(); coluna++){
                    if(game.maze.get(linha, coluna) instanceof MazeElementPosicaoInicialFantasmas){
                        posX = coluna;
                        posY = linha;
                        primeiroMovimento = false;
                        elementoAnterior = new MazeElementCaverna();
                        elementoProximo = game.maze.get(posY, posX);
                        game.maze.set(posY, posX, this);
                        game.maze.set(y, x, elementoAnterior);
                        elementoAnterior = elementoProximo;
                        elementoProximo = null;
                    }

                    /*if(game.maze.get(linha, coluna) instanceof Blinky
                            && linha!=y && coluna!=x){
                        game.maze.set(linha, coluna, new MazeElementCaverna());
                    }*/
               /* }
            }
            return;
        }

        lista.clear();
        int numIteracoes = 0;
        int contador = 0;

        do{
            if(numIteracoes > 0){ //fazer reset do que foi feito
                switch (numAleatorio) {
                    case 0 -> posY = y + 1;     //up
                    case 1 -> posX = x - 1;     //right
                    case 2 -> posY = y - 1;     //down
                    case 3 -> posX = x + 1;     //left
                }
            }

            if(!movimentoAutomatico || cruzamento(y,x)) {
                Random rand = new Random();
                numAleatorio = rand.nextInt(4); //gera um numero de 0 a (4-1=)3

                if(numIteracoes > 0){
                    while (numero_ja_apareceu(numAleatorio)) {
                        if (lista.size() == 3) {
                            numAleatorio = numAnterior;
                            break;
                        }
                        numAleatorio = rand.nextInt(4); //gera um numero de 0 a (4-1=)3
                    }
                }

                lista.add(numAleatorio);
                movimentoAutomatico = true;
            }

            switch (numAleatorio) {
                case 0 -> posY = y - 1;     //up
                case 1 -> posX = x + 1;     //right
                case 2 -> posY = y + 1;     //down
                case 3 -> posX = x - 1;     //left
            }

            if(game.maze.get(posY, posX) instanceof MazeElementParede
                    || game.maze.get(posY, posX) instanceof MazeElementZonaWrap) {
                movimentoAutomatico = false;
            }


            numIteracoes++;
        }while((game.maze.get(posY, posX) instanceof MazeElementParede
                    || game.maze.get(posY, posX) instanceof MazeElementZonaWrap));

        //movAnterior = numAleatorio;
        numAnterior = numAleatorio;

        elementoProximo = game.maze.get(posY, posX);
        game.maze.set(posY, posX, this);
        game.maze.set(y, x, elementoAnterior);
        elementoAnterior = elementoProximo;
        elementoProximo = null;
        ModelLog.getInstance().add(String.format("Blinky at (X=%d,Y=%d)",posX,posY));
    }

    public boolean cruzamento(int posY, int posX) {

        /**
         * FUNÇÃO para verificar a presença de um cruzamento
         * retorna true se estiver mais de uma direção que pode seguir (está num cruzamento)
         * **/

        /*int contador = 0;

        //verifica vizinho UP
        if(!(game.maze.get(posY-1, posX) instanceof MazeElementParede))
            contador++;
                /*(game.maze.get(posY-1, posX) instanceof MazeElementBola)
                || (game.maze.get(posY-1, posX) instanceof MazeElementFruta)
                || (game.maze.get(posY-1, posX) instanceof MazeElementBolaComPoderes)
                || (game.maze.get(posY-1, posX) instanceof PacMan)
                || (game.maze.get(posY-1, posX) instanceof MazeElementPosicaoInicialPacman)
                || (game.maze.get(posY-1, posX) instanceof MazeElementPosicaoInicialFantasmas))*/

        //verifica vizinho RIGHT
        /*if(!(game.maze.get(posY, posX+1) instanceof MazeElementParede))
            contador++;
                /*(game.maze.get(posY, posX+1) instanceof MazeElementBola)
                || (game.maze.get(posY, posX+1) instanceof MazeElementFruta)
                || (game.maze.get(posY, posX+1) instanceof MazeElementBolaComPoderes)
                || (game.maze.get(posY, posX+1) instanceof PacMan)
                || (game.maze.get(posY, posX+1) instanceof MazeElementPosicaoInicialPacman)
                || (game.maze.get(posY, posX+1) instanceof MazeElementPosicaoInicialFantasmas))*/

        //verifica vizinho DOWN
        /*if(!(game.maze.get(posY+1, posX) instanceof MazeElementParede))
            contador++;
                /*(game.maze.get(posY+1, posX) instanceof MazeElementBola)
                || (game.maze.get(posY+1, posX) instanceof MazeElementFruta)
                || (game.maze.get(posY+1, posX) instanceof MazeElementBolaComPoderes)
                || (game.maze.get(posY+1, posX) instanceof PacMan)
                || (game.maze.get(posY+1, posX) instanceof MazeElementPosicaoInicialPacman)
                || (game.maze.get(posY+1, posX) instanceof MazeElementPosicaoInicialFantasmas))*/

        //verifica vizinho LEFT
        /*if(!(game.maze.get(posY, posX-1) instanceof MazeElementParede))
            contador++;
                /*(game.maze.get(posY, posX-1) instanceof MazeElementBola)
                || (game.maze.get(posY, posX-1) instanceof MazeElementFruta)
                || (game.maze.get(posY, posX-1) instanceof MazeElementBolaComPoderes)
                || (game.maze.get(posY, posX-1) instanceof PacMan)
                || (game.maze.get(posY, posX-1) instanceof MazeElementPosicaoInicialPacman)
                || (game.maze.get(posY, posX-1) instanceof MazeElementPosicaoInicialFantasmas))*/

        /*if(contador>1){
            return true;
        }

        return false;
    }

    public boolean numero_ja_apareceu(int numAleatorio) {

        /**
         * FUNÇÃO para verificar se o número já apareceu
         * destinada ao comportamento do Blinky de garantir que só volta atràs se não houver outra opção
         * retorna true se já tiver sido realisado este movimento
         * retorna false caso contrário
         * **/

        /*for(int i=0; i<lista.size(); i++)
            if(lista.get(i) != null && lista.get(i) == numAleatorio)
                return true;
        return false;
    }

}*/