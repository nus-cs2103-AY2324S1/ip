package Forgotten;

import Forgotten.Parser.Parser;
import Forgotten.Ui.Ui;
import Forgotten.Storage.Storage;
import Forgotten.TaskList.TaskList;

import java.io.FileNotFoundException;

/**
 * Encapsulates the chatbot.
 */
public class Forgotten {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Default constructor for this chabot.
     *
     * @param filePath This is the file path to which the tasks will be stored at.
     */
    public Forgotten(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.loadTask(), this.storage);
        } catch (FileNotFoundException fileException) {
            storage.createNewFile();
        }
    }

    public String generateResponse(String userCommand) {
        return Parser.parse(userCommand, this.taskList, this.ui);
    }
}
