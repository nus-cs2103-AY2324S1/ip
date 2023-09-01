package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks from/to a specified file.
 */
public class Storage {
    private String filePath;
    TaskList tasks = new TaskList();

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where task data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the constructor.
     *
     * @return A TaskList containing the loaded tasks.
     * @throws DukeException If there are errors during loading.
     */
    public TaskList load() throws DukeException {
        try {
            FileReader fileReader = new FileReader(filePath);
            Scanner scanner = new Scanner(fileReader);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (taskType) {
                case "T":
                    tasks.add(new Todo(description, isDone));
                    break;
                case "D":
                    String by = parts[3];
                    tasks.add(new Deadline(description, isDone, by));
                    break;
                case "E":
                    String[] parts2 = parts[3].split(" - ");
                    String from = parts2[0];
                    String to = parts2[1];
                    tasks.add(new Event(description, isDone, from, to));
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! Couldn't load file.");
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found: " + e.getMessage());
        } catch (IOException e) {
            throw new DukeException("Error reading file: " + e.getMessage());
        } catch (DukeException e) {
            throw new DukeException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the tasks to the file specified in the constructor.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws DukeException If there are errors during saving.
     */
    public void saveTasks(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter("./data/tasks.txt");
            for (int i = 0 ; i < tasks.listSize() ; i++) {
                String taskType = "";
                String dateInfo = "";

                if (tasks.getTask(i) instanceof Todo) {
                    taskType = "T";
                } else if (tasks.getTask(i) instanceof Deadline) {
                    taskType = "D";
                    dateInfo = ((Deadline) tasks.getTask(i)).getBy();
                } else if (tasks.getTask(i) instanceof Event) {
                    taskType = "E";
                    dateInfo = ((Event) tasks.getTask(i)).getFrom() +
                    " - " + ((Event) tasks.getTask(i)).getTo();
                } else {
                    throw new DukeException("Invalid tasks in list.");
                }

                fileWriter.write(taskType + " | " +
                (tasks.getTask(i).getDone() ? "1" : "0") + " | " +
                tasks.getTask(i).getDescription() + " | " + dateInfo + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error saving tasks: " + e.getMessage());
        } catch (DukeException e) {
            throw new DukeException("Invalid tasks in list: " + e.getMessage());
        }
    }
}
