package chatbot.commands;

import chatbot.exceptions.ChatBotException;
import chatbot.exceptions.InvalidIndexException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Printer;

public class ShowError extends Command{

    private ChatBotException chatBotException;
    
    public ShowError(String input, CommandType commandType, ChatBotException exception) {
        super(input, commandType);
        this.chatBotException = exception;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Printer ui) 
        throws InvalidIndexException {
            return ui.showError(this.chatBotException);
        }
}
