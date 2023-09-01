package carbonbot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage provides operations to write and read from the disk.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage to interact with the file at the provided file path.
     *
     * @param filePath Path to the file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data from the file.
     *
     * @return The lines in the file in a List.
     * @throws IOException The file data could not be fetched from the file.
     */
    public List<String> load() throws IOException {
        // Returns an empty list if the file does not exists
        if (!new File(this.filePath).exists()) {
            return new ArrayList<>();
        }

        return Files.readAllLines(Paths.get(filePath));
    }

    /**
     * Writes data to the file. Note that it overwrites instead of append to file.
     *
     * @param data The data to be written in the file.
     * @throws IOException The data could not be written to the file.
     */
    public void write(String data) throws IOException {
        // Create the file, and its directories if it does not already exist
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        // Writes the data to the file
        FileWriter fw = new FileWriter(file);
        fw.write(data);
        fw.close();
    }
}
