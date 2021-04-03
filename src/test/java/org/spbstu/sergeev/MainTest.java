package org.spbstu.sergeev;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    private String main(String[] args) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PrintStream old = System.out;
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        CommandLineArgument.main(args);

        System.out.flush();
        System.setOut(old);
        return baos.toString();


    }

    String s = File.separator;

    @Test

    void consoleFind() {
        assertEquals("[src" + s + "test" + s + "resources" + s + "Directory" + s + "File.txt]" +
                System.getProperty("line.separator"), main(new String[] {"File.txt", "-d" ,"src/test/resources/Directory"}));
    }

    @Test
    void searchFile() {
        List<String> expected = new ArrayList<>(List.of("src" + s + "test" + s + "resources" + s + "Directory" + s + "File.txt"));
        List<String> expectedTwo = new ArrayList<>
                (List.of("src" + s + "test" + s + "resources" + s + "Directory" + s + "Directory1" + s + "File1.txt"));
        List<String> expectedThree = new ArrayList<>
                (List.of("src" + s + "test" + s + "resources" + s + "Directory" + s + "Directory1" + s + "Directory2" + s + "File2.txt"));


        List<String> actual = Main.searchFile(Paths.get("src/test/resources/Directory/"),
                false, "File.txt");
        assertEquals(expected, actual);


        List<String> actualTwo = Main.searchFile(Paths.get("src/test/resources/Directory/Directory1"),
                true, "File1.txt");
        assertEquals(expectedTwo, actualTwo);


        List<String> actualThree = Main.searchFile(Paths.get("src/test/resources/Directory/Directory1/Directory2"),
                true, "File2.txt");
        assertEquals(expectedThree, actualThree);

    }
}