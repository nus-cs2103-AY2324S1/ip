public class ListCommand extends Command {
    public ListCommand() {
        // nothing
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.say(String.format(
                "Here are your tasks:\n%s",
                taskList.toString()));
    }
}
