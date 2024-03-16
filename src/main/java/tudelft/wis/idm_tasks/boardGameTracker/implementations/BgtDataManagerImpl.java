package tudelft.wis.idm_tasks.boardGameTracker.implementations;

import tudelft.wis.idm_tasks.boardGameTracker.BgtException;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;
import tudelft.wis.idm_tasks.entities.BoardGameEntity;
import tudelft.wis.idm_tasks.entities.PlaySessionEntity;
import tudelft.wis.idm_tasks.entities.BoardGameEntity;
import tudelft.wis.idm_tasks.entities.PlayerEntity;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

public class BgtDataManagerImpl implements BgtDataManager {
    private final EntityManager em;
    public BgtDataManagerImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Player createNewPlayer(String name, String nickname) {
        var p=new PlayerEntity().setName(name).setNickname(nickname).setGameCollection(new HashSet<>());
        em.persist(p);
        return p;
    }

    @Override
    public Collection<Player> findPlayersByName(String name) {
        var result=em.createQuery("""
                        select p from PlayerEntity p
                        where p.name=:name
                        """)
                .setParameter("name", name)
                .getResultList();
        return result;
    }

    public BoardGame createNewBoardgame(String name, String bggURL) throws BgtException {
        BoardGameEntity bge = new BoardGameEntity(name, bggURL);
        em.persist(bge);
        return bge;
    }

    @Override
    public Collection<BoardGame> findGamesByName(String name) throws BgtException {
        try {
            var bc = em.createQuery("""
                        select bge from BoardGameEntity bge
                        where bge.name=:name
                        """)
                .setParameter("name", name)
                .getResultList();
            return bc;
        }catch (Exception e){
            throw new BgtException();
        }
    }

    @Override
    public PlaySession createNewPlaySession(Date date, Player host, BoardGame game, int playtime, Collection<Player> players, Player winner) {
        var p=new PlaySessionEntity()
                .setGame(game)
                        .setDate(date)
                                .setHost(host)
                                        .setPlayers(new HashSet<>(players))
                                                .setWinner(winner);

        em.persist(p);
        return p;
    }
    @Override
    public Collection<PlaySession> findSessionByDate(Date date) throws BgtException {
        try {
            var bc = em.createQuery("""
                        select ps from PlaySessionEntity ps
                        where ps.date=:date
                        """)
                .setParameter("date", date)
                .getResultList();
            return bc;
        }catch (Exception e){
            throw new BgtException();
        }
    }

    @Override
    public void persistPlayer(Player player) {
        var p=new PlayerEntity()
                .setName(player.getPlayerName())
                        .setNickname(player.getPlayerNickName());
        em.persist(p);
    }

    @Override
    public void persistPlaySession(PlaySession session) {
        var p=new PlaySessionEntity()
                .setGame(session.getGame())
                .setHost(session.getHost())
                .setDate(session.getDate())
                .setWinner(session.getWinner())
                .setPlayers((Set<Player>) session.getAllPlayers());
        em.persist(p);
    }

    @Override
    public void persistBoardGame(BoardGame game) {
        var b=new BoardGameEntity()
                .setName(game.getName())
                .setBggUrl(game.getBGG_URL());
        em.persist(b);
    }
}
