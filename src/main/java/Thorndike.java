import exceptions.*;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import java.util.Scanner;
import command.CmdBye;
import command.Command;
import command.CommandParser;
import java.io.IOException;
import utility.TextFileHandler;

public class Thorndike {
    private Scanner scanner;
    private TaskList taskList;
    private Boolean running;
    private Ui ui;

    public Thorndike() {
        this.scanner = new Scanner(System.in);
        this.running = true;
        this.ui = new Ui();

        try {
            TextFileHandler.createFile(Storage.TASK_FILE_PATH);
            this.taskList = Storage.readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the chatbot.
     */
    public void start() {
        greet();
        while (running) {
            try {
                listen();
            } catch (ThorndikeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    /**
     * Listens to command given to user.
     */
    private void listen() throws ThorndikeException {
        System.out.print(">> ");
        String input = scanner.nextLine();
        Command command = CommandParser.parse(input);

        if (command == null) {
            throw new InvalidCommandException();
        }

        if (command instanceof CmdBye) {
            this.running = false;
        }

        command.execute(taskList, ui);
    }

    /**
     * Sends greetings to user.
     */
    private void greet() {
        ui.print("Meow! I'm Thorndike.");
        ui.print("What can I do for you?");
    }
}