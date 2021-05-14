package adapter;

import controller.*;
import view.*;
import java.awt.event.*;
import java.util.*;

public class Adapter implements ActionListener {
    private Controller controller;
    private GameView view;

    public Adapter(Controller controller, GameView view) {
        this.controller = controller;
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        if(view.isReset(e))
            controller.setRequest();
        else {
            ArrayList<Integer> position = view.getPosition(e);
            controller.setRequest(position);
        }
    }
}
