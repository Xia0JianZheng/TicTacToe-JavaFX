package com.example.tresenrallas;

import javafx.scene.control.TextInputDialog;

public class Alert {

    protected String winner;

    public Alert() {
    }

    //Sale un alert donde muestra el ganador o que se ha empatado la partida
    public void showWinner(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);

        alert.setTitle("Winner");
        alert.setHeaderText(null);
        if(!winner.equals("")){
            alert.setContentText(winner + " is the Winner !");
        } else{
            alert.setContentText("Tie, No winner this round !");
        }
        alert.showAndWait();
    }
}
