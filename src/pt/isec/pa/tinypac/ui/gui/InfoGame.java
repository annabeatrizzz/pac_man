package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.model.fsm.States;
import pt.isec.pa.tinypac.ui.gui.resources.ImageLoader;

public class InfoGame extends HBox {
    private GameManager model;
    private Label pontos, tempo;
    private HBox vidas;
    private ImageView coracao;
    private Button btnPausa;

    public InfoGame(GameManager model) {
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }

    public void createViews() {
        this.setStyle("-fx-background-color: #27374D");

        pontos = new Label();
        pontos.setTextFill(Color.WHITE);

        tempo = new Label();
        tempo.setTextFill(Color.WHITE);

        HBox hb2 = new HBox();
        hb2.getChildren().addAll(pontos, tempo);
        hb2.setSpacing(10);
        hb2.setPadding(new Insets(10));

        btnPausa = new Button();
        btnPausa.setText("PAUSE");
        btnPausa.setMinWidth(100);
        btnPausa.setTextFill(Color.INDIANRED);

        vidas = new HBox();

        this.getChildren().addAll(hb2, btnPausa, vidas);
        this.setPadding(new Insets(10));
        this.setSpacing(80);
        this.setAlignment(Pos.CENTER);
    }

    public void registerHandlers() {
        model.addPropertyChangeListener(evt ->
                Platform.runLater(() -> {
                    update();
        }));

        btnPausa.setOnMouseClicked(actionEvent ->{
            model.pausa_jogo();
        });

        //efeitos sobre os botões
        btnPausa.setOnMouseEntered(e -> {
            btnPausa.setStyle("-fx-background-color: #9DB2BF; -fx-text-fill: #27374D;");
        });
        btnPausa.setOnMouseExited(e -> {
            btnPausa.setStyle("-fx-text-fill: indianred;");
        });
    }

    public void update() {
        vidas.getChildren().clear();
        int qt_vidas = model.getVidas();
        for(int i=0; i<qt_vidas; i++){
            coracao = new ImageView(ImageLoader.getImage("coracao.png"));
            coracao.setFitHeight(25);
            coracao.setFitWidth(25);
            vidas.getChildren().add(coracao);
        }

        States state = model.getState();
        if(state == States.INICIO){
            btnPausa.setDisable(true);
        }
        else{
            btnPausa.setDisable(false);
        }

        pontos.setText("Pontos: " + model.getPontos());
        tempo.setText("Tempo: " + model.getContadorTempo());
    }
}