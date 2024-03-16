/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;
import tudelft.wis.idm_tasks.boardGameTracker.BgtException;
import tudelft.wis.idm_tasks.boardGameTracker.implementations.BgtDataManagerImpl;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;
import tudelft.wis.idm_tasks.entities.BoardGameEntity;
import tudelft.wis.idm_tasks.entities.PlaySessionEntity;
import tudelft.wis.idm_tasks.entities.PlayerEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type POJO test.
 *
 * @author Christoph Lofi, Alexandra Neagu
 */
public class POJO_Test extends tudelft.wis.idm_solutions.BoardGameTracker.AbstractBGTDemo {

    private final EntityManager em= Persistence.createEntityManagerFactory("idm_jpa")
            .createEntityManager();

    /**
     * Instantiates a new POJO test.
     */
    public POJO_Test() {
    }

    private final BgtDataManagerImpl dataManager = new BgtDataManagerImpl(em);

    @Override
    public BgtDataManager getBgtDataManager() {
        return dataManager;
    }

    @BeforeEach
    public void init() {
        em.getTransaction().begin();
    }

    @AfterEach
    public void close() {
        em.getTransaction().commit();
    }


    /**
     * Just runs the application with some simple queries and assertions. It's
     * not very comprehensive, essentially, just a single session is retrieved
     * and the hist and the game is being checked.
     */
    @Test
    public void basicTest() throws BgtException {


        // Make sure to start this test with an empty DB - trivial for POJO though...
        // Create dummy data
        Collection<PlaySessionEntity> testSessions = this.createDummyData(5, 6);

        for (PlaySessionEntity session : testSessions) {
            Logger.info("Session Created: \n" + session.toVerboseString());
        }

        // Get dummy session & related data
        PlaySessionEntity firstsession = testSessions.iterator().next();
        PlayerEntity host = firstsession.getHost();
        BoardGameEntity game = firstsession.getGame();

        // Retrieve the host from the database and check if it returns correctly
        PlayerEntity retrievedPlayer = this.getBgtDataManager().findPlayersByName(host.getName()).iterator().next();
        assertEquals(retrievedPlayer.getNickname(), retrievedPlayer.getNickname());
        assertEquals(retrievedPlayer.getGameCollection().size(), host.getGameCollection().size());
        Logger.info("Player check passed: " + retrievedPlayer.getName() + "; collectionSize: " + retrievedPlayer.getGameCollection().size());

        // Retrieve the game from the database and check if it returns correctly
        BoardGameEntity retrievedGame = this.getBgtDataManager().findGamesByName(game.getName()).iterator().next();
        assertEquals(retrievedGame.getBggUrl(), game.getBggUrl());

        // Retrieve session by date
        Collection<PlaySessionEntity> retrievedSession = this.getBgtDataManager().findSessionByDate(firstsession.getDate());
        assertEquals(firstsession.getDate(), retrievedSession.iterator().next().getDate());

        // Remove a game from the host's collection, add  it again
        BoardGameEntity firstGame = host.getGameCollection().iterator().next();
        int numOfGames = host.getGameCollection().size();
        host.getGameCollection().remove(firstGame);
        this.getBgtDataManager().persistPlayer(host);

        // Load the host again from DB
        PlayerEntity hostFromDB = this.getBgtDataManager().findPlayersByName(host.getName()).iterator().next();
        assertEquals(numOfGames - 1, hostFromDB.getGameCollection().size());

        // Add the game again
        hostFromDB.getGameCollection().add(firstGame);
        this.getBgtDataManager().persistPlayer(host);

        // Load the host again from DB
        PlayerEntity hostFromDB2 = this.getBgtDataManager().findPlayersByName(host.getName()).iterator().next();
        assertEquals(numOfGames, hostFromDB2.getGameCollection().size());
    }
}
