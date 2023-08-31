package sana;

public class ListCommand extends Command {

    public ListCommand(String cmd, String arguments) {
        super(cmd, arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SanaException {

        if (tasks.toString().isBlank()) {
            throw new SanaException("Your list is empty! Add tasks first to display list");
        }

        TaskList updatedTasks = new TaskList(storage.load());
        System.out.println(updatedTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Command) {
            Command c = (Command) obj;

            if (c == null || this == null) {
                return false;
            }

            return this.cmd.equals(c.cmd);
        }
        return false;
    }
}
