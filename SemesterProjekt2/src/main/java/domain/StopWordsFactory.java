package domain;
//target/StopwordsDa.csv
//target/StopWordsEng.csv these two files are located in the directory target
// Files contain the stopwords for  English & Danish

import dataSource.*;

public class StopWordsFactory {
    public IStopWords getWords(String wordType){
        if(wordType == null){
            return null;
        }
        if(wordType.equalsIgnoreCase("ENGLISH")){
            return new StopWordsEng();

        } else if(wordType.equalsIgnoreCase("DANISH")){
            return new StopWordsDa();
        }else{
            return null;
        }
    }
}

