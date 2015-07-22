package com.fun;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nickma on 2015-07-21.
 */
public class AutoCompleter {

    private File inputFile;
    private Trie trie;

    public AutoCompleter(File inputFile) {
        this.inputFile = inputFile;
        this.trie = new Trie();
    }

    public void preprocess() {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                trie.insert(line);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found.");
        }
    }

    public List<String> lookup(String prefix) {
        List<String> potentialCandidates = new ArrayList<>();
        Trie.TrieNode prefixNode = trie.lookup(prefix);
        if (prefixNode != null && !prefixNode.isWord) {
            for (String suffix : trie.allSuffixes(prefixNode, prefix)) {
                potentialCandidates.add(prefix + suffix);
            }
        }
        return potentialCandidates;
    }
}