package duke.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Class to handle storage of data.
 */
public class Storage {
    private static String baseDirectory = "./data/";

    /**
     * Sets the base directory for storage.
     * 
     * @param baseDirectory Base directory for storage.
     */
    public void setBaseDirectory(String baseDirectory) {
        // Ensures that it is valid directory
        if (baseDirectory.endsWith("/")) {
            Storage.baseDirectory = baseDirectory;
        } else {
            Storage.baseDirectory = baseDirectory + "/";
        }
    }

    private static void createFileIfNotExists(Path filePath) throws DukeException {
        // Handle errors
        if (!Files.exists(filePath)) {
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath); 
            } catch (IOException e) {
                throw new DukeException("An error occured while creating the task file.");
            }
        }
    }

    /**
     * Reads a file and returns a stream of lines.
     * 
     * @param fileName Name of file to be read.
     * @return Stream of lines from the file.
     * @throws DukeException If an error occurs while reading the file.
     */
    public static Stream<String> readFile(String fileName) throws DukeException {
        Path filePath = Path.of(Storage.baseDirectory + fileName);

        if (Files.notExists(filePath)) {
            return Stream.empty();
        }

        try {
            BufferedReader reader = Files.newBufferedReader(filePath);
            return reader.lines(); 
        } catch (IOException e) {
            throw new DukeException("An error occured while reading the file.");
        }
    }

    /**
     * Writes to a file.
     * 
     * @param fileName Name of file to be written to.
     * @param content Content to be written to the file.
     * @throws DukeException If an error occurs while writing to the file.
     */
    public static void writeFile(String fileName, Stream<String> content) throws DukeException {
        Path filePath = Path.of(Storage.baseDirectory + fileName);

        try {
            Storage.createFileIfNotExists(filePath);
            BufferedWriter writer = Files.newBufferedWriter(filePath);
            content.forEach(lineToWrite -> {
                try {
                    writer.write(lineToWrite);
                    writer.newLine(); 
                } catch (IOException e) {
                    //TODO: handle IOException
                }
            });
            writer.close();
        } catch (IOException e) {
            throw new DukeException(String.format("%s \"%s\"", "An error occured while writing the file ", fileName));
        }
    }
}