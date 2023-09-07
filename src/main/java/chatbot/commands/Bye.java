package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Printer;

/**
 * Command that exits the chatbot and forces it close.
 */
public class Bye extends Command {

    public Bye(String input, CommandType commandType) {
        super(input, commandType);
    }
    
    /**
     * {@inheritDoc}
     * 
     * Asks UI to print a goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Printer ui) {
        return ui.bye();
    }

    /**
     * {@inheritDoc}
     * 
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
