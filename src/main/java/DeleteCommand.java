public class DeleteCommand extends Command {

    public DeleteCommand(String cmd, String arguments) {
        super(cmd, arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.delete(Integer.parseInt(arguments) - 1);
        System.out.println("Noted. I've removed this task:\n" + deletedTask.toString() + "\n"
                + "Now you have " + tasks.size() + (tasks.size() <= 1 ? " task" : " tasks")
                + " in the list");
        tasks.update(storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
