package duck;

import java.util.ArrayList;

import duck.command.Command;

import duck.task.Task;
import duck.task.TaskList;

/**
 *  Represents the Duck chatbot.
 */
public class Duck {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

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
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DuckException e) {
                ui.showErrorMessage(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duck("data/tasks.txt").run();
    }
}
