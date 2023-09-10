package buddy;

import buddy.commands.Command;
import buddy.utils.BuddyException;
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

    /**
     * The constructor for a Buddy.
     */
    public Buddy() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.readFile());
    }

    /**
     * Runs the main program.
     */
    public void run() {
        String input;
        // this.tasks = storage.readFile();

        this.ui.printGreeting();

        while (true) {

            try {
                input = ui.readCommand();
                Command command = Parser.parse(input, tasks);
                command.execute(tasks, ui, storage);
            } catch (BuddyException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Starts up the chatbot.
     * @param args The source input.
     */
    public static void main(String[] args) {
        new Buddy().run();
    }
}
