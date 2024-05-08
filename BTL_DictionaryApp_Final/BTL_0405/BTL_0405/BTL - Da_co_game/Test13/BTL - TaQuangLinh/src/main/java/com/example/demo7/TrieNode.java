package com.example.demo7;

import java.util.HashMap;
import java.util.Map;

// hỗ trợ Trie.
public class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord = false;
}