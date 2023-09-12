package duke.assets.commands;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import duke.assets.tasks.TaskAbstract;
import duke.assets.storage.TaskList;
import duke.assets.tasks.Todo;

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
        Pattern commandRegex = Pattern.compile("^todo .+", Pattern.CASE_INSENSITIVE);
        Matcher inputMatcher = commandRegex.matcher(this.input);
        if (!inputMatcher.find()) {
            System.out.println("ChadGPT: Please provide a description about your task.");
            return false;
        }
        return true;
    }

    @Override
    protected void completeOperation(TaskList tasklist) {
        String information = this.input.split("^((?i)(todo))\\s")[1];
        TaskAbstract newTask = new Todo(information);
        if (this.isDone) {
            newTask.completeNewTask();
        }
        tasklist.addTask(newTask);
    }

    @Override
    public void printChatbotLine() {
        System.out.print("ChadGPT: No problem! Just remember to do your task before the deadline ;).\n");
    }
}
