package pt.isec.pa.tinypac.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.data.GameManager;
import javafx.scene.layout.BorderPane;

public class ExitUI extends BorderPane {

    //ATTRIBUTES
    GameManager model;
    private Button btnSim, btnNao;
    private Label titulo;


    //CONSTRUCTOR
    public ExitUI(GameManager model) {
        this.model = model;
        createViews();
        registerHandlers();
        update();
    }

    public void createViews() {

        //cria componentes
        titulo = new Label("Deseja sair?");
        titulo.setMinWidth(100);

        btnSim = new Button("SIM");
        btnSim.setMinWidth(100);
        btnSim.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");

        btnNao = new Button("NÃO");
        btnNao.setMinWidth(100);
        btnNao.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");

        //organiza a vista
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(btnSim, btnNao);
        hb.setSpacing(10);

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(titulo, hb);
        vb.setSpacing(10);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vb);
        borderPane.setStyle("-fx-background-color: #DDE6ED");

        setCenter(borderPane);
    }

    public void registerHandlers() {
        btnSim.setOnMouseClicked(actionEvent -> {
            // Obtém a cena atual
            Scene scene = getScene();

            // Obtém o palco (stage) atual a partir da cena
            Stage stage = (Stage) scene.getWindow();

            // Fecha a janela
            stage.close();
        });

        btnNao.setOnMouseClicked(actionEvent -> {
            setCenter(new MainMenuUI(model));
        });

        //efeitos sobre os botões
        btnSim.setOnMouseEntered(e -> {
            btnSim.setStyle("-fx-background-color: #9DB2BF; -fx-text-fill: #27374D;");
        });
        btnSim.setOnMouseExited(e -> {
            btnSim.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");
        });

        btnNao.setOnMouseEntered(e -> {
            btnNao.setStyle("-fx-background-color: #9DB2BF; -fx-text-fill: #27374D;");
        });
        btnNao.setOnMouseExited(e -> {
            btnNao.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");
        });
    }

    public void update() {

    }
}