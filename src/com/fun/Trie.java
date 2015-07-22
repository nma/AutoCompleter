package com.fun;

import java.util.*;

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

        TrieNode closestMatch = lookup(line);
        if (closestMatch != null && closestMatch.isWord) {
            found = true;
        }

        return found;
    }

    public TrieNode lookup(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return null;
        }

        TrieNode curNode = getRoot();
        for (int i = 0; i < prefix.length(); i++) {
            String ch = prefix.substring(i, i + 1);
            if (curNode.children.containsKey(ch)) {
                curNode = curNode.children.get(ch);
            } else {
                return null;
            }
        }

        return curNode;
    }

    public Set<String> allSuffixes(TrieNode node, String prefix) {
        Set<String> foundSuffixes = new HashSet<>();

        if (node.isWord) {
            foundSuffixes.add(prefix);
        }

        if (node.children.isEmpty()) {
            return foundSuffixes;
        }

        for (TrieNode childNode : node.children.values()) {
            foundSuffixes.addAll(allSuffixes(childNode, prefix + childNode.key));
        }

        return foundSuffixes;
    }
}
