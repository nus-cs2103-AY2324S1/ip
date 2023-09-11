package duke.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeBadInputException;
import duke.task.Task;

/**
 * Represents the Deadline command
 */
public class ReminderCommand extends Command {


    /**
     * Stores the range of the reminder
     */
    private int days;

    /**
     * Constructor of the reminder command
     *
     * @param days - the desc of the command
     */
    public ReminderCommand(int days) {
        this.days = days;
    }

    /**
     * Method to encapsulate the execution logic of the command
     *
     * @param taskList - the task list instance  of the current duke
     * @param ui       - the ui instance of DUKE
     * @param storage  - the storage instance to allow the command to write to the storage
     * @return the reply of Quack
     * @throws DukeBadInputException - if the input cannot be used
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeBadInputException {

        // filter out relevant
        List<Task> matches = Arrays.stream(taskList.getAllTask())
                .filter(task -> task.isRemind(this.days))
                .collect(Collectors.toList());

        // Handles no matches
        int size = matches.size();
        if (size == 0) {
            return this.days == 1
                    ? "Quack has not found any deadlines or events today, you are a free quack!"
                    : "Quack has not found any deadlines or events in the next "
                            + this.days + " days, you are a free quack!";
        }

        // Returns the matches
        StringBuilder ret;
        if (this.days == 1) {
            ret = new StringBuilder("Quack has found "

                    + matches.size() + " tasks due today");
        } else {
            ret = new StringBuilder("Quack has found "
                    + matches.size() + " tasks in the next " + this.days + " days");
        }
        for (int i = 0; i < size; i++) {
            ret.append("\n").append(i + 1).append(". ").append(matches.get(i).toString());
        }
        return ret.toString();
    }

    /**
     * Checks if the command is the exit command
     *
     * @return true if it is the exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
