import exceptions.IncorrectInputException;
import exceptions.NoDescriptionException;
import exceptions.SavedDataFormatException;
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

    static final String EMPTY_STRING = "";
    static final String WHITE_SPACE = " ";
    static final String ERROR_MESSAGE = "Error in parsing command"; //Used to indicate a logic error in Parser parsing

    /**
     * Retrieve a task based on the taskData input.
     *
     * @param taskData contains the information of the task from the saved file.
     * @return a Task based on the taskData.
     * @throws SavedDataFormatException When the taskData has a formatting error.
     * @throws StringIndexOutOfBoundsException When there is an error in parsing the data.
     */
    public static Task getTaskFromFile(String taskData)
            throws SavedDataFormatException, StringIndexOutOfBoundsException {
        final String splitter = "::";

        char type = taskData.charAt(0);
        String[] descriptions = taskData.split(splitter);

        switch (type) {
        case 'T':
            return new ToDo(descriptions[2], descriptions[1].matches("1"));

        case 'D':
            //Events.Deadline
            return new Deadline(descriptions[2], descriptions[3], descriptions[1].matches("1"));

        case 'E':
            //Events.Event
            return new Event(descriptions[2], descriptions[3], descriptions[1].matches("1"));

        default:
            throw new SavedDataFormatException("The saved data is not properly formatted.");
        }
    }

    /**
     * Returns an int value denoting which action to be performed.
     *
     * @param args is the input taken from the user.
     * @return non-negative int if args is valid input, else -1.
     */
    public static int parse(String args) {
        assert !(args.equals("")) : "args passed into Parser.parse is empty!";
        String[] splitArgs = decomposeIntoWords(args.toLowerCase());

        String argType = splitArgs[0];

        return getCodeFromCommand(argType);
    }

    private static int getCodeFromCommand(String argType) {
        final int errorCode = -1;

        assert !(argType.contains(WHITE_SPACE)) : ERROR_MESSAGE;
        if (argType.equals("bye")) {
            //Exit the program
            return 0;
        } else if (argType.equals("list")) {
            //See listed missions
            return 1;
        } else if (argType.equals("mark")) {
            //Mark task as done
            return 2;
        } else if (argType.equals("unmark")) {
            //Mark task as undone
            return 3;
        } else if (argType.equals("delete")) {
            //Delete task
            return 4;
        } else if (argType.equals("todo") || argType.equals("deadline") || argType.equals("event")) {
            //Add a new task
            return 5;
        } else if (argType.equals("find")) {
            //Find a task by a keyword
            return 6;
        } else if (argType.equals("update")) {
            //Edit task
            return 7;
        } else {
            return errorCode;
        }
    }

    /**
     * Returns the index of the task given in the command line argument args.
     *
     * @param args
     * @return an integer corresponding to the index of the task that we want.
     * @throws NumberFormatException if args does not contain an integer as the second word
     * @throws IncorrectInputException when the user did not input any additional arguments or more arguments
     *         than required.
     */
    public static int getTargetIndex(String args) throws NumberFormatException, IncorrectInputException {
        assert args != null : "args is null!";

        String[] splitArgs = decomposeIntoWords(args);
        final int length = splitArgs.length;

        if (length < 2) {
            throw new NoDescriptionException("There is no given task index.");
        }

        final int offset = -1; //Display list is 1 more greater than index
        int targetIndex = Integer.parseInt(splitArgs[1]) + offset;

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
    public static Task getTask(String args) throws IncorrectInputException {
        final String type = decomposeIntoWords(args)[0];
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
        final String[] splitArgs = decomposeIntoWords(args);

        if (splitArgs.length < 2) {
            throw new NoDescriptionException("");
        }

        final String description = args.replaceFirst(type + WHITE_SPACE, EMPTY_STRING);

        return new ToDo(description);
    }

    private static Deadline assignDeadlineTask(String args) throws IncorrectInputException {
        //Expected CL input: deadline <Description> /by <Due date in dd/MM/yyyy HHmm>
        final String type = "deadline";
        final String separator = " /by ";

        if (args.replace(WHITE_SPACE, EMPTY_STRING).equals(type)) {
            throw new IncorrectInputException(
                    "Please input the right order: deadline <Description> /by <due date>");
        }

        String detail = args.replaceFirst(type + WHITE_SPACE, EMPTY_STRING);
        String[] splitDetails = detail.split(separator);

        if (splitDetails.length < 2) {
            throw new IncorrectInputException("Please input the right order: deadline <Description> /by <due date>");
        }

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
        String[] splitDetails = detail.split(separatorFrom); //Split remaining args into description + (from and to)

        if (splitDetails.length < 2) {
            throw new IncorrectInputException("Correct usage: \n" +
                    "event {description} /from {dd/mm/yyyy HHmm} /to {dd/mm/yyyy HHmm}");
        }

        String description = splitDetails[0];
        String[] timeFromTo = splitDetails[1].split(separatorTo);

        if (timeFromTo.length < 2) {
            throw new IncorrectInputException("Correct usage: \n" +
                    "event {description} /from {dd/mm/yyyy HHmm} /to {dd/mm/yyyy HHmm}");
        }

        String from = timeFromTo[0];
        String to = timeFromTo[1];

        return new Event(description, from, to);
    }

    public static String getKeyword(String arg) {
        assert arg.contains("find ") : "arg does not contain \"find \"!";
        //Parse keyword
        final String keyword = arg.toLowerCase().replaceFirst("find ", EMPTY_STRING);

        return keyword;
    }

    /**
     * Returns a string that does not contain the method type at the front.
     *
     * @param input is the raw user input.
     * @return a string without method type.
     */
    public static String removeMethodType(String input) {
        final int method = parse(input);

        assert method > 1 : "Invalid method call of removeMethodType on single word commands";
        switch (method) {
        case 2:
            return input.replace("mark ", EMPTY_STRING);

        case 3:
            return input.replace("unmark ", EMPTY_STRING);

        case 4:
            return input.replace("delete ", EMPTY_STRING);

        case 5:
            final String type = decomposeIntoWords(input)[0];
            return input.replace(type + WHITE_SPACE, EMPTY_STRING);

        case 6:
            return input.replace("find ", EMPTY_STRING);

        case 7:
            return input.replace("update ", EMPTY_STRING);

        default:
            //No method type
            return input;
        }
    }

    private static String[] decomposeIntoWords(String arg) {
        return arg.split(WHITE_SPACE);
    }
}
