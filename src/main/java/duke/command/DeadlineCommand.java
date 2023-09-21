package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.*;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs a new DeadlineCommand object with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the task in the format "d/M/yyyy HHmm".
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the deadline command, which attempts to create a new Deadline task
     * and adds it to the given task list. It also updates the storage with the new task.
     *
     * @param tasks The task list that contains the list of tasks.
     * @param ui The UI of the application.
     * @param storage The storage that stores task list data.
     * @return A message indicating the result of the execution.
     * @throws DukeException If there is an error with the input or format of the deadline.
     * @throws IOException If there is an error saving the tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (description.isEmpty() || by.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description or deadline of a task cannot be empty.");
        }

        Deadline newDeadline;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
            newDeadline = new Deadline(description, dateTime);
            tasks.add(newDeadline);
            storage.saveTasks(tasks); // Save the updated tasks to file
            return ui.showAddedTask(newDeadline, tasks.size()); // Return the message about the added task
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date-time format! Please use d/M/yyyy HHmm format.");
        } catch (IOException e) {
            return ui.showSavingError(e.getMessage()); // Return the error message
        }
    }
}