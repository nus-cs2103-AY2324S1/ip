package command;

import exception.KoraException;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

public class ToDoCommand extends Command {
    private String taskDetails;
    public ToDoCommand(String[] details) throws KoraException {

        taskDetails = details[0].replace("todo", "").replace(" ", "");
        if (taskDetails.equals("")) {
            throw new KoraException("ToDo must have details!");
        }
    }
    String commandMessage = "";
    @Override
    public String getCommandMessage() {
        return commandMessage;
    }
    @Override
    public void execute(TaskList taskList) {
        Task currentTask = new ToDo(taskDetails);
        taskList.addTask(currentTask);
        commandMessage = "Okay! I have added this task" + "\n" +
                currentTask.toString() + "\n" + String.format("Now you have %d tasks!", taskList.getLength());
    }
}
