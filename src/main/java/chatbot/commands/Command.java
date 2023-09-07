package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Printer;

/**
 * Abstract class representing a command.
 * 
 * @author Owen Yeo
 */
public abstract class Command {

    protected final String input;
    protected final CommandType commandType;

    /**
     * Constructs a Command instance with a given type and input.
     * 
     * @param input String containing task label and other info.
     * @param commandType type of command instance
     */
    public Command(String input, CommandType commandType) {
        this.input = input;
        this.commandType = commandType;
    }

    /**
     * Modifies the TaskList, Storage, and UI of the ChatBot.
     * 
     * @param tasks TaskList
     * @param storage Storage
     * @param printer Printer
     */
    public String execute(TaskList tasks, Storage storage, Printer printer) {
        return printer.intro();
    }

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
