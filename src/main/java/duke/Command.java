package duke;

public abstract class Command {

    public enum CommandType {
        TODO,
        DEADLINE,
        EVENT,
        LIST,
        MARK,
        DELETE,
        BYE
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}

