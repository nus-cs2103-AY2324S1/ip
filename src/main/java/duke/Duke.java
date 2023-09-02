package duke;

import java.util.Scanner;

import duke.main.Parser;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * A chatbot that helps to record tasks and store tasks.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    private Scanner sc;

    /**
     * Constructs Duke object.
     */
    public Duke() {
        this.sc = new Scanner(System.in);
        this.tasks = new TaskList();
        this.storage = new Storage(tasks);
        this.parser = new Parser(tasks, storage);
        this.ui = new Ui();
    }

    /**
     * Runs the load function in Storage, called in MainWindow.
     */
    public void loadByDuke() {
        storage.load();
    }

    /**
     * Bridges GUI and Program. Takes in user command scanned by MainWindow and parses it.
     * @param reply
     * @return chatbot's reply
     */
    public String getResponse(String reply) {
        return parser.interact(reply);
    }

}
