package brandon.chatbot.commands.taskcommands;

import brandon.chatbot.tag.Tag;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.common.DukeException;
import brandon.chatbot.tasks.Todo;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Represents a command that adds a todo task into the task list.
 */
public class AddTodoCommand extends Command {
    public static final String ADD_SUCCESS = "ok... I'm adding..-ㅅ-";
    private Todo todoToAdd;

    private Optional<ArrayList<Tag>> tags;

    public AddTodoCommand(String taskName, Optional<ArrayList<Tag>> tags) throws DukeException {
        this.todoToAdd = new Todo(taskName, tags);
        this.tags = tags;
    }

    @Override
    public CommandResult execute() {
        assert this.todoToAdd != null: "Todo object should not be empty.";

        tasks.addTask(todoToAdd);

        if (tags.isPresent()) {
            for (Tag t : tags.get()) {
                tagTaskMap.add(t, todoToAdd);
            }
//            tagTaskMap.printMap();
        }

        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }
}
