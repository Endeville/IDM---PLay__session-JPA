package tudelft.wis.idm_tasks.implementations;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Manager m = new Manager();
        Connection c = m.getConnection();

    }
}
