package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Command that adds the task to the task list.
 */
public class AddCommand implements Command {

    /**
     * Determines the type of task using the latest chat message,
     * Creates a new task with the correct type and adds it to the list.
     *
     * @param tasks The task list to which the new task will be added.
     * @param ui    The user interface used to retrieve the last user message.
     * @return {@code false} as the program should continue running.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws DukeException {
        String userInput = ui.getLastMsg();
        Task task = null;

        // Determine the task type based on the message.
        if (userInput.toLowerCase().startsWith("todo")) {
            task = new Todo(userInput.substring(4).trim());
            tasks.add(task);
        } else if (userInput.toLowerCase().startsWith("deadline")) {
            // Check format
            if (!userInput.contains("/by "))
                throw new DukeException("Please use the format: deadline [description] /by [date]");
            String[] words = userInput.substring(8).split("/by", 2);
            task = new Deadline(words[0].trim(), words[1].trim());
            tasks.add(task);
        } else if (userInput.toLowerCase().startsWith("event")) {
            // Check format
            if (!userInput.contains("/from ") || !userInput.contains("/to "))
                throw new DukeException("Please use the format: event [description] /from [date] /to [date]");
            String[] words = userInput.substring(5).split("/from", 2);
            String description = words[0].trim();
            String[] time = words[1].split("/to");
            String from = time[0].trim();
            String to = time[1].trim();
            task = new Event(description, from, to);
            tasks.add(task);
        }

        ui.respond("Got it. I've added this task:" + "\n" + task.toString() +
                "\n" + "Now you have " + tasks.size() + " tasks in the list");
        return false;
    }
}
