package oscar.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import oscar.essential.ItemList;
import oscar.essential.Storage;
import oscar.exception.OscarException;
import oscar.item.DeadlineTask;
import oscar.item.Task;

/**
 * Command to create a new deadline task.
 */
public class DeadlineCommand extends Command {
    private final String details;

    /**
     * Instantiates a deadline command.
     *
     * @param d Description of deadline task.
     */
    public DeadlineCommand(String d) {
        this.details = d;
    }

    /**
     * Creates a new deadline task and save it to the info list.
     *
     * @param infos   ArrayList of infos.
     * @param storage File loading and saving handler.
     * @return String output of deadline task.
     * @throws OscarException Incorrect format of deadline command.
     */
    @Override
    public String execute(ItemList infos, Storage storage) throws OscarException {
        assert infos != null;
        assert storage != null;
        String[] validatedDetails = validate();
        String description = validatedDetails[0];
        String deadline = validatedDetails[1];
        LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, DATE_TIME_FORMAT);

        Task newDeadline = new DeadlineTask(description, deadlineDateTime);
        infos.add(newDeadline);
        storage.save(infos);
        return "Oscar has added:\n" + newDeadline + "\n\n" + infos.listCount();
    }

    /**
     * Validates details of deadline task.
     * Format: deadline [task] /by yyyy-MM-dd HHmm.
     *
     * @return String array of description and deadline for deadline task.
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
