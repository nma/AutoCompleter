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
    public Trie trieDictionary;

    public AutoCompleter(File inputFile) {
        this.inputFile = inputFile;
    }

    public void preprocess() {
        trieDictionary = new BasicTrie();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                trieDictionary.insert(line);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found.");
        }
        // TODO: add compressed Trie after implementation is done
        // trieDicitonary = new CompressedTrie(trieDictionary);
    }

    public List<String> lookup(String prefix) {
        List<String> potentialCandidates = new ArrayList<>();
        TrieNode prefixNode = trieDictionary.lookup(prefix);
        if (prefixNode != null) {
            for (String matches : trieDictionary.allSuffixes(prefixNode, prefix)) {
                potentialCandidates.add(matches);
            }
        }
        return potentialCandidates;
    }
}
