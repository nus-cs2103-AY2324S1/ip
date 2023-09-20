package brandon.chatbot.commands.taskcommands;

import brandon.chatbot.Message;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.tag.Tag;
import brandon.chatbot.tasks.TaskList;

import java.util.ArrayList;

public class FindTaskByTagCommand extends Command {
    private static final String FIND_SUCCESS = "ok... I'm finding the task...-ㅅ-\n";
    private String title;
    private ArrayList<Tag> tags;

    public FindTaskByTagCommand(String title, ArrayList<Tag> tags) {
        this.title = title;
        this.tags = tags;
    }
    @Override
    public CommandResult execute() {
        TaskList newList = new TaskList();
        for (Tag t : tags) {
            if (newList.isEmpty()) {
                newList.appendTaskList(tagTaskMap.getTaskList(t));
            } else {
                newList = newList.filterTaskWithTag(t);
            }
        }

        return new CommandResult(FIND_SUCCESS + Message.showTasks(newList));
    }

}
