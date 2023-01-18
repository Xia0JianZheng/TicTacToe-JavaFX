package com.example.tresenrallas;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Statistics {

    private StringProperty playerName = new SimpleStringProperty("");

    private  IntegerProperty win;

    private  IntegerProperty lose;

    private  IntegerProperty tied;

    public Statistics() {

    }

    //constructor de Estatisticas
    public Statistics(String playerName) {
        this.playerName = new SimpleStringProperty(playerName);
        this.win = new SimpleIntegerProperty(0);
        this.lose = new SimpleIntegerProperty(0);
        this.tied = new SimpleIntegerProperty(0);
    }
    //Getters y Setters
    public StringProperty playerNameProperty() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
    }

    public int getWin() {
        return win.get();
    }

    public IntegerProperty winProperty() {
        return win;
    }

    public void setWin(int win) {
        this.win.set(win);
    }

    public int getLose() {
        return lose.get();
    }

    public IntegerProperty loseProperty() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose.set(lose);
    }

    public int getTied() {
        return tied.get();
    }

    public IntegerProperty tiedProperty() {
        return tied;
    }

    public void setTied(int tied) {
        this.tied.set(tied);
    }
}
