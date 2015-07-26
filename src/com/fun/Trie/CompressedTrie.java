package com.fun.Trie;

import java.util.*;

/**
 * Created by nickma on 2015-07-22.
 * Updated 2015-07-25.
 */
public class CompressedTrie implements Trie {

    private TrieNode root;

    public CompressedTrie(Trie trie) {
        root = trie.getRoot();
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
     /* package private */ static TrieNode compressBranch(List<TrieNode> nodesToCompress) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        TrieNode terminalNode = nodesToCompress.get(nodesToCompress.size() - 1);
        if (!terminalNode.isWord) {
            throw new IllegalArgumentException("Expected Terminal Node to be the end of a word.");
        }
        nodesToCompress.remove(terminalNode);

        for (TrieNode node : nodesToCompress) {
            if (!isRedundantNode(node)) {
                throw new IllegalArgumentException("Expected Node to be redundant.");
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

    /**
     * Process for compressing a Trie is as follows with built up sub-components.
     *
     * Firstly Depth First Search Traverse all the branches of the Trie, caching the chain of redundant nodes.
     * Then passing it into #compressBranch which will return the new collapsed Node.
     *
     * Could be made into a recusively for better readability, but iteratively was better to validate correctness.
     *
     * @param trie
     */
    private void compress(Trie trie) throws IllegalArgumentException {
        Stack<TrieNode> workQueue = new Stack<>();
        workQueue.add(trie.getRoot());

        List<TrieNode> curBranchToCompress = new ArrayList<>();
        while (!workQueue.isEmpty()) {
            TrieNode curNode = workQueue.pop();

            // do work here
            if (isRedundantNode(curNode)) {
                curBranchToCompress.add(curNode);
            } else if (curNode.isWord) {
                curBranchToCompress.add(curNode);
                TrieNode parentNode = curBranchToCompress.get(0).parent;
                TrieNode compressedNode = compressBranch(curBranchToCompress);

                parentNode.children.remove(compressedNode.key.substring(0, 1));
                parentNode.children.put(compressedNode.key, compressedNode);

                curBranchToCompress.clear();
            }

            // tail conditions
            for (TrieNode node: curNode.children.values()) {
                workQueue.add(node);
            }
        }
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
        String stringSoFar = "";
        if (line == null || line.isEmpty()) {
            return false;
        }

        TrieNode curNode = getRoot();
        String prefixSeen = "";
        for (int i = 0; i < line.length(); i++) {
            String ch = line.substring(i, i + 1);
            String key = prefixSeen + ch;
            if (curNode.children.containsKey(key)) {
                curNode = curNode.children.get(key);
                prefixSeen = "";
                stringSoFar += key;
            } else {
                prefixSeen += ch;
            }
        }

        if (stringSoFar.equals(line) && curNode.isWord) {
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
        String prefixSeen = "";
        for (int i = 0; i < prefix.length(); i++) {
            String ch = prefix.substring(i, i + 1);
            String key = prefixSeen + ch;
            if (curNode.children.containsKey(key)) {
                curNode = curNode.children.get(key);
                prefixSeen = "";
            } else {
                prefixSeen += ch;
            }
        }
        for (Map.Entry<String, TrieNode> entry : curNode.children.entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                curNode = entry.getValue();
            }
        }

        return null;
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
