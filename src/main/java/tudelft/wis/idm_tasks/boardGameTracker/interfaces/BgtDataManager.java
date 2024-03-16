/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tudelft.wis.idm_tasks.boardGameTracker.interfaces;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import tudelft.wis.idm_tasks.boardGameTracker.BgtException;
import tudelft.wis.idm_tasks.entities.BoardGameEntity;
import tudelft.wis.idm_tasks.entities.PlaySessionEntity;
import tudelft.wis.idm_tasks.entities.PlayerEntity;

// Add other necessary imports here

/**
 * The Data Manager is used to create and access entities from the database.
 *
 * @author Christoph Lofi, Alexandra Neagu
 */
public interface BgtDataManager {

    /**
     * Creates a new player and stores it in the DB.
     *
     * @param name the player name
     * @param nickname the player nickname
     * @return the new player
     */
    public PlayerEntity createNewPlayer(String name, String nickname);
        // @TODO: Implement this method.

    /**
     * Searches for player in the database by a substring of their name.
     *
     * @param name the name substring to use, e.g., searching for "hris" will find "Christoph"
     * @return collection of all players containing the param substring in their names
     */
    public Collection<PlayerEntity> findPlayersByName(String name);
        // @TODO: Implement this method.

    /**
     * Creates a new board game and stores it in the DB.
     * <p>
     * Note: These "create" methods are somewhat unnecessary. However, I put
     * them here to make the task test a bit easier. You can call an appropriate
     * persist method for this.
     *
     * @param name the name of the game
     * @param bggURL the URL of the game at BoardGameGeek.com
     * @return the new game
     */
    public BoardGameEntity createNewBoardgame(String name, String bggURL);
        // @TODO: Implement this method.

    /**
     * Searches for game in the database by a substring of their name.
     *
     * @param name the name substring to use, e.g., searching for "clips" will
     * find "Eclipse: Second Dawn of the Galaxy""
     * @return collection of all boardgames containing the param substring in their names
     */
    public Collection<BoardGameEntity> findGamesByName(String name);
        // @TODO: Implement this method.

    /**
     * Creates a new play session and stores it in the DB.
     *
     * @param date the date of the session
     * @param host the session host
     * @param game the game which was played
     * @param playtime the approximate playtime in minutes
     * @param players all players
     * @param winner the one player who won (NULL in case of no winner; multiple
     * winners not supported)
     * @return the new play session
     */
    public PlaySessionEntity createNewPlaySession(Date date, PlayerEntity host, BoardGameEntity game, int playtime, Collection<PlayerEntity> players, PlayerEntity winner) throws BgtException;
        // @TODO: Implement this method.

    /**
     * Finds all play sessions from a specific date
     *
     * @param date the date to search from
     * @return collection of all play sessions from the param date
     * @throws BgtException the bgt exception
     */
    public Collection<PlaySessionEntity> findSessionByDate(Date date) throws BgtException;
        // @TODO: Implement this method.

    /**
     * Persists a given player to the DB. Note that this player might already exist and only needs an update :-)
     * @param player the player
     */
    public void persistPlayer(PlayerEntity player);
        // @TODO: Implement this method.

    /**
     * Persists a given session to the DB. Note that this session might already exist and only needs an update :-)
     * @param session the session
     */
    public void persistPlaySession(PlaySessionEntity session);
        // @TODO: Implement this method.

    /**
     * Persists a given game to the DB. Note that this game might already exist and only needs an update :-)
     * @param game the game
     */
    public void persistBoardGame(BoardGameEntity game);
        // @TODO: Implement this method.

}
