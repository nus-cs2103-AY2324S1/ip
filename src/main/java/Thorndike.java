import java.io.IOException;
import java.util.Scanner;

import command.CmdBye;
import command.Command;
import command.CommandParser;
import exceptions.InvalidCommandException;
import exceptions.ThorndikeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import utility.TextFileHandler;

/**
 * Thorndike - A simple chatbot for managing tasks and user interactions.
 *
 * @author Ho Khee Wei
 */
public class Thorndike {
    private Scanner scanner;
    private TaskList taskList;
    private Boolean isRunning;
    private Ui ui;

    /**
     * Constructs a Thorndike chatbot and initializes necessary components.
     * It sets up the user interface, task list, and handles potential file I/O
     * exceptions.
     */
    public Thorndike() {
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        this.ui = new Ui();

        try {
            TextFileHandler.createFile(Storage.TASK_FILE_PATH);
            this.taskList = Storage.readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the Thorndike chatbot and enters the main interaction loop.
     * It displays a greeting message and listens for user commands until the
     * chatbot is terminated.
     */
    public void start() {
        greet();
        while (isRunning) {
            try {
                listen();
            } catch (ThorndikeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    private void listen() throws ThorndikeException {
        System.out.print(">> ");
        String input = scanner.nextLine();
        Command command = CommandParser.parse(input);

        if (command == null) {
            throw new InvalidCommandException();
        }

        if (command instanceof CmdBye) {
            this.isRunning = false;
        }

        command.execute(taskList, ui);
    }

    private void greet() {
        ui.print("Meow! I'm Thorndike.");
        ui.print("What can I do for you?");
    }
}