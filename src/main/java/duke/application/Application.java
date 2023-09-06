package duke.application;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import duke.command.Command;
import duke.exception.DukeException;
import duke.message.Message;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * The application that manages UI and the taskList.
 */
public class Application extends javafx.application.Application {
    private static final String name = "Iris";
    private static final String filePath = "C:\\Users\\ortt2\\Documents\\ip\\src\\data\\tasks.txt";
    private TaskList taskList = new TaskList();
    private Ui ui;
    private Storage storage = null;

    /**
     * Shuts down application.
     */
    public void kill() {
        saveTaskList();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.schedule(() -> Platform.runLater(ui::closeGui), 1, TimeUnit.SECONDS);
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
        while (!(line = storage.getLine(currentLine)).equals("")) {
            taskList.addTask(Task.parse(line));
            currentLine++;
        }
    }

    /**
     * Saves the task list into storage.
     */
    public void saveTaskList() {
        storage.clear();
        for (int i = 0; i < getTaskCount(); i++) {
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

    /**
     * Retrieves a TaskList containing all the tasks that contain a substring.
     *
     * @param content The substring to search for.
     * @return A TaskList containing matching Tasks.
     */
    public TaskList findMatchingTasks(String content) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < getTaskCount(); i++) {
            if (taskList.getTask(i).containsString(content)) {
                matchingTasks.addTask(taskList.getTask(i));
            }
        }
        return matchingTasks;
    }

    /**
     * Initialize the components of the application.
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     */
    @Override
    public void start(Stage primaryStage) {
        this.ui = new Ui(this);
        ui.renderGui(primaryStage);
        try {
            this.storage = new Storage(filePath);
            loadTaskList();
        } catch (DukeException de) {
            ui.showError(de);
        }
        ui.showMessage(Message.onGreeting(name).fromDuke());
    }

    /**
     * Handle a command.
     *
     * @param command The command to execute.
     */
    public void executeCommand(Command command) throws DukeException {
        ui.showMessage(command.execute(this).fromDuke());
    }
}
