import java.sql.SQLException;

/**
 * Author: Erick Chavez
 * Description: Simulating a text-based store curbside system
 */
public class CurbsidePickupDriver {

    /**
     * starting point of the program
     * @param args string array of list of arguments
     */
    public static void main(String[] args) throws SQLException {
        CurbsideSystem sys = new CurbsideSystem();
        DatabaseManager.initializeDatabase();
        sys.start();
    }
}
