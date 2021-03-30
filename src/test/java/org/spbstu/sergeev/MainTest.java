package org.spbstu.sergeev;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void searchFile() {
        List<String> expected = new ArrayList<>();
        List<String> expectedTwo = new ArrayList<>();
        List<String> expectedThree = new ArrayList<>();

        expected.add("." + File.separator + "Directory" + File.separator + "File.txt");
        List<String> actual = Main.searchFile(new File("." + File.separator + "Directory"),
                false, "File.txt");
        assertEquals(expected, actual);

        expectedTwo.add("." + File.separator + "Directory" + File.separator + "Directory1" + File.separator + "File1.txt");
        List<String> actualTwo = Main.searchFile(new File("." + File.separator + "Directory" + File.separator + "Directory1"),
                true, "File1.txt");
        assertEquals(expectedTwo, actualTwo);

        expectedThree.add("." + File.separator + "Directory" + File.separator + "Directory1" + File.separator + "Directory2" + File.separator + "File2.txt");
        List<String> actualThree = Main.searchFile(new File("." + File.separator + "Directory" + File.separator + "Directory1" + File.separator + "Directory2"),
                true, "File2.txt");
        assertEquals(expectedThree, actualThree);


    }
}