package buddy;

import buddy.commands.Command;
import buddy.commands.ExitCommand;
import buddy.exceptions.BuddyCommandException;
import buddy.exceptions.BuddyException;
import buddy.utils.Parser;
import buddy.utils.Storage;
import buddy.utils.Ui;

/**
 * The Buddy program is a chatbot named Task Buddy
 * that executes commands to create and edit a tasklist.
 *
 * @author Lim Jin Yin
 */
public class Buddy {
    private static final String FILE_PATH = "./data/tasks.txt";
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private boolean isExit;

    /**
     * The constructor for a Buddy.
     */
    public Buddy() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.readFile());
        this.isExit = false;
    }

    /**
     * The main method to start and run the Buddy application.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        new Buddy().run();
    }

    /**
     * Runs the main program.
     */
    public void run() {
        String input;
        // this.tasks = storage.readFile();

        while (true) {

            try {
                input = ui.readCommand();
                Command command = Parser.parse(input, tasks);
                command.execute(tasks, ui, storage);
            } catch (BuddyCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getGreeting() {
        return this.ui.printGreeting();
    }

    public boolean getExitStatus() {
        return this.isExit;
    }

    /**
     * Retrieves the response of the Task Buddy chatbot.
     *
     * @param input The input string provided by the user.
     */
    public String getResponse(String input) {
        if (isExit) {
            return "";
        }
        String response;
        try {
            Command c = Parser.parse(input, tasks);
            response = c.execute(tasks, ui, storage);
            if (c instanceof ExitCommand) {
                this.isExit = true;
            }
        } catch (BuddyCommandException e) {
            return e.getMessage();
        }
        return response;
    }
}
