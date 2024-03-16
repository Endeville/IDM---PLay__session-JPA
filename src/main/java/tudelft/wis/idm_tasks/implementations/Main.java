package tudelft.wis.idm_tasks.implementations;

import java.sql.Connection;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
             var entityMng =Persistence.createEntityManagerFactory("idm_jpa");
             var em=entityMng.createEntityManager();
             em.getTransaction().begin();
             em.getTransaction().commit();
    }
}
