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

    private static void createTables(Connection conn) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS orders (
                    id TEXT PRIMARY KEY,
                    customer_name TEXT NOT NULL,
                    contents TEXT NOT NULL
                );
                """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("✅ Orders table is ready.\n");
        }
    }
}
