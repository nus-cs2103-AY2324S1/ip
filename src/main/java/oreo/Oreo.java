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
    private MainWindow ui;

    private Storage storage;

    private TaskList tasks;

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
     * Handles the main logic of the chatbot.
     *
     * @throws IllegalCommandException If user inputs a command that
     *                                 is not accepted.
     */
    public void startUp() throws FileNotFoundException, IllegalDateTimeException {
            storage.readFile(tasks); // reads loaded file
    }
    public void clearTaskAndFile() {
        storage.clearFile();
        tasks.clearAll();
    }

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
    public String sayBye() {
        return "I will be sad to see you go! bye!\n";
    }

    public String execute(Command command) {
        return command.execute(tasks);
    }

    public String executeEditMode(Command command) throws IllegalDateTimeException {
        String input = (String) getCache(0);
        Task oldTask = (Task) getCache(1);
        int id = Integer.parseInt(input.split(" ")[1]);
        return command.executeEditMode(tasks, id - 1, oldTask);
    }

    public Task getTask(EditCommand editCommand) {
        return editCommand.getEditTask();
    }

    public void cache(Object toCache) {
        cache.add(toCache);
    }

    public Object getCache(int index) {
        return cache.get(index);
    }

    public void clearCache() {
        cache.clear();
    }

    public void closeProcess() throws IOException {
        storage.writeFile(tasks);
    }
}