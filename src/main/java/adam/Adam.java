package adam;

import java.util.ArrayList;

import adam.exception.AdamException;
import adam.command.Command;
import adam.tasks.Task;

/**
 * This is the main java class that contains instructions to create the chatbot Tasks.Task manager Adam.Adam.
 */
public class Adam {
    private Storage storage;
    private TaskList list;
    private Ui ui;

    /**
     * Creates an instance of an Adam Object that is used to initialize the other objects.
     */
    public Adam() {
        ui = new Ui();
        storage = new Storage();
        try {
            list = new TaskList(storage.read());
        } catch (AdamException e) {
            list = new TaskList(new ArrayList<Task>());
        }

    }

    /**
     * Starts the program and this method will only end when the program stops running.
     */
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
