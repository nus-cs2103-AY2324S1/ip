public class AddCommand implements Command{
    private final Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws Exception{
        ui.showMessage(" Got it. I've added this task:\n");
        tasks.add(task);
        ui.showMessage("    " + task + "\n");
        ui.showMessage("Now you have " + Integer.toString(tasks.getTasks().size()) +
                " tasks in the list.\n");
        storage.save(tasks);
    }
}
