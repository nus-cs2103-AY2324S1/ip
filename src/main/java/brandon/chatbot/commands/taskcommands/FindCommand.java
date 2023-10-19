package brandon.chatbot.commands.taskcommands;

import static brandon.chatbot.commands.Feedback.FIND_EMPTY_ARGUMENTS;
import static brandon.chatbot.commands.Feedback.FIND_FAIL;
import static brandon.chatbot.commands.Feedback.FIND_SUCCESS;

import java.util.ArrayList;
import java.util.Optional;

import brandon.chatbot.Message;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.common.DukeException;
import brandon.chatbot.tag.Tag;
import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;

/**
 * Represents a command that finds a task in the task list with a given keyword.
 */
public class FindCommand extends Command {

    private Optional<String> title;
    private Optional<ArrayList<Tag>> tags;

    /**
     * Constructs a FindCommand with the title and the tags of a task.
     * @param title of the task to find. At least one of title or tags must be present.
     * @param tags of the task to find. At least one of title or tags must be present.
     */
    public FindCommand(Optional<String> title, Optional<ArrayList<Tag>> tags) {
        this.title = title;
        this.tags = tags;
    }
    @Override
    public CommandResult execute() throws DukeException {
        TaskList newList = new TaskList();
        boolean titleExists = false;

        if (!title.isPresent() && !tags.isPresent()) {
            throw new DukeException(FIND_EMPTY_ARGUMENTS);
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
            if (t.getTitle().contains(title.get())) {
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
