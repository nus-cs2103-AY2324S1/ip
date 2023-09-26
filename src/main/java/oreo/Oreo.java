package oreo;

import oreo.command.EditCommand;
import oreo.exception.IllegalCommandException;
import oreo.exception.IllegalDateTimeException;
import oreo.command.Command;
import oreo.storage.Storage;
import oreo.task.TaskList;
import oreo.task.Task;
import oreo.ui.MainWindow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 * This class implements the chatbot Oreo.
 *
 * @author Daniel Loh
 * @version 03/09/2023
 */
public class Oreo {

    /** The GUI of the application */
    private MainWindow ui;

    /** Storage class that handles read write to files */
    private Storage storage;

    /** Stores all tasks that are inputted */
    private TaskList tasks;

    /** Cache for Oreo to store data when transiting between scenes */
    private ArrayList<Object> cache;

    /**
     * Constructor of Oreo chatbot.
     *
     * @param filePath file path to where saved file is and where saved
     *                 task will be written to file for next start up
     *                 of chatbot.
     */
    public Oreo(String filePath, MainWindow gui) {
        this.tasks = new TaskList();
        this.ui = gui;
        gui.setOreo(this);
        this.storage = new Storage(filePath);
        this.cache = new ArrayList();
        gui.startUp();
    }

    /**
     * Handles the start-up process of oreo.
     *
     * @throws IllegalCommandException If user inputs a command that is not accepted.
     * @throws FileNotFoundException If filepath cannot be found.
     */
    public void startUp() throws FileNotFoundException, IllegalDateTimeException {
            storage.readFile(tasks); // reads loaded file
    }

    /**
     * Clears saved tasks and stored files if stored file is corrupt and unreadable.
     */
    public void clearTaskAndFile() {
        storage.clearFile();
        tasks.clearAll();
    }

    /**
     * Generates greeting message for user.
     *
     * @return greet message.
     */
    public String greet() {
        String greetMessage = "Woof! I'm Oreo! How may I help you?\n";
        if (tasks.getNumberOfTask() != 0) {
            return greetMessage
                    + "Welcome back! "
                    + tasks.list();
        } else {
            return greetMessage;
        }
    }

    /**
     * Returns the bye message to user.
     *
     * @return bye message.
     */
    public String sayBye() {
        return "I will be sad to see you go! bye!\n";
    }


    /**
     * Executes given command.
     *
     * @param command command to be executed.
     * @return String response from executed command.
     */
    public String execute(Command command) {
        return command.execute(tasks);
    }

    /**
     * Executes command in Edit Mode.
     *
     * @param command command to be executed.
     * @return String response from executed command.
     * @throws IllegalDateTimeException wrong date time input format.
     */
    public String executeEditMode(Command command) throws IllegalDateTimeException {
        String input = (String) getCache(0);    // gets the cached input from user
        Task oldTask = (Task) getCache(1);      // get the cached task to edit
        int id = Integer.parseInt(input.split(" ")[1]);
        return command.executeEditMode(tasks, id - 1, oldTask);
    }

    /**
     * Gets task upon edit command input.
     *
     * @param editCommand
     * @return Task specified by edit command.
     */
    public Task getTask(EditCommand editCommand) {
        return editCommand.getEditTask();
    }

    /**
     * Cache an Object to an ArrayList.
     *
     * @param toCache object to cache.
     */
    public void cache(Object toCache) {
        cache.add(toCache);
    }

    /**
     * Gets cached object at specified index.
     *
     * @param index of ArrayList.
     * @return Object stored in cache.
     */
    public Object getCache(int index) {
        return cache.get(index);
    }

    /**
     * Clears the cache.
     */
    public void clearCache() {
        cache.clear();
    }

    /**
     * Runs closing process of application.
     *
     * @throws IOException
     */
    public void closeProcess() throws IOException {
        storage.writeFile(tasks);
    }
}