package duck;

import java.util.ArrayList;

import duck.command.Command;

import duck.task.Task;
import duck.task.TaskList;

import duck.ui.Ui;

/**
 * Represents the Duck chatbot.
 */
public class Duck {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";

    public Duck() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Initialises the Duck chatbot and attempts to load tasks from storage.
     * 
     * @param filePath The path to the file where the tasks are stored.
     */
    public Duck(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            ArrayList<Task> list = storage.loadTasks();
            this.tasks = new TaskList(list);
        } catch (DuckException e) {
            ui.showErrorMessage(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Duck chatbot.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                System.out.println(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DuckException e) {
                ui.showErrorMessage(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duck(DEFAULT_FILE_PATH).run();
    }

    /**
     * Generates a response to user input.
     * 
     * @param input The user input.
     * @return String The response to the user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input.trim());
            String reply = c.execute(tasks, ui, storage);
            return reply;
        } catch (DuckException e) {
            return e.getMessage();
        }
    }
}
