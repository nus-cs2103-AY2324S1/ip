public abstract class Command {

    public enum CommandType {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, BYE, INVALID
    }

    public CommandType type;

    public Command(CommandType type) {
        this.type = type;
    }

    public abstract void execute(TaskList tasks, Ui ui);
}
