import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcConnection {

    private static final Logger LOGGER =
            Logger.getLogger(JdbcConnection.class.getName());
    private static Optional<Connection> connection = Optional.empty();

    public static Optional<Connection> getConnection() {
        if (connection.isEmpty()) {
            String jdbcURL = "jdbc:postgresql://localhost:5432/2semproject";
            String username = "postgres";
            String password = "password";

            try {
                connection = Optional.ofNullable(
                        DriverManager.getConnection(jdbcURL, username, password));
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }

        return connection;
    }
}