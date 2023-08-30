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

}
