package duke.command;

import duke.DukeException;
import duke.management.NotesList;
import duke.management.Storage;
import duke.management.TaskList;
import duke.task.Task;
import duke.task.Todo;

/**
 * TodoCommand Class.
 */
public class TodoCommand extends Command {
    /**
     * Constructor for Todo Command.
     * @param command User command.
     */
    public TodoCommand(String command) {
        super(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Storage storage, TaskList tasks, NotesList notes) {
        String[] commandArr = this.command.split(" ", 2);
        Task todo = new Todo(commandArr[1]);
        tasks.addTask(todo);
        storage.saveTasksToFile(tasks.getTasks());
        String result = "Ren helped you add: \n" + todo.toString() + "\n";
        return result + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateCommand() throws DukeException {
        String[] commandArr = this.command.split(" ", 2);
        if (commandArr.length < 2 || commandArr[1].isEmpty()) {
            throw new DukeException(String.format(DukeException.EMPTY_DESCRIPTION, "todo"));
        }
    }
}
