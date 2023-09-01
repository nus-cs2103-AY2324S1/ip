package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import common.DateParser;
import data.TaskList;
import data.exception.DukeException;
import data.tasks.Deadline;
import data.tasks.Event;
import data.tasks.Task;
import data.tasks.Todo;

/**
 * The Storage class.
 * Load tasks created by the user from previous session
 * via a text file. Updates the same text file for each
 * modification to the {@link TaskList}.
 */
public class Storage {
    private final String fileDir;
    private final String filePath;

    /**
     * The constructor method for the Storage class.
     * Takes in the directory and file path that the
     * user wishes to store their tasks on memory.
     * 
     * @param filePath The file path of the file containing the tasks.
     * @param fileDir The directory of the file.
     */
    public Storage(String filePath, String fileDir) {
        this.filePath = filePath;
        this.fileDir = fileDir;
    }

    /**
     * This method loads in the tasks from the file specified by the user.
     * If the file cannot be found, a new directory is created to store the file.
     * 
     * @return An ArrayList of {@link Task} loaded from the file.
     */
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            BufferedReader br = new BufferedReader(
                new FileReader(filePath)
            );

            String line;
            while((line = br.readLine()) != null) {
                String[] parse = line.strip().split("\\|");
                // Shortest length is 3 for any task type
                if (parse.length < 3) continue;
                Task task = this.createTask(parse);
                if (task != null) {
                    tasks.add(task);
                }
            }

            // Close the reader after parsing the file
            br.close();
            return tasks;
        } catch (FileNotFoundException f) {
            // Create a new data directory if it cannot be found
            new File(fileDir).mkdir();
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * This method handles the parsing of each line containing
     * the data of a task from the file.
     * 
     * @param parse The line containing the task data.
     * @return A {@link Task} instance.
     */
    private Task createTask(String[] parse) {
        String taskType = parse[0];
        boolean isDone = parse[1].equals("1");

        // Create task based on type given
        switch(taskType) {
        case "T":
            return new Todo(parse[2], isDone);
        case "D":
            if (parse.length < 4) break;
            return new Deadline(
                parse[2], 
                DateParser.parseDateString(parse[3]), 
                isDone
            );
        case "E":
            if (parse.length < 5) break;
            return new Event(
                parse[2], 
                DateParser.parseDateString(parse[3]), 
                DateParser.parseDateString(parse[4]), 
                isDone
            );
        default:
            return null;
        }
        return null;
    }

    /**
     * This method updates the file by overwriting its old content
     * with the contents of the new {@link TaskList} after a modification
     * is made by the user.
     * 
     * @param tasks The {@link TaskList} containing the new list of tasks.
     * @throws DukeException Thrown when there's an issue with writing
     *                       the tasks to file.
     */
    public void update(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(tasks.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Sorry error with saving tasks!");
        }
    }
}
