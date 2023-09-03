import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;
import VedaExceptions.ExcessiveArgumentException;
import VedaExceptions.IncorrectInputException;
import VedaExceptions.NoDescriptionException;

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
     * @return non-negative int if args is valid input, else -1/.
     */
    public static int parse(String args) {
        args = args.toLowerCase();

        if (args.equals("bye")) {
            //User wishes to exit the program
            return 0;

        } else if (args.equals("list")) {
            //User wishes to see his listed missions
            return 1;
        }

        args = args.split(" ")[0];

        if (args.equals("mark")) {
            //User wishes to mark task as done
            return 2;
        } else if (args.equals("unmark")) {
            //User wishes to mark task as undone
            return 3;
        } else if (args.equals("delete")) {
            //User wishes to delete a task
            return 4;
        } else if (args.equals("todo") || args.equals("deadline") || args.equals("event")) {
            //User wishes to add a new task
            return 5;
        } else if (args.equals("find")) {
            //User wishes to find a task by a keyword
            return 6;
        }


        return -1;
    }

    /**
     * Returns the index of the task given in the command line argument args.
     *
     * @param args
     * @return
     * @throws NumberFormatException
     * @throws NoDescriptionException when the user did not input any additional arguments.
     * @throws ExcessiveArgumentException when the user input more arguments than required.
     */
    public static int getTargetIndex(String args)
            throws NumberFormatException, NoDescriptionException, ExcessiveArgumentException, IncorrectInputException {
        if (args.split(" ").length < 2) {
            throw new NoDescriptionException("There is no given task index.");
        } else if (args.split(" ").length > 2) {
            throw new ExcessiveArgumentException("There are too many arguments.");
        }

        int targetIndex = Integer.parseInt(args.toLowerCase().split(" ")[1]) - 1;

        if (targetIndex < 0) {
            throw new IncorrectInputException("Index of task must be greater than 0.");
        }

        return targetIndex;
    }

    /**
     * Returns a new task based on the command line argument args.
     *
     * @param args
     * @return
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
                    throw new NoDescriptionException("Please input the right order: deadline <Description> /by <due date>");
                }

                description = args.replaceFirst("deadline ", "");
                descriptions = description.split(" /by ");

                if (descriptions.length < 2) {
                    throw new IncorrectInputException("Please input the right order: deadline <Description> /by <due date>");
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
        final String KEYWORD = arg.toLowerCase().replaceFirst("find ", "");

        return KEYWORD;
    }
}
