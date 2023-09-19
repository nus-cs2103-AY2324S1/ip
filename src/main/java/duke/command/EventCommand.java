package duke.command;

import duke.DukeException;
import duke.management.NotesList;
import duke.management.Storage;
import duke.management.TaskList;
import duke.task.Event;
import duke.task.Task;

/**
 * Event Command Class.
 */
public class EventCommand extends Command {
    /**
     * Constructor for Event Command.
     * @param command User command.
     */
    public EventCommand(String command) {
        super(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Storage storage, TaskList tasks, NotesList notes) {
        String[] commandArr = this.command.split(" ", 2);
        String[] eventArr1 = commandArr[1].split(" /from ", 2);
        String[] eventArr2 = eventArr1[1].split(" /to ", 2);
        Task event = new Event(eventArr1[0], eventArr2[0], eventArr2[1]);
        tasks.addTask(event);
        storage.saveTasksToFile(tasks.getTasks());
        String result = "Ren helped you add: \n" + event.toString() + "\n";
        return result + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateCommand() throws DukeException {
        String[] commandArr = this.command.split(" ", 2);
        if (commandArr.length < 2 || commandArr[1].isEmpty()) {
            throw new DukeException(String.format(DukeException.EMPTY_DESCRIPTION, "event"));
        }
    }
}
