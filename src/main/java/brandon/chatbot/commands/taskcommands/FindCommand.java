package brandon.chatbot.commands.taskcommands;

import brandon.chatbot.Message;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.common.DukeException;
import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;
import brandon.chatbot.tag.Tag;

import java.util.ArrayList;
import java.util.Optional;

import static brandon.chatbot.commands.Feedback.*;

/**
 * Represents a command that finds a task in the task list with a given keyword.
 */
public class FindCommand extends Command {

    private Optional<String> title;
    private Optional<ArrayList<Tag>> tags;

    public FindCommand(Optional<String> title, Optional<ArrayList<Tag>> tags) {
        this.title = title;
        this.tags = tags;
    }
    @Override
    public CommandResult execute() throws DukeException {
        TaskList newList = new TaskList();
        boolean titleExists = false;

        if (!title.isPresent() && !tags.isPresent()) {
            throw new DukeException(TITLE_BLANK);
        }

        if (title.isPresent()) {
            findAndAddTasks(newList);
            titleExists = true;
        }

        if (tags.isPresent()) {
            findTasksByTagsAndFilter(newList, titleExists);
        }

        if (newList.isEmpty()) {
            return new CommandResult(FIND_FAIL);
        }

        return new CommandResult(FIND_SUCCESS + Message.showTasks(newList));
    }

    private TaskList findAndAddTasks(TaskList newList) {
        for (Task t: tasks.getList()) {
            if (t.toString().contains(title.get())) {
                newList.addTask(t);
            }
        }

        return newList;
    }

    private TaskList findTasksByTagsAndFilter(TaskList newList, boolean titleExists) {
        for (Tag t : tags.get()) {
            if (newList.isEmpty() && !titleExists) {
                newList.appendTaskList(tagTaskMap.getTaskList(t));
            } else {
                newList = newList.filterTaskWithTag(t);
            }
        }

        return newList;
    }
}
