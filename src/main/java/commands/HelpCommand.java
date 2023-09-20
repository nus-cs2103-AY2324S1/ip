package commands;

/**
 * The class for executing a help command to render assistance to user
 */
public class HelpCommand extends Command {

    @Override
    public String execute() {
        String message = "Hello! Please find the following commands available below: \n"
                + "1. Deadline. Syntax: deadline {description} /by {date time} \n"
                + "2. Delete. Syntax: delete {Task Number} \n"
                + "3. Event. Syntax: event {description} /from {date time} /to {date time} \n"
                + "4. Find. Syntax: find {keyword} \n"
                + "5. Help. Syntax: help \n"
                + "6. List. Syntax: list \n"
                + "7. Mark. Syntax: mark {Task Number} \n"
                + "8. ToDo. Syntax: todo {description} \n"
                + "9. Unmark. Syntax: unmark {Task Number} \n";

        return message;
    }
}
