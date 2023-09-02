public class ListCommand extends Command {

    @Override
    public void execute(Ui ui, TaskList tasks) {
        ui.say(tasks.list());
    }
}
