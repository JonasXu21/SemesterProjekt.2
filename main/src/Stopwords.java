import java.sql.*;
import java.util.*;

public class Stopwords {


    private Collection<String> TreesetStopwords;
    private String searchWord;

    public Stopwords() {
        this.searchWord = searchWord;
        //TreesetStopwords = new TreeSet<>();

    }

    public static  Collection<Stopword> getAll() {
        Collection<Stopword> stopwordList = new ArrayList<>();
        String sql = "SELECT * FROM \"StopWords\"";


        JdbcConnection.getConnection().ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()){
                    Stopword stopword = new Stopword(resultSet.getString("\"StopWords\""));
                    stopwordList.add(stopword);
                }

            } catch (SQLException e) {
                System.out.println("Error connecting to database");
                e.printStackTrace();
            }
        });

        return stopwordList;
    }




    public String addSearchword(String searchWord) {
        return this.searchWord = searchWord;
    }


    public String displayString() {
        return searchWord;

    }

    public void displayRemovedWords() {
        if (TreesetStopwords.contains(searchWord)) {
            displayString();
        }
    }

    public void addStopword(String stopword) {
        //TreesetStopwords.add(stopword);
        String jdbcURL = "jdbc:postgresql://localhost:5432/2semproject";
        String username = "postgres";
        String password = "password";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL,username,password);
            System.out.println("Connected to database");

            String sql = "INSERT INTO \"StopWords\" (\"StopWords\")"
                    + "VALUES ('" + stopword + "') ON CONFLICT DO NOTHING";

            Statement statement = connection.createStatement();

            int rows = statement.executeUpdate(sql);
            if (rows > 0){
                System.out.println("A new stopword has been added");
            }

            connection.close();

        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            e.printStackTrace();
        }



    }

    public void removeStopword(String stopword) {
        TreesetStopwords.remove(stopword);
    }

    public String compareString(String searhWord) {

            String result = "";

            this.searchWord = searhWord;
            String[] words = searchWord.split("\\W+");
            List<String> wordslist = new ArrayList<>(Arrays.asList(words));


            wordslist.removeAll(getAll());

            for ( String str: wordslist){
                result += str+ " ";
            }
            return  result;
    }



    public static void main(String[] args) {

        Stopwords sw = new Stopwords();
        sw.addStopword("more");
        System.out.println(sw.compareString("more where products on top of the world"));


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
