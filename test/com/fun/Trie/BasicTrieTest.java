package com.fun.Trie;

import com.fun.Trie.BasicTrie;
import com.fun.Trie.TrieNode;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by nickma on 2015-07-21.
 */
public class BasicTrieTest {

    @Test
    public void testTrieNode() {
        BasicTrie t = new BasicTrie();
        t.insert("a");

        TrieNode gotNode = t.getRoot();
        assertThat(gotNode, is(notNullValue()));
        assertThat(gotNode.children.size(), is(1));
        assertThat(gotNode.isWord, is(false));
        assertThat(gotNode.key, is(nullValue()));

        TrieNode insertedNode = gotNode.children.get("a");
        assertThat(insertedNode.children.size(), is(0));
        assertThat(insertedNode.isWord, is(true));
        assertThat(insertedNode.key, is("a"));
    }

    @Test
    public void testTrieInsertionAndSearch() {

        String[] testStrings = new String[] {
            "abc", "a", "abcde"
        };

        BasicTrie t = new BasicTrie();
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

        BasicTrie t = new BasicTrie();
        t.insert("abc");
        t.insert("abcde");
        t.insert("abcdef");

        TrieNode node = t.lookup("ab");
        Set<String> allSuffixes = t.allSuffixes(node, "ab");

        assertThat(allSuffixes, hasItems("abc", "abcde", "abcdef"));
    }

}
