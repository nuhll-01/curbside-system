import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DB_PATH = "database/orders.db";
    private static final String URL = "jdbc:sqlite:" + DB_PATH;

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                System.out.println("✅ Database created or opened at: " + DB_PATH);
                createTables(conn);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error creating database: " + e.getMessage());
        }
    }

    /**
     * Create a database table
     * @param conn connection to the database
     * @throws SQLException error-handling
     */
    private static void createTables(Connection conn) throws SQLException {
        /* initialize the 'orders' table with four columns */
        String createQuery = """
                CREATE TABLE IF NOT EXISTS orders (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    customer_first_name TEXT NOT NULL,
                    customer_last_name TEXT NOT NULL,
                    item_description TEXT NOT NULL
                );
                """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createQuery);
        }
    }

    /**
     * Drop a table from the database - (use method with caution)
     * @param conn connection to the database
     * @throws SQLException error-handling
     */
    private static void dropTable(Connection conn) throws SQLException {
        /* dropping database table */
        String dropQuery = "DROP TABLE IF EXISTS orders";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(dropQuery);
        }
    }

    /**
     * Insert a record to the database
     * @param conn connection to the database
     * @throws SQLException error-handling
     */
    private static void insert(Connection conn) throws SQLException {
        /* inserting records */
        String insertQuery = """
             INSERT INTO orders (customer_first_name, customer_last_name, item_description)\s
             VALUES ("Juan", "Dingo", "RTX-4080")
           \s""";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(insertQuery);
        }
    }
}
