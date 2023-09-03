package Remy.command;

import Remy.Task.TaskList;
import Remy.Ui;
import Remy.Storage;
import Remy.ChatbotException;
import Remy.Task.Deadline;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Command that creates and adds a Deadline to the TaskList upon executing.
 */
public class DeadlineCommand extends Command {
    private String taskName;
    private String dueDate;
    public static final String COMMAND_WORD = "deadline";

    /**
     * Creates new DeadLine command that parses user input and check that the format is correct.
     * @param input The String submitted by the user to the Chatbot.
     * @throws ChatbotException if input is missing information, or in the wrong format.
     */
    // Modified from addressBook Level 2
    // Source: https://github.com/se-edu/addressbook-level2
    public DeadlineCommand(String input) throws ChatbotException {

        // Define the regex pattern for the "Deadline" command
        String commandPattern = "^deadline\\s+(.+?)\\s+/by\\s+(\\d{4}-\\d{2}-\\d{2})$";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(commandPattern, Pattern.CASE_INSENSITIVE);

        // Create a Matcher to check if the input matches the pattern
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            this.taskName = matcher.group(1);
            this.dueDate = matcher.group(2);
        } else {
            throw new ChatbotException("missing info or wrong format");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Creates a new Deadline object and adds it to the TaskList.
     * @param taskList The TaskList to be acted on.
     * @param ui Handles User interaction.
     * @param storage Handles saving the updated TaskList.
     * @throws ChatbotException if the time information is in the wrong format.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        try {
            Deadline temp = new Deadline(this.taskName, this.dueDate);
            taskList.add(temp);
            storage.save(taskList);
            Ui.printAddedTask(temp, taskList.size());
        } catch (DateTimeParseException e) {
            throw new ChatbotException("You don't know how to write the time isit?: " + e.getMessage());
        }
    }
}
