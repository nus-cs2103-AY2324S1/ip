package command;

import services.bizerrors.EmptyArgumentException;
import services.bizerrors.InvalidArgumentException;
import services.bizerrors.InvalidCommandException;
import services.bizerrors.JarvisException;
import services.tasklist.List;

public class CommandResolver {
    public static void resolve(String command, String args) throws JarvisException {
        try {
            CommandType commandType = CommandType.valueOf(command.toUpperCase());
            // go through commands without any arguments.
            switch (commandType) {
                case LIST: {
                    List.show();
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
                        List.markDone(taskNumber);
                    } catch (NumberFormatException e) {
                        throw new InvalidArgumentException(command);
                    }
                    return;
                }
                case UNMARK: {
                    try {
                        int taskNumber = Integer.parseInt(args);
                        List.markUndone(taskNumber);
                    } catch (NumberFormatException e) {
                        throw new InvalidArgumentException(command);
                    }
                    return;
                }
                case TODO: {
                    List.add(args, CommandType.TODO);
                    return;
                }
                case DEADLINE: {
                    try {
                        String[] varargs = args.split("\\s+/by\\s+");
                        String description = varargs[0], by = varargs[1];
                        List.add(description, CommandType.DEADLINE, by);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InvalidArgumentException(command);
                    }
                    return;
                }
                case EVENT: {
                    try {
                        String[] varargs = args.split("\\s+/from\\s+|\\s+/to\\s+");
                        String description = varargs[0], from = varargs[1], to = varargs[2];
                        List.add(description, CommandType.EVENT, from, to);
                    } catch (ArrayIndexOutOfBoundsException e) {
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
