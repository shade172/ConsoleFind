package org.spbstu.sergeev;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<String> searchFile(Path directory, boolean subdirectory, String fileName) {
        List<String> res = new ArrayList<>();
        File mainDirectory = directory.toFile();
        File[] allFiles = mainDirectory.listFiles();
        if (allFiles == null) {
            throw new IllegalArgumentException("Directory is empty");
        }
        for (File file : allFiles) {
            if (fileName.equals(file.getName())) {
                res.add(String.valueOf(Paths.get(file.getPath())));
            }
        }
        if (subdirectory) {
            for (File file : allFiles) {
                if (file.isDirectory()) {
                        res.addAll(searchFile(Paths.get(file.getPath()), true, fileName));
                }
            }
        }
        return res;
    }
}