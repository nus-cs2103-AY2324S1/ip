package duke.commands;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.tasks.Events;
import duke.ui.Ui;
import duke.utilities.EventList;
import duke.utilities.Storage;
import duke.utilities.TaskList;

/**
 * A command to called to initialize an event.
 */
public class EventCommand extends Command {
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;
    protected String input;

    /**
     * Constructor for an event command.
     *
     * @param input The input for the command.
     * @param taskList The taskList to store the task.
     * @param storage The storage system to store the list.
     * @param ui The ui to print the commands.
     */
    public EventCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        this.input = input;
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * A method to detect whether there is a clash between event dates.
     *
     * @param tasks The current list of tasks.
     * @param startDate StartDate of event.
     * @param endDate EndDate of event.
     * @return Whether it is an anomaly.
     */
    public boolean isClash(TaskList tasks, LocalDateTime startDate, LocalDateTime endDate) {
        boolean isClash = false;
        EventList eventList = new EventList(tasks);
        eventList.sort();
        for (Events event : eventList.getEventList()) {
            if ((startDate.compareTo(event.getStartDate()) > 0
                    && startDate.compareTo(event.getEndDate()) < 0)
                    || (endDate.compareTo(event.getStartDate()) > 0
                    && endDate.compareTo(event.getEndDate()) < 0)) {
                isClash = true;
                break;
            }
        }
        return isClash;
    }

    /**
     * Checks whether the timing of the event is valid by comparing the start time with
     * the current time
     *
     * @param startTime the starting time of the event
     * @return whether the timing is after current time, and hence whether its valid
     */
    public boolean isValid(LocalDateTime startTime) {
        return LocalDateTime.now().compareTo(startTime) < 0;
    }

    /**
     * Executes the method to create the event task.
     *
     * @throws DukeException Throws exception if there is an error parsing the date or saving the file.
     */
    @Override
    public String execute() throws DukeException {
        String task = "event";

        try {
            Events newTask = new Events(getDescription(task, input),
                    getStartDate(input), getEndDate(task, input));

            if (isClash(taskList, newTask.getStartDate(), newTask.getEndDate())) {
                throw new DukeException("Invalid event date entered. There is a clash with "
                        + "another event.");
            }

            if (!isValid(newTask.getStartDate())) {
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
