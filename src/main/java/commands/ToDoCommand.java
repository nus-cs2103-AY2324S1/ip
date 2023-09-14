package commands;

import functions.TaskList;
import tasks.ToDo;

public class ToDoCommand extends Command{

    private TaskList taskList;
    private String description;

    /**
     * A public constructor to initialize the ToDoCommand.
     *
     * @param taskList a taskList consisting of
     * @param description the description of
     */
    public ToDoCommand(TaskList taskList, String description) {
        this.taskList = taskList;
        this.description = description;
    }

    @Override
    public String execute() {
        if (this.description.strip().isEmpty() || this.description.matches("todo")) {
            return "OOPS! The description of a todo cannot be empty.";
        }

        ToDo newTodo = new ToDo(this.description);
        this.taskList.add(newTodo);

        return "I have added the following: \n" + newTodo.getTaskAsString();
    }
}
