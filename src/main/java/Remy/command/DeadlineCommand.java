package remy.command;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import remy.ChatbotException;
import remy.Parser;
import remy.Storage;
import remy.Ui;
import remy.task.Deadline;
import remy.task.TaskList;


/**
 * A Command that creates and adds a Deadline to the TaskList upon executing.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private String taskName;
    private String dueDate;
    private String priority;

    /**
     * Creates new DeadLine command that parses user input and check that the format is correct.
     *
     * @param input The String submitted by the user to the Chatbot.
     * @throws ChatbotException if input is missing information, or in the wrong format.
     */
    // Modified from addressBook Level 2
    // Source: https://github.com/se-edu/addressbook-level2
    public DeadlineCommand(String input) throws ChatbotException {

        // Define the regex pattern for the "Deadline" command
        String commandPattern = "^deadline\\s+(.+?)\\s+/by\\s+(\\d{4}-\\d{2}-\\d{2})\\s+/p\\s*(\\w+)?$";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(commandPattern, Pattern.CASE_INSENSITIVE);

        // Create a Matcher to check if the input matches the pattern
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            throw new ChatbotException("missing info or wrong format");
        }

        this.taskName = matcher.group(1);
        this.dueDate = matcher.group(2);

        // Check priority and assign if correct
        // Throws exception if incorrect
        if (Parser.checkValidPriority(matcher.group(3))) {
            this.priority = matcher.group(3);
        }
    }


    /**
     * Creates a new Deadline object and adds it to the TaskList.
     *
     * @param taskList The TaskList to be acted on.
     * @param ui       Handles User interaction.
     * @param storage  Handles saving the updated TaskList.
     * @throws ChatbotException if the time information is in the wrong format.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        try {
            Deadline temp = new Deadline(this.taskName, this.dueDate, this.priority);
            taskList.add(temp);
            storage.save(taskList);
            return Ui.getAddedTaskMessage(temp, taskList.size());
        } catch (DateTimeParseException e) {
            throw new ChatbotException("You don't know how to write the time isit?: " + e.getMessage());
        }
    }
}
