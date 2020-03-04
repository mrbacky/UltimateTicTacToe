package ultimatetictactoe.bll.bot;

import ultimatetictactoe.bll.game.IGameState;
import ultimatetictactoe.bll.move.IMove;

/**
 *
 * @author mjl
 */
public interface IBot {

    /**
     * Makes a turn. Implement this method to make your dk.easv.bll.bot do something.
     *
     * @param state the current dk.easv.bll.game state
     * @return The column where the turn was made.
     */
    IMove doMove(IGameState state);

    String getBotName();
}
