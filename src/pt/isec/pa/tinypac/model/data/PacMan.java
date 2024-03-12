package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.mazeElements.*;

import java.io.Serializable;

public class PacMan extends Agents implements Serializable {

    //ATTRIBUTES
    private static final long serialVersionUID = 1L;
    private int velocidade;
    private boolean movimentoAtivo = false;
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private boolean primeiroMovimento = true;
    private boolean flagZonaWrap = false;
    IMazeElement elementoAnterior, elementoAtual, elementoProximo = null;
    private static final char SYMBOL = 'A';
    private int contadorFruta = 1;
    private int quantidadeFantasmas = 0;
    private int contadorZonaWrap = 0;


    //CONSTRUCTOR
    public PacMan(Game game) {
        super(game);
    }


    //GETTERS AND SETTERS
    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public boolean isMovimentoAtivo() {
        return movimentoAtivo;
    }

    public void setMovimentoAtivo(boolean movimentoAtivo) {
        this.movimentoAtivo = movimentoAtivo;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
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
            elementoAnterior = null;
            elementoAtual = null;
            primeiroMovimento = false;
        }

        if(elementoAtual instanceof MazeElementBola){
            game.setPontos(game.getPontos() + 1);
            game.setQuantidadeBolas(game.getQuantidadeBolas() - 1);
            elementoAtual = null;
        }

        if(elementoAtual instanceof Blinky
                || elementoAtual instanceof Inky
                || elementoAtual instanceof Clyde
                || elementoAtual instanceof Pinky){
           if(!game.isVulneravel()) {
               if (game.getVidas() <= 0)
                   game.setPerdeu(true);
               else {
                   game.setResetGame(true);
                   game.resetGame();
                   game.setVidas(game.getVidas() - 1);
               }
           }
        }

        if(elementoAtual instanceof MazeElementBolaComPoderes){
            game.setPontos(game.getPontos()+10);
            quantidadeFantasmas = 0;
            game.setVulneravel(true);
            //game.maze.set(y,x,null);
            elementoAtual = null;
        }

        if(elementoAtual instanceof MazeElementZonaWrap){
            //deve aparecer na outra ponta de zona wrap

            contadorZonaWrap++;

            if(flagZonaWrap == false){
                for (int linha = 0; linha < game.getHeight(); linha++) {
                    for (int coluna = 0; coluna < game.getWidth(); coluna++) {
                        if (game.maze.get(linha, coluna) instanceof MazeElementZonaWrap) {
                            posX = coluna;
                            posY = linha;
                            game.maze.set(posY, posX, this);
                            game.maze.set(y, x, new MazeElementZonaWrap());
                            flagZonaWrap = true;

                            return;
                        }
                    }
                }
            }

            else{   //dá o reset
                if(contadorZonaWrap%2 == 0){
                    contadorZonaWrap = 0;
                    flagZonaWrap = false;
                }
            }
        }

        if(elementoAtual instanceof MazeElementFruta){
            game.setPontos(game.getPontos()+(25*contadorFruta));
            contadorFruta++;
            //game.maze.set(y,x,null);
            elementoAtual = null;
        }

        if(game.isUp()){
            posY = y - 1;
        }
        if(game.isRight()){
            posX = x + 1;
        }
        if(game.isDown()){
            posY = y + 1;
        }
        if(game.isLeft()){
            posX = x - 1;
        }

        elementoProximo = game.maze.get(posY,posX);

        /*if(posX > game.getWidth() || posX < 0
            || posY > game.getHeight() || posY < 0){
            if(isUp()) {
                posY = posY + 1;
                setUp(false);
            }
            else if(isRight()) {
                posX = posX - 1;
                setRight(false);
            }
            else if(isDown()) {
                posY = posY - 1;
                setDown(false);
            }
            else if(isLeft()) {
                posX = posX + 1;
                setLeft(false);
            }
            elementoProximo = elementoAtual;
        }*/
        if(elementoProximo instanceof MazeElementParede
            || elementoProximo instanceof MazeElementCaverna){      //faz o reset do movimento

            posX = x;
            posY = y;

            game.maze.set(posY, posX, this);
        }
        else{
            game.maze.set(posY, posX, this);

            game.maze.set(y, x, elementoAtual);

            elementoAnterior = elementoAtual;
            elementoAtual = elementoProximo;
        }

        /*if (elementoAnterior instanceof MazeElementBola) {
            game.setPontos(game.getPontos() + 1);
            game.maze.set(y, x, null);
        }*/

        /*if(game.maze.get(y+1, x) instanceof MazeElementParede
                || game.maze.get(y-1, x) instanceof MazeElementParede
                || game.maze.get(y, x+1) instanceof MazeElementParede
                || game.maze.get(y, x-1) instanceof MazeElementParede
                || game.maze.get(y+1, x) instanceof MazeElementCaverna
                || game.maze.get(y-1, x) instanceof MazeElementCaverna
                || game.maze.get(y, x+1) instanceof MazeElementCaverna
                || game.maze.get(y, x-1) instanceof MazeElementCaverna){
            //pegar direção do utilizador
            setMovimento_ativo(false);
            setUp(false);
            setRight(false);
            setDown(false);
            setLeft(false);
        }*/

        /*if(elementoProximo instanceof MazeElementBola) {
            game.setPontos(game.getPontos() + 1);
            //elementoProximo = null;
            game.maze.set(y,x,null);
        }*/

        /*if((elemento instanceof Blinky
                || elemento instanceof Inky
                || elemento instanceof Clyde
                || elemento instanceof Pinky)
                && !game.isVulneravel()){
            if(game.getVidas()<=0)
                game.setPerdeu(true);
            game.setVidas(game.getVidas()-1);
        }*/

        /*if(elemento instanceof MazeElementBolaComPoderes){
            game.setPontos(game.getPontos()+10);
            game.setVulneravel(true);
            game.maze.set(y,x,null);
        }*/

        /*if(elemento instanceof MazeElementZonaWrap){
            //deve aparecer na outra ponta de zona wrap
            for(int linha=0; linha<game.getHeight() && linha!=y; linha++){
                for(int coluna=0; coluna<game.getWidth() && coluna!=x; coluna++){
                    if(game.maze.get(linha, coluna) instanceof MazeElementZonaWrap) {
                        posX = coluna;
                        posY = linha;
                        game.maze.set(y, x, new MazeElementZonaWrap());
                        game.maze.set(posY, posX, this);
                        return;
                    }
                }
            }
        }*/

        /*if(elemento instanceof MazeElementFruta){
            int contador = 1;
            game.setPontos(game.getPontos()*contador);
            contador++;
            game.maze.set(y,x,null);
        }*/


       /* elementoProximo = game.maze.get(posY,posX);

        game.maze.set(posY,posX,this);

        game.maze.set(y, x, elementoAnterior);
        elementoAnterior = elementoProximo;
        //elementoProximo = null;

        //IMazeElement anterior = null;
        //anterior = game.maze.get(y,x);
        /*posY = y + 1;
        posX = x;
        game.maze.set(y,x,null);
        game.maze.set(posY,posX,this);*/

    }

    @Override
    public void evolveVulneravel(int y, int x){
        int posX = x, posY = y;

        if(elementoAtual instanceof MazeElementBola){
            game.setPontos(game.getPontos() + 1);
            elementoAtual = null;
        }

        if(elementoAtual instanceof Blinky
                || elementoAtual instanceof Inky
                || elementoAtual instanceof Clyde
                || elementoAtual instanceof Pinky) {
            quantidadeFantasmas++;
            game.setPontos(game.getPontos() + 50*quantidadeFantasmas);
            if(quantidadeFantasmas == 4){
                game.setVulneravel(false);
            }
        }

        if(elementoAtual instanceof MazeElementZonaWrap){
            //deve aparecer na outra ponta de zona wrap

            contadorZonaWrap++;

            if(flagZonaWrap == false){
                for (int linha = 0; linha < game.getHeight(); linha++) {
                    for (int coluna = 0; coluna < game.getWidth(); coluna++) {
                        if (game.maze.get(linha, coluna) instanceof MazeElementZonaWrap) {
                            posX = coluna;
                            posY = linha;
                            game.maze.set(posY, posX, this);
                            game.maze.set(y, x, new MazeElementZonaWrap());
                            flagZonaWrap = true;

                            return;
                        }
                    }
                }
            }

            else{   //dá o reset
                if(contadorZonaWrap%2 == 0){
                    contadorZonaWrap = 0;
                    flagZonaWrap = false;
                }
            }
        }

        if(elementoAtual instanceof MazeElementFruta){
            game.setPontos(game.getPontos()+(25*contadorFruta));
            contadorFruta++;
            //game.maze.set(y,x,null);
            elementoAtual = null;
        }

        if(game.isUp()){
            posY = y - 1;
        }
        if(game.isRight()){
            posX = x + 1;
        }
        if(game.isDown()){
            posY = y + 1;
        }
        if(game.isLeft()){
            posX = x - 1;
        }

        elementoProximo = game.maze.get(posY,posX);

        if(elementoProximo instanceof MazeElementParede
                || elementoProximo instanceof MazeElementCaverna){      //faz o reset do movimento
            posY = y;
            posX = x;
            game.maze.set(posY, posX, this);
        }
        else{
            game.maze.set(posY, posX, this);
            game.maze.set(y, x, elementoAtual);

            elementoAnterior = elementoAtual;
            elementoAtual = elementoProximo;
        }
    }
}