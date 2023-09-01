package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

/**
 * Abstract class representing a command.
 * 
 * @author Owen Yeo
 */
public abstract class Command {

    protected String input;

    public Command(String input) {
        this.input = input;
    }

    /**
     * Modifies the TaskList, Storage, and UI of the ChatBot.
     * 
     * @param tasks TaskList
     * @param storage Storage
     * @param ui UI
     */
    public void execute(TaskList tasks, Storage storage, Ui ui) {}

    /**
     * Checks if the current command will cause an exit.
     * 
     * @return false
     */
    public boolean isExit() {return false;}

}
