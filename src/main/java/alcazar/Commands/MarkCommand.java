package alcazar.Commands;

import alcazar.Exceptions.InvalidSerialException;
import alcazar.Storage;
import alcazar.TaskList;

public class MarkCommand extends Command {
    private String inputPrompt;

    public MarkCommand(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }
    @Override
    public String execute(TaskList tasks,
                          Storage storage) throws InvalidSerialException {

        char characterIndexToMark = inputPrompt.charAt(inputPrompt.length() - 1);
        int intIndexOfElementToMark = Integer.parseInt( characterIndexToMark + "");

        if (intIndexOfElementToMark > tasks.size()) {
            throw new InvalidSerialException("☹ OOPS!!! I think you have added "
                    + "an incorrect serial number greater than " + (tasks.size()));
        }

        tasks.markElement(intIndexOfElementToMark - 1);
        storage.writeUp(tasks);

        return "Nice! I've marked this task as done:\n"
                + tasks.elementAt(intIndexOfElementToMark - 1) + "\n";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
