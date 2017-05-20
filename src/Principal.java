import sun.nio.ch.sctp.SctpNet;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static final String DB_URL = "jdbc:sqlite:escola.db";
    public static Connection dbConnection;
    public static boolean dbConnected = true;

    public static void main(String[] args)
    {
        // configurar conexão com banco de dados
        try
        {
            dbConnection = DriverManager.getConnection(DB_URL);

            System.out.println("Conectado a base de dados!");
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            dbConnected = false;
        }

        // obter dados se conectado
        if (dbConnected)
        {
            Escola.getInstance().obterDados();
        }

        MenuUI.getInstance().run();
    }
}
