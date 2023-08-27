
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }
    public void start() throws DukeException {
        ui.showWelcomeMessage();
        while (true) {
            String command = ui.getUserInput();
            if (command.equalsIgnoreCase("bye")) {
                ui.showGoodbyeMessage();
                ui.closeScanner();
                break;
            } else {
                Parser.parse(command, tasks);
            }
        }
        storage.saveTasks(tasks.getTasks());
    }


    public static void main(String[] args) throws DukeException {
       new Duke().start();
    }
}

