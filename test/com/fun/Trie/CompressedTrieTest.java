package com.fun.Trie;

import com.fun.Trie.BasicTrie;
import com.fun.Trie.CompressedTrie;
import com.fun.Trie.TrieNode;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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

    @Test
    public void testCompressSingleRedundantNodeTest() {
        BasicTrie t = new BasicTrie();
        t.insert("ab");

        CompressedTrie compressedTrie = new CompressedTrie(t);
        assertThat(compressedTrie.getRoot().children.size(), is(1));

        TrieNode firstChild = compressedTrie.getRoot().children.get("ab");
        assertThat(firstChild, notNullValue());
        assertThat(firstChild.isWord, is(true));
    }

}
