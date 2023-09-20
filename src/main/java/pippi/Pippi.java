package pippi;

import java.util.ArrayList;

import pippi.parser.Parser;
import pippi.storage.Storage;
import pippi.task.Task;

/**
 * The Pippi class represents the main controller of the Pippi chatbot application.
 *
 * @author Nathan
 */
public class Pippi {
    private ArrayList<Task> tasks;
    /**
     * Constructs a Pippi instance and loading tasks from storage as
     * it is instantiated
     *
     */
    public Pippi() {
        tasks = Storage.readTask();
    }
    public String getResponse(String userInput) {
        return Parser.reply(userInput, tasks);
    }

}
