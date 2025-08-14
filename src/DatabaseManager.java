import java.sql.*;

public class DatabaseManager {

    private static final String DB_PATH = "database/orders.db";
    private static final String URL = "jdbc:sqlite:" + DB_PATH;

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                System.out.println("Connection Successful ✅");
                createTables(conn);
            }
        } catch (SQLException e) {
            System.out.println("Error: Database Initialization Failed " + e.getMessage() + " ❌");
        }
    }

    /**
     * Create a database table
     * @param conn connection to the database
     * @throws SQLException Error-Handling
     */
    private static void createTables(Connection conn) throws SQLException {
        // initialize the 'orders' table with four columns
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
        } catch(SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Drop a table from the database - (use method with caution)
     * @param conn connection to the database
     * @throws SQLException Error-Handling
     */
    private static void dropTable(Connection conn) throws SQLException {
        // dropping database table
        String dropQuery = "DROP TABLE IF EXISTS orders";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(dropQuery);
        } catch(SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Insert a record to the database
     * @param conn connection to the database
     * @throws SQLException Error-Handling
     */
    private static void insert(Connection conn) throws SQLException {
        // inserting records
        String insertQuery = """
             INSERT INTO orders (customer_first_name, customer_last_name, item_description)\s
             VALUES ("Juan", "Dingo", "RTX-4080")
           \s""";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(insertQuery);
        } catch(SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     *
     * @param conn
     * @param orderID The specific order ID to be used for comparison or filtering in future queries.
     * @throws SQLException Error-Handling
     */
    private static void searchIDs(Connection conn, String orderID) throws SQLException {
        // iterate through the primary-key of the database
        String searchQuery = """
                SELECT id FROM orders
        """;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(searchQuery);

            while (rs.next()) {
                int id = rs.getInt("id"); // Retrieve 'id' as int
                // TODO - Convert 'int' to 'String'




                // TODO - Compare Values (ID & orderID)




                System.out.println("ID: " + id);
            }
        } catch(SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
