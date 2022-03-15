import java.util.*;

public class Stopwords {


    private Collection<String> TreesetStopwords;
    private String searchWord;

    public Stopwords() {
        this.searchWord = searchWord;
        TreesetStopwords = new TreeSet<>();

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
        TreesetStopwords.add(stopword);
    }

    public void removeStopword(String stopword) {
        TreesetStopwords.remove(stopword);
    }

    public String compareString(String searhWord) {

            String result = "";

            this.searchWord = searhWord;
            String[] words = searchWord.split("\\W+");
            List<String> wordslist = new ArrayList<>(Arrays.asList(words));


    
            wordslist.removeAll(TreesetStopwords);

            for ( String str: wordslist){
                result += str+ " ";
            }
            return  result;
    }



    public static void main(String[] args) {

        Stopwords sw = new Stopwords();

        sw.addStopword("more");
        sw.addStopword("where");



        System.out.println(sw.compareString("more where products on top of the world"));


    }

}
