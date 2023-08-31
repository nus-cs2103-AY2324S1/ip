package seedu.duke;

import java.util.Scanner;

/**
 * Encapsulates the Duke class
 * Duke is the chatbot that runs the program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a new Duke instance.
     *
     * @param filePath The relative path to the file used to store the saved data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
            parser = new Parser(storage, tasks, ui);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Executes the chatbot program.
     */
    public void run() {
        ui.printIntro();

        Scanner sc = new Scanner(System.in);
        // Get user command
        String cmd = sc.nextLine();

        // Parse the cmd as long as it is not "bye"
        while (!cmd.equals("bye")) {
            parser.parse(cmd);
            cmd = sc.nextLine();
        }

        ui.printExit();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}