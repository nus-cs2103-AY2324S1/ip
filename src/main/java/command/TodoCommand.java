package command;

import parser.Parser;
import tasks.TaskList;
import tasks.TodoTask;
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
        this.description = Parser.getArgs(rawCommand)[1];
    }

    public void execute(TaskList taskList) {
        if (!this.valid) {
            return;
        }
        this.deconstruct(super.getRawCommand());
        taskList.addTask(new TodoTask(this.description));
    }

    public static boolean validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 2) {
            return false;
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.TODO)) {
            return false;
        }

        return true;
    }

}
