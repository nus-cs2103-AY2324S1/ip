package brandon.chatbot.commands.taskcommands;

import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.common.DukeException;
import brandon.chatbot.tag.Tag;
import brandon.chatbot.tasks.Todo;

import java.util.ArrayList;
import java.util.Optional;

import static brandon.chatbot.commands.Feedback.ADD_SUCCESS;

/**
 * Represents a command that adds a todo task into the task list.
 */
public class AddTodoCommand extends Command {

    private String title;
    private Optional<ArrayList<Tag>> tags;

    public AddTodoCommand(String title, Optional<ArrayList<Tag>> tags) {
        this.title = title;
        this.tags = tags;
    }

    @Override
    public CommandResult execute() throws DukeException {
        Todo todoToAdd = new Todo(title, tags);
        tasks.addTask(todoToAdd);

        if (tags.isPresent()) {
            addTodoItemToMap(todoToAdd);
        }

        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }

    private void addTodoItemToMap(Todo todoToAdd) {
        for (Tag t : tags.get()) {
            tagTaskMap.add(t, todoToAdd);
        }
    }
}
