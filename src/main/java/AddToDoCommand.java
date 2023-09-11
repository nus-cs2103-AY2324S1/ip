import java.io.IOException;

public class AddToDoCommand extends Command {
    private String taskDescription;
    public AddToDoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            System.out.println("Executing Add ToDo Command");
            ToDo newTask = new ToDo(taskDescription);
            taskList.addTask(newTask);
            int nTasks = taskList.getSize();
            ui.showAddedTask(newTask, nTasks);
            storage.saveTasksToDisk(taskList);
        } catch (IOException e) {
            System.out.println("Error in Add ToDo Command");
        }
    }
}
