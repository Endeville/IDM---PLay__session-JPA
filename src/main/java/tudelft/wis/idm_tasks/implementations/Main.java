package tudelft.wis.idm_tasks.implementations;

import tudelft.wis.idm_tasks.boardGameTracker.implementations.BgtDataManagerImpl;

import java.sql.SQLException;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
             var entityMng =Persistence.createEntityManagerFactory("idm_jpa");
             var em=entityMng.createEntityManager();
             em.getTransaction().begin();
             var manager=new BgtDataManagerImpl(em);
             manager.createNewPlayer("something", "nickname");
             manager.createNewPlayer("something", "yeag");
             manager.findPlayersByName("something");
             em.getTransaction().commit();
    }
}
