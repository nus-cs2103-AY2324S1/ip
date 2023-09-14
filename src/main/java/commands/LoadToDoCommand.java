package commands;

import functions.TaskList;
import tasks.ToDo;

public class LoadToDoCommand extends Command {

    private String currentTaskAsString;
    private TaskList taskList;

    public LoadToDoCommand(String currentTaskAsString, TaskList taskList) {
        this.currentTaskAsString = currentTaskAsString;
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        boolean isDone = currentTaskAsString.substring(4, 5).toUpperCase().equals("X");
        int descriptionBeginIndex = 7;

        String description = currentTaskAsString.substring(descriptionBeginIndex);
        ToDo todo = new ToDo(description, isDone);
        taskList.add(todo);
        return "Ok";
    }
}
