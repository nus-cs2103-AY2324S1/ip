package duke;

import duke.command.Command;
import duke.exception.InvalidIndexException;
import duke.exception.NoSuchCommandException;
import duke.task.TaskList;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.nio.file.Path;

public class Duke {

    /*
        The task list of Duke.
     */
    private TaskList tasks;
    /*
        The interface for interaction with user.
     */
    private Ui ui;
    /*
        The file that is responsible for reading, writing and saving of the tasks.
     */
    private Storage storage;
    /*
        A parser that is used to parse the input of the user.
     */
    private Parser parser;

    /**
     * Constructs a Duke object with the given file path.
     *
     * @param filePath The path of the file that contains the information of the tasks.
     */
    public Duke(Path filePath) {

        this.ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.processFile());
    }

    /**
     * Runs the Duke application to interact with the user until the exit command is entered.
     */
    public void run() {

        ui.welcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(Ui.showLine()); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (NoSuchCommandException e) {
                System.out.println(e);
            } catch (InvalidIndexException e) {
                System.out.println(e);
            } catch (NumberFormatException | StringIndexOutOfBoundsException | DateTimeException e) {

                System.out.println(Ui.showLine());
                System.out.println("\tPlease enter a proper date.");
                System.out.println("\t" + e.getMessage());
                System.out.println();
                System.out.println(Ui.showLine());
            }
        }
        ui.farewell();
    }

    /**
     * The entry point for the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Duke(Paths.get("data", "duke.txt")).run();
    }
}
