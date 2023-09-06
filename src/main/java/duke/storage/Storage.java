package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;

/**
 * Represents a storage that stores the task list in the hard disk.
 * The tasks are stored in a text file. Each line corresponds to a task.
 */
public class Storage {
    private final String filePath; // The path of the file to store the task list.

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the all lines from the file as a list of strings.
     *
     * @return A list of strings representing the lines in the file.
     * @throws DukeException If there are problems loading the task list from the file.
     */
    public List<String> load() throws DukeException {
        File file = new File(this.filePath);
        List<String> lines = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                lines.add(line);
            }
            sc.close();
        } catch (FileNotFoundException fileNotFoundException) {
            try {
                file.createNewFile();
            } catch (IOException ioException) {
                throw new DukeException("OOPS!!! I have problems creating the file to save your tasks.");
            }
        }
        return lines;
    }

    /**
     * Saves the given list of strings to the file.
     *
     * @param lines The list of strings to be saved.
     * @throws DukeException If there are problems saving to the file.
     */
    public void save(List<String> lines) throws DukeException {
        File file = new File(this.filePath);
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (String line : lines) {
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! I have problems saving your tasks.");
        }
    }
}
