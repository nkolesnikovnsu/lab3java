package view;

import controller.*;
import adapter.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GameView extends JFrame{
    Adapter adapter;
    JFrame gui;
    JButton reset;
    JButton[][] blocks;
    JTextArea playerTurn;

    public GameView() {
        gui = new JFrame("TicTacToe");
        blocks = new JButton[3][3];
        reset = new JButton("Reset");
        playerTurn = new JTextArea();

        gui.setSize(new Dimension(800, 580));
        gui.setResizable(false);
        gui.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel gamePanel = new JPanel(new FlowLayout());
        JPanel game = new JPanel(new GridLayout(3,3));
        gamePanel.add(game, BorderLayout.CENTER);
        JPanel options = new JPanel(new FlowLayout());

        options.add(reset);
        JPanel messages = new JPanel(new FlowLayout());
        messages.setBackground(Color.white);
        gui.add(gamePanel, BorderLayout.NORTH);
        gui.add(options, BorderLayout.CENTER);
        gui.add(messages, BorderLayout.SOUTH);
        messages.add(playerTurn);
        playerTurn.setText("Player 1 play as 'X'");

        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 3; column++) {
                blocks[row][column] = new JButton();
                blocks[row][column].setPreferredSize(new Dimension(140,140));
                blocks[row][column].setText("");
                game.add(blocks[row][column]);

            }
        }
        gui.setVisible(true);
    }

    public void setActionListener(Controller c) {
        this.adapter = new Adapter(c,this);
        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 3; column++) {
                blocks[row][column].addActionListener(adapter);
            }
        }
        reset.addActionListener(adapter);
    }

    public boolean isReset(ActionEvent e) {
        return e.getSource() == reset;
    }

    public ArrayList<Integer> getPosition(ActionEvent e) {
        ArrayList<Integer> position = new ArrayList<>();
        for(int row = 0; row<3 ; row++) {
            for(int column = 0; column<3 ;column++) {
                if(e.getSource() == blocks[row][column]) {
                    position.add(row);
                    position.add(column);
                }
            }
        }
        return position;
    }

    public void update(int row, int column, char symbol, String message) {
        blocks[row][column].setText(Character.toString(symbol));
        blocks[row][column].setEnabled(false);
        playerTurn.setText(message);
    }

    public void isWinner(int row, int column, char symbol, String message) {
        blocks[row][column].setText(Character.toString(symbol));
        blocks[row][column].setEnabled(false);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                blocks[i][j].setEnabled(false);
            }
        }
        playerTurn.setText(message);
    }

    public void resetGame() {
        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 3; column++) {
                blocks[row][column].setText("");
                blocks[row][column].setEnabled(true);
            }
        }
        playerTurn.setText("Player 1 play as 'X'");
    }
}
