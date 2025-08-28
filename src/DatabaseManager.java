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
                        // Retrieve 'id' from database (represented as an int)
                        int order_number = rs.getInt("id");

                        String copy_order_number = String.valueOf(order_number);

                        if (orderID.equals(copy_order_number)) {
                            return true;
                        }
                    }
                }
            }
        }
        if (!found) { System.out.println("\nORDER NOT FOUND."); }
        return false;
    }

    /**
     * Retrieves the first name of the customer by using the primary key as the identifier.
     * @param primaryKey Used to identify the customer.
     * @return The customer's first name or <code>null</code> if not found.
     * @throws SQLException Error-Handling
     */
    public static String getFirstName(String primaryKey) throws SQLException {

        // Establish a connection to the database
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {

                // Define the query with a placeholder '?'
                String query = "SELECT customer_first_name FROM orders WHERE id = ?";

                // Create a PreparedStatement object
                try  (PreparedStatement stmt = conn.prepareStatement(query)) {
                    // Set the user-provided value. The JDBC driver handles the escaping.
                    stmt.setString(1, primaryKey);

                    // Retrieve the first name from database (represented as a string)
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            return rs.getString("customer_first_name");
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * Retrieves the last name of the customer by using the primary key as the identifier.
     * @param primaryKey Used to identify the customer.
     * @return The customer's last name or <code>null</code> if not found.
     * @throws SQLException Error-Handling
     */
    public static String getLastName(String primaryKey) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                String query = "SELECT customer_last_name FROM orders WHERE id = ?";
                try  (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, primaryKey);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            return rs.getString("customer_last_name");
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * Retrieves the customer's item by using the primary key as the identifier.
     * @param primaryKey Used to identify the customer item.
     * @return The customer's item or <code>null</code> if not found.
     * @throws SQLException Error-Handling
     */
    public static String getItem(String primaryKey) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                String query = "SELECT item_description FROM orders WHERE id = ?";
                try  (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, primaryKey);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            return rs.getString("item_description");
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * Create a database table
     * @param conn connection to the target database
     * @throws SQLException Error-Handling
     */
    private static void createTables(Connection conn) throws SQLException {
        // initialize the 'orders' table with four columns
        String createQuery = """
                CREATE TABLE IF NOT EXISTS orders (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    customer_first_name TEXT NOT NULL,
                    customer_last_name TEXT NOT NULL,
                    customer_phone_number INTEGER,
                    item_description TEXT NOT NULL,
                    quantity INTEGER,
                    status TEXT NOT NULL
                );
                """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createQuery);
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
                  INSERT INTO orders (customer_first_name, customer_last_name,\s
                                      customer_phone_number, item_description,
                                      quantity, status)\s
                  VALUES ("Greg", "Long", 4144952518, "Microfiber Cloth", 2, "incomplete")
                \s""";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(insertQuery);
        }
    }
}
