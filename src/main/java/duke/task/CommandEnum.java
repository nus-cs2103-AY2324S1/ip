package duke.task;

/**
 * This is an enum containing all possible commands.
 */
public enum CommandEnum {
    LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID;

    public static CommandEnum assignEnum(String task) {
        if (task.startsWith("bye")) return CommandEnum.BYE;
        else if (task.startsWith("list")) return CommandEnum.LIST;
        else if (task.startsWith("mark")) return CommandEnum.MARK;
        else if (task.startsWith("unmark")) return CommandEnum.UNMARK;
        else if (task.startsWith("todo")) return CommandEnum.TODO;
        else if (task.startsWith("deadline")) return CommandEnum.DEADLINE;
        else if (task.startsWith("event")) return CommandEnum.EVENT;
        else if (task.startsWith("delete")) return CommandEnum.DELETE;
        else return CommandEnum.INVALID;
    }
}
