package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.mazeElements.MazeElementCaverna;
import pt.isec.pa.tinypac.model.data.mazeElements.MazeElementParede;
import pt.isec.pa.tinypac.model.data.mazeElements.MazeElementPosicaoInicialFantasmas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Clyde extends Agents {

    /**
     * Classe destinada ao fantasma Clyde
     */

    //ATTRIBUTES
    private static final char SYMBOL = 'C';
    IMazeElement elementoAnterior, elementoProximo;
    private boolean primeiroMovimento = true;
    private int contadorInicio = 5;
    private record Position(int y, int x) {}
    private List<Position> listaPosicoes = new ArrayList<>();
    private boolean movimentoPermitido = true;
    private int numero;
    private Random random;


    //CONSTRUCTOR
    public Clyde(Game game) {
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