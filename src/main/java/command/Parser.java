package command;

import services.bizerrors.EmptyArgumentException;
import services.bizerrors.InvalidArgumentException;
import services.bizerrors.InvalidCommandException;
import services.bizerrors.JarvisException;
import services.tasklist.ITaskList;

/**
 * Represents a parser that parses the user input and executes the corresponding command.
 */
public class Parser {

    protected ITaskList taskList;

    /**
     * Creates a new Command Parser object with the given TaskList object.
     *
     * @param taskList the TaskList object that stores the list of tasks.
     */
    public Parser(ITaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the user input and executes the corresponding command.
     *
     * @param userInput the user input with the command and arguments.
     * @return the result of the command execution.
     * @throws JarvisException if the command is invalid or the arguments are invalid/empty or
     *                         there are exceptions during execution.
     */
    public String parseAndExecute(String userInput) throws JarvisException {
        String command = userInput.split(" ")[0];
        String arguments = userInput.replaceFirst(command, "").strip();
        return execute(command, arguments);
    }

    /**
     * Executes the command with the given arguments.
     * This is a helper method for parseAndExecute.
     *
     * @param command the command to be executed.
     * @param args    the argument list for the command.
     * @return the result of the command execution.
     * @throws JarvisException if the command is invalid or the arguments are invalid/empty or
     *                         there are exceptions during execution.
     */
    protected String execute(String command, String args) throws JarvisException {
        try {
            CommandType commandType = CommandType.valueOf(command.toUpperCase());

            // go through commands without any arguments.
            if (args.isEmpty()) {
                return executeCommandWithoutArgs(commandType);
            }

            // go through commands with arguments.
            switch (commandType) {
            case FIND:
                return executeFindCommand(args);
            case MARK:
            case UNMARK:
            case DELETE:
                // explicit fall-through
                return executeCommandWithIntegerArgs(commandType, args);
            case TAG:
            case UNTAG:
                // explicit fall-through
                return executeTagCommand(commandType, args);
            case TODO:
            case DEADLINE:
            case EVENT:
                // explicit fall-through
                return executeAddTaskCommand(commandType, args);
            default:
                // the program should never reach this point since all the invalid commands
                // are caught by the first line of this method and thrown as exceptions.
                return null;
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException(command);
        }
    }

    /** Executes the command without any arguments. */
    private String executeCommandWithoutArgs(CommandType command) throws JarvisException {
        switch (command) {
        case LIST:
            return taskList.showAllTasks();
        case EXIT:
            return "Goodbye, sir.\n" + "Shutting down...";
        default:
            // An exception is thrown if the command is not a standalone command.
            throw new EmptyArgumentException(command.name());
        }
    }

    /** Executes the find command with the given keyword. */
    private String executeFindCommand(String keyword) {
        return taskList.findTask(keyword);
    }

    /** Executes the command with the given integer argument. This includes mark, unmark, and delete. */
    private String executeCommandWithIntegerArgs(CommandType command, String args) throws JarvisException {
        try {
            int taskNumber = Integer.parseInt(args);

            switch (command) {
            case MARK:
                return taskList.markDone(taskNumber);
            case UNMARK:
                return taskList.markUndone(taskNumber);
            case DELETE:
                return taskList.deleteTask(taskNumber);
            default:
                // the program should never reach this point.
                return null;
            }
        } catch (NumberFormatException e) {
            // An exception is thrown if the argument is not an integer.
            throw new InvalidArgumentException(command.name());
        }
    }

    /** Executes the add task command with the given description and optional arguments. */
    private String executeAddTaskCommand(CommandType command, String args) throws JarvisException {
        try {
            switch (command) {
            case TODO: {
                return taskList.addTask(args, CommandType.TODO);
            }
            case DEADLINE: {
                String[] varargs = args.split("\\s+/by\\s+");
                String description = varargs[0], by = varargs[1];
                return taskList.addTask(description, CommandType.DEADLINE, by);
            }
            case EVENT: {
                String[] varargs = args.split("\\s+/from\\s+|\\s+/to\\s+");
                String description = varargs[0], from = varargs[1], to = varargs[2];
                return taskList.addTask(description, CommandType.EVENT, from, to);
            }
            default:
                // the program should never reach this point.
                return null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // An exception is thrown if the argument number does not match the command.
            throw new InvalidArgumentException(command.name());
        }
    }

    /** Executes the tag command with the given description and optional arguments. */
    private String executeTagCommand(CommandType command, String args) throws JarvisException {
        try {
            String[] varargs = args.split(" ", 2);
            int taskNumber = Integer.parseInt(varargs[0]);
            String[] tags = varargs[1].split(" ");

            switch (command) {
            case TAG:
                return taskList.addTagsToTask(taskNumber, tags);

            case UNTAG:
                return taskList.deleteTagsFromTask(taskNumber, tags);
            default:
                // the program should never reach this point.
                return null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // An exception is thrown if the argument number does not match the command.
            throw new InvalidArgumentException(command.name());
        }
    }
}
