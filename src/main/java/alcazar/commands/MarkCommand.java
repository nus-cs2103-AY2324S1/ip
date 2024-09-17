package alcazar.commands;

import alcazar.Response;
import alcazar.Storage;
import alcazar.TaskList;
import alcazar.exceptions.InvalidSerialException;

/**
 * Encapsulates the command to mark a Task
 */
public class MarkCommand extends Command {
    /** The passed prompt represent the task to be marked */
    private String inputPrompt;

    /**
     * Construct a MarkCommand object
     * @param inputPrompt The prompt representing the task to be marked
     */
    public MarkCommand(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }

    /**
     * Executes the marking of a Task
     * @param tasks the TaskList containing all the tasks upto present
     * @param storage The Storage which stores all the tasks upto now
     * @return A Response to showcase that the respective Task has been marked
     * @throws InvalidSerialException If the serial number of the task to be marked is negative
     *       or out of bounds
     */
    @Override
    public Response execute(
            TaskList tasks, Storage storage) throws InvalidSerialException {

        char characterIndexToMark = inputPrompt.charAt(inputPrompt.length() - 1);
        int intIndexOfElementToMark = Integer.parseInt( characterIndexToMark + "");

        if (intIndexOfElementToMark > tasks.size()) {
            throw new InvalidSerialException("☹ OOPS!!! I think you have added "
                    + "an incorrect serial number greater than " + (tasks.size()));
        } else if (intIndexOfElementToMark < 0) {
            throw new InvalidSerialException("☹ OOPS!!! I think you have added "
                    + "an incorrect serial number less than zero");
        }

        tasks.markElement(intIndexOfElementToMark - 1);
        storage.writeUp(tasks);

        String result = "Nice! I've marked this task as done:\n"
                + tasks.elementAt(intIndexOfElementToMark - 1) + "\n";
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
}