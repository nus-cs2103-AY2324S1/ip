public class DeleteCommand extends Command {
    public DeleteCommand(String desc) {
        setDescription(desc);
    }
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        int index = Integer.parseInt(this.getDescription());
        Task task = tasks.getTaskByIndex(index);
        tasks.remove(task);
        storage.save(tasks);
        ui.showDeleteNotice(task, tasks.getSize());
    }

    @Override
    boolean isExit() {
        return false;
    }
}
