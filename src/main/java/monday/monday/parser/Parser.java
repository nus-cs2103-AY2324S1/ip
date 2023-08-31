package monday.monday.parser;

import monday.monday.exception.MondayExceptions;
import monday.monday.ui.Ui;
import monday.task.TaskList;
import monday.task.ToDo;
import monday.task.Event;
import monday.task.Deadline;

public class Parser {

    /**
     * An enumeration of available commands.
     */
    private enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }

    /**
     * Parses the user input and performs the corresponding action.
     *
     * @param userInput the user input to be parsed
     * @return true if the application should continue running, false otherwise
     * @throws MondayExceptions if there are errors related to Monday application
     * @throws IllegalArgumentException if user input is in the wrong format
     */
    public static boolean mondayParser(String userInput, TaskList taskList) throws MondayExceptions {
        String[] input = userInput.split(" ", 2);
        String command = input[0];
        String content = input.length > 1 ? input[1] : null;

        Command commandEnum = Command.valueOf(command.toUpperCase());


        switch (commandEnum) {
        case BYE:
            Ui.exit();
            return false;
        case LIST:
            taskList.displayList();
            break;
        case MARK: {
            if (content == null) {
                throw new MondayExceptions("Mark requires a index to mark the task as completed.");
            }
            int index = Integer.parseInt(content);

            taskList.mark(index);
            break;
        }
        case UNMARK: {
            if (content == null) {
                throw new MondayExceptions("UnMark requires a index to mark the task as uncompleted.");
            }

            int index = Integer.parseInt(content);

            taskList.unMark(index);
            break;
        }
        case TODO:
            if (content == null) {
                throw new MondayExceptions("The description of a todo cannot be empty.\n" +
                        "Usage: todo (task)");
            }

            taskList.addToTask(new ToDo(content));
            break;
        case DEADLINE:
            try {
                if (content == null) {
                    throw new MondayExceptions("The description of a deadline cannot be empty.\n" +
                            "Usage: deadline (task) /by (time)");
                }

                String[] taskDetails = content.split("/by",2);
                String description = taskDetails[0];
                String date = taskDetails[1];

                taskList.addToTask(new Deadline(description.trim(), date.trim()));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Invalid Format. " +
                        "Usage: deadline (task) /by (time)");
            }
            break;
        case EVENT:
            try {
                if (content == null) {
                    throw new MondayExceptions("The description of a event cannot be empty.\n" +
                            "Usage: event (task) /from (start time) /to (end time)");
                }

                String[] taskDetails = content.split("/from", 2);
                String description = taskDetails[0];
                String[] taskTiming = taskDetails[1].split("/to", 2);
                String start = taskTiming[0];
                String end = taskTiming[1];

                taskList.addToTask(new Event(description.trim(),
                        start.trim(),
                        end.trim()));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Invalid Format. " +
                        "Usage: event (task) /from (start time) /to (end time)");
            }
            break;
        case DELETE:
            if (content == null) {
                throw new MondayExceptions("Delete requires a index to delete the task");
            }
            int index = Integer.parseInt(content);

            taskList.delete(index);
            break;
        default:
            throw new MondayExceptions("Sorry, I do not understand what that means.\n" +
                    "Please provide a valid input/command. e.g todo read book");
        }
        return true;
    }
}
