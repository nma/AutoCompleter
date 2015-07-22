package com.fun;

import org.junit.Test;

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
        assertThat(gotNode.children.size(), is(0));
        assertThat(gotNode.matches, hasItem("a"));
        assertThat(gotNode.label, is("a"));
    }

    @Test
    public void testTrieInsertion() {

        String[] testStrings = new String[] {
            "abc", "a", "abcde"
        };

        Trie t = new Trie();
        for (String line : testStrings) {
            t.insert(line);
        }

        //assertThat(t.getNodes(), is(5));
        //assertThat(t.getHeight(), is(5));
    }
}
