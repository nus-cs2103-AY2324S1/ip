package Remy.command;
import Remy.Task.TaskList;
import Remy.Ui;
import Remy.Storage;
import Remy.ChatbotException;

/**
 * A representation of a Command that parses user input and follows-up with the relevant action.
 */
public abstract class Command {

    /**
     * Whether the Task should trigger the end of the current Chatbot session.
     * @return boolean indicating Exit status.
     */
    public abstract boolean isExit();

    /**
     * Performs the action associated with the Task on the given TaskList.
     * @param tasks The TaskList to be acted on.
     * @param ui Handles User interaction.
     * @param storage Handles saving the updated TaskList.
     * @throws ChatbotException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChatbotException;
}
