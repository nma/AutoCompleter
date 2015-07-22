package com.fun.Trie;

import java.util.Set;

/**
 * Created by nickma on 2015-07-22.
 */
public interface Trie {

    public TrieNode getRoot();
    public void insert(String line);
    public boolean find(String line);
    public TrieNode lookup(String prefix);
    public Set<String> allSuffixes(TrieNode node, String prefix);
}
