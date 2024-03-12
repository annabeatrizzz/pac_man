package pt.isec.pa.tinypac.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.data.GameManager;

public class RegisterPlayerUI extends BorderPane {
    
    GameManager model;
    Label l, instrucao;
    TextField textField;
    Button btn_done;
    
    public RegisterPlayerUI(GameManager model){
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }
    
    public void createViews(){
        this.setStyle("-fx-background-color: rgb(221,230,237)");

        instrucao = new Label("Nome do jogador: ");

        textField = new TextField();
        textField.setPrefWidth(250);
        textField.setPrefHeight(50);
        textField.setPadding(new Insets(20));

        btn_done = new Button();
        btn_done.setText("Pronto");
        btn_done.setPrefWidth(100);
        btn_done.setPrefHeight(40);

        l = new Label();

        HBox hb = new HBox();
        hb.getChildren().addAll(textField, btn_done, l);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);

        VBox vb = new VBox();
        vb.getChildren().addAll(instrucao, hb);
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);

        setCenter(vb);
    }
    
    public void registerHandlers(){
        btn_done.setOnMouseClicked(actionEvent -> {
            l.setText(textField.getText());
            model.atualizaTop5(textField.getText());
            this.setVisible(false);
        });
    }
    
    public void update() {
        //this.setVisible(!model.isTOP5atualizado());
    }
}