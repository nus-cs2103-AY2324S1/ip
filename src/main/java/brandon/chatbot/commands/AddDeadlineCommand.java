package brandon.chatbot.commands;

import brandon.chatbot.common.DukeException;
import brandon.chatbot.tasks.Deadline;
import brandon.chatbot.tasks.Todo;

public class AddDeadlineCommand extends Command {
    private Deadline toAdd;
    public static final String ADD_SUCCESS = "ok... I'm adding..";

    public AddDeadlineCommand(String taskName, String deadline) throws Exception {
        this.toAdd = new Deadline(taskName, deadline);
    }

    @Override
    public CommandResult execute() {
        tasks.addTask(toAdd);
        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }
}
