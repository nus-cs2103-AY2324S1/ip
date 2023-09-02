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

    // The path where the data is saved.
    private final Path saveFilePath;

    /**
     * Constructor for a Storage.
     * Creates the storage file if it does not exist.
     *
     * @param saveFileName The name of the file to save the data to. Eg: data.txt
     */
    public Storage(String saveFileName) throws DukeIOException {
        saveFilePath = Path.of(System.getProperty("user.dir"), "data", saveFileName);
        try {
            if (!Files.exists(saveFilePath)) {
                Files.createDirectories(saveFilePath.getParent());
                Files.createFile(saveFilePath);
            }
        } catch (IOException ioe) {
            throw new DukeIOException("Error creating file: " + ioe.getMessage());
        }
    }

    /**
     * Reads from the file and returns the data as a String.
     *
     * @return The data in the file as a String.
     * @throws DukeIOException If there is an error reading from the file.
     */
    public String load() throws DukeIOException {
        try {
            BufferedReader reader = Files.newBufferedReader(saveFilePath, StandardCharsets.UTF_8);
            StringBuilder data = new StringBuilder();

            String line = reader.readLine();
            while (line != null) {
                data.append(line).append("\n");
                line = reader.readLine();
            }

            reader.close();
            return data.toString();

        } catch (IOException ioe) {
            throw new DukeIOException("Error reading from file: " + ioe.getMessage());
        }
    }

    /**
     * Writes to the file. Overwrites the existing data in the file.
     *
     * @param data The data to be written to the file.
     * @throws DukeIOException If there is an error writing to the file.
     */
    public void save(String data) throws DukeIOException {
        try {
            Files.writeString(saveFilePath, data);
        } catch (IOException ioe) {
            throw new DukeIOException("Error writing to file: " + ioe.getMessage());
        }
    }
}
