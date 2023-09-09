package remy.command;

import remy.ChatbotException;
import remy.Storage;
import remy.Ui;
import remy.task.TaskList;


/**
 * A representation of a Command that parses user input and follows-up with the relevant action.
 */
public abstract class Command {

    /**
     * Whether the Task should trigger the end of the current Chatbot session.
     * False by default, and overriden by the ExitCommand.
     * @return boolean indicating Exit status.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Performs the action associated with the Task on the given TaskList.
     * @param tasks The TaskList to be acted on.
     * @param ui Handles User interaction.
     * @param storage Handles saving the updated TaskList.
     * @throws ChatbotException
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ChatbotException;
}
