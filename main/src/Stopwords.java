import java.util.*;

public class Stopwords {


    private Collection<String> TreesetStopwords;
private String searchWord;

    public Stopwords(){
        this.searchWord= searchWord;
        TreesetStopwords = new TreeSet<>();

    }


    public String addSearchword(String searchWord){
      return this.searchWord=searchWord;
    }

    public String displayString(){
        return searchWord;

    }

    public void displayRemovedWords(){
       if(TreesetStopwords.contains(searchWord)){
           displayString();
       }
    }

    public void addStopword(String stopword){
        TreesetStopwords.add(stopword);
    }

    public void removeStopword (String stopword){
        TreesetStopwords.remove(stopword);
    }

    public void compareString() {

        String[] words = searchWord.split("\\W+");

        List<String> wordslist = new ArrayList<String>(Arrays.asList(words));



        for(int i = 0; i<wordslist.size(); i++){
            for(int j = 0; j<TreesetStopwords.size();j++)
            if( words[i].equals(TreesetStopwords[j] ){
                wordslist.remove(i);
            }
        }

    }

    public static void main(String[] args) {

        Stopwords sw = new Stopwords();

        sw.addStopword("where");
        sw.addStopword("where");
        sw.addStopword("more");

        System.out.println(sw.TreesetStopwords);

        sw.removeStopword("where");

        System.out.println(sw.TreesetStopwords);

        sw.addSearchword("where more products");

        System.out.println(sw.displayString());

        sw.compareString();

        System.out.println(sw.displayString());


    }

}
