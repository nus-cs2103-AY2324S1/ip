package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Represents a Duke class that deals with the main logic of the program.
 */
public class Duke {

    private static final String DUKE_FILEPATH = "./src/main/data/duke.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duke. Initializes the Ui, Storage and TaskList.
     *
     * @param filePath path to duke.txt.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program by waiting for user input and responding to it.
     * If user input is "bye", the program will exit.
     */
    public void run() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine().trim();
            try {
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                if (command.isExit()) {
                    break;
                }

            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }

        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke(DUKE_FILEPATH).run();
    }
}
