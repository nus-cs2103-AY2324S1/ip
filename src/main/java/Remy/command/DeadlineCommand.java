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
 * Creates and adds a Deadline to the TaskList upon execution.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private String taskName;
    private String dueDate;
    private String priority;

    /**
     * Checks that the format of the input is correct
     * Creates new DeadLine command by parsing user input.
     *
     * @param input The String submitted by the user to the Chatbot.
     * @throws ChatbotException if input is missing information, or in the wrong format.
     */
    // Modified from addressBook Level 2
    // Source: https://github.com/se-edu/addressbook-level2
    public DeadlineCommand(String input) throws ChatbotException {

        // Checks if user input matches the deadline format
        String regex = "^deadline\\s+(.+?)\\s+/by\\s+(\\S+)(?:\\s+/p\\s+(\\S+))?$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        // Throws exception if it does not match
        if (!matcher.matches()) {
            throw new ChatbotException("missing info or wrong format lah. "
                    + "Use deadline NAME /by STARTDATE {opt: /p high/medium/low}");
        }

        this.taskName = matcher.group(1);

        // Checks date and assign if correct. Otherwise, throws exception.
        if (Parser.checkValidDate(matcher.group(2))) {
            this.dueDate = matcher.group(2);
        }

        // Checks priority and assign if correct. Otherwise, throws exception.
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
            throw new ChatbotException("You don't know how to write a date isit?: " + e.getMessage());
        }
    }
}
