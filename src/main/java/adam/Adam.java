package adam;

import java.util.ArrayList;
import adam.exception.AdamException;
import adam.command.Command;
import adam.tasks.Task;

public class Adam {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Adam() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.read());
        } catch (AdamException e) {
            tasks = new TaskList(new ArrayList<Task>());
        }

    }
    public void start() {
        boolean running =  true;
        ui.welcome();
            while (running) {
                try {
                    String li = ui.readInput();
                    Command command = Parser.parse(li);
                    command.execute(tasks,storage,ui);
                    running = tasks.isRunning();
                    }
                catch (AdamException e) {
                    ui.displayError(e.getInfo());
                }
            }
    }

    public static void main(String[] args) {
        Adam test = new Adam();
        test.start();
    }
}
