package command;

import enums.CommandWord;
import parser.Parser;
import tasks.TaskList;
import tasks.TodoTask;

public class TodoCommand extends Command {
    private final boolean valid;
    private String description;

    public TodoCommand(String rawCommand) {
        super(rawCommand);
        this.valid = validate(rawCommand);
    }

    public static boolean validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 2) {
            return false;
        }

        return CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.TODO);
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

}
