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

    protected final String input;
    protected final CommandType commandType;

    public Command(String input, CommandType commandType) {
        this.input = input;
        this.commandType = commandType;
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
    
    /**
     * Getter for commandType. Mainly used for testing.
     * 
     * @return CommandType of the command.
     */
    public CommandType getType() {
        return this.commandType;
    }

}
