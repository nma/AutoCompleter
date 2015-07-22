package com.fun;

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by nickma on 2015-07-21.
 */
public class TrieTest {

    @Test
    public void testTrieNode() {
        Trie t = new Trie();
        t.insert("a");

        Trie.TrieNode gotNode = t.getRoot();
        assertThat(gotNode, is(notNullValue()));
        assertThat(gotNode.children.size(), is(1));
        assertThat(gotNode.isWord, is(false));
        assertThat(gotNode.key, is(nullValue()));

        Trie.TrieNode insertedNode = gotNode.children.get("a");
        assertThat(insertedNode.children.size(), is(0));
        assertThat(insertedNode.isWord, is(true));
        assertThat(insertedNode.key, is("a"));
    }

    @Test
    public void testTrieInsertionAndSearch() {

        String[] testStrings = new String[] {
            "abc", "a", "abcde"
        };

        Trie t = new Trie();
        for (String line : testStrings) {
            t.insert(line);
        }

        assertThat(t.find("a"), is(true));
        assertThat(t.find("ab"), is(false));
        assertThat(t.find("d"), is(false));
        assertThat(t.find("abc"), is(true));
        assertThat(t.find("abcde"), is(true));
    }


    @Test
    public void testFindAllSuffixes() {

        Trie t = new Trie();
        t.insert("abc");
        t.insert("abcde");
        t.insert("abcdef");

        Trie.TrieNode node = t.lookup("ab");
        Set<String> allSuffixes = t.allSuffixes(node, "ab");

        assertThat(allSuffixes, hasItems("abc", "abcde", "abcdef"));
    }
}
