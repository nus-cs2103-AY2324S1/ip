package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.EmptyDateException;
import duke.exception.EmptyTaskException;
import duke.exception.NoEndDateException;

/**
 * The duke.Storage class is responsible for loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    protected String filepath;

    /**
     * Constructor for duke.Storage class.
     *
     * @param filepath The file path where the tasks are stored.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the file using the file path.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws DukeException If there is an issue loading tasks or parsing the file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<String> dataArray = new ArrayList<>();
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            File file = new File(this.filepath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                dataArray.add(input);
            }

            scanner.close();
            String[] data = dataArray.toArray(new String[dataArray.size()]);

            for (String item : data) {
                taskList.add(loadData(item));
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File Not Found: " + this.filepath);
        }

        return taskList;
    }

    /**
     * Creates a duke.Task from the string representation from the file.
     *
     * @param dataInput The string representation of the task.
     * @return The created duke.Task object.
     * @throws DukeException If the string representation does not meet task requirements.
     */
    public Task loadData(String dataInput) throws DukeException {
        Task newTask;
        String[] arr = dataInput.split("\\|");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }

        String taskType = arr[0];


        if (taskType.equals("T")) {
            if (arr.length < 3 || arr[2].isEmpty()) {
                throw new EmptyTaskException("todo");
            }

            newTask = new ToDo(arr[2]);
        } else if (taskType.equals("D")) {
            if (arr.length < 3 || arr[2].isEmpty()) {
                throw new EmptyTaskException("deadline");
            }
            if (arr.length != 4 || arr[3].isEmpty()) {
                throw new EmptyDateException("deadline");
            }

            newTask = new Deadline(arr[2], arr[3]);
        } else {
            if (arr.length < 3 || arr[2].isEmpty()) {
                throw new EmptyTaskException("event");
            }
            if (arr.length < 4 || arr[3].isEmpty()) {
                throw new EmptyDateException("event");
            }
            if (arr.length != 5 || arr[4].isEmpty()) {
                throw new NoEndDateException();
            }

            newTask = new Event(arr[2], arr[3], arr[4]);
        }

        // mark as done
        if (arr[1].equals("1")) {
            newTask.markAsDone();
        }

        return newTask;
    }
}
