import java.sql.*;

public abstract class MySqlObject {
    static final String DB_URL = "jdbc:mariadb://localhost:3306/DB?user=escola&password=escola";

    protected Connection connection;
    protected boolean connected = true;

    public MySqlObject()
    {
        try {
            connection = DriverManager.getConnection(DB_URL);

            obterDados();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connected = false;
        }
    }

    public void close()
    {
        if (connected)
            populaDb();
    }

    protected abstract void obterDados();

    protected abstract void populaDb();
}
