package us.mattgreen.test;

import us.mattgreen.FileInput;

import static org.junit.Assert.*;

public class FileInputTest {
    private final static FileInput places = new FileInput("the_book.csv");
    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void fileReadLine() {
        assertEquals(true, places.fileReadLine() instanceof String);
    }
}