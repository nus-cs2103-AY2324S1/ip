package gui;

import commands.Command;
import exceptions.FishronException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The main class for the Fishron task management application.
 */
public class Fishron {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initializes a new instance of the Fishron class with a default file path.
     */
    public Fishron() {
        this.ui = new Ui();
        this.storage = new Storage("./data/fishron.txt");
        this.taskList = storage.loadTasksFromFile();
    }

    /**
     * Generates a response to user input by parsing and executing a command.
     *
     * @param input The user's input message.
     * @return The response message generated by the application.
     */
    protected String getResponse(String input) {
        try {
            Command command = Parser.parse(input, taskList);
            String output = command.execute(taskList, ui, storage);
            return output;
        } catch (FishronException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }
}
