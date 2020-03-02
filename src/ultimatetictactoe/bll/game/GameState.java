/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ultimatetictactoe.bll.game;

import ultimatetictactoe.bll.field.Field;
import ultimatetictactoe.bll.field.IField;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class GameState implements IGameState {

    private IField field;
    private int moveNumber;
    private int roundNumber;

    public GameState() {
        field = new Field();
        moveNumber = 0;
        roundNumber = 0;
    }

    @Override
    public IField getField() {
        return field;
    }

    @Override
    public int getMoveNumber() {
        return moveNumber;
    }

    @Override
    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }

    @Override
    public int getRoundNumber() {
        return roundNumber;
    }

    @Override
    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;  
    }

}
