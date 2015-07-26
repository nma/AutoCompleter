package com.fun.Trie;

import java.util.Set;

/**
 * Created by nickma on 2015-07-22.
 */
public interface Trie {

    public TrieNode getRoot();

    /**
     * Insert into the trie
     * @param line
     */
    public void insert(String line);

    /**
     * Find an exact match of the string
     * @param line
     * @return
     */
    public boolean find(String line);

    /**
     * Returns the closest node matched by the prefix
     * @param prefix
     * @return
     */
    public TrieNode lookup(String prefix);

    /**
     * Locates all suffixes from the current node, appending the targeted prefix
     * @param node
     * @param prefix
     * @return
     */
    public Set<String> allSuffixes(TrieNode node, String prefix);
}
