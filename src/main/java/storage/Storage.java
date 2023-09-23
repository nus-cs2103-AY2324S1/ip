package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import common.DateParser;
import data.TaskList;
import data.exception.InvalidParamException;
import data.exception.StorageException;
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
            while ((line = br.readLine()) != null) {
                String[] parse = line.strip().split("\\|");
                // Shortest length is 3 for any task type
                if (parse.length < 3) {
                    continue;
                }
                Task task = null;
                try {
                    task = this.createTask(parse);
                } catch (InvalidParamException e) {
                    continue;
                }
                if (task != null) {
                    tasks.add(task);
                }
            }
            assert !tasks.contains(null) : "null task detected";

            // Close the reader after parsing the file
            br.close();
            return tasks;
        } catch (FileNotFoundException f) {
            // Create a new data directory if it cannot be found
            boolean isDirCreated = new File(fileDir).mkdir();
            assert isDirCreated : "failed to create data directory";
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
     * @throws InvalidParamException Thrown when parameters parsed
     *                               from file are invalid.
     */
    private Task createTask(String[] parse) throws InvalidParamException {
        String taskType = parse[0];
        boolean isDone = parse[1].equals("1");

        Task newTask = null;

        // Create task based on type given
        switch(taskType) {
        case "T":
            newTask = new Todo(parse[2]);
            break;
        case "D":
            newTask = parseDeadline(parse);
            break;
        case "E":
            newTask = parseEvent(parse);
            break;
        default:
            return null;
        }

        if (isDone && newTask != null) {
            newTask.mark();
        }

        return newTask;
    }

    private Deadline parseDeadline(String[] parse) {
        if (parse.length < 4) {
            return null;
        }
        LocalDateTime by = DateParser.parseDateString(parse[3]);
        if (by == null) {
            by = LocalDateTime.now();
        }
        return new Deadline(
            parse[2],
            by
        );
    }

    private Event parseEvent(String[] parse) throws InvalidParamException {
        if (parse.length < 5) {
            return null;
        }

        LocalDateTime from = DateParser.parseDateString(parse[3]);
        LocalDateTime to = DateParser.parseDateString(parse[4]);
        if (from == null) {
            from = LocalDateTime.now();
        }
        if (to == null) {
            to = LocalDateTime.now();
        }

        return new Event(
            parse[2],
            from,
            to
        );
    }

    /**
     * This method updates the file by overwriting its old content
     * with the contents of the new {@link TaskList} after a modification
     * is made by the user.
     *
     * @param tasks The {@link TaskList} containing the new list of tasks.
     * @throws StorageException Thrown when there's an issue with writing
     *                          the tasks to file.
     */
    public void update(TaskList tasks) throws StorageException {
        try {
            File f = new File(filePath);
            if (!f.canWrite()) {
                throw new StorageException(
                    "It seems I do not have permission to write your"
                        + "tasks to a local file. "
                        + "Perhaps you could run my program somewhere else?"
                );
            }
            FileWriter fw = new FileWriter(f);
            fw.write(tasks.toString());
            fw.close();
        } catch (IOException e) {
            throw new StorageException(
                "I couldn't save your tasks to a local file :( "
                    + "It would be best if you restart my program"
                    + "and try again."
            );
        }
    }
}
