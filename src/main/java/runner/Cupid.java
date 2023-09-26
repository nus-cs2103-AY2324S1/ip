package runner;

import java.io.IOException;

import functions.Parser;
import functions.Storage;
import functions.TaskList;
import functions.Ui;

/**
 * Cupid is a task tracking bot that manages a list of tasks and provides responses based on user input.
 */
public class Cupid {

    private String saveFilePath;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a new instance of the Cupid bot with the specified save file path.
     * If the save file exists, the task list is loaded from the file. Otherwise, a new empty task list is created.
     *
     * @param saveFilePath The path to the save file for the task list.
     */
    public Cupid(String saveFilePath) {
        this.saveFilePath = saveFilePath;
        this.ui = new Ui();
        this.taskList = null;
        this.storage = null;

        try {
            this.storage = new Storage(this.saveFilePath);
            this.taskList = this.storage.load();
        } catch (IOException e) {
            this.taskList = new TaskList();
            this.storage.save(this.taskList);
        }

    }

    /**
     * Processes the user input and returns a response from the Cupid bot.
     * The user input is parsed using a Parser object, and the resulting response is returned as a String.
     * After processing the user input, the task list is saved to the storage.
     *
     * @param input The user input to be processed.
     * @return The response from the Cupid bot.
     */
    public String getResponse(String input) {

        Parser parser = new Parser(input, this.taskList);
        String result = parser.parse();

        this.storage.save(this.taskList);
        return result;
    }

}
