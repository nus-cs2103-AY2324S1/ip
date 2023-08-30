package friday;

import java.util.Scanner;

/**
 * Represents the main class for the Friday application.
 */
public class Friday {

    private TaskList taskList;
    private Storage storage;
    private Scanner input;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a new instance of the Friday application.
     * Initialises the user interface, task list, input scanner, storage, and parser.
     *
     * @param filePath The path to the storage file.
     */
    public Friday(String filePath) {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.input = new Scanner(System.in);
        this.storage = new Storage(filePath);
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        Friday friday = new Friday("data/tasks.txt");
        friday.start();
    }

    /**
     * Starts the Friday application.
     * Greets the user and begins taking and processing user input.
     */
    public void start() {
        ui.greeting();
        parser.echo(input, taskList, ui, storage);
    }


}
