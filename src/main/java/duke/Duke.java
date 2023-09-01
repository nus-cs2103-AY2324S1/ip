package duke;

import duke.command.ByeCommand;
import duke.command.Command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 * The main Duke class that represents the chatbot.
 * It initializes the application and interacts with the user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object and initializes the UI, Storage, and TaskList.
     *
     * @param filePath The path to the file where tasks are saved and loaded.
     * @throws FileNotFoundException If the file is not found.
     */
    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        ui.showWelcome();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    /**
     * Runs the Duke chatbot. It reads user input and executes the appropriate commands.
     * The method continues running until a ByeCommand is received.
     */
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

    /**
     * The main method for the Duke chatbot application.
     *
     * @param args Command line arguments.
     * @throws FileNotFoundException If the file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Duke("data/tasks.txt").run();
    }
}
