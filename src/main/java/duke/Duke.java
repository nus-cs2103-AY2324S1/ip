package duke;

import duke.command.ByeCommand;
import duke.command.Command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        ui.showWelcome();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String userInput = scanner.nextLine();
                Command command = Parser.parse(userInput); // This interprets the user input and returns the respective command

                command.execute(tasks, ui, storage); // Execute the command

                if (command instanceof ByeCommand) {
                    break;
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showSavingError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Duke("data/tasks.txt").run();
    }
}
