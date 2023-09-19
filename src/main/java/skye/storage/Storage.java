package skye.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import skye.data.Serializable;
import skye.data.exception.DukeException;
import skye.parser.Decoder;

/**
 * Represents a generic storage utility that loads and saves data.
 *
 * @param <T> An object that is serializable
 */
public class Storage<T extends Serializable> {
    private final Path filePath;

    /**
     * Initializes a generic storage utility.
     *
     * @param directory Directory of the save file
     * @param file Filename of the save file
     */
    public Storage(String directory, String file) {
        this.filePath = Paths.get(directory, file);
    }

    /**
     * Loads save data from the specified file path and decodes line by line into the specified object
     * of type T.
     *
     * @param decoder A decoder that decodes an encoded string to the specified object of type T.
     * @return A list of serializable objects
     * @throws IOException Describes the I/O error encountered in the OS file system
     * @throws DateTimeParseException Describes the error with the date format
     * @throws DukeException Describes the error encountered when executing the command
     */
    public List<T> load(Decoder<T> decoder) throws IOException, DateTimeParseException, DukeException {
        List<T> list = new ArrayList<>();

        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } else {
            List<String> encodedTasks = Files.readAllLines(filePath);
            for (String encodedTask : encodedTasks) {
                list.add(decoder.decode(encodedTask));
            }
        }

        return list;
    }

    /**
     * Writes a list of decoded Task objects to the specified file path.
     *
     * @param list List of objects
     * @throws IOException Signifies that writing to file has failed
     */
    public void write(List<T> list) throws IOException {
        FileWriter fw = new FileWriter(filePath.toString());
        for (T item: list) {
            fw.write(item.toSaveDataFormat() + System.lineSeparator());
        }
        fw.close();
    }
}
