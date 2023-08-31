package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The `Storage` class handles reading and writing tasks to and from a data
 * file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a `Storage` object with the specified file path.
     *
     * @param filePath The path to the data file used for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the data file and returns them as an `ArrayList` of `Task`
     * objects.
     *
     * @return An `ArrayList` of `Task` objects representing the tasks loaded from
     *         the data file.
     * @throws DukeException If an error occurs while reading from the data file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> items = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineArr = line.split("\\|");

                String taskType = lineArr[0];
                boolean isDone = Integer.parseInt(lineArr[1]) == 1;
                String name = lineArr[2];
                switch (taskType) {
                    case "T":
                        items.add(new ToDo(name, isDone));
                        break;
                    case "D":
                        String by = lineArr[3];
                        items.add(new Deadline(name, LocalDateTime.parse(by), isDone));
                        break;
                    case "E":
                        String from = lineArr[3];
                        String to = lineArr[4];
                        items.add(new Event(name, LocalDateTime.parse(from), LocalDateTime.parse(to), isDone));
                        break;
                    default:
                        continue;
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred while reading from the data file.");
        }
        return items;
    }

    /**
     * Writes tasks to the data file.
     *
     * @param items An `ArrayList` of `Task` objects representing the tasks to be
     *              saved.
     * @throws DukeException If an error occurs while writing to the data file.
     */
    public void writeData(ArrayList<Task> items) throws DukeException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task t : items) {
                writer.write(t.toDataString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred while writing to the data file.");
        }
    }
}
