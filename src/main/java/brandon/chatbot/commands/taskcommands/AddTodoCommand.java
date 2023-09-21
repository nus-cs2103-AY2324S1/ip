package brandon.chatbot.commands.taskcommands;

import static brandon.chatbot.commands.Feedback.ADD_SUCCESS;

import java.util.ArrayList;
import java.util.Optional;

import brandon.chatbot.Message;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.common.DukeException;
import brandon.chatbot.tag.Tag;
import brandon.chatbot.tasks.Todo;

/**
 * Represents a command that adds a todo task into the task list.
 */
public class AddTodoCommand extends Command {
    private String title;
    private Optional<ArrayList<Tag>> tags;

    /**
     * Constructs an AddTodoCommand with the give title and tags of the Todo task.
     * @param title of the Todo task.
     * @param tags that are relevant to the Todo task. Tags are optional.
     */
    public AddTodoCommand(String title, Optional<ArrayList<Tag>> tags) {
        this.title = title;
        this.tags = tags;
    }

    @Override
    public CommandResult execute() throws DukeException {
        // Creating a todo task with a title being null throws a DukeException.
        Todo todoToAdd = new Todo(title, tags);

        tasks.addTask(todoToAdd);

        if (tags.isPresent()) {
            addTodoItemToMap(todoToAdd);
        }

        CommandResult result = new CommandResult(ADD_SUCCESS + "\n" + Message.showTasks(tasks));
        return result;
    }

    private void addTodoItemToMap(Todo todoToAdd) {
        for (Tag t : tags.get()) {
            tagTaskMap.add(t, todoToAdd);
        }
    }
}
