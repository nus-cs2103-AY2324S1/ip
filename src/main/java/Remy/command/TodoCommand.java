package remy.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import remy.ChatbotException;
import remy.Parser;
import remy.Storage;
import remy.Ui;
import remy.task.TaskList;
import remy.task.Todo;


/**
 * A Command that creates and adds a Todo to the TaskList upon executing.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    /* Define the regular expression pattern */
    public static final Pattern PATTERN = Pattern.compile("todo\\s+(.*?)(?:\\s+/p\\s+(\\S+))?");
    private String taskName;
    private String priority;

    /**
     * Creates new DeadLine command that parses user input and check that the format is correct.
     *
     * @param input The String submitted by the user to the Chatbot.
     * @throws ChatbotException if input is missing information, or in the wrong format.
     */
    public TodoCommand(String input) throws ChatbotException {
        if (input.length() < 6) {
            throw new ChatbotException("missing info lah.");
        }

        // Create a Matcher object
        Matcher matcher = PATTERN.matcher(input);

        if (!matcher.matches()) {
            throw new ChatbotException("wrong format lah. Use todo TASKNAME {/p high/medium/low}.");
        }

        // Extract the task name
        this.taskName = matcher.group(1);

        // Check priority and assign if correct
        // Throw exception if incorrect
        if (Parser.checkValidPriority(matcher.group(2))) {
            this.priority = matcher.group(2);
        }
    }

    /**
     * Creates a Todo instance and adds it to the TaskList.
     *
     * @param taskList The TaskList to be acted on.
     * @param ui       Handles User interaction.
     * @param storage  Handles saving the updated TaskList.
     * @throws ChatbotException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        Todo temp = new Todo(this.taskName, this.priority);
        taskList.add(temp);
        storage.save(taskList);
        return Ui.getAddedTaskMessage(temp, taskList.size());
    }
}
