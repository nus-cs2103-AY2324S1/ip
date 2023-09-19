package jeo;

import java.util.Scanner;

import jeo.command.Command;
import jeo.exception.JeoException;
import jeo.parser.Parser;
import jeo.storage.Storage;
import jeo.task.TaskList;
import jeo.ui.Ui;

/**
 * A chatbot that helps user to maintain their tasks.
 *
 * @author Joseph Oliver Lim
 */
public class Jeo {
    private TaskList tasks = new TaskList();
    private Scanner sc;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs an instance of Je-O chatbot.
     */
    public Jeo() {
        this.sc = new Scanner(System.in);
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.storage.initialize();
            this.tasks = this.storage.readFile();
        } catch (JeoException e) {
            this.ui.errorMessage(e);
        }
    }

    /**
     * Runs the Je-O chatbot.
     */
    public String run(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.tasks, this.ui, this.storage);
        } catch (JeoException e) {
            return this.ui.errorMessage(e);
        }
    }

    /**
     * Gets a response from the Je-O chatbot.
     */
    public String getResponse(String input) {
        return run(input);
    }
}
