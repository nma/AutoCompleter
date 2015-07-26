package com.fun.Trie;

import java.util.HashMap;

/**
 * Created by nickma on 2015-07-22.
 */
public class TrieNode {
    public String key;
    public Boolean isWord = false;
    public TrieNode parent = null;
    public HashMap<String, TrieNode> children = new HashMap<>();

    public TrieNode(String key) {
        this.key = key;
    }
}
