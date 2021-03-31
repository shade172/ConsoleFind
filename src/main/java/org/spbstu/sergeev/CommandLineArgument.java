package org.spbstu.sergeev;

/*
Поиск файла(ов) с заданным в командной строке именем в указанной ключом -d
директории, по умолчанию в текущей директории. Ключ -r указывает на необходимость
поиска также во всех поддиректориях.

Command Line: find [-r] [-d directory] filename.txt
 */

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;

public class CommandLineArgument {
    @Option(name = "-d", metaVar = "Directory")
    private File Directory = new File(".");

    @Option(name = "-r", metaVar = "Subdirectory")
    private boolean Subdirectory;

    @Argument(metaVar = "FileName", required = true)
    private String FileName;

    public static void main(String[] args) throws IOException {
        new CommandLineArgument().launch(args);
    }

    private void launch(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (Directory == null) throw new IllegalArgumentException("Directory does not exist");
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.println(Main.searchFile(Directory, Subdirectory, FileName));
    }
}
