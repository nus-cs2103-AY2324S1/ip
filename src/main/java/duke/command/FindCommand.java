package duke.command;

import duke.TaskList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = "find <keyword>";
    public static final String MESSAGE_EMPTY_KEYWORD = "you are missing a keyword to find by!";
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String[] execute() {
        TaskList filteredTasks = tasks.filter(task -> task.getDesc().contains(keyword));

        String[] response;
        if (filteredTasks.size() == 0) {
            response = new String[1];
            response[0] = "There are no matching tasks in your list.";
        } else {
            response = new String[filteredTasks.size() + 1];
            response[0] = "Here are the matching tasks in your list:";
            for (int i = 1; i <= filteredTasks.size(); i++) {
                response[i] = (i + ". " + this.tasks.get(i));
            }
        }
        return response;
    }
}
