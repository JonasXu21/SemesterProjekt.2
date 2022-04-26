package domain;


import dataSource.StopWordsDa;

import java.util.ArrayList;

public class FactoryPattern {
    public static void main(String[] args) {
        StopWordsFactory stopWordsFactory = new StopWordsFactory();
        IStopWords stopWords = stopWordsFactory.getWords("ENGLISH");
        ArrayList<String> test = new ArrayList<>();
        stopWords.removeStopWord("Ali");
        test.add("after");
        test.add("Product");
        test.add("multiple");
        test.add("Software");
        test.add("Emil");
        stopWords.removeStopWord("dlovan");
        stopWords.removeStopWord("dlova");
        stopWords.removeStopWord("Furthermore");
        System.out.println("Pre filtermethod: " + StopWordsDa.unfiltered());
        stopWords.filter(StopWordsDa.unfiltered());
        System.out.println("Post filtermethod: " + StopWordsDa.unfiltered());
        stopWords.queryStopWord();



        //////////////////////////////////////////////////
        //////////////////////////////////////////////////
        IStopWords stopWords1 = stopWordsFactory.getWords("DANISH");
        ArrayList<String> test1 = new ArrayList<>();
        stopWords1.removeStopWord("Ali");
        test1.add("ad");
        test1.add("af");
        test1.add("throug");
        test1.add("Products");
        test1.add("multiple");
        test1.add("tage");
        test1.add("Ali");
        stopWords1.removeStopWord("DlovanMohamad");
        stopWords1.removeStopWord("Furthermore");
        if(StopWordsDa.unfiltered().isEmpty()){
            System.out.println("Pre filtermethod: " + StopWordsDa.unfiltered());
            stopWords1.filter(StopWordsDa.unfiltered());
            System.out.println("Post filtermethod: " + StopWordsDa.unfiltered());
        }else{
            StopWordsDa.unfiltered().clear();
            System.out.println("Pre filtermethod: " + StopWordsDa.unfiltered());
            stopWords1.filter(StopWordsDa.unfiltered());
            System.out.println("Post filtermethod: " + StopWordsDa.unfiltered());
        }

    }
}
