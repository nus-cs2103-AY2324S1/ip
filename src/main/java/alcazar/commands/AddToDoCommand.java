package alcazar.commands;

import alcazar.Response;
import alcazar.Storage;
import alcazar.TaskList;
import alcazar.exceptions.InvalidArgumentException;
import alcazar.tasks.ToDo;

/**
 * Encapsulates the command to add a ToDo
 */
public class AddToDoCommand extends Command {

    /** The prompt passed by the user to add a ToDo */
    private String inputPrompt;

    /**
     * Constructs an AddToDoCommand object
     * @param inputPrompt The prompt passed by the user to add an event.
     */
    public AddToDoCommand(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }

    /**
     * Executes the addition of a ToDo Task to the TaskList
     * @param tasks the TaskList containing all the tasks upto present
     * @param storage The Storage which stores all the tasks upto now
     * @return A Response to showcase that the respective ToDo has been added to the TaskList
     * @throws InvalidArgumentException If no prompt/topic is given for the ToDo
     */
    @Override
    public Response execute(
            TaskList tasks, Storage storage) throws InvalidArgumentException {

        String commandContent = this.getCommandContent();
        tasks.add(new ToDo(commandContent));
        storage.writeUp(tasks);

        String result = "Got it. I've added this task:\n "
                + tasks.elementAt(tasks.size() - 1) + "\n"
                + "Now you have " + tasks.size() + " tasks in the list\n";
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
                    "☹ OOPS!!! The description of a ToDo cannot be empty.");
        }

        for (int i = 1; i < inputWords.length; i++) {
            commandContent += inputWords[i] + " ";
        }

        commandContent = commandContent.trim();
        return commandContent;
    }
}