package duke;

import java.util.Scanner;

/**
 * Main class to run BUTTER.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor for Duke. Initialises the ui, storage and tasks
     * used in for the BUTTER chatbot program.
     *
     * @param filePath the path of the file containing results from previous interactions
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        tasks = new TaskList(storage.loadTasks(), storage, ui);
    }

    /**
     * Runs the main logic of the program. Uses a scanner object to scan user input, and
     * passes it to the parser object for interpretation.
     */
    public void run() {
        this.ui.greeting();

        Parser parser = new Parser(this.storage, this.tasks, this.ui);
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (parser.isGoodbye(input)) {
                break;
            } else {
                parser.parseInput(input);
            }
        }
        this.ui.bye();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
