package br.com.model.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author wesley_s
 */
public class ConnectionFactory {

    private static final String STR_CONEXAO = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE = "exemplochaveestrangeira";
    private static final String USER = "root";
    private static final String PASSWORD = "123";

    public static Connection getConnection() throws SQLException {

        try {
            return DriverManager.getConnection(STR_CONEXAO + DATABASE, USER, PASSWORD);
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    public static void closeConnection(Connection con) throws SQLException {
        if (con != null) {
            con.close();
            System.out.println("Conexao Finalizada");
        }

    }

    public static void closeConnection(Connection con, Statement st) throws SQLException {
        if (st != null) {
            st.close();
            System.out.println("Statement finalizado");
        }
        closeConnection(con);
    }

    public static void closeConnection(Connection con, Statement st, ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
            System.out.println("ResultSet finalizado");
        }
        closeConnection(con, st);
    }
}