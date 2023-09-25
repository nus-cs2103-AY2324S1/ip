package alcazar.Commands;

import alcazar.Exceptions.InvalidArgumentException;
import alcazar.Storage;
import alcazar.TaskList;

public class FindCommand extends Command {
    private String inputPrompt;

    public FindCommand(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }
    @Override
    public String execute(TaskList tasks,
                          Storage storage) throws InvalidArgumentException {

        String wordToSearch = this.getCommandContent();

        return "Here are the matching tasks in your list:\n" + tasks.printEquals(wordToSearch);
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
                    "☹ OOPS!!! The description of a Deadline cannot be empty."
            );
        }

        for (int i = 1; i < inputWords.length; i++) {
            commandContent += inputWords[i] + " ";
        }

        commandContent = commandContent.trim();
        return commandContent;
    }
}
