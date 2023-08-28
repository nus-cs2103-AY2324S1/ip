package rat.command;

import rat.tasks.RatTaskManager;

public class FindCommand extends RatCommand {

    private String keyword;

    public FindCommand(RatTaskManager ratTaskManager, String input) {
        super(ratTaskManager);
        this.keyword = input.substring(5);
    }

    @Override
    public void execute() {
        this.ratTaskManager.printFoundTasks(this.keyword);
    }
}
