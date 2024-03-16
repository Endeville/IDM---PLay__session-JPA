package tudelft.wis.idm_tasks.boardGameTracker.implementations;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.Collection;
import java.util.Date;

public class PlaySessionImpl implements PlaySession {
    @Override
    public Date getDate() {
        return null;
    }

    @Override
    public Player getHost() {
        return null;
    }

    @Override
    public BoardGame getGame() {
        return null;
    }

    @Override
    public Collection<Player> getAllPlayers() {
        return null;
    }

    @Override
    public Player getWinner() {
        return null;
    }

    @Override
    public int getPlaytime() {
        return 0;
    }

    @Override
    public String toVerboseString() {
        return null;
    }
}
