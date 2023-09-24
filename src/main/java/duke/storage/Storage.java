package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a storage which handles loading tasks from a file and updating tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path to the file used for task storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return An ArrayList containing loaded tasks.
     * @throws DukeException If there's an issue loading tasks.
     */
    public ArrayList<Task> load() throws DukeException {
        File file = new File(this.filePath);
        try {
            return processFiles(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Processes a file, reading and parsing its contents into tasks.
     *
     * @param file The file to be processed.
     * @return An ArrayList containing the parsed tasks.
     * @throws IOException If there's an issue reading the file.
     */
    private ArrayList<Task> processFiles(File file) throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        Scanner sc = new Scanner(file);
        ArrayList<Task> taskArr = new ArrayList<>();

        while (sc.hasNext()) {
            String currLine = sc.nextLine();
            String[] wordArr = currLine.split("\\|"); // Escape the | and space characters
            String category = wordArr[0].trim();

            switch (category) {
            case "T":
                processTodo(wordArr, taskArr);
                break;
            case "D":
                processDeadline(wordArr, taskArr);
                break;
            case "E":
                processEvent(wordArr, taskArr);
                break;
            default:
                break;
            }
        }
        return taskArr;
    }

    /**
     * Processes an "event" task from a string array and adds it to the taskArr.
     *
     * @param wordArr The string array containing task information.
     * @param taskArr The ArrayList to which the task will be added.
     */
    private void processEvent(String[] wordArr, ArrayList<Task> taskArr) {
        String time = wordArr[3].trim();
        String[] timeline = time.split("to");
        Event e = new Event(wordArr[2].trim(), timeline[0].trim(), timeline[1].trim());
        if (wordArr[1].trim().equals("1")) {
            e.markAsDone();
        }
        taskArr.add(e);
    }

    /**
     * Processes a deadline task from a string array and adds it to the taskArr.
     *
     * @param wordArr The string array containing task information.
     * @param taskArr The ArrayList to which the task will be added.
     */
    private void processDeadline(String[] wordArr, ArrayList<Task> taskArr) {
        Deadline dl = new Deadline(wordArr[2].trim(), wordArr[3].trim());
        if (wordArr[1].trim().equals("1")) {
            dl.markAsDone();
        }
        taskArr.add(dl);
    }

    /**
     * Processes a todo task from a string array and adds it to the taskArr.
     *
     * @param wordArr The string array containing task information.
     * @param taskArr The ArrayList to which the task will be added.
     */
    private void processTodo(String[] wordArr, ArrayList<Task> taskArr) {
        Todo todo = new Todo(wordArr[2].trim());
        if (wordArr[1].trim().equals("1")) {
            todo.markAsDone();
        }
        taskArr.add(todo);
    }

    /**
     * Updates the storage file with the tasks in the given task list.
     *
     * @param taskList The task list to update the file with.
     */
    public void updateFile(TaskList taskList) {
        try {
            initialiseFiles();
            writeToFile(taskList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes tasks from the task list to the storage file.
     *
     * @param tasks The task list to be written to the file.
     * @throws IOException If there's an issue writing to the file.
     */
    private void writeToFile(TaskList tasks) throws IOException {
        // Open the FileWriter in append mode.
        FileWriter fWriter = new FileWriter(this.filePath, true);

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t instanceof Todo) {
                fWriter.write("T | " + t.getNumber() + " | " + t.getDescription() + "\n");
            } else if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                fWriter.write("D | " + d.getNumber() + " | " + d.getDescription() + " | " + d.getBy() + "\n");
            } else {
                //event
                Event e = (Event) t;
                fWriter.write("E | " + e.getNumber() + " | " + e.getDescription() + " | " + e.getFrom()
                        + " to " + e.getTo() + "\n");
            }
        }
        fWriter.close();
    }

    /**
     * Initializes the storage file by clearing its contents.
     *
     * @throws IOException If there's an issue initializing the file.
     */
    private void initialiseFiles() throws IOException {
        // Open the FileWriter without append mode.
        FileWriter fWriter = new FileWriter(this.filePath);
        // Delete whole text
        fWriter.write("");
        // Close the FileWriter
        fWriter.close();
    }
}
