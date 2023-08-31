package max.commands;

import max.commands.*;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.tasks.TaskList;
import max.tasks.*;
import max.ui.Ui;


public class AddCommand extends Command {
    public static final String COMMAND_WORD_DEADLINE = "deadline";
    public static final String COMMAND_WORD_EVENT = "event";
    public static final String COMMAND_WORD_TODO = "todo";
    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.writeToFile(tasks);
        ui.showAdd(task, tasks.getList().size());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
