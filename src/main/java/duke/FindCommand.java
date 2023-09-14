package duke;

/**
 * Finds tasks by searching for a keyword.
 */
public class FindCommand extends Command {
    private final String KEYWORD;
    public FindCommand(String word) {
        KEYWORD = word.toLowerCase();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList results = new TaskList();
        for (int i = 0; i < tasks.total(); i++) {
            Task task = tasks.get(i);
            String Taskdetail = task.toString().toLowerCase();
            if (Taskdetail.contains(KEYWORD)) {
                results.add(task);
            }
        }
        return ui.showMatchingTasks(results);
    }

}
