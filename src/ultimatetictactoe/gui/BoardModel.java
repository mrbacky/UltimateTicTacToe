/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.gui;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import ultimatetictactoe.bll.bot.IBot;
import ultimatetictactoe.bll.game.GameManager;
import ultimatetictactoe.bll.game.GameState;
import ultimatetictactoe.bll.game.IGameState;
import ultimatetictactoe.bll.move.IMove;



public class BoardModel implements Observable{
    private static final int TIME_PER_MOVE = 1000; //Each bot is allowed 1000ms per move
    private final List<InvalidationListener> listeners = new ArrayList<>();
    private final GameManager game;
    
    public BoardModel() {
        game = new GameManager(new GameState());
        game.getCurrentState().setTimePerMove(TIME_PER_MOVE);
    }
    public BoardModel(IBot bot, boolean humanPlaysFirst) {
        game = new GameManager(new GameState(), bot, humanPlaysFirst);
        game.getCurrentState().setTimePerMove(TIME_PER_MOVE);
    }
    public BoardModel(IBot bot1, IBot bot2) {
        game = new GameManager(new GameState(), bot1, bot2);
        game.getCurrentState().setTimePerMove(TIME_PER_MOVE);
    }

    private void notifyAllListeners(){
        for (InvalidationListener listener : listeners){
            listener.invalidated(this);
        }
    }

    @Override
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listeners.remove(listener);
    }

    public IGameState getGameState() {
        return game.getCurrentState();
    }

    public boolean doMove() {
        boolean valid = game.updateGame();
        if(valid)
            notifyAllListeners();
        return valid;
    }

    public boolean doMove(IMove move){
        boolean valid = game.updateGame(move);
        if(valid)
            notifyAllListeners();
        return valid;
    }

    public String[][] getMacroboard()
    {
        return game.getCurrentState().getField().getMacroboard();
    }

    public String[][] getBoard(){
        return game.getCurrentState().getField().getBoard();
    }

    public int getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public GameManager.GameOverState getGameOverState() {
        return game.getGameOver();
    }
}

