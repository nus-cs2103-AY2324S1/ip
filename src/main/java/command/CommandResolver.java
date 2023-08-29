package command;

import services.bizerrors.EmptyArgumentException;
import services.bizerrors.InvalidArgumentException;
import services.bizerrors.InvalidCommandException;
import services.bizerrors.JarvisException;
import services.tasklist.ListManager;

public class CommandResolver {

    public static void resolve(String userInput) throws JarvisException {
        String command = userInput.split(" ")[0];
        String arguments = userInput.replaceFirst(command, "").strip();
        resolve(command, arguments);
    }

    // a helper method for the above resolve method.
    private static void resolve(String command, String args) throws JarvisException {
        try {
            CommandType commandType = CommandType.valueOf(command.toUpperCase());
            // go through commands without any arguments.
            switch (commandType) {
                case LIST: {
                    ListManager.show();
                    return;
                }
            }

            if (args.isEmpty()) {
                throw new EmptyArgumentException(command);
            }
            // go through commands with arguments.
            switch (commandType) {
                case MARK: {
                    try {
                        int taskNumber = Integer.parseInt(args);
                        ListManager.markDone(taskNumber);
                    } catch (NumberFormatException e) {
                        throw new InvalidArgumentException(command);
                    }
                    return;
                }
                case UNMARK: {
                    try {
                        int taskNumber = Integer.parseInt(args);
                        ListManager.markUndone(taskNumber);
                    } catch (NumberFormatException e) {
                        throw new InvalidArgumentException(command);
                    }
                    return;
                }
                case TODO: {
                    ListManager.add(args, CommandType.TODO);
                    return;
                }
                case DEADLINE: {
                    try {
                        String[] varargs = args.split("\\s+/by\\s+");
                        String description = varargs[0], by = varargs[1];
                        ListManager.add(description, CommandType.DEADLINE, by);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InvalidArgumentException(command);
                    }
                    return;
                }
                case EVENT: {
                    try {
                        String[] varargs = args.split("\\s+/from\\s+|\\s+/to\\s+");
                        String description = varargs[0], from = varargs[1], to = varargs[2];
                        ListManager.add(description, CommandType.EVENT, from, to);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InvalidArgumentException(command);
                    }
                    return;
                }
                case DELETE: {
                    try {
                        int taskNumber = Integer.parseInt(args);
                        ListManager.delete(taskNumber);
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
