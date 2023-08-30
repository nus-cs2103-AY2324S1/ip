public class HelpCommand extends Command {

    public HelpCommand(String cmd, String arguments) {
        super(cmd, arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(super.cmd + " is not a valid command!");
    }
}
