package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;


/**
 * Class that handles the event command.
 */
public class EventCommand extends Command {
    private String input;
    public EventCommand(String input) {
        this.input = input;
    }
    /**
     * Executes the command.
     * @param ui the ui class used.
     * @param storage the storage that is used.
     * @param tasks the tasklist that is used.
     * @return String representation of the execution.
     * @throws IOException IOException
     * @throws DukeException DukeException
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws IOException, DukeException {
        if (input.trim().length() <= 5) {
            throw new DukeException("Sorry! The description of a event cannot be empty :(");
        }
        if (!input.contains("/from")) {
            throw new DukeException("Hey bud! Please include when the event is!"
                    + "\nFor example you can type: event hangout /from 2023-09-01 1700 /to 2023-09-01 2000");
        }

        int indexFrom = input.lastIndexOf("/from");
        int indexTo = input.lastIndexOf("/to");

        if ((input.substring(6, indexFrom).isEmpty())) {
            throw new DukeException("Sorry! The description of an event cannot be empty :(");
        }
        if (!input.contains("/to")) {
            throw new DukeException("Hey bud! Please include when the end date of the event is!"
                    + "\nFor example you can type: event hangout /from 2023-09-01 1700 /to 2023-09-01 2000");
        }
        Task task = new Event(input.substring(6, indexFrom - 1),
                input.substring(indexFrom + 6, indexTo - 1), input.substring(indexTo + 4));
        tasks.addTask(task);
        storage.writeTasksToFile(tasks);
        return ui.printAddTaskToList(tasks, task);
    }

}
