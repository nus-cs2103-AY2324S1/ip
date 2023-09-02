package Remy.command;
import Remy.Task.TaskList;
import Remy.Ui;
import Remy.Storage;
import Remy.ChatbotException;
import Remy.Task.Todo;

/**
 * A Command that creates and adds a Todo to the TaskList upon executing.
 */
public class TodoCommand extends Command {
    private String taskName;
    public static final String COMMAND_WORD = "todo";

    /**
     * Creates new DeadLine command that parses user input and check that the format is correct.
     *
     * @param input The String submitted by the user to the Chatbot.
     * @throws ChatbotException if input is missing information, or in the wrong format.
     */
    public TodoCommand(String input) throws ChatbotException {
        if (input.length() < 6) throw new ChatbotException("missing info lah.");
        String taskName = input.substring(5);
        if (taskName.trim().length() > 0) {
            this.taskName = taskName;
        } else {
            throw new ChatbotException("missing info lah.");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Creates a Todo instance and adds it to the TaskList.
     *
     * @param taskList The TaskList to be acted on.
     * @param ui Handles User interaction.
     * @param storage Handles saving the updated TaskList.
     * @throws ChatbotException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        Todo temp = new Todo(this.taskName);
        taskList.add(temp);
        storage.save(taskList);
        Ui.printAddedTask(temp, taskList.size());
    }
}
