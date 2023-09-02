package duck;

import java.util.ArrayList;

import duck.command.Command;

import duck.task.Task;
import duck.task.TaskList;

public class Duck {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

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
