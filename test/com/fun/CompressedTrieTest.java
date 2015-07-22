package com.fun;

import com.fun.Trie.BasicTrie;
import com.fun.Trie.CompressedTrie;
import com.fun.Trie.Trie;
import com.fun.Trie.TrieNode;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by nickma on 2015-07-22.
 */
public class CompressedTrieTest {

    //@Test
    public void testCompressedTrieMaintiansTheSameLookup() {

        BasicTrie t = new BasicTrie();
        t.insert("abc");
        t.insert("abcde");
        t.insert("abcdef");

        CompressedTrie compressedTrie = new CompressedTrie(t);
        TrieNode node = compressedTrie.lookup("ab");
        Set<String> gotAllSuffixes = compressedTrie.allSuffixes(node, "ab");

        TrieNode basicNode = t.lookup("ab");
        Set<String> expAllSuffixes = t.allSuffixes(basicNode, "ab");

        assertThat(gotAllSuffixes, is(equalTo(expAllSuffixes)));
    }
}
