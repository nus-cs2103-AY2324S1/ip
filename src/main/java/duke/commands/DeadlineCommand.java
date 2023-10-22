package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.TaskList;

import java.util.Date;

/**
 * Represents a Deadline Command to be executed
 */
public class DeadlineCommand extends Command {
    String input;

    public DeadlineCommand(String input, TaskList taskList) {
        super(taskList);
        this.input = input;
    }

    /**
     * create deadline object, splitting the due date by "/" and stripping off the by:
     *
     * @return response String
     * @throws DukeException
     */
    @Override
    public String execute() throws DukeException {
        String[] splitStr = input.trim().split("\\s+");
        inputChecker(splitStr, "deadline");
        String[] deadlineArr = input.split("/by ");
        Date deadline = dateParser(deadlineArr[1]);
        Deadline d = new Deadline(deadlineArr[0].substring(9), deadline);
        taskList.addTask(d);
        return d.addedMessage();
    }
}
