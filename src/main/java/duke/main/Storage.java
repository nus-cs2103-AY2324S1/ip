package duke.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import duke.exception.DukeException;
import duke.exception.DukeInvalidSavedToFileLineType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * A class to handle storing and loading.
 */
public class Storage {
    private TaskList tasks;

    /**
     * Constructs Storage objects.
     * @param tasks
     */
    public Storage(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Saves task list into disc.
     */
    public void save() {
        String dataPath = Paths.get("data", "duke.txt").toString();
        try {
            File dataFile = new File(dataPath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String line = task.saveToFileLine();
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File duke.txt not found.");
        }

    }

    /**
     * Loads tasks from disc into list.
     */
    public void load() {
        try {
            String dataPath = Paths.get("data", "duke.txt").toString();
            File dataFile = new File(dataPath);
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            for (String line; (line = reader.readLine()) != null;) {
                tasks.add(fileToTask(line));
            }
        } catch (IOException ex) {
            System.out.println("File duke.txt not found.");
        } catch (DukeException ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * Reads from disc and constructs task string to store in list.
     * @param line
     * @return task string
     * @throws DukeInvalidSavedToFileLineType
     */
    private Task fileToTask(String line) throws DukeInvalidSavedToFileLineType {
        String[] savedToFileLine = line.split(" \\| ");
        String type = savedToFileLine[0];

        String status = savedToFileLine[1];
        String description = savedToFileLine[2];
        switch (type) {
        case "T":
            return ToDo.create(status, description);
        case "D":
            String due = savedToFileLine[3];
            return Deadline.create(status, description, due);
        case "E":
            String range = savedToFileLine[3];
            String from = range.substring(0, range.indexOf(" to")).replace("from ", "");
            String to = range.substring(range.indexOf("to ")).replace("to ", "");
            return Event.create(status, description, from, to);
        default:
            throw new DukeInvalidSavedToFileLineType();
        }
    }

}
