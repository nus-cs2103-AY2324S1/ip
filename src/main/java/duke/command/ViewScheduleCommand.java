package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * A class that represents the user command to view tasks
 * relevant to user on a date specified.
 */
public class ViewScheduleCommand extends Command {
    private LocalDate date;

    public ViewScheduleCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * A method that executes view schedule command.
     * @params tasks TaskList containing all existing Task objects.
     * @params ui UI interface that is used to print messages to the terminal.
     * @params storage Storage object that houses database of the program.
     * @throws DukeException when command is unable to be executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.viewSchedule(tasks, this.date);
    }
}
