package alcazar.commands;

import alcazar.Response;
import alcazar.Storage;
import alcazar.TaskList;
import alcazar.exceptions.InvalidSerialException;

/**
 * Encapsulates the command to un-mark a Task
 */
public class UnmarkCommand extends Command {
    /** The passed prompt represent the task to be un-marked */
    private String inputPrompt;

    /**
     * Construct a UnmarkCommand object
     * @param inputPrompt The prompt representing the task to be un-marked
     */
    public UnmarkCommand(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }

    /**
     * Executes the un-marking of a Task
     * @param tasks the TaskList containing all the tasks upto present
     * @param storage The Storage which stores all the tasks upto now
     * @return A String response to showcase that the respective Task has been un-marked
     * @throws InvalidSerialException If the serial number of the task to be un-marked is negative
     *       or out of bounds
     */
    @Override
    public Response execute(
            TaskList tasks, Storage storage) throws InvalidSerialException {

        char characterIndexToUnmark = inputPrompt.charAt(inputPrompt.length() - 1);
        int intIndexOfElementToUnmark = Integer.parseInt( characterIndexToUnmark + "");

        if (intIndexOfElementToUnmark > tasks.size()) {
            throw new InvalidSerialException("☹ OOPS!!! I think you have added "
                    + "an incorrect serial number greater than " + (tasks.size()));
        } else if (intIndexOfElementToUnmark < 0) {
            throw new InvalidSerialException("☹ OOPS!!! I think you have added "
                    + "an incorrect serial number less than zero");
        }

        tasks.unmarkElement(intIndexOfElementToUnmark - 1);
        storage.writeUp(tasks);

        String result = "OK, I've marked this task as not done yet:\n"
                        + tasks.elementAt(intIndexOfElementToUnmark - 1) + "\n";
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
