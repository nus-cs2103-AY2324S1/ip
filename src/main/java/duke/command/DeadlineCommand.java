package duke.command;

import duke.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

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
