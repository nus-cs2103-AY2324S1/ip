package brandon.chatbot.commands;

import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;

public class FindCommand extends Command {
    private final static String FIND_SUCCESS = "ok... I'm finding the task...";
    private String title;

    public FindCommand(String title) {
        this.title = title;
    }
    @Override
    public CommandResult execute() {
        TaskList newList = new TaskList();
        for (Task task : tasks.getList()) {
            if (task.toString().contains(this.title)) {
                newList.addTask(task);
            }
        }

        return new CommandResult(FIND_SUCCESS, newList);
    }
}
