package corgi.commands;

import java.util.Stack;

import corgi.storage.Storage;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.ui.TextRenderer;
import javafx.util.Pair;

public class UndoCommand extends Command {
    /**
     * Initializes a new UndoCommand instance.
     */
    public UndoCommand() {
        super(false);
    }

    /**
     * Executes the command by reverting the previous action based on the provided 
     * history stack and updating the task list accordingly.
     *
     * @param list The task list.
     * @param renderer The text renderer to return formatted message.
     * @param storage The storage for saving and loading tasks.
     * @param history The history stack to store the states.
     * @return A string message indicating the result of the command execution.
     */
    @Override
    public String execute(
        TaskList list, TextRenderer renderer, Storage<Task> storage, Stack<Pair<Command, TaskList>> history) 
        throws CommandExecutionException {
        if (history.isEmpty()) {
            throw new CommandExecutionException("Nothing to undo!");
        }

        Pair<Command, TaskList> prevState = history.pop();
        Command prevCommand = prevState.getKey();
        TaskList prevTaskList = prevState.getValue();

        list = prevTaskList;

        storage.save(list);

        return renderer.showUndoSucceed(prevCommand.toString());
    }
}
