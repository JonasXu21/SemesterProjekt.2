package dataSource;
import domain.*;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class StopWordsDa implements IStopWords {
    public static final String DB_NAME = "stop_words_Eng";
    public static final String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/" + DB_NAME;
    public static final String DB_PASSWORD = "7872667";
    public static final String DB_USER = "postgres";
    protected Statement statement ;
    protected Connection connection ;
    private static Scanner input = new Scanner(System.in);
    private static List<String> tokenTester = new ArrayList<>();


   public StopWordsDa() {


    }

    public static List<String> unfiltered() {
        if (tokenTester.isEmpty()) {
            System.out.println("Type a token ");
            String token = input.nextLine();
            String[] split = token.split(" ",0);
            for (String s : split)
                tokenTester.add(s);
        }
        return tokenTester;
    }

    protected void open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Collection<String> filter(List<String> unfilteredTokens) {
       open();
               Collection<String> useableStopwords = new ArrayList<>();

        try {
            for (String i : unfilteredTokens) {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT name FROM \"words_to_removeDa\" WHERE" +
                        " name ='" + i + "';");
                {
                    while (resultSet.next()) {
                        useableStopwords.add(resultSet.getString("name"));
                    }
                    resultSet.close();
                }
            }
            unfilteredTokens.removeAll(useableStopwords);
            statement.close();
            connection.close();


        } catch (SQLException e) {
            System.out.println("Error connecting to database" + e.getMessage());
            e.printStackTrace();
        }
        return unfilteredTokens;

    }


    @Override
    public void addStopWord(String stopWord) {
       open();
        try {
            statement = connection.createStatement();
            int rows = statement.executeUpdate("INSERT INTO \"words_to_removeDa\" (name)" + "VALUES ('" + stopWord + "') ON CONFLICT DO NOTHING");
            if (rows > 0) {
                System.out.println("Add command: A new stop-word has been added: " + stopWord + "");
            } else {
                System.out.println("Add command: The stop-word: " + stopWord + " already exist");
            }
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error connecting to database" + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void removeStopWord(String stopWord) {
       open();
        try {
            statement = connection.createStatement();
            int rows = statement.executeUpdate("DELETE FROM \"words_to_removeDa\" WHERE name = '" + stopWord + "'");
            if (rows > 0) {
                System.out.println("Remove command: The stop-word: " + stopWord + " Has been removed");
            } else {
                System.out.println("Remove command: The stop-word: " + stopWord + " Does not exist");
            }
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error connecting to database" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void queryStopWord() {
       open();
        try {
            statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM \"words_to_removeDa\"");
            while (results.next()) {
                System.out.println(results.getInt("nr") + ":" +
                        results.getString("name"));
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error connecting to database" + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void createTable() {
        open();
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS words_to_removeDa ()"); //+// write your own name
                    // of the table and add attributes like phone, search_historic...etc
                   // "(nr NOT_NULL AUTO_INCREMENT UNIQUE PRIMARY KEY , name NOT NULL UNIQUE TEXT )");
            System.out.println(" Table is created successfully ! ");
            statement.close();// remember to close the statement and connection to save resources
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error connecting to database" + e.getMessage());
            e.printStackTrace();
        }
    }
}

