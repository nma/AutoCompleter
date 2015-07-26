package main.com.fun;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by nickma on 2015-07-21.
 */
public class AutoCompleterTest {

    private File testFile;

    @Before
    public void setUp() throws IOException {
        testFile = File.createTempFile("autocompleter", "tmp");
        testFile.deleteOnExit();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(testFile))) {
            bw.write("arm\n" +
                    "armchair\n" +
                    "armour\n" +
                    "armoured\n" +
                    "armoire\n" +
                    "armoired\n" +
                    "armoires");
        }
    }

    @Test
    public void testAutoComplete() {
        AutoCompleter p = new AutoCompleter(testFile);
        p.preprocess();

        List<String> words = p.lookup("arm");
        assertThat(words.size(), is(7));
        assertThat(words, hasItems("arm", "armchair", "armour", "armoires"));
        assertThat(words, not(hasItems("armchairer", "armourz", "armoirers")));

        List<String> wordsNothing = p.lookup("notfound");
        assertThat(wordsNothing.size(), is(0));
    }
}
