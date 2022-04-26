package domain;
import java.util.Collection;
import java.util.List;

public interface IStopWords {
    //Collection<String> filter(List<String> unfilteredTokens);
    Collection<String> filter(List<String> unfilteredTokens);
    void addStopWord(String stopWord);
    void removeStopWord(String stopWord);
    void queryStopWord();
    void createTable();
}
