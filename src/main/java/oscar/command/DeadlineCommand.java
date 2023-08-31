package oscar.command;

import oscar.essential.Storage;
import oscar.essential.TaskList;

import oscar.exception.OscarException;

import oscar.task.Deadline;
import oscar.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Command to create a new deadline task.
 */
public class DeadlineCommand extends Command {
    private final String details;

    /**
     * Instantiates a deadline command.
     * @param details Description of deadline task.
     */
    public DeadlineCommand(String details) {
        super(false);
        this.details = details;
    }

    /**
     * Creates a new deadline task and save it to the task list.
     * @param tasks ArrayList of tasks.
     * @param storage File loading and saving handler.
     * @throws OscarException Incorrect format of deadline command.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws OscarException {
        String[] validatedDetails = validate();
        String description = validatedDetails[0];
        String deadline = validatedDetails[1];
        LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, DATE_TIME_FORMAT);
        Task newDeadline = new Deadline(description, deadlineDateTime);
        tasks.add(newDeadline);
        storage.save(tasks);
        System.out.println("Oscar has added:\n" + newDeadline + "\n");
        tasks.listCount();
    }

    /**
     * Validates details of deadline task.
     * Format: deadline [task] /by yyyy-MM-dd HHmm
     * @throws OscarException Incorrect format of deadline command.
     */
    public String[] validate() throws OscarException {
        if (!details.contains(" /by ")) {
            throw new OscarException("Sorry! "
                    + "The deadline task is not formatted correctly.\n"
                    + "Please use the format: 'deadline [task] /by yyyy-MM-dd HHmm'.\n");
        }
        String[] splitDetails = details.split(" /by ", 2);
        String description = splitDetails[0];
        if (description.isEmpty()) {
            throw new OscarException("Sorry! "
                    + "The description of a deadline task cannot be empty.\n");
        }
        String deadline = splitDetails[1];
        if (!deadline.contains(" ")) {
            throw new OscarException("Sorry! "
                    + "Please enter a valid date and time in this format: '2019-10-15 1800'.\n");
        }
        try {
            LocalDateTime.parse(deadline, DATE_TIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new OscarException("Sorry! "
                    + "Please enter a valid date and time in this format: '2019-10-15 1800'.\n");
        }
        return new String[]{description, deadline};
    }

}
