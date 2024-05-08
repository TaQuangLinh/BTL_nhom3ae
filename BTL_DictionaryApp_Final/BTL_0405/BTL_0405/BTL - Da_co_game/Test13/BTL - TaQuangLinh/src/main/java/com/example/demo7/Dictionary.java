package com.example.demo7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//đọc file từ điển offline

public class Dictionary {
    private final Map<String, String> wordMap;

    public Dictionary() {
        wordMap = new HashMap<>();
    }

    public void addWord(String word, String definition) {
        wordMap.put(word, definition);
    }

    public String getDefinition(String word) {
        return wordMap.get(word);
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String word = null, definition = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("|")) {
                    if (word != null && definition != null) {
                        addWord(word, definition);
                    }
                    word = line.substring(1).trim();
                    definition = "";
                } else if (!line.trim().isEmpty()) {
                    definition += line.trim() + "\n";
                }
            }
            if (word != null && definition != null) {
                addWord(word, definition);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
            e.printStackTrace();
        }
    }

    public void removeWord(String word) {
        wordMap.remove(word);
    }

}
