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
        String[] dataArray = dataInput.split("\\|");

        for (int i = 0; i < dataArray.length; i++) {
            dataArray[i] = dataArray[i].trim();
        }


        if (dataArray[0].equals("T")) {
            if (dataArray.length < 3 || dataArray[2].isEmpty()) {
                throw new EmptyTaskException("todo");
            }

            newTask = new ToDo(dataArray[2]);
        } else if (dataArray[0].equals("D")) {
            if (dataArray.length < 3 || dataArray[2].isEmpty()) {
                throw new EmptyTaskException("deadline");
            }
            if (dataArray.length != 4 || dataArray[3].isEmpty()) {
                throw new EmptyDateException("deadline");
            }

            newTask = new Deadline(dataArray[2], dataArray[3]);
        } else {
            if (dataArray.length < 3 || dataArray[2].isEmpty()) {
                throw new EmptyTaskException("event");
            }
            if (dataArray.length < 4 || dataArray[3].isEmpty()) {
                throw new EmptyDateException("event");
            }
            if (dataArray.length != 5 || dataArray[4].isEmpty()) {
                throw new NoEndDateException();
            }

            newTask = new Event(dataArray[2], dataArray[3], dataArray[4]);
        }

        // mark as done
        if (dataArray[1].equals("1")) {
            newTask.markAsDone();
        }

        return newTask;
    }
}
