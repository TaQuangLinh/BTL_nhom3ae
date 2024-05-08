package com.example.demo7;

import java.util.ArrayList;
import java.util.List;


public class History {
    private final List<String> wordHistory;

    public History() {
        wordHistory = new ArrayList<>();
    }

    public void addWord(String word) {
        wordHistory.add(word);
    }

    public List<String> getWordHistory() {
        return wordHistory;
    }
}
