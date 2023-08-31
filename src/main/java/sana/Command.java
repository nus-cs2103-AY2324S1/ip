package sana;
public abstract class Command {

    protected String cmd;

    protected String arguments;

    public Command(String cmd, String arguments) {
        this.cmd = cmd;
        this.arguments = arguments;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SanaException;
    public abstract boolean isExit();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Command) {
            Command c = (Command) obj;

            if (c == null || this == null) {
                return false;
            }

            return this.cmd.equals(c.cmd) && this.arguments.equals(c.arguments);
        }
        return false;
    }
}
