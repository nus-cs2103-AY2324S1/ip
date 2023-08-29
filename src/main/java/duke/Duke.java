package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.util.Scanner;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private static final String DUKE_FILEPATH = "./src/main/data/duke.txt";

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
