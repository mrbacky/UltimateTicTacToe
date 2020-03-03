package ultimatetictactoe.be;

import javafx.scene.control.Button;
import ultimatetictactoe.bll.move.IMove;

public class UTTTButton extends Button {

    private IMove move;

    public IMove getMove() {
        return move;
    }

    public void setMove(IMove move) {
        this.move = move;
    }
}
