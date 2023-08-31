package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the main class for the Duke application.
 * It initializes the UI, storage, and task list, and then runs the application.
 */

public class Duke {

//    private Scanner sc = new Scanner(System.in);
//    private final TaskManager taskManager = new TaskManager();

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object.
     *
     * @param filePath The path to the file where tasks are saved and loaded from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    public void run() {
        boolean isExit = false;
        ui.showWelcome();
        Parser parser = new Parser();
        while(!isExit) {
            try {
                String input = ui.readCommand();
                parser.parseAndExecute(input, tasks, ui, storage);
                isExit = parser.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }


}

