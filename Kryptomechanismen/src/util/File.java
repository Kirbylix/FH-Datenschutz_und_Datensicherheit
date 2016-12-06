package util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class File {

    /**
     * einlesen der txt Datei
     * @param path Pfad
     * @return List<String> Inhalt
     */
    public static List<String> readFile(String path, String fileName){
        List<String> list = null;

        try (Stream<String> stream = Files.lines(Paths.get(path + fileName))) {
            list = stream.collect(Collectors.toList());
            System.out.println(fileName + " wurde gelesen");
        } catch (IOException exception) {
            System.err.println(fileName + " konnte nicht gelesen werden !!");
        }
        return list;
    }

    /**
     * schreiben einer txt Datei
     * @param list Inhalt
     * @param path Pfad
     */
    public static void writeFile(List<String> list, String path, String fileName){
        try {
            Files.write(Paths.get(path + fileName), list);
            System.out.println(fileName + " wurde geschrieben");
        } catch (IOException exception) {
            System.err.println(fileName + " konnte nicht geschrieben werden !! \n" + exception);
        }
    }
}
