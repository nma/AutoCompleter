package com.fun.Trie;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nickma on 2015-07-22.
 */
public class CompressedTrie implements Trie {

    private TrieNode root;

    public CompressedTrie() {
        root = null;
    }

    public CompressedTrie(Trie trie) {
        root = new TrieNode(null);
        compress(trie);
    }

    /**
     * Locates redundant nodes in a Trie and merges them into a squashed TrieNode
     * @param trie
     */
    private void compress(Trie trie) {
        Set<TrieNode> redundantNodes = new HashSet<>();
    }

    @Override
    public TrieNode getRoot() {
        return root;
    }

    @Override
    public void insert(String line) {
        throw new UnsupportedOperationException("No new insertions inside a compressed Trie supported at this time.");
    }

    @Override
    public boolean find(String line) {
        return false;
    }

    @Override
    public TrieNode lookup(String prefix) {
        return null;
    }

    @Override
    public Set<String> allSuffixes(TrieNode node, String prefix) {
        return null;
    }
}
