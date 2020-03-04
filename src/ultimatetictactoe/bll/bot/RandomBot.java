package ultimatetictactoe.bll.bot;


import java.util.List;
import java.util.Random;
import ultimatetictactoe.bll.game.IGameState;
import ultimatetictactoe.bll.move.IMove;

public class RandomBot implements IBot {

    private static final String BOTNAME = "Random Dude";
    private Random rand = new Random();

    /**
     * Makes a turn. Edit this method to make your bot smarter.
     * Currently does only random moves.
     *
     * @return The selected move we want to make.
     */
    @Override
    public IMove doMove(IGameState state) {
        List<IMove> moves = state.getField().getAvailableMoves();

        if (moves.size() > 0) {
            return moves.get(rand.nextInt(moves.size())); /* get random move from available moves */
        }

        return null;
    }
    @Override
    public String getBotName() {
        return BOTNAME;
    }
}
