import java.sql.*;
import java.util.*;

public class Stopwords {


    private Collection<String> TreesetStopwords;
    private String searchWord;

    public Stopwords() {
        this.searchWord = searchWord;
        //TreesetStopwords = new TreeSet<>();

    }

    public static ArrayList<Stopword> getAllStopwords(){
        ArrayList<Stopword> stopwordList;

        try {
        String jdbcURL = "jdbc:postgresql://localhost:5432/2semproject";
        String username = "postgres";
        String password = "password";


            Connection connection = DriverManager.getConnection(jdbcURL,username,password);
            System.out.println("Connected to database");

            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM \"StopWords\"";
            ResultSet rst;
            rst = statement.executeQuery(sql);
            stopwordList = new ArrayList<>();

            while (rst.next()){
                Stopword stopword = new Stopword(rst.getString("\"StopWords\""));
                stopwordList.add(stopword);
            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            e.printStackTrace();
        }

            return null;
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



            wordslist.removeAll(getAllStopwords());

            for ( String str: wordslist){
                result += str+ " ";
            }
            return  result;
    }



    public static void main(String[] args) {

        Stopwords sw = new Stopwords();
        sw.addStopword("more");
        sw.addStopword("more");
        sw.addStopword("multiple");
        sw.addStopword("multiple");
        sw.addStopword("where");
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
