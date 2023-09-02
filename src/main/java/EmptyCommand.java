public class EmptyCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList tasks) {
        ui.say("uhhh what???");
    }
}
