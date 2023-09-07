package monday.monday.parser;

import monday.monday.exception.MondayExceptions;
import monday.monday.ui.Ui;
import monday.task.Deadline;
import monday.task.Event;
import monday.task.TaskList;
import monday.task.ToDo;


/**
 * Parser class is responsible for parsing user input and performing the corresponding actions.
 */
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
        DELETE,
        FIND,
        HI
    }

    /**
     * Parses the user input and performs the corresponding action.
     *
     * @param userInput the user input to be parsed
     * @param taskList the TaskList object to perform actions on
     * @return response from chatbot
     * @throws MondayExceptions if there are errors related to the Monday application
     * @throws IllegalArgumentException if the user input is in the wrong format
     */
    public static String mondayParser(String userInput, TaskList taskList) throws MondayExceptions {
        String[] input = userInput.split(" ", 2);
        String command = input[0];
        String content = input.length > 1 ? input[1] : null;

        Command commandEnum = Command.valueOf(command.toUpperCase());


        switch (commandEnum) {
        case HI:
            return Ui.greet();
        case BYE:
            return Ui.exit();
        case LIST:
            return taskList.displayList();
        case MARK: {
            if (content == null) {
                throw new MondayExceptions("Mark requires a index to mark the task as completed.");
            }
            int index = Integer.parseInt(content);

            return taskList.mark(index);
        }
        case UNMARK: {
            if (content == null) {
                throw new MondayExceptions("UnMark requires a index to mark the task as uncompleted.");
            }

            int index = Integer.parseInt(content);

            return taskList.unMark(index);
        }
        case TODO:
            if (content == null) {
                throw new MondayExceptions("The description of a todo cannot be empty.\n"
                        + "Usage: todo (task)");
            }

            return taskList.addToTask(new ToDo(content));
        case DEADLINE:
            try {
                if (content == null) {
                    throw new MondayExceptions("The description of a deadline cannot be empty.\n"
                            + "Usage: deadline (task) /by (time)");
                }

                String[] taskDetails = content.split("/by", 2);
                String description = taskDetails[0];
                String date = taskDetails[1];

                return taskList.addToTask(new Deadline(description.trim(), date.trim()));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Invalid Format. "
                        + "Usage: deadline (task) /by (time)");
            }
        case EVENT:
            try {
                if (content == null) {
                    throw new MondayExceptions("The description of a event cannot be empty.\n"
                            + "Usage: event (task) /from (start time) /to (end time)");
                }

                String[] taskDetails = content.split("/from", 2);
                String description = taskDetails[0];
                String[] taskTiming = taskDetails[1].split("/to", 2);
                String start = taskTiming[0];
                String end = taskTiming[1];

                return taskList.addToTask(new Event(description.trim(),
                        start.trim(),
                        end.trim()));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Invalid Format. "
                        + "Usage: event (task) /from (start time) /to (end time)");
            }
        case DELETE:
            if (content == null) {
                throw new MondayExceptions("Delete requires a index to delete the task");
            }
            int index = Integer.parseInt(content);

            return taskList.delete(index);
        case FIND:
            if (content == null) {
                throw new MondayExceptions("Find requires a keyword to find the tasks");
            }

            String[] keywordDetails = content.split(" ");

            if (keywordDetails.length > 1) {
                throw new IllegalArgumentException("Invalid Format. "
                        + "Usage: find (keyword), there should only be one keyword.");
            }

            return taskList.find(keywordDetails[0]);
        default:
            throw new MondayExceptions("Sorry, I do not understand what that means.\n"
                    + "Please provide a valid input/command. e.g todo read book");
        }
    }
}
