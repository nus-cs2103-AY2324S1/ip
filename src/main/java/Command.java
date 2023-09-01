

public abstract class Command {

    protected String input;

    public Command(String input) {
        this.input = input;
    }

    public void execute(TaskList tasks, Storage storage, Ui ui) {}

    public boolean isExit() {return false;}

}
