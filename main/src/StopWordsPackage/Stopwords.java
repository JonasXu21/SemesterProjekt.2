package StopWordsPackage;

import java.sql.*;
import java.util.*;

public class Stopwords {

    public Stopwords() {
    }

    public static Collection<String> filter(List<String> unfilteredTokens) {

        Collection<String> useableStopwords = new ArrayList<>();
        String jdbcURL = "jdbc:postgresql://localhost:5432/2semproject";
        String username = "postgres";
        String password = "password";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL,username,password);

            for(String i: unfilteredTokens) {
                String sql = "SELECT stopword FROM \"StopWords\" WHERE stopword = '" + i + "';";



            Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sql);{
                    while (resultSet.next()){
                        useableStopwords.add(resultSet.getString("stopword"));
                    }
                }
        }
            unfilteredTokens.removeAll(useableStopwords);


        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            e.printStackTrace();
        }
        return unfilteredTokens;
    }



    public String displayString(List<String> tokens) {
        return tokens.toString();

    }

    public void displayRemovedWords() {

    }



    public static void addStopword(String stopword) {
        
        String jdbcURL = "jdbc:postgresql://localhost:5432/2semproject";
        String username = "postgres";
        String password = "password";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL,username,password);


            String sql = "INSERT INTO \"StopWords\" (stopword)"
                    + "VALUES ('" + stopword + "') ON CONFLICT DO NOTHING";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows > 0){
                System.out.println("A new stopword has been added: '" + stopword + "'");
            }
            else {
                System.out.println("The stopword: '" + stopword +  "' already exist");
            }

            connection.close();

        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            e.printStackTrace();
        }



    }

    public void removeStopword(String stopword) {
        //TreesetStopwords.remove(stopword);
    }

   /* public String compareString(String searhWord) {

            String result = "";

            this.searchWord = searhWord;
            String[] words = searchWord.split("\\W+");
            List<String> wordslist = new ArrayList<>(Arrays.asList(words));


            wordslist.removeAll();

            for ( String str: wordslist){
                result += str+ " ";
            }
            return  result;
    }*/



    public static void main(String[] args) {
        ArrayList<String> test = new ArrayList<>();
        test.add("more");
        test.add("Products");
        test.add("multiple");
        addStopword("Furthermore");
        //System.out.println(sw.compareString("more where products on top of the world"));
        System.out.println("Pre filtermethod: "+test);
        filter(test);
        System.out.println("Post filtermethod: "+test);


   /*     String jdbcURL = "jdbc:postgresql://localhost:5432/2semproject";
        String username = "postgres";
        String password = "password";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL,username,password);
            System.out.println("Connected to database");

            String sql = ""

            connection.close();

        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            e.printStackTrace();
        }
*/




    }

}
