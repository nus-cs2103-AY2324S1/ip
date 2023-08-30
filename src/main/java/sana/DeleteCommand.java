package sana;
public class DeleteCommand extends Command {

    public DeleteCommand(String cmd, String arguments) {
        super(cmd, arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SanaException {
        if (arguments.isBlank()) {
            throw new SanaException("Error! Need to specify which task to delete");
        }

        Task deletedTask = tasks.delete(Integer.parseInt(arguments));
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
