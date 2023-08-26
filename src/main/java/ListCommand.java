public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.display();
    }
}
