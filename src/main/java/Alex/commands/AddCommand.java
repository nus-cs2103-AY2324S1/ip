package Alex.commands;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Alex.AlexException;
import Alex.tasks.*;
import Alex.ui.Ui;



/**
 * A class that extends from Command class. It represents a command that is trying to add task to the TaskList.
 * The instance(s) of this class can be a command trying to add a todo task, deadline task and event task.
 */
public class AddCommand extends Command {
    private String command;
    private Add addType;

    /**
     * The constructor of AddCommand when an user input string and addType is passed.
     *
     * @param command The user input string.
     * @param addType The type of add command which consist of "TODO", "DEADLINE" and "EVENT".
     */
    public AddCommand(String command, Add addType) {
        this.command = command;
        this.addType = addType;
    }

    /**
     * Override the method from the abstract Command class.
     * The execute method of AddCommand class is to actually add the task specified by user to the TaskList.
     */
    @Override
    public String execute() {
        String output = null;

        switch(this.addType) {
        case TODO:
            output = executeTodo();
            break;

        case DEADLINE:
            output = executeDeadline();
            break;

        case EVENT:
            output = executeEvent();
            break;
        }

        return output;
    }

    private String executeTodo() {
        String output = "";
        try {
            if (!command.substring(4, 5).equals(" ")) {
                String message = "OOPS!!! Please enter a todo task in the following format:\n"
                        + "   " + "todo (description)";
                throw new AlexException(message);
            }
            assert command.substring(4, 5).equals(" ") : "There should be space after todo";
            Task todo = new ToDos(command.substring(5));
            output = TaskList.store(todo);
            return output;
        } catch (IndexOutOfBoundsException e) {
            output = Ui.getAlertForTodo();
            return output;
        } catch (AlexException e) {
            output = e.getMessage();
            return output;
        }
    }

    private String executeDeadline() {
        String output = "";
        try {
            String regex = "\\b /by \\b";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(command);
            if (!matcher.find()) {
                String message = "Please enter a deadline task in the format: "
                        + "deadline (description) /by yyyy-MM-dd HHmm";
                throw new AlexException(message);
            }
            int startIndex = matcher.start();
            int endIndex = matcher.end();
            String description = startIndex > 9 ? command.substring(9, startIndex) : "";
            String by = command.substring(endIndex);
            Task deadline = new Deadline(description, by);
            output = TaskList.store(deadline);

            return output;
        } catch (AlexException e) {
            output = e.getMessage();
            return output;
        } catch (DateTimeParseException e) {
            output = Ui.getAlertForDeadline();
            return output;
        }

    }

    private String executeEvent() {
        String output = "";
        try {
            String regex = "\\b /from \\b";
            Pattern pattern1 = Pattern.compile(regex);
            Matcher matcher1 = pattern1.matcher(command);
            if (!matcher1.find()) {
                String message = "Please enter an event task in the format: "
                        + "event (description) /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm";
                throw new AlexException(message);
            }
            int firstStart = matcher1.start();
            int firstEnd = matcher1.end();

            String regex2 = "\\b /to \\b";
            Pattern pattern2 = Pattern.compile(regex2);
            Matcher matcher2 = pattern2.matcher(command);
            if (!matcher2.find()) {
                String message = "Please enter an event task in the format: "
                        + "event (description) /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm";
                throw new AlexException(message);
            }
            int secondStart = matcher2.start();
            int secondEnd = matcher2.end();

            String description = firstStart > 6 ? command.substring(6, firstStart) : "";
            String fromTime = command.substring(firstEnd, secondStart);
            String toTime = command.substring(secondEnd);

            Task event = new Event(description, fromTime, toTime);
            output = TaskList.store(event);
            return output;
        } catch (AlexException e) {
            output = e.getMessage();
            return output;
        } catch (DateTimeParseException e) {
            output = Ui.getAlertForEvent();
            return output;
        }
    }

}
