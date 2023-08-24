import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CS2103T IP
 * AY 23/24 Semester 1
 *
 * <p> A Task Manager that helps manage the list of tasks </p>
 *
 * @author Koo Yu Cong
 * @version CS2103T AY 23/24 Sem 1
 */
public class TaskManager {
    private List<Task> tasks;
    private File jsonFile;

    private static final String DIRECTORY_PATH = "data";
    private String FILE_NAME = "tasks.json";
    private String FILE_PATH = DIRECTORY_PATH + "/" + FILE_NAME;

    /**
     * A constructor that constructs a Task Manager
     */
    public TaskManager() {
        getFile();
        this.tasks = new ArrayList<>();
    }

    private void getFile() {
        try {
            String currentWorkingDir = System.getProperty("user.dir");

            // create a file object for the directory
            File directory = new File(currentWorkingDir, DIRECTORY_PATH);

            // If the directory does not exist, create it
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create a File object for the file within the directory
            File file = new File(currentWorkingDir, FILE_PATH);

            // If the file doesn't exist, create a new one
            if (!file.exists()) {
                file.createNewFile();
            }
            
            this.jsonFile = file;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lists the tasks in order
     *
     * @return The string representation of the list of tasks.
     */
    public String listTasks() {
        if (tasks.size() == 0) {
            return "There are no tasks in your list.\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            String item = (i + 1) + "." + tasks.get(i).toString() + "\n";
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * Adds a task to the end of the list of tasks.
     *
     * @param task The task to be added.
     * @return The string description after adding the task.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return "Got it. I've added this task:\n  "
                + task.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n";
    }


    /**
     * Marks the i-th task as either done or not done
     *
     * @param i The index of the task in the list(1-indexed)
     * @param done Whether to mark the task as done or not
     * @return The String description after marking the task
     * @throws DukeException if the index is out of bound
     */
    public String markTask(int i, boolean done) throws DukeException {
        i--;
        if (i < 0 || i >= this.tasks.size()) {
            // invalid index
            throw new DukeException("Please input a valid index for the task to marked/unmarked");
        }

        Task task = this.tasks.get(i);
        task.markTask(done);

        return done
                ? "Nice! I've marked this task as done:\n  " + task.toString() + "\n"
                : "OK, I've marked this task as not done yet:\n  " + task.toString() + "\n";
    }

    /**
     * Deletes the i-th task from the list of tasks.
     *
     * @param i The index of the task to be deleted(1-indexed)
     * @return The string description after deleting the task
     * @throws DukeException DukeException is thrown when the index is out of bound
     */
    public String deleteTask(int i) throws DukeException {
        i--;
        if (i < 0 || i >= this.tasks.size()) {
            // invalid index
            throw new DukeException("Please input a valid index for the task to removed");
        }

        Task task = this.tasks.remove(i);
        return "Noted! I've removed this task:\n  "
                + task.toString() + "\n"
                + "Now you have " + this.tasks.size() + " tasks in the list.\n";

    }
}
