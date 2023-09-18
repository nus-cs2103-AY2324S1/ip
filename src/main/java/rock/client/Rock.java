package rock.client;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

import rock.client.exceptions.RockException;
import rock.logic.commands.Commands;
import rock.logic.io.Invoker;
import rock.ui.Ui;
import rock.storage.Storage;
import rock.storage.exceptions.StorageException;
import rock.tasks.Task;
import rock.tasks.TaskList;

/**
 * Rock is the name of and the main program used
 * to run the chatbot for ip.
 * @author Alvis Ng (supermii2)
 */
public class Rock {
    private static final Path DEFAULT_FILEPATH = Paths.get("tasks.ser");
    public TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Invoker invoker;
    private boolean isTerminated = false;

    /**
     * Initialises Rock with default file path
     */
    public Rock() {
        this(DEFAULT_FILEPATH);
    }

    /**
     * Initialises Rock
     * @param path Path to save and load Rock data
     */
    public Rock(Path path) {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.invoker = new Invoker(new Commands(this));
        try {
            this.storage = new Storage(path.toAbsolutePath().toFile(), this);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        this.ui.startup();
    }
    private void run() {
        while (!isTerminated) {
            String userInput = this.ui.getInput();
            try {
                ui.respond(invoker.handle(userInput));
            } catch (RockException e) {
                this.ui.respond(e.getMessage());
            }
        }
    }
    public String getResponse(String input) {
        try {
            return invoker.handle(input);
        } catch (RockException e) {
            return e.getMessage();
        }
    }
    /* Methods for Interactions with task list */
    /**
     * Adds the given task to
     * the task list
     * @param task Task to be added
     */
    public void addTask(Task task) {
        taskList.addTask(task);
    }
    /**
     * Removes the task at index i
     * @param i Task to be removed
     * @return Removed task
     * @throws IndexOutOfBoundsException Given index is invalid
     */
    public Task removeTask(int i) throws IndexOutOfBoundsException {
        return taskList.removeTask(i);
    }
    /**
     * Marks given task as the given
     * state completed
     * @param index Index of task to be marked
     * @param completed State to be marked as
     * @throws IllegalArgumentException Task is already the correct state
     * @throws IndexOutOfBoundsException Given index is invalid
     */
    public void markTask(int index, boolean completed) throws IllegalArgumentException, IndexOutOfBoundsException {
        taskList.mark(index, completed);
    }
    /**
     * Set the bots task list as the given task list
     * @param taskList Task list to be set to
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
    /**
     * Clears the task list
     */
    public void resetTaskList() {
        taskList.reset();
    }
    /**
     * Performs a filtered search of the task
     * list with the given predicate
     * @param condiiton Predicate to test against
     * @return The string representation of the found tasks
     */
    public String taskListFilteredSearch(Predicate<Task> condiiton) {
        return taskList.filteredSearch(condiiton);
    }
    /**
     * Gets the string representation
     * of the task list
     * @return String representation
     */
    public String taskListToString() {
        return taskList.toString();
    }
    /* Commands that call the UI */
    /**
     * Sends a message to the user
     * @param msg Message to be sent
     */
    public void say(String msg) {
        ui.say(msg);
    }
    /* Commands that call the save/load feature */
    /**
     * Saves the existing tasklist
    */
    public void saveFile() {
        try {
            storage.saveSaveFile(taskList);
        } catch (StorageException e) {
            ui.say(e.getMessage());
        }
    }
    /**
     * Saves the given task list
     * @param tl Task list to be saved
     */
    public void saveFile(TaskList tl) {
        try {
            storage.saveSaveFile(tl);
        } catch (StorageException e) {
            ui.say(e.getMessage());
        }
    }
    /**
     * Terminates the program
     */
    public void terminate() {
        // Sets necessary fields to closed.
        this.isTerminated = true;
        ui.close();
    }
    public static void main(String[] args) {
        new Rock().run();
    }
}
