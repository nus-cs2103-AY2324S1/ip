package alcazar.Commands;

import alcazar.Exceptions.InvalidSerialException;
import alcazar.Storage;
import alcazar.TaskList;

public class UnmarkCommand extends Command {
    private String inputPrompt;

    public UnmarkCommand(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }
    @Override
    public String execute(TaskList tasks,
                          Storage storage) throws InvalidSerialException {

        char characterIndexToUnmark = inputPrompt.charAt(inputPrompt.length() - 1);
        int intIndexOfElementToUnmark = Integer.parseInt( characterIndexToUnmark + "");

        if (intIndexOfElementToUnmark > tasks.size()) {
            throw new InvalidSerialException("☹ OOPS!!! I think you have added "
                    + "an incorrect serial number greater than " + (tasks.size()));
        }

        tasks.unmarkElement(intIndexOfElementToUnmark - 1);
        storage.writeUp(tasks);

        return "OK, I've marked this task as not done yet:\n"
                + tasks.elementAt(intIndexOfElementToUnmark - 1) + "\n";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
