import services.tasklist.List;

public class CommandResolver {
    public enum Type {
        LIST,
        MARK, UNMARK,
        TODO, DEADLINE, EVENT
    }

    public static void resolve(String command, String args) {
        // condition on the first word of the user input.
        Type commandType = Type.valueOf(command.toUpperCase());
        switch (commandType) {
            case LIST: {
                List.show();
                break;
            }
            case MARK: {
                int taskNumber = Integer.parseInt(args);
                List.markDone(taskNumber);
                break;
            }
            case UNMARK: {
                int taskNumber =Integer.parseInt(args);
                List.markUndone(taskNumber);
                break;
            }
            case TODO: {
                List.add(args, "todo");
                break;
            }
            case DEADLINE: {
                String[] varargs = args.split("\\s+/by\\s+");
                String description = varargs[0], by = varargs[1];
                List.add(description, "deadline", by);
                break;
            }
            case EVENT: {
                String[] varargs = args.split("\\s+/from\\s+|\\s+/to\\s+");
                String description = varargs[0], from = varargs[1], to = varargs[2];
                List.add(description, "event", from, to);
                break;
            }
            default:
        }
    }


}
