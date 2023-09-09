package duke.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import duke.exceptions.DukeIOException;

/**
 * Stores data into specified files. Data is stored at ./data/ directory.
 */
public class Storage {

    // Error message templates
    private static final String ERROR_MESSAGE_TEMPLATE_CREATING_FILE = "Error creating file: %s";
    private static final String ERROR_MESSAGE_TEMPLATE_READING_FILE = "Error reading from file: %s";
    private static final String ERROR_MESSAGE_TEMPLATE_WRITING_TO_FILE = "Error writing to file: %s";

    // The path where the data is saved.
    private final Path pathToSaveFile;

    /**
     * Constructor for a Storage.
     * Creates the storage file if it does not exist.
     *
     * @param saveFileName The name of the file to save the data to. Eg: data.txt
     */
    public Storage(String saveFileName) throws DukeIOException {
        pathToSaveFile = Path.of(System.getProperty("user.dir"), "data", saveFileName);
        try {
            if (!Files.exists(pathToSaveFile)) {
                Files.createDirectories(pathToSaveFile.getParent());
                Files.createFile(pathToSaveFile);
            }
        } catch (IOException ioe) {
            throw new DukeIOException(String.format(ERROR_MESSAGE_TEMPLATE_CREATING_FILE, ioe.getMessage()));
        }
    }

    /**
     * Reads from the file and returns the data as a String.
     *
     * @return The data in the file as a String.
     * @throws DukeIOException If there is an error reading from the file.
     */
    public String load() throws DukeIOException {
        assert Files.exists(pathToSaveFile) : "Save File should exist at this point.";
        try {
            BufferedReader reader = Files.newBufferedReader(pathToSaveFile, StandardCharsets.UTF_8);
            StringBuilder data = new StringBuilder();

            String line = reader.readLine();
            while (line != null) {
                // \n is used here instead of %n to preserve save file formatting
                data.append(line).append("\n");
                line = reader.readLine();
            }

            reader.close();
            return data.toString();
        } catch (IOException ioe) {
            throw new DukeIOException(String.format(ERROR_MESSAGE_TEMPLATE_READING_FILE, ioe.getMessage()));
        }
    }

    /**
     * Writes to the file. Overwrites the existing data in the file.
     *
     * @param data The data to be written to the file.
     * @throws DukeIOException If there is an error writing to the file.
     */
    public void save(String data) throws DukeIOException {
        assert Files.exists(pathToSaveFile) : "Save File should exist at this point.";
        try {
            Files.writeString(pathToSaveFile, data);
        } catch (IOException ioe) {
            throw new DukeIOException(String.format(ERROR_MESSAGE_TEMPLATE_WRITING_TO_FILE, ioe.getMessage()));
        }
    }
}
