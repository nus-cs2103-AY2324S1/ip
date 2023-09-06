package chat.commands;

import chat.exceptions.ChatException;
import chat.tasks.TaskList;
import chat.utils.Storage;

/**
 * @author juzzztinsoong
 */
public class MarkDoneCommand extends Command {

    private boolean isDone;
    private int index;

    /**
     * Constructor method for MarkDoneCommand.
     * @param isDone true to mark the task as done, false to unmark it.
     * @param index the index of the task.
     */
    public MarkDoneCommand(boolean isDone, int index) {
        this.isDone = isDone;
        this.index = index;
    }

    @Override
    public String execute(TaskList tasklist, Storage storage) throws ChatException {
        String doneString = tasklist.setDone(isDone, index);
        try {
            storage.writeToFile(tasklist);
            return doneString;
        } catch (ChatException e) {
            return e.getMessage();
        }
    }
}
