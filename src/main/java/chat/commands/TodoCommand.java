package chat.commands;

import chat.exceptions.ChatException;
import chat.tasks.TaskList;
import chat.utils.Storage;

/**
 * @author juzzztinsoong
 */
public class TodoCommand extends TaskCommand {

    public TodoCommand(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public void load(TaskList tasklist) {
        tasklist.add(description, isDone);
    }

    @Override
    public String execute(TaskList tasklist, Storage storage) {
        String todoString = tasklist.add(description, isDone);
        try {
            storage.writeToFile(tasklist);
            return String.format(
                "I've added this task:\n%s\nNow you have %d tasks in the list.",
                todoString, tasklist.getSize());
        } catch (ChatException e) {
            return e.getMessage();
        }
    }
}
