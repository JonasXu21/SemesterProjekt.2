import java.util.ArrayList;

public class Stopwords {

private  ArrayList<String> arraylistStopwords ;
private String searchWord;

    public Stopwords(String searchWord){
        this.searchWord= searchWord;
        arraylistStopwords=new ArrayList<>();
    }



    public String displayString(){
        return searchWord;
    }

    public void displayRemovedWords(){
       if(arraylistStopwords.contains(searchWord)){
           displayString();
       }
    }

    public void addStopword(String stopword){
        arraylistStopwords.add(stopword);
    }

    public void removeStopword (String stopword){
        arraylistStopwords.remove(stopword);
    }

    public void compareString() {

    }



}
