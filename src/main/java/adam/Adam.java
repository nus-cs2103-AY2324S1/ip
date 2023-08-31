package adam;

import java.util.ArrayList;

import adam.exception.AdamException;
import adam.command.Command;
import adam.tasks.Task;
public class Adam {
    private Storage storage;
    private TaskList list;
    private Ui ui;

    public Adam() {
        ui = new Ui();
        storage = new Storage();
        try {
            list = new TaskList(storage.read());
        } catch (AdamException e) {
            list = new TaskList(new ArrayList<Task>());
        }

    }
    public void start() {
        boolean isRunning =  true;
        ui.welcome();
            while (isRunning) {
                try {
                    String li = ui.readInput();
                    Command command = Parser.parse(li);
                    command.execute(list, storage, ui);
                    isRunning = list.isRunning();
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
