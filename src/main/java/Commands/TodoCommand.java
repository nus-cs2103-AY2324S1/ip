package Commands;

import Task.TaskList;
import Task.Todo;

public class TodoCommand extends Command {

    private final TaskList taskList;
    private final Todo todo;

    public TodoCommand(TaskList taskList, String taskDetail) {
        this.taskList = taskList;
        this.todo = new Todo(taskDetail);
    }

    public void execute() {
        this.taskList.add(this.todo);
        System.out.println(this);
    }

    @Override
    public String toString() {
        if (this.taskList.length() <= 1) {
            return Command.SPACER + "\n" +
                    "Got it. I've added this task:\n" +
                    this.todo + "\n" +
                    "Now you have " + this.taskList.length() + " task in the list.\n" +
                    Command.SPACER;
        } else {
            return Command.SPACER + "\n" +
                    "Got it. I've added this task:\n" +
                    this.todo + "\n" +
                    "Now you have " + this.taskList.length() + " tasks in the list.\n" +
                    Command.SPACER;
        }
    }

}
