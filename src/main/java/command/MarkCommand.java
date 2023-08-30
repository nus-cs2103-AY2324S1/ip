public class MarkCommand extends Command{
    private int index;
    private boolean isExit = false;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (this.index + 1 > tasks.size()) {
            throw new DukeException(ui.messageCard("The current number of tasks is " + tasks.size() + ", " +
                    "so you can't mark task " + (index + 1) + "!!."));
        }
        //update
        Task task = tasks.get(index);
        tasks.markTask(index);
        storage.updateFile(tasks);

        String res = "Nice! I've marked this task as done:" + "\n"
                + "[" + task.getStatusIcon() + "] " + task.getDescription();
        ui.printMessage(res);
    }

    @Override
    public boolean isExit(){
        return this.isExit;
    }
}
