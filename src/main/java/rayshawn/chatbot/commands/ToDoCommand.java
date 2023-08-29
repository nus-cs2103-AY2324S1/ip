package rayshawn.chatbot.commands;

import rayshawn.chatbot.exceptions.ChatBotException;
import rayshawn.chatbot.tasks.Task;
import rayshawn.chatbot.tasks.ToDo;

public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a ToDo task to the task list. " +
            "Parameters: Description\n" + "Example: " + COMMAND_WORD + " homework";
    public static final String MESSAGE_SUCCESS = "Got it. I've added this task: \n %1$s \n";

    private Task toAdd;

    public ToDoCommand(String toDo) throws ChatBotException {
        this.toAdd = new ToDo(toDo);
    }

    @Override
    public CommandResult execute() {
        taskList.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS + getTaskListCount(), toAdd, taskList.getAllTasks()));
    }
}
