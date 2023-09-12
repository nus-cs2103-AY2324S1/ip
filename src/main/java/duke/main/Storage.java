package duke.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * Handles the loading and saving of tasks to a data file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The file path where task data will be stored and loaded from.
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Loads tasks from the data file and returns them as a list.
     *
     * @return An ArrayList containing the loaded tasks.
     * @throws DukeException If there is an error during the loading process.
     */
    public ArrayList<Task> loadTasksFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                return tasks;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] taskData = data.split(" \\| ");
                Task task;

                //taskData[0] = task type
                //taskData[1] = Done status
                //taskData[2] = description

                boolean isDone = taskData[1].equals("1");

                switch (taskData[0]) {
                case "T":
                    task = new ToDo(taskData[2]);
                    break;
                case "D":
                    task = new Deadline(taskData[2], taskData[3]);
                    break;
                case "E":
                    String[] eventData = taskData[3].split(" to ");
                    task = new Event(taskData[2], eventData[0], eventData[1]);
                    break;
                default:
                    System.out.println("error: unknown task type found in file.");
                    continue; // Skip to the next line
                }

                // Set done status
                if (isDone) {
                    task.markDone();
                } else {
                    task.markNotDone();
                }

                tasks.add(task);

            }

            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error loading file: IOException occurred.");
        }
    }

    /**
     * Saves tasks to the data file.
     *
     * @param tasks The TaskList containing the tasks to be saved.
     * @throws DukeException If there is an error during the saving process.
     */
    public void saveTasksToFile(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task: tasks.getAllTasks()) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.close();

        } catch (IOException e) {
            throw new DukeException("Error saving file: IOException occurred.");
        }
    }
}
