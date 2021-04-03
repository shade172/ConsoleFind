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

import java.nio.file.Path;
import java.nio.file.Paths;

public class CommandLineArgument {

    @Option(name = "-d", metaVar = "Directory")
    private Path path = Paths.get(".");

    @Option(name = "-r", metaVar = "SubDirectory")
    private boolean subDirectory;

    @Argument(metaVar = "FileName", required = true)
    private String fileName;

    public static void main(String[] args) {
        new CommandLineArgument().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            return;
        }
        Path directory;
        if (path == null) {
            directory = Paths.get("D:\\ConsoleFind", "Directory");
        } else {
            directory = Paths.get(String.valueOf(path));
        }
        System.out.println((Main.searchFile(directory, subDirectory, fileName)).toString());
    }
}
