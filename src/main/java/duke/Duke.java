package duke;

import java.nio.file.Path;
import java.util.Scanner;

import duke.commands.Command;
import duke.commands.CommandType;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Duke is a chatbot that helps you keep track of your tasks.
 * In this version, Duke is renamed to LilBro.
 */
public class Duke {

    /** The task list that is used to store the user's tasks. */
    private final TaskList taskList = new TaskList();

    /** The storage that is used to save and load the user's tasks. */
    private final Storage storage;

    private final Parser parser = new Parser(this.taskList);

    public Duke() {
        this("data/tasks.txt");
    }

    /**
     * Constructs a new Duke object.
     *
     * @param path The path to the save file.
     */
    public Duke(String path) {
        Ui.greet();
        Ui.println("Checking for a save file...");
        String projectRoot = System.getProperty("user.dir");
        String fullPath = Path.of(projectRoot, path).toString();
        this.storage = new Storage(fullPath);
        try {
            storage.load(this.taskList);
        } catch (DukeException e) {
            Ui.println(e.getMessage());
        }
        Ui.println("OK, ready to roll");
    }

    /**
     * The main method is used to run the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }
    public String greet() {
        return "Hello! I'm LilBro!\nWhat can I do for you?";
    }
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            return command.execute();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Runs the program to interact with the user.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(this.taskList);
        while (true) {
            String input = scanner.nextLine();
            try {
                Command command = parser.parse(input);
                if (command.getCommandType() == CommandType.EXIT) {
                    break;
                }
                command.execute();
            } catch (DukeException e) {
                Ui.println(e.getMessage());
            }
        }

        scanner.close();
        Ui.println("Before you go, let me save your tasks...");
        storage.save(this.taskList);
        Ui.bye();
    }
}
