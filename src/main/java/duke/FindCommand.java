package duke;

import java.util.ArrayList;

public class FindCommand extends Command{
    private final String KEYWORD;
    public FindCommand(String word) {
        KEYWORD = word;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList results = new TaskList();
        for (int i = 0; i < tasks.total(); i++) {
            Task task = tasks.get(i);
            String description = task.getDescription();
            if (description.contains(KEYWORD)) {
                results.add(task);
            }
        }
        ui.showMatchingTasks(results);
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
