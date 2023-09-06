package duke.assets.commands;

import duke.assets.tasks.TaskAbstract;
import duke.data.TaskList;
import duke.assets.tasks.Todo;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CreateTodoCommand extends CommandAbstract {
    private final boolean isDone;

    public CreateTodoCommand(String input, boolean isDone) {
        super(input);
        this.isDone = isDone;
    }

    @Override
    protected boolean isValid(TaskList tasklist) {
        return this.isValid();
    }

    private boolean isValid() {
        Pattern commandRegex = Pattern.compile("^todo\\s\\w+", Pattern.CASE_INSENSITIVE);
        Matcher inputMatcher = commandRegex.matcher(this.input);
        if (!inputMatcher.find()) {
            System.out.println("ChadGPT: Please provide a description about your task.");
        }
        return inputMatcher.find();
    }

    @Override
    protected void completeOperation(TaskList tasklist) {
        String information = this.input.split("^((?i)(todo))\\s")[0];
        TaskAbstract newTask = new Todo(information);
        if (this.isDone) {
            newTask.completeNewTask();
        }
        tasklist.addTask(newTask);
    }
}
