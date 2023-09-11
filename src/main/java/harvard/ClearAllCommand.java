package harvard;

public class ClearAllCommand extends Command{
    private String type;
    public ClearAllCommand(String type) {
        this.type = type;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.clearAll(type);
        storage.save(tasks);
        return ui.showClearAll(type);
    }
}
