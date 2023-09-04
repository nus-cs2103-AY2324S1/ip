package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;

/**
 * Handles read and write to file data.
 */
public class Storage {
    private String home;
    private Path path;
    private String filePath;

    /**
     * Constructor for storage.
     */
    public Storage() {
        this.home = System.getProperty("user.dir");
        this.path = Paths.get(home, "data", "duke.txt");
        this.filePath = this.path.toString();
    }

    /**
     * Constructor for storage with specified filePath.
     * @param filePath
     */
    public Storage(String filePath) {
        this.home = System.getProperty("user.dir");
        this.path = Paths.get(home, filePath);
        this.filePath = filePath;
    }

    /**
     * Reads from the specified file.
     * @return TaskList instance containing all tasks in file
     * @throws DukeException if error reading file
     */
    public TaskList read() throws DukeException {
        TaskList tasks = new TaskList();
        if (Files.exists(this.path)) {
            File f = new File(this.filePath);
            try {
                Scanner s = new Scanner(f);
                while (s.hasNextLine()) {
                    String nextLine = s.nextLine();
                    if (nextLine.equals("")) {
                        continue;
                    }
                    tasks.add(Parser.parseFile(nextLine));
                }
                s.close();
            } catch (FileNotFoundException e) {
                throw new DukeException("Error reading file");
            }
        }
        return tasks;
    }

    /**
     * Writes to the specified file.
     * @param textToAdd All tasks exported to text format
     * @throws DukeException if error writing to file
     */
    private void writeToFile(String textToAdd) throws DukeException {
        Path folder = this.path.getParent();
        if (!Files.exists(folder)) {
            try {
                Files.createDirectories(folder);
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error writing to file");
        }
    }

    /**
     * Takes TaskList of the program and exports it to text file.
     * @param tasks TaskList
     * @throws DukeException if error writing to file
     */
    public void write(TaskList tasks) throws DukeException {
        String data = "";
        for (int i = 0; i < tasks.size(); i++) {
            data += tasks.get(i).exportToText();
            if (i < tasks.size() - 1) {
                data += System.lineSeparator();
            }
        }
        writeToFile(data);
    }
}
