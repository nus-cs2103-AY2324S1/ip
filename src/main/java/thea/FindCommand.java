package thea;

public class FindCommand extends Command{
    String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.relevantTasksFound(tasks.find(keyword));
    }
}
