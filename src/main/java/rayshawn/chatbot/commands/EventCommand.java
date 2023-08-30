package rayshawn.chatbot.commands;

import rayshawn.chatbot.exceptions.ChatBotException;
import rayshawn.chatbot.tasks.Task;
import rayshawn.chatbot.tasks.Event;

/**
 * Adds an event task to the tasklist.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD +
            ": Adds a Event task to the task list.\n" +
            "Parameters: Description /from YYYY-MM-DD HH(AM/PM) /to HH(AM/PM)\n" +
            "Example: " + COMMAND_WORD + " birthday party /from 2023-08-28 06PM /to 10PM";
    private static final String MESSAGE_SUCCESS = "Got it. I've added this task: \n %1$s \n";


    public final Task toAdd;

    /**
     * Constructor for EventCommand
     *
     * @param description description of task
     * @param start date and start time of task
     * @param end end time of task
     * @throws ChatBotException if any of the data is invalid
     */
    public EventCommand(String description, String start, String end) throws ChatBotException {
        String[] temp = start.split(" ");
        this.toAdd = new Event(description, temp[0], temp[1], end);
    }

    @Override
    public CommandResult execute() {
        taskList.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS + getTaskListCount(), toAdd, taskList.getAllTasks()));
    }
}
