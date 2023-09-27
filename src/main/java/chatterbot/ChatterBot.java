package chatterbot;

import chatterbot.data.TaskList;
import chatterbot.parser.Parser;
import chatterbot.storage.Storage;
import chatterbot.ui.Ui;

/**
 * Represents the chatbot that is able to maintain a list of tasks.
 */
public class ChatterBot {

    protected static String file = "data/ChatterBot.txt";
    protected Ui ui;
    protected Storage storage;
    protected TaskList taskList;

    public ChatterBot(TaskList taskList) {
        this.taskList = taskList;
        this.ui = new Ui(taskList);
        this.storage = new Storage();

        taskList.initiateTaskList(storage);
    }

    /**
     * This method passes in the input, for it to be evaluated.
     * @param input This is the input by the user.
     * @return String This is what will be returned to the user.
     */
    public String getResponse(String input) {
        return Parser.evaluateCommand(input, ui, storage, taskList);
    }
}