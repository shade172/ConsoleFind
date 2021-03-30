package org.spbstu.sergeev;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<String> searchFile(File Directory, boolean Subdirectory, String FileName) {
        List<String> res = new ArrayList<>();
        String[] allFiles = Directory.list();
        if (allFiles == null) {
            throw new IllegalArgumentException("Directory does not exist");
        }
        if (allFiles != null) {
            for (String file : allFiles) {
                if (FileName.equals(file)) {
                    res.add(Directory.getPath() + File.separator + FileName);
                }
            }
            if (Subdirectory) {
                for (String file : allFiles) {
                    File searchF = new File(Directory.getPath() + file);
                    if (searchF.isDirectory()) {
                            res.addAll(searchFile(new File(Directory.getPath(), file), true, FileName));
                    }
                }
            }
        }
        return res;
    }
}