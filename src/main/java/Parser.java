import exceptions.ExcessiveArgumentException;
import exceptions.IncorrectInputException;
import exceptions.NoDescriptionException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;


/**
 * Parser makes sense of the user command.
 *
 * @author Sebastian Tay
 */
public class Parser {

    /**
     * Returns an int value denoting which action to be performed.
     *
     * @param args is the input taken from the user.
     * @return non-negative int if args is valid input, else -1.
     */
    public static int parse(String args) {
        String argType = args.toLowerCase().split(" ")[0];

        return getCode(argType);
    }

    /**
     * Returns an integer representing the command type based on argType.
     *
     * @param argType is the first word of the command input.
     * @return an int that determines Veda should perform.
     */
    private static int getCode(String argType) {
        final int errorCode = -1;

        if (argType.equals("bye")) {
            //User wishes to exit the program
            return 0;
        } else if (argType.equals("list")) {
            //User wishes to see his listed missions
            return 1;
        } else if (argType.equals("mark")) {
            //User wishes to mark task as done
            return 2;
        } else if (argType.equals("unmark")) {
            //User wishes to mark task as undone
            return 3;
        } else if (argType.equals("delete")) {
            //User wishes to delete a task
            return 4;
        } else if (argType.equals("todo") || argType.equals("deadline") || argType.equals("event")) {
            //User wishes to add a new task
            return 5;
        } else if (argType.equals("find")) {
            //User wishes to find a task by a keyword
            return 6;
        }

        return errorCode;
    }

    /**
     * Returns the index of the task given in the command line argument args.
     *
     * @param args
     * @return an integer corresponding to the index of the task that we want.
     * @throws NumberFormatException
     * @throws IncorrectInputException when the user did not input any additional arguments or more arguments than
     * required.
     */
    public static int getTargetIndex(String args) throws NumberFormatException, IncorrectInputException {
        String[] splittedArg = args.split(" ");
        final int length = splittedArg.length;

        if (length < 2) {
            throw new NoDescriptionException("There is no given task index.");
        } else if (length > 2) {
            throw new ExcessiveArgumentException("There are too many arguments.");
        }

        int targetIndex = Integer.parseInt(splittedArg[1]) - 1;

        if (targetIndex < 0) {
            throw new IncorrectInputException("Index of task must be greater than 0.");
        }

        return targetIndex;
    }

    /**
     * Returns a new task based on the command line argument args.
     *
     * @param args
     * @return a Task from the given args.
     * @throws NoDescriptionException
     */
    public static Task getTask(String args) throws NoDescriptionException {
        String type = args.split(" ")[0].toLowerCase();
        Task newTask = null;
        String description = "";
        String[] descriptions = null; //For multiple arguments

        switch (type) {
        case "todo":
            description = args.replaceFirst("todo ", "");

            if (description.toLowerCase() == type) {
                throw new NoDescriptionException("");
            }

            newTask = new ToDo(description);
            break;

        case "deadline":
            //Expected CL input: deadline <Description> /by <Due date in dd/MM/yyyy HHmm>
            //TODO add error handling for no "/by" keyword
            if (description.toLowerCase() == type) {
                throw new NoDescriptionException(
                        "Please input the right order: deadline <Description> /by <due date>");
            }

            description = args.replaceFirst("deadline ", "");
            descriptions = description.split(" /by ");

            if (descriptions.length < 2) {
                throw new IncorrectInputException(
                        "Please input the right order: deadline <Description> /by <due date>");
            }

            newTask = new Deadline(descriptions[0], descriptions[1]);
            break;

        case "event":
            //Expected CL input: event <Description> /from <start> /to <end>
            if (description.toLowerCase() == type) {
                throw new NoDescriptionException("");
            }

            description = args.replaceFirst("event ", ""); //Remove type

            descriptions = description.split(" /from "); //Split remaining args into description + (from and to)
            String from = descriptions[1].split(" /to ")[0]; //Split (from and to) into from and to
            String to = descriptions[1].split(" /to ")[1];

            newTask = new Event(descriptions[0], from, to);
            break;

        default:
            break;
        }

        return newTask;
    }

    public static String getKeyword(String arg) {
        //Parse keyword
        final String keyword = arg.toLowerCase().replaceFirst("find ", "");

        return keyword;
    }
}
