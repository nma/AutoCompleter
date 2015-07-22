package com.fun;

import java.util.HashMap;
import java.util.List;

/**
 * Created by nickma on 2015-07-21.
 */
public class Trie {

    public TrieNode getRoot() {
        return root;
    }

    public static class TrieNode {
        public String key;
        public Boolean isWord = false;

        public HashMap<String, TrieNode> children = new HashMap<>();

        public TrieNode(String key) {
            this.key = key;
        }
    }

    private TrieNode root = new TrieNode(null);

    public void insert(String line) {

        if (line == null || line.isEmpty()) {
            return;
        }

        TrieNode curNode = getRoot();
        for (int i = 0; i < line.length(); i++) {
            String ch = line.substring(i, i + 1);
            TrieNode nextNode = curNode.children.get(ch);
            if (nextNode == null) {
                nextNode = new TrieNode(ch);
                curNode.children.put(ch, nextNode);
            }
            curNode = nextNode;
        }
        curNode.isWord = true;
    }

    public boolean find(String line) {
        boolean found = false;

        if (line == null || line.isEmpty()) {
            return found;
        }

        TrieNode curNode = getRoot();
        for (int i = 0; i < line.length(); i++) {
            String ch = line.substring(i, i + 1);
            if (curNode.children.containsKey(ch)) {
                curNode = curNode.children.get(ch);
            } else {
                return found;
            }
        }

        if (curNode.isWord) {
            found = true;
        }

        return found;
    }
}
