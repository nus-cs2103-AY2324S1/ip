package Duke.application;

import Duke.duke.Duke;
import Duke.exception.DukeException;
import Duke.storage.Storage;
import Duke.task.Task;
import Duke.task.TaskList;

/**
 * The application that manages UI and the taskList.
 */
public class Application {
    private TaskList taskList = new TaskList();

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
        saveTaskList();
        duke.kill();
    }

    /**
     * Adds a task to the application's task list and storage.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.addTask(task);
    }

    /**
     * Removes a task from the application's task list and storage.
     *
     * @param task The task to be removed.
     */
    public void removeTask(Task task) {
        storage.removeLine(taskList.findTaskIndex(task) + 1);
        taskList.removeTask(task);
    }

    /**
     * Loads the task list from storage into the application.
     *
     * @throws DukeException If there's an issue with loading tasks from storage.
     */
    public void loadTaskList() throws DukeException {
        taskList = new TaskList();
        String line;
        int currentLine = 1;
        while(!(line = storage.getLine(currentLine)).equals("")) {
            taskList.addTask(Task.parse(line));
            currentLine++;
        }
    }

    public void saveTaskList() {
        storage.clear();
        for(int i = 0; i < getTaskCount(); i++) {
            Task task = taskList.getTask(i);
            storage.addLine(task.toSaveFormat());
        }
    }

    /**
     * Returns the number of tasks in the application's task list.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return taskList.getTaskCount();
    }

    /**
     * Retrieves a task from the application's task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return taskList.getTask(index);
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public TaskList findMatchingTasks(String content) {
        TaskList matchingTasks = new TaskList();
        for(int i = 0; i < getTaskCount(); i++) {
            if(taskList.getTask(i).containsString(content)) {
                matchingTasks.addTask(taskList.getTask(i));
            }
        }
        return matchingTasks;
    }
}
