public class ByeCommand extends Command {

    @Override
    public void execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        ui.bye();
        this.exitsNext = true;
    }
}
