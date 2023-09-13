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

    final static String EMPTY_STRING = "";
    final static String WHITE_SPACE = " ";

    /**
     * Returns an int value denoting which action to be performed.
     *
     * @param args is the input taken from the user.
     * @return non-negative int if args is valid input, else -1.
     */
    public static int parse(String args) {
        String[] splitArgs = args.toLowerCase().split(WHITE_SPACE);

        if (splitArgs.length < 2) {
            return getCodeSingleWordCommand(splitArgs[0]);
        }

        String argType = args.toLowerCase().split(WHITE_SPACE)[0];

        return getCodeMultiWordCommand(argType);
    }

    private static int getCodeSingleWordCommand(String arg) {
        final int errorCode = -1;

        if (arg.equals("bye")) {
            //User wishes to exit the program
            return 0;
        } else if (arg.equals("list")) {
            //User wishes to see his listed missions
            return 1;
        } else {
            return errorCode;
        }
    }

    /**
     * Returns an integer representing the command type based on argType.
     *
     * @param argType is the first word of the command input.
     * @return an int that determines Veda should perform.
     */
    private static int getCodeMultiWordCommand(String argType) {
        final int errorCode = -1;

        if (argType.equals("mark")) {
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
        String[] splitArgs = args.split(" ");
        final int length = splitArgs.length;

        if (length < 2) {
            throw new NoDescriptionException("There is no given task index.");
        } else if (length > 2) {
            throw new ExcessiveArgumentException("There are too many arguments.");
        }

        int targetIndex = Integer.parseInt(splitArgs[1]) - 1;

        if (targetIndex < 0) {
            throw new IncorrectInputException("Index of task must be greater than 0.");
        }

        return targetIndex;
    }

    /**
     * Returns a new task based on the command line argument args.
     *
     * @param args is a lowercase String containing the user input.
     * @return a Task from the given args.
     * @throws NoDescriptionException
     */
    public static Task getTask(String args) throws NoDescriptionException {
        final String type = args.split(WHITE_SPACE)[0];
        Task newTask;

        switch (type) {
        case "todo":
            newTask = assignToDoTask(args);
            break;

        case "deadline":
            newTask = assignDeadlineTask(args);
            break;

        case "event":
            newTask = assignEventTask(args);
            break;

        default:
            throw new NoDescriptionException("Wrong input");
        }

        return newTask;
    }

    private static ToDo assignToDoTask(String args) throws NoDescriptionException {
        final String type = "todo";
        final String[] splitArgs = args.split(WHITE_SPACE);

        if (splitArgs.length < 2) {
            throw new NoDescriptionException("");
        }

        final String description = args.replaceFirst(type + WHITE_SPACE, EMPTY_STRING);

        return new ToDo(description);
    }

    private static Deadline assignDeadlineTask(String args) {
        //Expected CL input: deadline <Description> /by <Due date in dd/MM/yyyy HHmm>
        final String type = "deadline";
        final String separator = " /by ";

        if (args.replace(WHITE_SPACE, EMPTY_STRING).equals(type)) {
            throw new IncorrectInputException(
                    "Please input the right order: deadline <Description> /by <due date>");
        }

        String detail = args.replaceFirst(type + WHITE_SPACE, EMPTY_STRING);
        String[] splitDetails = detail.split(separator);

        return new Deadline(splitDetails[0], splitDetails[1]);
    }

    private static Event assignEventTask(String args) {
        final String type = "event";
        final String separatorFrom = " /from ";
        final String separatorTo = " /to ";

        if (args.replace(WHITE_SPACE, EMPTY_STRING).equals(type)) {
            throw new NoDescriptionException("");
        }

        final String detail = args.replaceFirst(type + WHITE_SPACE, EMPTY_STRING); //Remove type
        String[] splitDetails = detail.split(separatorFrom);  //Split remaining args into description + (from and to)

        String description = splitDetails[0];
        String from = splitDetails[1].split(separatorTo)[0]; //Split (from and to) into from and to
        String to = splitDetails[1].split(separatorTo)[1];

        return new Event(description, from, to);
    }

    public static String getKeyword(String arg) {
        //Parse keyword
        final String keyword = arg.toLowerCase().replaceFirst("find ", "");

        return keyword;
    }
}
