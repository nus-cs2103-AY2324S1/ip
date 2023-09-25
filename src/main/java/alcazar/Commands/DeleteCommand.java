package alcazar.Commands;

import alcazar.Exceptions.InvalidSerialException;
import alcazar.Storage;
import alcazar.TaskList;
import alcazar.Tasks.Task;

public class DeleteCommand extends Command {
    private String inputPrompt;

    public DeleteCommand(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }
    @Override
    public String execute(TaskList tasks,
                          Storage storage) throws InvalidSerialException {

        char indexToDelete = inputPrompt.charAt(inputPrompt.length() - 1);
        int indexOfElementToDelete = Integer.parseInt( indexToDelete + "");

        if (indexOfElementToDelete > tasks.size()) {
            throw new InvalidSerialException("☹ OOPS!!! I think you have added "
                    + "an incorrect serial number greater than " + (tasks.size()));
        }

        Task deletedTask = tasks.elementAt(indexOfElementToDelete - 1);
        tasks.delete(indexOfElementToDelete - 1);
        storage.writeUp(tasks);

        return "Noted. I've removed this task:\n" + "  "
                + deletedTask + "\nNow you have " + tasks.size() + " tasks in the list\n";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
