public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // add task into tasks, input coming from Parse class, Parse class is supposed to make the String into a Task
        // print in ui
        // write in storage
        taskList.add(this.task);
        ui.printAddedTask(this.task, taskList.size());
        String toBeWritten = taskList.toWrite();
        storage.write(toBeWritten);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
