package ultimatetictactoe.bll.field;

import java.util.List;
import ultimatetictactoe.bll.move.IMove;

public class Field implements IField {

    private String board[][] = new String[9][9];
    private String macroboard[][] = new String[3][3];

    public Field() {
        clearBoard();
    }

    @Override
    public void clearBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = EMPTY_FIELD;
            }
        }

        for (int i = 0; i < macroboard.length; i++) {
            for (int j = 0; j < macroboard[i].length; j++) {
                macroboard[i][j] = AVAILABLE_FIELD;
            }
        }
    }

    @Override
    public List<IMove> getAvailableMoves() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPlayerId(int column, int row) {
        String playerID = board[column][row];
        return playerID;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(EMPTY_FIELD)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!board[i][j].equals(EMPTY_FIELD)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean isInActiveMicroboard(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[][] getBoard() {
        return board;
    }

    @Override
    public String[][] getMacroboard() {
        return macroboard;
    }

    @Override
    public void setBoard(String[][] board) {
        this.board = board;
    }

    @Override
    public void setMacroboard(String[][] macroboard) {
        this.macroboard = macroboard;
    }

}
