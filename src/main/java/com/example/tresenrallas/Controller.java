package com.example.tresenrallas;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.VirtualFlow;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class Controller {

    com.example.tresenrallas.Alert alert = new com.example.tresenrallas.Alert();

    private int turno;
    ArrayList<Button> botones;
    @FXML
    private RadioButton playervsplayer, playervscomp, compvscomp;
    @FXML
    private Button button1,button2,button3,button4,button5,button6,button7,button8,button9;
    @FXML
    private Text playerTurnText;

    private boolean compmove;

    private boolean gameOver;

    private int buttonsClicked;

    //Genera un numero random para decidir quien empieza el juego
    @FXML
    protected void TurnoEmpezar(){
        int randomNumber = ThreadLocalRandom.current().nextInt(0,11);

        if (randomNumber >= 1 && randomNumber <= 5){
            turno = 1;
        }else {
            turno = 2;
        }
    }

    //Aqui es donde seleccionamos los modos del juego
    @FXML
    protected void ModeSelect(ActionEvent event) {

        if (playervsplayer.isSelected()) {
            botones = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));
            botones.forEach(this::cleanGame);
            botones.forEach(this::gamePvsP);
        }
        else if(playervscomp.isSelected()) {
            botones = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));
            botones.forEach(this::cleanGame);
            botones.forEach(this::gamePvsC);
        }
        else if(compvscomp.isSelected()){
            botones = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));
            botones.forEach(this::cleanGame);
            botones.forEach(this::gameCvsC);
        }
    }

    //Partida de Jugador contra jugador
    protected void gamePvsP(Button button){
        TurnoEmpezar();
        checkTurno();
        button.setOnMouseClicked(mouseEvent -> {

            if(turno == 1){
                button.setText("X");
                disableButton(button);
                checkGameOver();
                turno+=1;
                checkTurno();

            }else if(turno == 2){
                button.setText("O");
                disableButton(button);
                checkGameOver();
                turno-=1;
                checkTurno();
            }
        });
    }
    //Partida de Jugador contra Computer
    protected void gamePvsC(Button button){
        button.setOnMouseClicked(mouseEvent -> {
            while(!compmove){
                button.setText("X");
                disableButton(button);
                checkGameOver();
                if(!gameOver){
                    compmove = true;
                }else{
                    break;
                }
            }
            while(compmove){
                compMove(botones);
                checkGameOver();
                if (!gameOver){
                    compmove = false;
                }else {
                    break;
                }
            }

        });

        }
    //Partida de Computer contra Computer
        protected void gameCvsC(Button button){
            TurnoEmpezar();
                while(!gameOver){
                    compMove(botones);
                    checkGameOver();
                }
        }

    //Mira el turno si es 1 Muestra el turno de jugador X y en 2 muestra el turno de jugador O
    protected void checkTurno(){
        if(turno == 1){
            playerTurnText.setText("Player X Turn");
        }else if(turno == 2){
            playerTurnText.setText("Player O Turn");
        }
    }

    //Este es el metodo donde decide el movimiento de Computer, dependiendo del modo hace cosas diferentes
    protected void compMove(ArrayList<Button> buttons){
        if(playervscomp.isSelected()){
            for(int i = 0; i < 1; i++){
                int randomNum = (int) ((Math.random()*9));

                if(buttons.get(randomNum).isDisable()){
                    i--;
                }else {
                    buttons.get(randomNum).setText("O");
                    buttons.get(randomNum).setDisable(true);
                }
            }
        }else if(compvscomp.isSelected()){
            for(int i = 0; i < 1; i++){
                int randomNum = (int) ((Math.random()*9));

                if(buttons.get(randomNum).isDisable()){
                    i--;
                }else {
                    if(turno == 1){
                        turno+=1;
                        buttons.get(randomNum).setText("X");
                        buttons.get(randomNum).setDisable(true);
                    }else if(turno == 2){
                        turno-=1;
                        buttons.get(randomNum).setText("O");
                        buttons.get(randomNum).setDisable(true);

                    }
                }
            }
        }

        }

    //Comprueba si alguien ha ganado la partida
    protected void checkGameOver(){
        for(int i = 0; i < 8; i++) {
            String line = switch (i){
                case 0 -> button1.getText() + button2.getText() + button3.getText();
                case 1 -> button4.getText() + button5.getText() + button6.getText();
                case 2 -> button7.getText() + button8.getText() + button9.getText();
                case 3 -> button1.getText() + button5.getText() + button9.getText();
                case 4 -> button3.getText() + button5.getText() + button7.getText();
                case 5 -> button1.getText() + button4.getText() + button7.getText();
                case 6 -> button2.getText() + button5.getText() + button8.getText();
                case 7 -> button3.getText() + button6.getText() + button9.getText();
                default -> null;
            };

            switch (line) {
                case "XXX" -> {
                    botones.forEach(this::disableButton);
                    gameOver = true;
                    alert.winner = "player X";
                    alert.showWinner();
                }
                case "OOO" -> {
                    botones.forEach(this::disableButton);
                    gameOver = true;
                    alert.winner = "player O";
                    alert.showWinner();
                }
                default -> checkTie(line);
            }
        }
    }

    //Comprueba Si ha empatado la partida
    protected void checkTie(String line){
        if(button1.isDisable() && button2.isDisable() && button3.isDisable() && button4.isDisable() && button5.isDisable() && button6.isDisable() && button7.isDisable() && button8.isDisable() && button9.isDisable()){
            switch (line){
                case "XXO", "XOX", "OXX", "OOX", "OXO", "XOO" -> {
                    buttonsClicked++;
                    if (buttonsClicked == 8) {
                        botones.forEach(this::disableButton);
                        gameOver = true;
                        alert.winner = "";
                        alert.showWinner();
                    }
                }
            }
        }
    }

    //Resetea la partida para el empiezo de la partida
    protected void cleanGame(Button button){
        button.setText("");
        playerTurnText.setText("");
        alert.winner = "";
        buttonsClicked = 0;
        turno = 0;
        button.setDisable(false);
        gameOver = false;
        compmove = false;
    }

    //Poner un boton en disabled
    protected void disableButton(Button button){
        button.setDisable(true);
    }

    //Salir del juego
    @FXML
    protected void exitGame(){
        System.exit(0);
    }

    //Muestra la info del programa
    @FXML
    protected void showAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("App Information");
        alert.setHeaderText(null);
        alert.setContentText("Tic Tac Toe hecho por Alumno de DAM2A - Xiao");

        alert.showAndWait();
    }

    //Muestra la estadistica de los jugadores(Solo muestra una tabla blanca - NotWorking)
    @FXML
    protected void showStatistics() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("estadisticas.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage statisticsStage = new Stage();
        statisticsStage.setTitle("Players Statistics");
        statisticsStage.initModality(Modality.NONE);
        statisticsStage.setScene(scene);
        statisticsStage.show();
    }



}