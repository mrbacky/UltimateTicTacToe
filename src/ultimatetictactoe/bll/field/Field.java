
package ultimatetictactoe.bll.field;

import java.util.List;
import ultimatetictactoe.bll.move.IMove;


public class Field implements IField{
    private String board[][] = new String[3][3];
    private String macroboard[][] = new String[3][3];

    @Override
    public void clearBoard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IMove> getAvailableMoves() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPlayerId(int column, int row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
     this.board=board;  
    }

    @Override
    public void setMacroboard(String[][] macroboard) {
      this.macroboard=macroboard;
    }

}
