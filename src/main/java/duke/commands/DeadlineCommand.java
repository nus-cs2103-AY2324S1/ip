package duke.commands;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.tasks.Deadlines;
import duke.ui.Ui;
import duke.utilities.Storage;
import duke.utilities.TaskList;

/**
 * A command to create a deadline task.
 */
public class DeadlineCommand extends Command {
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;
    protected String input;

    /**
     * Deadline command constructor to create a deadline task.
     *
     * @param input The input for the command.
     * @param taskList The taskList to store the task.
     * @param storage The storage system to store the list.
     * @param ui The ui to print the commands.
     */
    public DeadlineCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        this.input = input;
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Checks whether the timing of the event is valid by comparing the deadline with
     * the current time
     *
     * @param deadline the deadline of the task
     * @return whether the timing is after current time, and hence whether its valid
     */
    public boolean isValid(LocalDateTime deadline) {
        return LocalDateTime.now().compareTo(deadline) < 0;
    }

    /**
     * Execute the command itself.
     *
     * @throws DukeException When there is an error saving.
     */
    @Override
    public String execute() throws DukeException {
        String task = "deadline";

        try {
            Deadlines newTask = new Deadlines(getDescription(task, input), getEndDate(task, input));

            if (!isValid(newTask.getDeadline())) {
                throw new DukeException("Invalid event date entered. Timing must be after current timing");
            }

            taskList.addTask(newTask);
            assert taskList.contains(newTask);
            storage.save(taskList);
            return ui.printAddTask(taskList, newTask);
        } catch (IOException e) {
            return ui.printError(e.getMessage());
        } catch (DateTimeParseException e) {
            return ui.printInvalidTimeError();
        } catch (DukeException e) {
            return ui.printError(e.getMessage());
        }
    }
}
