package Duke.application;

import Duke.duke.Duke;
import Duke.exception.DukeException;
import Duke.storage.Storage;
import Duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * The application that manages UI and the taskList.
 */
public class Application {
    private List<Task> taskList = new ArrayList<>();

    private final Duke duke;

    private final Storage storage;

    /**
     * Constructs an Application object.
     *
     * @param duke The main Duke instance associated with this application.
     * @param storage The storage object responsible for data persistence.
     * @throws DukeException If there's an issue with initializing the application or loading tasks.
     */
    public Application(Duke duke, Storage storage) throws DukeException {
        this.duke = duke;
        this.storage = storage;
        loadTaskList();
    }

    /**
     * Shuts down application.
     */
    public void kill() {
        duke.kill();
    }

    /**
     * Adds a task to the application's task list and storage.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        storage.addLine(task.toSaveFormat());
        taskList.add(task);
    }

    /**
     * Removes a task from the application's task list and storage.
     *
     * @param task The task to be removed.
     */
    public void removeTask(Task task) {
        storage.removeLine(taskList.indexOf(task) + 1);
        taskList.remove(task);
    }

    /**
     * Loads the task list from storage into the application.
     *
     * @throws DukeException If there's an issue with loading tasks from storage.
     */
    public void loadTaskList() throws DukeException {
        taskList = new ArrayList<>();
        String line;
        int currentLine = 1;
        while(!(line = storage.getLine(currentLine)).equals("")) {
            taskList.add(Task.Parse(line));
            currentLine++;
        }
    }

    /**
     * Returns the number of tasks in the application's task list.
     *
     * @return The number of tasks.
     */
    public int taskCount() {
        return taskList.size();
    }

    /**
     * Retrieves a task from the application's task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }
}
