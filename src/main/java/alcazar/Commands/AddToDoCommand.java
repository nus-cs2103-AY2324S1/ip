package alcazar.Commands;

import alcazar.Exceptions.InvalidArgumentException;
import alcazar.Storage;
import alcazar.TaskList;
import alcazar.Tasks.ToDo;

public class AddToDoCommand extends Command {

    private String inputPrompt;

    public AddToDoCommand(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }
    @Override
    public String execute(TaskList tasks,
                          Storage storage) throws InvalidArgumentException {

        String commandContent = this.getCommandContent();
        tasks.add(new ToDo(commandContent));
        storage.writeUp(tasks);

        return "Got it. I've added this task:\n "
                + tasks.elementAt(tasks.size() - 1) + "\n"
                + "Now you have " + tasks.size() + " tasks in the list\n";
    }

    @Override
    public boolean isExit() {
        return false;
    }
    public String getCommandContent() throws InvalidArgumentException {

        String commandContent = "";
        String[] inputWords = inputPrompt.split(" ");

        if (inputWords.length == 1) {
            throw new InvalidArgumentException(
                    "☹ OOPS!!! The description of a Deadline cannot be empty.");
        }

        for (int i = 1; i < inputWords.length; i++) {
            commandContent += inputWords[i] + " ";
        }

        commandContent = commandContent.trim();
        return commandContent;
    }
}
