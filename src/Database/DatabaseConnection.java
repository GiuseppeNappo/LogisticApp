package Database;



import java.sql.*;

/**
 * Una classe singleton che ci permette di mantenere una connessione attiva con il db senza andare a creare innumrevoli connessioni  pero ogni queri da effettuare
 */
public  class DatabaseConnection {

    private static Connection conn = null;
    public static final String JDBC_DRIVER = "org.sqlite.JDBC";
    public static final String DATABASE_URl = "jdbc:sqlite:LogisticDB.sqlite";


    private static void connect() {
        try {
            //Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DATABASE_URl);
            conn.createStatement().execute("PRAGMA foreign_keys = ON");
        } catch (SQLException  e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection(){
        if(conn == null)
            connect();
        return conn;
    }
}


