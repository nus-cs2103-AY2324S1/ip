package commands;

import functions.TaskList;
import tasks.ToDo;

public class ToDoCommand extends Command{

    private TaskList taskList;
    private String description;

    public ToDoCommand(TaskList taskList, String description) {
        this.taskList = taskList;
        this.description = description;
    }

    @Override
    public void execute() {
        if (this.description.strip().isEmpty() || this.description.matches("todo")) {
            System.out.println("OOPS! The description of a todo cannot be empty.");
            return;
        }
        ToDo newTodo = new ToDo(this.description);
        this.taskList.add(newTodo);
        System.out.println("Added: " + newTodo.getTaskAsString());
    }
}
