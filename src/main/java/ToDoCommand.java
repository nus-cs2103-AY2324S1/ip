public class ToDoCommand extends Command {
    String description;
    public ToDoCommand(String description) {
        this.description = description;
    }
    @Override
    public void execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        ToDo toDoTask = new ToDo(description, false);
        taskList.addTask(toDoTask);
        storage.saveToFile(taskList);
        ui.addsTasksMsg(toDoTask, taskList.size());
    }
}
