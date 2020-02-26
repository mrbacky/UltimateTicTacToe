/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.gui.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ultimatetictactoe.be.UTTTButton;
import ultimatetictactoe.bll.game.GameManager;
import ultimatetictactoe.bll.game.GameState;
import ultimatetictactoe.bll.game.IGameState;
import ultimatetictactoe.bll.move.IMove;
import ultimatetictactoe.bll.move.Move;

public class MainController implements Initializable {

    private GameManager gameManager;
    private IGameState gameState;
    private IMove move;
    @FXML
    private AnchorPane mainPane;
    private UTTTButton btn;
    private final int BTN_WIDTH = 40;
    private final int BTN_HEIGHT = 40;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameManager = new GameManager(gameState);
        gameState = new GameState();

        createTiles();

    }

    private void createTiles() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {

                btn = new UTTTButton();
                btn.setPrefSize(BTN_WIDTH, BTN_HEIGHT);

                btn.setMove(new Move(x, y));

                btn.setLayoutX(20 + (BTN_WIDTH + 5) * x);
                btn.setLayoutY(20 + (BTN_HEIGHT + 5) * y);
                play();
                mainPane.getChildren().add(btn);
            }
        }
    }

    private void play() {
        btn.setOnMouseClicked(ActionEvent -> {
            UTTTButton b = (UTTTButton) ActionEvent.getSource();
            boolean isSuccess = gameManager.updateGame(b.getMove());
            if (isSuccess) {
                if (gameState.getMoveNumber() % 2 == 0) {
                    b.setText("X");
                } else {
                    b.setText("O");
                }
            }
        });
    }
}
