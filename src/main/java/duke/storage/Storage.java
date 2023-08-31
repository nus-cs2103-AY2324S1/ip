package duke.storage;

import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.parser.Parser;

/**
 * Represents a storage utility object which provides operations to load task information
 * from the user's file system and write task information to the specified file path.
 */
public class Storage {

    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private final String filePath;

    /**
     * Initializes the storage object by providing a file path
     *
     * @param filePath Path to the save file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load all task information into the save file by reading from a text file specified
     * by the file path and decoding each line into a Task object.
     *
     * @return
     * @throws IOException
     * @throws DateTimeParseException
     * @throws DukeException
     */
    public List<Task> load() throws IOException, DateTimeParseException, DukeException {
        List<Task> tasks = new ArrayList<>();
        File saveFile = new File(Paths.get(CURRENT_DIRECTORY, filePath).toString());
        if (saveFile.exists()) {
            Scanner sc = new Scanner(saveFile);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] data = line.split("\\|");
                String taskType = data[0].trim();
                switch (taskType) {
                    case "D":
                        // duke.data.task.Deadline
                        if (data.length == 4) {
                            // Ensure deadline date is a Datetime object
                            LocalDateTime localDateTime =
                                    LocalDateTime.parse(data[3].trim(), Parser.DATE_TIME_FORMAT);
                            // Create new task object
                            Task t = new Deadline(data[2].trim(), localDateTime);
                            if (Integer.parseInt(data[1].trim()) == 1) {
                                t.markAsDone();
                            }
                            tasks.add(t);
                        }
                        break;
                    case "E":
                        // duke.data.task.Event
                        if (data.length == 5) {
                            // Ensure deadline date is a Datetime object
                            Task t = new Event(data[2].trim(),
                                    LocalDateTime.parse(data[3].trim(), Parser.DATE_TIME_FORMAT),
                                    LocalDateTime.parse(data[4].trim(), Parser.DATE_TIME_FORMAT));
                            if (Integer.parseInt(data[1].trim()) == 1) {
                                t.markAsDone();
                            }
                            tasks.add(t);
                        }
                        break;
                    case "T":
                        // To Do
                        if (data.length == 3) {
                            Task t = new ToDo(data[2].trim());
                            if (Integer.parseInt(data[1].trim()) == 1) {
                                t.markAsDone();
                            }
                            tasks.add(t);
                        }
                        break;
                }
            }
        } else if (saveFile.getParentFile().mkdirs()){
            saveFile.createNewFile();
        } else {
            throw new DukeException("Failed to create directory.");
        }
        return tasks;
    }

    /**
     * Writes a list of decoded Task objects to the specified file path.
     *
     * @param tasks List of Task objects
     * @throws IOException Signifies that writing to file has failed
     */
    public void write(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(Paths.get(CURRENT_DIRECTORY, filePath).toString());
        for (Task t: tasks) {
            fw.write(t.toSaveDataFormat() + System.lineSeparator());
        }
        fw.close();
    }
}