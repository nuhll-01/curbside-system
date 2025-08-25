import java.sql.*;
import java.lang.String;

public class DatabaseManager {

    private static final String DB_PATH = "database/orders.db";
    private static final String URL = "jdbc:sqlite:" + DB_PATH;

    public static void initializeDatabase() {
        // establish a connection to the database
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                System.out.println("Connection Successful ✅");
            }
        } catch (SQLException e) {
            System.out.println("Error: Database Initialization Failed " + e.getMessage() + " ❌");
        }
    }

    /**
     * Performs the direct database query based on the provided parameters.
     * @param orderID The specific order number to be used for comparison.
     * @throws SQLException Error-Handling
     * @return "true" if order number found, otherwise return "false".
     */
    public static boolean searchOrderNumber(String orderID) throws SQLException {
        boolean found = false;

        // Establish a connection to the database
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                String searchQuery = """
                        SELECT id FROM orders
                        """;
                // Create the static SQL statement
                try (Statement stmt = conn.createStatement()) {

                    // Cursor is positioned before the first row of the data column
                    ResultSet rs = stmt.executeQuery(searchQuery);

                    // Move cursor to the next row of the data column
                    while (rs.next()) {
                        // Retrieve 'id' from database (it's represented as an 'int')
                        int order_number = rs.getInt("id");

                        String copy_order_number = String.valueOf(order_number);

                        if (orderID.equals(copy_order_number)) {
                            return true;
                        }
                    }
                } catch (SQLException e) {
                    throw new SQLException(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to Connect to database " + e.getMessage());
        }

        if (!found) { System.out.println("\nORDER NOT FOUND."); }
        return false;
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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Insert a record to the database
     * @param conn connection to the database
     * @throws SQLException Error-Handling
     */
    private static void insertRecords(Connection conn) throws SQLException {
        // inserting records
        String insertQuery = """
                  INSERT INTO orders (customer_first_name, customer_last_name, item_description)\s
                  VALUES ("Alexander", "Jones", "32GB DDR5 RAM")
                \s""";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(insertQuery);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
