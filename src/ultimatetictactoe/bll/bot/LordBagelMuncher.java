package ultimatetictactoe.bll.bot;

import java.util.List;
import java.util.Random;
import ultimatetictactoe.bll.game.IGameState;
import ultimatetictactoe.bll.move.IMove;

public class LordBagelMuncher implements IBot{
    private static final String BOTNAME = "Lord Bagel Muncher";

    @Override
    public IMove doMove(IGameState state) {
        Random r = new Random();
        List<IMove> validMoves = state.getField().getAvailableMoves();
        return validMoves.get(r.nextInt(validMoves.size()));
    }

    @Override
    public String getBotName() {
        return BOTNAME;
    }
}
