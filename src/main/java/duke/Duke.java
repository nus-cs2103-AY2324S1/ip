package duke;

import java.util.Scanner;

/**
 * This class encapsulates the main program that will run.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();

    /**
     * Constructor for Duke
     *
     * @param filePath the path of the file for the Storage object to load from.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
            Ui.showTaskList(tasks.taskArray, false);
            Ui.horizontalLine();
        } catch (DukeException e) {
            Ui.showExceptionError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.welcomeMessage();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            Parser parser = new Parser(userInput, this.tasks, this.storage);
            parser.parse();
            if (parser.isBye()) {
                break;
            }
        }

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
