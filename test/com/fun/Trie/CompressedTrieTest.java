package com.fun.Trie;

import org.junit.Test;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by nickma on 2015-07-22.
 * Updated 2015-07-25.
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

    //@Test
    public void testCompressSingleRedundantNodeTest() {
        BasicTrie t = new BasicTrie();
        t.insert("ab");

        CompressedTrie compressedTrie = new CompressedTrie(t);
        assertThat(compressedTrie.getRoot().children.size(), is(1));

        TrieNode firstChild = compressedTrie.getRoot().children.get("ab");
        assertThat(firstChild, notNullValue());
        assertThat(firstChild.isWord, is(true));
    }

    @Test
    public void testRedundantNodeDetection() {
        TrieNode rootNode = new TrieNode(null);
        rootNode.children.put("a", new TrieNode("a"));
        assertThat(CompressedTrie.isRedundantNode(rootNode), is(false));

        TrieNode redundantNode = new TrieNode("a");
        redundantNode.children.put("b", new TrieNode("b"));
        assertThat(CompressedTrie.isRedundantNode(redundantNode), is(true));

        TrieNode notRedundantNode = new TrieNode("a");
        notRedundantNode.children.put("b", new TrieNode("b"));
        notRedundantNode.children.put("c", new TrieNode("c"));
        assertThat(CompressedTrie.isRedundantNode(notRedundantNode), is(false));
    }

    @Test
    public void testCompressChainOfRedundantNodes() throws UnexpectedException {
        List<TrieNode> redundantChain = new ArrayList<>();
        TrieNode prevNode = null;
        // build our chain of redundant nodes start -> end
        for (String ch : new String[] {"w", "e", "e", "k", "e", "n", "d"}) {
            TrieNode curNode = new TrieNode(ch);
            if (prevNode != null) {
                prevNode.children.put(ch, curNode);
            }
            redundantChain.add(curNode);
            prevNode = curNode;
        }
        prevNode.isWord = true;

        TrieNode compressedNode = CompressedTrie.compressBranch(redundantChain);
        assertThat(compressedNode.isWord, is(true));
        assertThat(compressedNode.key, is("weekend"));
    }

    @Test(expected=UnexpectedException.class)
    public void testCompressChainOfRedundantNodesException() throws UnexpectedException {
        List<TrieNode> redundantChain = new ArrayList<>();
        redundantChain.add(new TrieNode("p"));

        CompressedTrie.compressBranch(redundantChain);
    }
}