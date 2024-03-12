package pt.isec.pa.tinypac.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.ImageLoader;

public class MazeUI extends BorderPane {
    private ImageView img_pacman, img_blinky, img_clyde, img_pinky, img_inky, img_fruta, img_zonaWrap, img_fantasma_vulneravel;
    private Circle bola, bolaComPoder;
    private int TAM_CELULA = 15;
    GridPane gridPane;

    private char [][] maze;

    MazeUI(GameManager model){
        imprime_maze(model);
    }

    public void imprime_maze(GameManager model){
        maze = model.getGameMaze();

        gridPane = new GridPane();
        for(int linha=0; linha<maze.length; linha++){
            for(int coluna=0; coluna<maze[0].length; coluna++){
                Rectangle celula = new Rectangle(TAM_CELULA, TAM_CELULA);
                celula.setFill(Color.WHITE);
                gridPane.add(celula, coluna, linha);


                char elemento = maze[linha][coluna];
                // Criar um StackPane para agrupar elementos duplicados
                StackPane stackPane = new StackPane();

                if(elemento == 'x'){
                    Rectangle celulaParede = new Rectangle(TAM_CELULA, TAM_CELULA);
                    celulaParede.setFill(Color.BLACK);
                    stackPane.getChildren().add(celulaParede);
                }
                else if(elemento == 'W'){
                    img_zonaWrap = new ImageView(ImageLoader.getImage("WrapZone.png"));
                    img_zonaWrap.setFitWidth(14);
                    img_zonaWrap.setFitHeight(14);
                    stackPane.getChildren().add(img_zonaWrap);
                }
                else if(elemento == 'o'){
                    bola = new Circle(3);
                    String codigoHexadecimal = "#9DB2BF";
                    Color corRGB = Color.web(codigoHexadecimal);
                    bola.setFill(corRGB);
                    stackPane.getChildren().add(bola);
                }
                else if(elemento == 'F'){
                    img_fruta = new ImageView(ImageLoader.getImage("Fruta.png"));
                    img_fruta.setFitWidth(14);
                    img_fruta.setFitHeight(14);
                    stackPane.getChildren().add(img_fruta);
                }
                else if(elemento == 'O'){
                    bolaComPoder = new Circle(5);
                    String codigoHexadecimal = "#9DB2BF";
                    Color corRGB = Color.web(codigoHexadecimal);
                    bolaComPoder.setFill(corRGB);
                    stackPane.getChildren().add(bolaComPoder);
                }
                else if(elemento == 'B'){
                    if(model.isVulneravel()){
                        img_fantasma_vulneravel = new ImageView(ImageLoader.getImage("FantasmaVulneravel.gif"));
                        img_fantasma_vulneravel.setFitWidth(14);
                        img_fantasma_vulneravel.setFitHeight(14);
                        stackPane.getChildren().add(img_fantasma_vulneravel);
                    }
                    else {
                        img_blinky = new ImageView(ImageLoader.getImage("Blinky.png"));
                        img_blinky.setFitWidth(14);
                        img_blinky.setFitHeight(14);
                        stackPane.getChildren().add(img_blinky);
                    }
                }
                else if(elemento == 'C'){
                    if(model.isVulneravel()){
                        img_fantasma_vulneravel = new ImageView(ImageLoader.getImage("FantasmaVulneravel.gif"));
                        img_fantasma_vulneravel.setFitWidth(14);
                        img_fantasma_vulneravel.setFitHeight(14);
                        stackPane.getChildren().add(img_fantasma_vulneravel);
                    }
                    else {
                        img_clyde = new ImageView(ImageLoader.getImage("Clyde.png"));
                        img_clyde.setFitWidth(14);
                        img_clyde.setFitHeight(14);
                        stackPane.getChildren().add(img_clyde);
                    }
                }
                else if(elemento == 'I'){
                    if(model.isVulneravel()){
                        img_fantasma_vulneravel = new ImageView(ImageLoader.getImage("FantasmaVulneravel.gif"));
                        img_fantasma_vulneravel.setFitWidth(14);
                        img_fantasma_vulneravel.setFitHeight(14);
                        stackPane.getChildren().add(img_fantasma_vulneravel);
                    }
                    else {
                        img_inky = new ImageView(ImageLoader.getImage("Inky.png"));
                        img_inky.setFitWidth(14);
                        img_inky.setFitHeight(14);
                        stackPane.getChildren().add(img_inky);
                    }
                }
                else if(elemento == 'P'){
                    if(model.isVulneravel()){
                        img_fantasma_vulneravel = new ImageView(ImageLoader.getImage("FantasmaVulneravel.gif"));
                        img_fantasma_vulneravel.setFitWidth(14);
                        img_fantasma_vulneravel.setFitHeight(14);
                        stackPane.getChildren().add(img_fantasma_vulneravel);
                    }
                    else {
                        img_pinky = new ImageView(ImageLoader.getImage("Pinky.png"));
                        img_pinky.setFitWidth(14);
                        img_pinky.setFitHeight(14);
                        stackPane.getChildren().add(img_pinky);
                    }
                }
                else if(elemento == 'A'){
                    img_pacman = new ImageView(ImageLoader.getImage("Pacman.png"));
                    img_pacman.setFitWidth(14);
                    img_pacman.setFitHeight(14);
                    stackPane.getChildren().add(img_pacman);
                }

                // Adicionar o StackPane ao GridPane
                gridPane.add(stackPane, coluna, linha);
            }
        }
        gridPane.setAlignment(Pos.CENTER);
        setCenter(gridPane);
    }
}