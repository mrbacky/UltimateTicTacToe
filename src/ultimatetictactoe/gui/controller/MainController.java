/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.gui.controller;


import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import ultimatetictactoe.be.UTTTButton;
import ultimatetictactoe.bll.bot.IBot;
import ultimatetictactoe.bll.field.IField;
import ultimatetictactoe.bll.game.GameManager;
import ultimatetictactoe.bll.game.GameState;
import ultimatetictactoe.bll.game.IGameState;
import ultimatetictactoe.bll.move.Move;
import ultimatetictactoe.gui.BoardModel;

public class MainController implements Initializable {

    private GameManager gm;
    IGameState gameState;
    @FXML
    private AnchorPane Mpane;
    @FXML
    private GridPane Gpane;

    private final GridPane[][] gridMicros = new GridPane[3][3];
    private final JFXButton[][] jfxButtons = new JFXButton[9][9];

    BoardModel model;
    IBot bot0 = null;
    IBot bot1 = null;
    String player0 = null;
    String player1 = null;
    private boolean simulation;

    private long botDelay = 500;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameState = new GameState();
        gm = new GameManager(gameState);
        createAllCells();
        Mpane.setId("Mpane");
        Gpane.setId("Gpane");
    }

    public void startGame() {
        if (model != null) {
            model.removeListener(observable -> update());
        }

        model.addListener(observable -> update());

        // HumanVsHuman
        if (player0 != null && player1 != null) {

        } // HumanVsAI
        else if (bot1 != null && player0 != null) {

        } // AIvsHuman
        else if (bot0 != null && player1 != null) {
            doBotMove();
        } // AIvsAI
        else if (bot0 != null && bot1 != null) {

            Thread t = new Thread(() -> {
                while (model.getGameOverState() == GameManager.GameOverState.Active
                        && model.getGameState().getField().getAvailableMoves().size() > 0) {
                    doBotMove();
                    try {
                        Thread.sleep(botDelay);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            t.setDaemon(true); // Stops thread when main thread dies
            t.start();

        }
    }

    private void doBotMove() {
        int currentPlayer = model.getCurrentPlayer();
        Boolean valid = model.doMove();
        checkAndLockIfGameEnd(currentPlayer);
    }

    private void createAllCells() {
        int btnWidth = 75;
        int btnHeight = 75;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                UTTTButton btn = new UTTTButton();
                btn.setMove(new Move(x, y));
                btn.setPrefSize(btnWidth, btnHeight);
                btn.setLayoutX(20 + (btnWidth + 10) * x);
                btn.setLayoutY(20 + (btnHeight + 10) * y);
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

    public void setupGame(IBot bot0, IBot bot1) {
        model = new BoardModel(bot0, bot1);
        this.bot0 = bot0;
        this.bot1 = bot1;
    }

    public void setupGame(String humanName, IBot bot1) {
        model = new BoardModel(bot1, true);
        this.bot1 = bot1;
        this.player0 = humanName;
    }

    public void setupGame(IBot bot0, String humanName) {
        model = new BoardModel(bot0, false);
        this.bot0 = bot0;
        this.player1 = humanName;
    }

    public void setupGame(String humanName0, String humanName1) {
        model = new BoardModel();
        this.player0 = humanName0;
        this.player1 = humanName1;
    }

    public void update() {
        //updateConsole();
        Platform.runLater(() -> updateGUI());
    }
    
            private void checkAndLockIfGameEnd(int currentPlayer) {
        if (model.getGameOverState() != GameManager.GameOverState.Active) {
            String[][] macroboard = model.getMacroboard();
            // Lock game
            for (int i = 0; i < 3; i++) {
                for (int k = 0; k < 3; k++) {
                    if (macroboard[i][k].equals(IField.AVAILABLE_FIELD)) {
                        macroboard[i][k] = IField.EMPTY_FIELD;
                    }
                }
            }
            /*if (model.getGameOverState().equals(GameManager.GameOverState.Tie)) {
                Platform.runLater(() -> showWinnerPane("TIE"));
            }
            else {
                Platform.runLater(() -> showWinnerPane(currentPlayer + ""));
            }*/
        }
    }

    private void updateGUI() throws RuntimeException {
        String[][] board = model.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[i].length; k++) {
                if (board[i][k].equals(IField.EMPTY_FIELD)) {
                    jfxButtons[i][k].getStyleClass().add("empty");
                }
                else {
                    jfxButtons[i][k].getStyleClass().add("player" + board[i][k]);
                }

            }
        }
        String[][] macroBoard = model.getMacroboard();
        for (int i = 0; i < macroBoard.length; i++) {
            for (int k = 0; k < macroBoard[i].length; k++) {
                if (gridMicros[i][k] != null) {
                    // Highlight available plays
                    if (macroBoard[i][k].equals(IField.AVAILABLE_FIELD)) {
                        gridMicros[i][k].getStyleClass().add("highlight");
                    }
                    else {
                        gridMicros[i][k].getStyleClass().removeAll("highlight");
                    }

                    // If there is a win
                    if (!macroBoard[i][k].equals(IField.AVAILABLE_FIELD)
                            && !macroBoard[i][k].equals(IField.EMPTY_FIELD)
                            && gridMicros[i][k] != null) {
                        setMacroWinner(i, k);
                    }
                }
            }
        }

    }
    
        private void setMacroWinner(int x, int y) {
        String[][] macroBoard = model.getMacroboard();
        Gpane.getChildren().remove(gridMicros[x][y]);
        Label lbl = new Label("");
        lbl.getStyleClass().add("winner-label");
        lbl.getStyleClass().add("player" + macroBoard[x][y]);
        lbl.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridMicros[x][y] = null;
        Gpane.add(lbl, x, y);
    }
    
   
}
