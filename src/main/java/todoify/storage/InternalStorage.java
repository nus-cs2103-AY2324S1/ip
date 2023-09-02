package todoify.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A class that manages the program's internal storage's read-write operations.
 */
public class InternalStorage {

    private static InternalStorage sharedInstance = null;
    private final Path baseNioPath;

    /**
     * Constructs a new instance of an internal storage manager.
     *
     * @param baseNioPath The Java NIO path to the base directory. May be null to use default.
     */
    public InternalStorage(Path baseNioPath) {
        this.baseNioPath = baseNioPath;
    }

    /**
     * Returns the global singleton for the storage manager with the default path.
     *
     * @return The shared default instance of the internal storage manager.
     */
    public static InternalStorage getDefault() {
        if (sharedInstance == null) {
            sharedInstance = new InternalStorage(null);
        }
        return sharedInstance;
    }

    /**
     * Saves the given String data into the given path, relative to the configured base directory.
     *
     * @param internalPath The path to save to.
     * @param data         The data to save in the file with.
     * @throws IOException if the file cannot be written for any reason.
     */
    public void saveTo(InternalPath internalPath, String data) throws IOException {

        // Auto-create directory if necessary.
        Path nioPath = internalPath.excludingLastComponent().toJavaNioFilePath(this.baseNioPath);
        Files.createDirectories(nioPath);

        // Write to the file.
        File file = internalPath.toFile(this.baseNioPath);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(data);
        fileWriter.close();

    }

    /**
     * Reads the string data from the given filename, relative to the configured base directory.
     *
     * @param internalPath The path to read from.
     * @return The resulting data read from the file.
     * @throws FileNotFoundException if the file does not exist.
     * @throws IOException           if the file cannot be read for any reason.
     */
    public String loadFrom(InternalPath internalPath) throws IOException {

        // Reads from the file.
        File file = internalPath.toFile(this.baseNioPath);

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        return stringBuilder.toString();

    }
}
