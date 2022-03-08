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

    public void compareString(String searhWord) {

        this.searchWord = searhWord;
        String[] words = searchWord.split("\\W+");


        List<String> wordslist = new ArrayList<>(Arrays.asList(words));

        System.out.println(wordslist);
        System.out.println(TreesetStopwords);

        for (int i = 0; i < wordslist.size(); i++) {
                if(wordslist.get(i).equals(TreesetStopwords.toArray()[i])){
                    wordslist.remove(i);
                }

            }
        System.out.println(wordslist);

    }



    public static void main(String[] args) {

        Stopwords sw = new Stopwords();

        sw.addStopword("more");
        sw.addStopword("where");



        sw.compareString("more where products");


    }

}
