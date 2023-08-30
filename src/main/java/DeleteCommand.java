public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    private boolean isExit = false;
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index + 1 > tasks.size()) {
            throw new DukeException(ui.messageCard("The current number of tasks is " + tasks.size() + ", " +
                    "so you can't delete task " + (index + 1) + "!!."));
        }
        Task task = tasks.get(index);

        //remove task and updating
        tasks.deleteTask(index);
        storage.updateFile(tasks);

        //printing messages
        String res = "Noted. I've removed this task: \n" + task + "\nNow you have "
                + tasks.size() + " tasks in the list.";
        ui.printMessage(res);
    }
    public boolean isExit() {
        return this.isExit;
    }
}
