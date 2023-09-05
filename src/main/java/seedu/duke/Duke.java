package seedu.duke;

import java.io.IOException;

import seedu.duke.ui.Ui;
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
        this.parser = new Parser(storage);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(storage.readFile(), ui);
            //this.taskList = storage.readFile(this.ui);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Returns response to the GUI.
     *
     * @param userInput users input
     * @return the relevant response to user
     */
    public String getResponse(String userInput) {
        String toReturn = parser.parse(userInput, taskList);
        return toReturn;
    }
}
