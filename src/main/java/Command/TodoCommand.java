package Command;

import Tasks.TaskList;
import Tasks.TodoTask;
import enums.CommandWord;

public class TodoCommand extends Command {
    private String description;
    private final boolean valid;

    public TodoCommand(String rawCommand) {
        super(rawCommand);
        this.valid = validate(rawCommand);
    }

    private void deconstruct(String rawCommand) {
        if (!this.valid) {
            return;
        }
        this.description = getArgs(rawCommand)[1];
    }

    public void execute(TaskList taskList) {
        if (!this.valid) {
            System.out.println("No Task was added.");
            return;
        }
        this.deconstruct(super.getRawCommand());
        taskList.addTask(new TodoTask(this.description));
    }

    protected boolean validate(String rawCommand) {
        String[] args = getArgs(rawCommand);
        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.TODO)) {
            return false;
        }

        return args.length == 2;
    }

}
