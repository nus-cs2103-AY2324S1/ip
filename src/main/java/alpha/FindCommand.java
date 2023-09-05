package alpha;

public class FindCommand extends Command{
    private final String input;

    public FindCommand(TaskList taskList, FileHandler fh, UI ui, String input) {
        super(taskList, fh, ui);
        isExit = false;
        this.input = input.trim();
    }

    @Override
    public String execute() {
        System.out.println(input);
        TaskList newList = taskList.search(input);
        return ui.list(newList);
    }
}
