package controller;

import model.*;
import java.util.ArrayList;

public class Controller {
    private GameModel m;

    public void setModel(GameModel m) {
        this.m = m;
    }

    public void setRequest(ArrayList<Integer> position) {
        m.PlayMove(position.get(0), position.get(1));
    }

    public void Reset() {
        m.ResetModel();
    }
}