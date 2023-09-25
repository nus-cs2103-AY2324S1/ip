package alcazar.commands;

import alcazar.Response;
import alcazar.Storage;
import alcazar.TaskList;
import alcazar.exceptions.InvalidSerialException;
import alcazar.tasks.Task;

/**
 * Encapsulates a command to represent deletion of a Task
 */
public class DeleteCommand extends Command {

    /** The prompt for the task to be deleted*/
    private String inputPrompt;

    /**
     * Constructs a DeletCommand object
     * @param inputPrompt The prompt which represents the task to be deleted
     */
    public DeleteCommand(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }

    /**
     * Executes the deletion of a Task
     * @param tasks the TaskList containing all the tasks upto present
     * @param storage The Storage which stores all the tasks upto now
     * @return A Response to showcase that the respective Task has been deleted
     * @throws InvalidSerialException If the serial number of the task to be deleted is negative
     *       or out of bounds
     */
    @Override
    public Response execute(
            TaskList tasks, Storage storage) throws InvalidSerialException {

        char indexToDelete = inputPrompt.charAt(inputPrompt.length() - 1);
        int indexOfElementToDelete = Integer.parseInt( indexToDelete + "");

        if (indexOfElementToDelete > tasks.size()) {
            throw new InvalidSerialException("☹ OOPS!!! I think you have added "
                    + "an incorrect serial number greater than " + (tasks.size()));
        } else if (indexOfElementToDelete < 0) {
            throw new InvalidSerialException("☹ OOPS!!! I think you have added "
                    + "an incorrect serial number less than zero");
        }

        Task deletedTask = tasks.elementAt(indexOfElementToDelete - 1);
        tasks.delete(indexOfElementToDelete - 1);
        storage.writeUp(tasks);

        String result = "Noted. I've removed this task:\n" + "  "
                        + deletedTask + "\nNow you have " + tasks.size()
                        + " tasks in the list\n";
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
