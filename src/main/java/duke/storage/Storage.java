package duke.storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;




/**
 * Represents a storage that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final File file;

    /**
     * Creates a new Storage object.
     *
     * @param file_Path The path to the file used for storage.
     * @throws IOException When there's an error with file operations.
     * @throws DukeException When there's an error specific to the Duke application.
     */
    public Storage(String file_Path) throws IOException, DukeException {
        File savedFile = new File(file_Path);
        try {
            if (!savedFile.exists() && !savedFile.createNewFile()) {
                throw new IOException("Failed to create a new file.");
            }
        } catch (IOException e) {
            throw new DukeException("Error while saving the file: " + e.getMessage());
        }
        this.file = savedFile;
    }

    /**
     * Loads tasks from the file.
     *
     * @return An ArrayList of Task objects.
     * @throws FileNotFoundException When the file is not found.
     * @throws DukeException For other errors.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException, DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try (Scanner sc = new Scanner(this.file)) {
            while (sc.hasNext()) {
                String curLine = sc.nextLine();
                String[] splits = curLine.split("#");
                switch (splits[0].trim()) {
                case "T":
                    Task newTask_Todo = new Todo(splits[2].trim(), (splits[1].trim().equals("1")));
                    tasks.add(newTask_Todo);
                    break;
                case "D":
                    Task newTask_Deadline =
                            new Deadline(splits[2].trim(), splits[3].trim(), (splits[1].trim().equals("1")));
                    tasks.add(newTask_Deadline);
                    break;
                case "E":
                    Task newTask_Event = new Event(splits[2].trim(), splits[3].trim(), (splits[1].trim().equals("1")));
                    tasks.add(newTask_Event);
                    break;
                default:
                    throw new DukeException("Invalid task type in the data file.");
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Storage file not found.");
        } catch (Exception e) {
            throw new DukeException("Error occurred when reading the data file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws IOException When there's an error with file operations.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        String textToAdd = translateToStore(tasks.getTasks());
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Converts the list of tasks into a storable string format.
     *
     * @param tasks The ArrayList of tasks to be converted.
     * @return A string in the storage format.
     */
    public String translateToStore(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();

        for (Task task : tasks) {
            switch (task.getType()) {
            case TODO:
                result.append("T # ")
                        .append(task.getIsDone() ? "1" : "0")
                        .append(" # ")
                        .append(task.getTaskDescription())
                        .append("\n");
                break;
            case DEADLINE:
                Deadline deadline = (Deadline) task;
                result.append("D # ")
                        .append(task.getIsDone() ? "1" : "0")
                        .append(" # ")
                        .append(task.getTaskDescription())
                        .append(" # ")
                        .append(deadline.getTime())
                        .append("\n");
                break;
            case EVENT:
                Event event = (Event) task;
                result.append("E # ")
                        .append(task.getIsDone() ? "1" : "0")
                        .append(" # ")
                        .append(task.getTaskDescription())
                        .append(" # ")
                        .append(event.getE_start())
                        .append(" - ")
                        .append(event.getE_end())
                        .append("\n");
                break;
            default:
                throw new IllegalStateException("Unexpected task type: " + task.getType());
            }
        }

        return result.toString();
    }
}
