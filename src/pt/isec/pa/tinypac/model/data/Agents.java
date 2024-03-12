package pt.isec.pa.tinypac.model.data;

public abstract class Agents implements IMazeElement {

    /**
     * Classe base abstrata para todos os agentes: PacMan, Blinky, Clyde, Inky e Pinky
     */

    //ATTRIBUTES
    protected Game game;


    //CONSTRUCTOR
    public Agents(Game game) {
        this.game = game;
    }


    //METHODS
    abstract public void evolve(int y, int x);
    abstract public void evolveVulneravel(int y, int x);
}