package alcazar.commands;

import alcazar.Response;
import alcazar.Storage;
import alcazar.TaskList;
import alcazar.exceptions.InvalidArgumentException;

/**
 * Encapsulates the Command of finding a task
 */
public class FindCommand extends Command {
    /** The input prompt to represent the task to be found*/
    private String inputPrompt;

    /**
     * Constructs a FindCommand object
     * @param inputPrompt The input prompt representing the tasks to be found
     */
    public FindCommand(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }

    /**
     * Executes the command of finding tasks
     * @param tasks the TaskList containing all the tasks upto present
     * @param storage The Storage which stores all the tasks upto now
     * @return A String response listing the tasks with the passed prompt
     * @throws InvalidArgumentException If the basis keyword for find is empty
     */
    @Override
    public Response execute(
            TaskList tasks, Storage storage) throws InvalidArgumentException {

        String wordToSearch = this.getCommandContent();

        String result = "Here are the matching tasks in your list:\n"
                        + tasks.printEquals(wordToSearch);
        return new Response(result, this.isExit());
    }

    /**
     * Checks if this is an exit command
     * @return boolean depending on whether this is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Extracts the content, the text after the command specification, of the command
     * passed by the user
     * @return String containing the command content
     * @throws InvalidArgumentException If command is passed without content
     */
    public String getCommandContent() throws InvalidArgumentException {

        String commandContent = "";
        String[] inputWords = inputPrompt.split(" ");

        if (inputWords.length == 1) {
            throw new InvalidArgumentException(
                    "☹ OOPS!!! The keyword on the basis of which tasks are to "
                            + "be found cannot be empty"
            );
        }

        for (int i = 1; i < inputWords.length; i++) {
            commandContent += inputWords[i] + " ";
        }

        commandContent = commandContent.trim();
        return commandContent;
    }
}
