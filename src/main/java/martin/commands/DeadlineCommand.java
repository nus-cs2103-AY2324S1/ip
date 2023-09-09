package martin.commands;

import martin.exceptions.EmptyTaskDescriptionException;
import martin.exceptions.MartinException;
import martin.task.Deadline;
import martin.task.Task;

import java.util.ArrayList;

public class DeadlineCommand implements Command {

    private String command;
    private ArrayList<Task> tasks;

    public DeadlineCommand(String command, ArrayList<Task> tasks) {
        this.command = command;
        this.tasks = tasks;
    }

    /**
    * Adds a new Deadline task to the task list and returns a confirmation message.
    * @return String A confirmation message of the added Deadline task.
    **/
    @Override
    public String execute() throws MartinException {
        if (command.length() <= 8) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        String[] parts = command.substring(9).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a deadline or its date cannot be empty.");
        }

        tasks.add(new Deadline(parts[0], parts[1]));
        return "Got it. I've added this task:\n       " + tasks.get(tasks.size() - 1) + "\n     Now you have " + tasks.size() + " tasks in the list.";
    }
}
