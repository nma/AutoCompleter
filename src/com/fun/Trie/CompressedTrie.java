package com.fun.Trie;

import java.rmi.UnexpectedException;
import java.util.*;

/**
 * Created by nickma on 2015-07-22.
 * Updated 2015-07-25.
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
     * Single Branch compressions
     *
     * Definition:
     * Node is compressed all redundant nodes are removed and their keys are appended in order to the resultant node.
     *
     * Signatures:
     * -- Input: Given a chain of redundant Nodes and the TerminalNode
     * -- Output: Output one new Node that includes the key of the previously redundant nodes
     *
     * Constraints:
     * -- The last node must end in a word
     *
     * @param nodesToCompress
     * @return the compressedNode
     */
     /* package private */ static TrieNode compressBranch(List<TrieNode> nodesToCompress) throws UnexpectedException {
        StringBuilder sb = new StringBuilder();
        TrieNode terminalNode = nodesToCompress.get(nodesToCompress.size() - 1);
        if (!terminalNode.isWord) {
            throw new UnexpectedException("Expected Terminal Node to be the end of a word.");
        }
        nodesToCompress.remove(terminalNode);

        for (TrieNode node : nodesToCompress) {
            if (!isRedundantNode(node)) {
                throw new UnexpectedException("Expected Node to be redundant.");
            }
            sb.append(node.key);
        }

        sb.append(terminalNode.key);
        terminalNode.key = sb.toString();

        return terminalNode;
    }

    /**
     * Definitions:
     * -- Redundant Node is equivalent to a (non-root) node that is not a Word and only has 1 child.
     *
     * @param node
     * @return
     */
     /* package private */ static boolean isRedundantNode(TrieNode node) {
        // is root?
        if (node.key == null) {
            return false;
        }

        return (!node.isWord && node.children.size() == 1);
    }

    private void compress(Trie trie) {

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
