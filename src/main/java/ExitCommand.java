public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.respond("Bye. Hope to see you again soon!");
        Duke.stop();
    }
}
