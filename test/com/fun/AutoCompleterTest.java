package com.fun;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

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
    public void testCanProcessFile() {
        AutoCompleter p = new AutoCompleter(testFile);
        p.preprocess();
    }
}
