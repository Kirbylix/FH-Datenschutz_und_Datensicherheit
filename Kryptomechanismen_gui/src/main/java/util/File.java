package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class File {
    static final Logger LOG = LogManager.getLogger(File.class.getName());

    /**
     * einlesen der txt Datei
     * @param path Pfad
     * @return List<String> Inhalt
     */
    public static List<String> readFile(String path, String fileName){
        LOG.entry();
        List<String> list = null;

        try (Stream<String> stream = Files.lines(Paths.get(path + fileName))) {
            list = stream.collect(Collectors.toList());
            LOG.info(fileName + " wurde gelesen");
        } catch (IOException exception) {
            LOG.error(fileName + " konnte nicht gelesen werden !!");
        }
        return list;
    }

    /**
     * schreiben einer txt Datei
     * @param list Inhalt
     * @param path Pfad
     */
    public static void writeFile(List<String> list, String path, String fileName){
        LOG.entry();
        try {
            Files.write(Paths.get(path + fileName), list, StandardOpenOption.APPEND);
            LOG.info(fileName + " wurde geschrieben");
        } catch (IOException exception) {
            LOG.error(fileName + " konnte nicht geschrieben werden !! \n" + exception);
        }

    }

    public static void writeFile(String text, String path, String fileName){
        LOG.entry();
        try {
            Files.write(Paths.get(path + fileName), text.getBytes(), StandardOpenOption.APPEND);
            LOG.info(fileName + " wurde geschrieben");
        } catch (IOException exception) {
            LOG.error(fileName + " konnte nicht geschrieben werden !! \n" + exception);
        }

    }
}
