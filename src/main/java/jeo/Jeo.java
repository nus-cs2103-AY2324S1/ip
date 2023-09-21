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
    private boolean systemStatus = true;

    /**
     * Constructs an instance of Je-O chatbot.
     */
    public Jeo() {
        sc = new Scanner(System.in);
        ui = new Ui();
        storage = new Storage();
        try {
            storage.initialize();
            tasks = storage.readFile();
        } catch (JeoException e) {
            ui.errorMessage(e);
        }
    }

    /**
     * Runs the Je-O chatbot.
     *
     * @param input A String representing the user input.
     * @return A String to be shown to the user.
     */
    public String run(String input) {
        try {
            Command command = Parser.parse(input);
            systemStatus = command.getSystemStatus();
            return command.execute(tasks, ui, storage);
        } catch (JeoException e) {
            return ui.errorMessage(e);
        }
    }

    /**
     * Gets a response from the Je-O chatbot.
     *
     * @param input A String representing the user input.
     * @return A String representing the response from the chatbot.
     */
    public String getResponse(String input) {
        return run(input);
    }

    /**
     * Gets the system status of the Je-O chatbot.
     *
     * @return A boolean value representing the system status.
     */
    public boolean getSystemStatus() {
        return systemStatus;
    }
}
