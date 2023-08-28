package spot.command;

import java.time.LocalDate;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;

public class ListTasksOnCommand extends Command {

    private LocalDate date;

    /**
     * Constructs a new ListTasksOnCommand object.
     *
     * @param date Specified date of tasks to list.
     */
    public ListTasksOnCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the ListTasksOnCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui object.
     * @param storage Current Storage object.
     * @throws SpotException If there are any errors when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(ui, tasks, date);
    }

    /**
     * Checks if the ListTasksOnCommand is an ExitCommand.
     *
     * @return Boolean representing whether the ListTasksOnCommand is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
