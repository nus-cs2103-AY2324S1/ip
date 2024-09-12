package rayshawn.chatbot.commands;

import rayshawn.chatbot.exceptions.ChatBotException;
import rayshawn.chatbot.tasks.Deadline;
import rayshawn.chatbot.tasks.Task;

/**
 * Adds a deadine task to the tasklist.
 */
public class DeadlineCommand extends Command {
    // Adapted from
    // https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/commands/AddCommand.java

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a Deadline task to the task list.\n"
            + "Parameters: Description /by YYYY-MM-DD\n"
            + "Example: " + COMMAND_WORD + " homework /by 2023-08-28";
    public static final String MESSAGE_SUCCESS = "Got it. I've added this task: \n %1$s\n";

    public final Task toAdd;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param description description of task
     * @param date deadline date
     * @throws ChatBotException if any of the data is invalid
     */
    public DeadlineCommand(String description, String date) throws ChatBotException {
        assert description != null : "Description should not be null";
        assert date != null : "Date should not be null";
        this.toAdd = new Deadline(description, date);
    }

    @Override
    public CommandResult execute() {
        assert taskList != null : "Task list should not be null";
        taskList.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS + getTaskListCount(taskList.getAllTasks()),
                toAdd, taskList.getAllTasks()));
    }
}
