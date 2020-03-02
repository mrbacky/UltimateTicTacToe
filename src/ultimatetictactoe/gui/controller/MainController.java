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



/**
 *
 * @author rtlop
 */
public class MainController implements Initializable {

    private GameManager gm;
    IGameState gameState;
    @FXML
    private AnchorPane Mpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameState = new GameState();
        gm = new GameManager(gameState);
        createAllCells();

    }

    private void createAllCells() {
        int btnWidth = 30;
        int btnHeight = 30;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                UTTTButton btn = new UTTTButton();
                btn.setMove(new Move(x, y));
                btn.setPrefSize(btnWidth, btnHeight);
                btn.setLayoutX(30+(btnWidth+10)*x);
                btn.setLayoutY(30+(btnHeight+10)*y);
                btn.setOnMouseClicked(event -> {
                    UTTTButton b = (UTTTButton) event.getSource();
                    boolean isSucces = gm.updateGame(b.getMove());
                    if (isSucces) {
                        if (gameState.getMoveNumber() % 2 == 0) {
                            b.setText("X");
                        } else {
                            b.setText("O");
                        }
                    }
                });
                Mpane.getChildren().add(btn);
            }
        }

    }
}

