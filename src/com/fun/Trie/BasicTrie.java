package com.fun.Trie;

import java.util.*;

/**
 * Created by nickma on 2015-07-21.
 */
public class BasicTrie implements Trie {

    @Override
    public TrieNode getRoot() {
        return root;
    }

    private TrieNode root = new TrieNode(null);

    @Override
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
            nextNode.parent = curNode;
            curNode = nextNode;
        }
        curNode.isWord = true;
    }

    @Override
    public boolean find(String line) {
        if (line == null || line.isEmpty()) {
            return false;
        }

        TrieNode curNode = getRoot();
        for (int i = 0; i < line.length(); i++) {
            String ch = line.substring(i, i + 1);
            if (curNode.children.containsKey(ch)) {
                curNode = curNode.children.get(ch);
            } else {
                return false;
            }
        }

        if (curNode.isWord) {
            return true;
        }

        return false;
    }

    @Override
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
                break;
            }
        }

        return curNode;
    }

    @Override
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
