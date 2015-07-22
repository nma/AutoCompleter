package com.fun;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
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
    public void setUp() {
        testFile = new File(AutoCompleterTest.class.getResource("/data/test1").getPath());
    }

    @Test
    public void testAutoComplete() {
        AutoCompleter p = new AutoCompleter(testFile);
        p.preprocess();

        List<String> words = p.lookup("arm");
        assertThat(words.size(), is(6));
        assertThat(words, hasItems("armchair", "armour", "armoires"));
        assertThat(words, not(hasItems("armchairer", "armourz", "armoirers")));
    }
}
