package chatbot.commands;

import chatbot.exceptions.ChatBotException;
import chatbot.exceptions.InvalidIndexException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Printer;

/**
 * Displays the error message when executed.
 */
public class ShowError extends Command{

    private ChatBotException chatBotException;
    
    /**
     * {@inheritDoc}
     * 
     * Displays the error when executed.
     * 
     * @param input User input
     * @param commandType Type of command
     * @param exception Exception to be displayed
     */
    public ShowError(String input, CommandType commandType, ChatBotException exception) {
        super(input, commandType);
        this.chatBotException = exception;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Printer ui) 
        throws InvalidIndexException {
            return ui.showError(this.chatBotException);
        }
}
