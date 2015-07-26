package com.fun;

import com.fun.Trie.CompressedTrie;
import com.fun.Trie.BasicTrie;
import com.fun.Trie.Trie;
import com.fun.Trie.TrieNode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nickma on 2015-07-21.
 */
public class AutoCompleter {

    private File inputFile;
    public CompressedTrie trieDictionary;

    public AutoCompleter(File inputFile) {
        this.inputFile = inputFile;
    }

    public void preprocess() {
        Trie basicTrie = new BasicTrie();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                basicTrie.insert(line);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found.");
        }
        trieDictionary = new CompressedTrie(basicTrie);
    }

    public List<String> lookup(String prefix) {
        List<String> potentialCandidates = new ArrayList<>();
        TrieNode prefixNode = trieDictionary.lookup(prefix);
        if (prefixNode != null && prefixNode.key != null) {
            for (String matches : trieDictionary.allSuffixes(prefixNode, prefix)) {
                potentialCandidates.add(matches);
            }
        }
        return potentialCandidates;
    }
}
