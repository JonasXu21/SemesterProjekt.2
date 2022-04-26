package dataSource;
import domain.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;




public class StopWordsEng extends StopWordsDa implements IStopWords {
   private static Collection<String> useableStopwords;


   public StopWordsEng() {
        useableStopwords =new ArrayList<>();
    }

@Override
    public Collection<String> filter(List<String> unfilteredTokens) {
       open();
        try {
            for(String i: unfilteredTokens) {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT name FROM \"words_to_remove\" WHERE name = '" + i + "';");
                {
                    while (resultSet.next()){
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
            int rows = statement.executeUpdate("INSERT INTO \"words_to_remove\" (name)"
                    + "VALUES ('" + stopWord + "') ON CONFLICT DO NOTHING");
            if (rows > 0){
                System.out.println("Add command: A new stop-word has been added: " + stopWord + "");
            }
            else {
                System.out.println("Add command: The stop-word: " + stopWord +  " already exist");
            }
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error connecting to database"+e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public void removeStopWord(String stopWord) {
       open();
        try {
            statement = connection.createStatement();
            int rows = statement.executeUpdate("DELETE FROM \"words_to_remove\" WHERE name = '" + stopWord + "'");
            if (rows > 0){
                System.out.println("Remove command: The stop-word: " + stopWord + " Has been removed");
            }
            else {
                System.out.println("Remove command: The stop-word: " + stopWord +  " Does not exist");
            }
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error connecting to database"+e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public void queryStopWord() {
      open();
        try {
            statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM \"words_to_remove\"");
            while (results.next()) {
                System.out.println(results.getInt("nr") +":"+
                        results.getString("name"));
            }
            results.close();
            statement.close();
            connection.close();
        }catch (SQLException e) {
            System.out.println("Error connecting to database"+e.getMessage());
            e.printStackTrace();
        }

    }
    @Override
    public void createTable() {
       open();
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS words_to_removeEng ()"); //+// write your own name
            // of the table and add attributes like phone, search_historic...etc
            // "(nr NOT_NULL AUTO_INCREMENT UNIQUE PRIMARY KEY , name NOT NULL UNIQUE TEXT )");
            statement.close();// remember to close the statement and connection to save resources
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error connecting to database" + e.getMessage());
            e.printStackTrace();
        }
    }
}
//import java.util.*;
//import java.sql.*;
//class DataModel.StopWords {
//    // factory pattern for at skifte factories
//
//    public static Collection<String> filter(List<String> unfilteredTokens) {
//
//        Collection<String> useableStopwords = new ArrayList<>();
//        String jdbcURL = "jdbc:postgresql://localhost:5432/stop_words_Eng";
//        String username = "postgres";
//        String password = "7872667";
//
//        try {
//            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
//
//            for (String i : unfilteredTokens) {
//                String sql = "SELECT name FROM \"Words_to_remove\" WHERE name = '" + i + "';";
//
//
//                Statement statement = connection.createStatement();
//
//                ResultSet resultSet = statement.executeQuery(sql);
//                {
//                    while (resultSet.next()) {
//                        useableStopwords.add(resultSet.getString("name"));
//                    }
//                }
//            }
//            unfilteredTokens.removeAll(useableStopwords);
//
//
//        } catch (SQLException e) {
//            System.out.println("Error connecting to database");
//            e.printStackTrace();
//        }
//        return unfilteredTokens;
//    }
//
//    public static void addStopword(String stopword) {
//
//        String jdbcURL = "jdbc:postgresql://localhost:5432/stop_words_Eng";
//        String username = "postgres";
//        String password = "7872667";
//
//        try {
//            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
//
//
//            String sql = "INSERT INTO \"Words_to_remove\" (name)"
//                    + "VALUES ('" + stopword + "') ON CONFLICT DO NOTHING";
//            Statement statement = connection.createStatement();
//            int rows = statement.executeUpdate(sql);
//            if (rows > 0) {
//                System.out.println("Add command: A new stopword has been added: '" + stopword + "'");
//            } else {
//                System.out.println("Add command: The stopword: '" + stopword + "' already exist");
//            }
//
//            connection.close();
//
//        } catch (SQLException e) {
//            System.out.println("Error connecting to database");
//            e.printStackTrace();
//        }
//    }
//
//    public static void removeStopword(String stopword) {
//        //TreesetStopwords.remove(stopword);
//
//        String jdbcURL = "jdbc:postgresql://localhost:5432/stop_words_Eng";
//        String username = "postgres";
//        String password = "7872667";
//
//        try {
//            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
//
//
//            String sql = "DELETE FROM \"Words_to_remove\" WHERE name = '" + stopword + "'";
//
//            Statement statement = connection.createStatement();
//            int rows = statement.executeUpdate(sql);
//            if (rows > 0) {
//                System.out.println("Remove command: The stopword: '" + stopword + "' Has been removed");
//            } else {
//                System.out.println("Remove command: The stopword: '" + stopword + "' Does not exist");
//            }
//
//            connection.close();
//
//        } catch (SQLException e) {
//            System.out.println("Error connecting to database");
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void main(String[] args) {
//        ArrayList<String> test = new ArrayList<>();
//        removeStopword("Ali");
//        test.add("throug");
//        test.add("Products");
//        test.add("multiple");
//        test.add("tage");
//        test.add("Ali");
//        addStopword("Furthermore");
//        addStopword("Furthermore");
//        removeStopword("Furthermore");
//        removeStopword("Furthermore");
//        addStopword("Furthermore");
//        System.out.println("Pre filtermethod: " + test);
//        filter(test);
//        System.out.println("Post filtermethod: " + test);
//
//    }
//}
