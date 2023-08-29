public class Parser {

    private final TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public Command parse(String input) {
        String[] parts = input.split(" ", 2);
        CommandType commandType = CommandType.fromString(parts[0]);
        String commandArgs = parts.length > 1 ? parts[1].trim() : "";

        switch (commandType) {
        case EXIT:
            return new ExitCommand(CommandType.EXIT, this.taskList, commandArgs);

        case LIST_TASKS:
            return new ListTasksCommand(CommandType.LIST_TASKS, this.taskList, commandArgs);

        case ADD_TODO:
            return new AddToDoCommand(CommandType.ADD_TODO, this.taskList, commandArgs);

        case ADD_DEADLINE:
            return new AddDeadlineCommand(CommandType.ADD_DEADLINE, this.taskList, commandArgs);

        case ADD_EVENT:
            return new AddEventCommand(CommandType.ADD_EVENT, this.taskList, commandArgs);

        case MARK_TASK:
            return new MarkTaskCommand(CommandType.MARK_TASK, this.taskList, commandArgs);

        case UNMARK_TASK:
            return new UnmarkTaskCommand(CommandType.UNMARK_TASK, this.taskList, commandArgs);

        case DELETE_TASK:
            return new DeleteTaskCommand(CommandType.DELETE_TASK, this.taskList, commandArgs);

        default:
            throw new DukeInvalidCommandException("I'm gonna be honest, no idea what you're saying.");
        }

    }
}
