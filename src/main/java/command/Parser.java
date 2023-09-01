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

    public Parser(ITaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the user input and executes the corresponding command.
     *
     * @param userInput the user input with the command and arguments.
     * @throws JarvisException if the command is invalid or the arguments are invalid/empty or
     *                         there are exceptions during execution.
     */
    public void parseAndExecute(String userInput) throws JarvisException {
        String command = userInput.split(" ")[0];
        String arguments = userInput.replaceFirst(command, "").strip();
        execute(command, arguments);
    }

    /**
     * Executes the command with the given arguments.
     * This is a helper method for parseAndExecute.
     *
     * @param command the command to be executed.
     * @param args    the argument list for the command.
     * @throws JarvisException if the command is invalid or the arguments are invalid/empty or
     *                         there are exceptions during execution.
     */
    protected void execute(String command, String args) throws JarvisException {
        try {
            CommandType commandType = CommandType.valueOf(command.toUpperCase());
            // go through commands without any arguments.
            switch (commandType) {
            case LIST: {
                taskList.show();
                return;
            }
            }

            if (args.isEmpty()) {
                throw new EmptyArgumentException(command);
            }
            // go through commands with arguments.
            switch (commandType) {
            case FIND: {
                taskList.find(args);
                return;
            }
            case MARK: {
                try {
                    int taskNumber = Integer.parseInt(args);
                    taskList.markDone(taskNumber);
                } catch (NumberFormatException e) {
                    throw new InvalidArgumentException(command);
                }
                return;
            }
            case UNMARK: {
                try {
                    int taskNumber = Integer.parseInt(args);
                    taskList.markUndone(taskNumber);
                } catch (NumberFormatException e) {
                    throw new InvalidArgumentException(command);
                }
                return;
            }
            case TODO: {
                taskList.add(args, CommandType.TODO);
                return;
            }
            case DEADLINE: {
                try {
                    String[] varargs = args.split("\\s+/by\\s+");
                    String description = varargs[0], by = varargs[1];
                    taskList.add(description, CommandType.DEADLINE, by);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidArgumentException(command);
                }
                return;
            }
            case EVENT: {
                try {
                    String[] varargs = args.split("\\s+/from\\s+|\\s+/to\\s+");
                    String description = varargs[0], from = varargs[1], to = varargs[2];
                    taskList.add(description, CommandType.EVENT, from, to);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidArgumentException(command);
                }
                return;
            }
            case DELETE: {
                try {
                    int taskNumber = Integer.parseInt(args);
                    taskList.delete(taskNumber);
                } catch (NumberFormatException e) {
                    throw new InvalidArgumentException(command);
                }
                return;
            }
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException(command);
        }
    }


}
