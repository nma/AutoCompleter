package com.fun;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Created by nickma on 2015-07-21.
 */
public class PreprocessorTest {

    private File testFile;

    @Before
    public void setUp() {
        testFile = new File(PreprocessorTest.class.getResource("/data/test1").getPath());
    }

    @Test
    public void testCanProcessFile() {
        Preprocessor p = new Preprocessor(testFile);
        p.process();
    }
}
