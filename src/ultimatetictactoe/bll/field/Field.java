package ultimatetictactoe.bll.field;

import java.util.ArrayList;
import java.util.List;
import ultimatetictactoe.bll.move.IMove;
import ultimatetictactoe.bll.move.Move;

public class Field implements IField {

    String[][] macroBoard; //3x3
    String[][] board; //9x9

    public Field() {
        macroBoard = new String[3][3];
        board = new String[9][9];

        clearBoard();
    }

    @Override
    public void clearBoard() {
        for (int x = 0; x < macroBoard.length; x++) {
            for (int y = 0; y < macroBoard[x].length; y++) {
                macroBoard[x][y] = AVAILABLE_FIELD;
            }
        }

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = EMPTY_FIELD;
            }
        }
    }

    @Override
    public List<IMove> getAvailableMoves() {
        List<IMove> moveLst = new ArrayList();
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                boolean isEmpty = board[x][y] == EMPTY_FIELD;
                if (isEmpty && isInActiveMicroboard(x, y)) {
                    moveLst.add(new Move(x, y));
                }
            }
        }
        return moveLst;

    }

    @Override
    public String getPlayerId(int column, int row) {
        return board[column][row];
    }

    @Override
    public boolean isEmpty() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (!board[x][y].equals(EMPTY_FIELD)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isFull() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y].equals(EMPTY_FIELD)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Boolean isInActiveMicroboard(int x, int y) {
        int macroX = x / 3;
        int macroY = y / 3;

        return macroBoard[macroX][macroY].equals(AVAILABLE_FIELD);
    }

    @Override
    public String[][] getBoard() {
        return board;
    }

    @Override
    public String[][] getMacroboard() {
        return macroBoard;
    }

    @Override
    public void setBoard(String[][] board) {
        this.board = board;
    }

    @Override
    public void setMacroboard(String[][] macroboard) {
        this.macroBoard = macroboard;
    }

}
