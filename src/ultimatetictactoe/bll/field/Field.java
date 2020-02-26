package ultimatetictactoe.bll.field;

import java.util.List;
import ultimatetictactoe.bll.move.IMove;

public class Field implements IField {

    private String[][] board;
    private String[][] macroboard;

    public Field() {
        board = new String[9][9];
        macroboard = new String[3][3];
        clearBoard();
    }

    @Override
    public void clearBoard() {
        clearMacroboard();
        clearMicroboards();
    }

    private void clearMacroboard() {
        for (int i = 0; i < macroboard.length; i++) {
            for (int k = 0; k < macroboard[i].length; k++) {
                board[i][k] = AVAILABLE_FIELD;
            }
        }
    }

    private void clearMicroboards() {
        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[i].length; k++) {
                board[i][k] = EMPTY_FIELD;
            }
        }
    }

    @Override
    public List<IMove> getAvailableMoves() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPlayerId(int column, int row) {
        return board[column][row];
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[i].length; k++) {
                if (!board[i][k].equals(EMPTY_FIELD)) {
                    return false;
                }
            }
        }
        return true;
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
        for (int i = 0; i < macroboard.length; i++) {
            for (int k = 0; k < macroboard[i].length; k++) {
                if (macroboard[i][k].equals(AVAILABLE_FIELD) || macroboard[i][k].equals(EMPTY_FIELD)) {
                    return false;
                }
            }
        }
        return true;
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
