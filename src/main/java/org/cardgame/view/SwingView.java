package org.cardgame.view;

import org.cardgame.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class SwingView implements GameViewable {

    GameController gameController;
    JTextArea textArea;
    JFrame frame;

    public void createAndShowGUI() {
        frame = new JFrame("MVC-SOLID Openclassrooms Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        addControllerCommandTracker(contentPane);
        frame.setVisible(true);
    }

    private void addControllerCommandTracker(Container contentPane) {
        textArea = new JTextArea("Game status: ", 100, 1);
        JScrollPane scrollPane = new JScrollPane(textArea);
        addCenteredComponent(scrollPane, contentPane);
        textArea.setSize(500, 500);
    }

    private void addCenteredComponent(JComponent component, Container contentPane) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(component);
    }

    private void appendText(String text) {
        textArea.append(text + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    @Override
    public void setController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void showPlayerName(int playerIndex, String playerName) {
        appendText(playerIndex + ": " + playerName);
    }

    @Override
    public void showCardForPlayer(int playerIndex, String playerName, String cardRank, String cardSuit) {
        appendText(playerName + ": " + cardRank + ", " + cardSuit);
    }

    @Override
    public void showWinner(String playerName) {
        appendText("The Winner is: " + playerName);
    }

    @Override
    public void showFaceDownCardForPlayer(int playerIndex, String playerName) {
        appendText(playerName + ": [][]");
    }

    @Override
    public void promptForPlayerName() {
        String result = (String) JOptionPane.showInputDialog(frame, "Add a player", "Player", JOptionPane.PLAIN_MESSAGE, null, null, "");
        if (result == null || result.isEmpty()) {
            gameController.nextAction("+q");
        }
        gameController.addPlayer(result);

        int addMore = JOptionPane.showConfirmDialog(frame, "Add more players?", "More players", JOptionPane.YES_NO_OPTION);

        if( addMore == JOptionPane.NO_OPTION) {
            gameController.startGame();
        }
    }

    @Override
    public void promptForFlip() {
        gameController.flipCards();
    }

    @Override
    public void promptForNewGame() {
        int newGame = JOptionPane.showConfirmDialog(frame, "Play again ?", "Play again", JOptionPane.YES_NO_OPTION);
        gameController.nextAction(newGame == JOptionPane.NO_OPTION ? "+q" : "");
    }
}
