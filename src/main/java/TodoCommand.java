public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        Task todo = new Todo(description);
        tasks.add(todo);
        storage.update(tasks);
        ui.displayMsg(new String[] {
            "Okie! I've added a new " + Ui.cTxt("TODO", Ui.COLOR.GREEN) + ":",
            "  " + todo.toString(),
            "Total no. of tasks stored: " + tasks.getSize()
        });
    }
}
