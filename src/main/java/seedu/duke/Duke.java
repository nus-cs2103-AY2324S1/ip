package seedu.duke;

import java.io.IOException;

import seedu.duke.utils.Parser;
import seedu.duke.utils.Storage;
import seedu.duke.utils.TaskList;

/**
 * main Duke class
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /**
     * Duke constructor
     *
     * @param filePath filepath to duke.txt
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.parser = new Parser(this.storage);
        this.ui = new Ui();
        try {
            this.taskList = storage.readFile(this.ui);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * to run the bot
     */
    private void run() {
        ui.printGreet();
        boolean ongoing = true;
        while (ongoing) {
            String userInput = ui.getUserInput();
            ongoing = parser.parse(userInput, taskList);
        }
        ui.printExit();
    }

    /**
     * main function
     * @param args args
     * @throws IOException exception thrown
     */
    public static void main(String[] args) throws IOException {
        Duke main = new Duke("data/duke.txt");
        main.run();
    }
}
