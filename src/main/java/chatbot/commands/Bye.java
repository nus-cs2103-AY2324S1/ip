package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

/**
 * Command that exits the chatbot and forces it close.
 */
public class Bye extends Command {

    public Bye(String input) {
        super(input);
    }
    
    /**
     * {@inheritDoc}
     * 
     * Asks UI to print a goodbye message.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.bye();
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
