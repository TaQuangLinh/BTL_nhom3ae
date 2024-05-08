package com.example.demo7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// có nhiệm vụ cho việc tìm kiếm và đề xuất gợi ý.
public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.isWord = true;
    }

    public List<String> search(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c);
            if (node == null) return results;
        }
        findAllWords(node, results, new StringBuilder(prefix));
        return results;
    }

    private void findAllWords(TrieNode node, List<String> results, StringBuilder prefix) {
        if (node.isWord) results.add(prefix.toString());
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            findAllWords(entry.getValue(), results, prefix.append(entry.getKey()));
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("|")) {
                    String word = line.substring(1).trim();
                    insert(word);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
            e.printStackTrace();
        }
    }

    public void delete(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.get(c);
            if (node == null) {
                // Từ không tồn tại trong Trie.
                return;
            }
        }
        // Đặt lại trạng thái isWord của nút cuối cùng thành false.
        node.isWord = false;
    }
}
