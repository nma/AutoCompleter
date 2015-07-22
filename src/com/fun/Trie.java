package com.fun;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by nickma on 2015-07-21.
 */
public class Trie {

    public TrieNode getRoot() {
        return root;
    }

    public static class TrieNode {
        public String label;
        public Boolean isWord;

        public HashSet<String> matches = new HashSet<>();
        public ArrayList<TrieNode> children = new ArrayList<>();
    }

    private int height;
    private TrieNode root;

    public int getHeight() {
        return height;
    }

    public void insert(String line) {

        if (line == null || line.isEmpty()) {
            return;
        }

        if (root == null) {
            root = new TrieNode();
            root.isWord = true;
            root.label = line.substring(0, 1);

            root.matches.add(line);
        }
    }
}
