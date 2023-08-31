package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;

public class DeleteCommand extends Command{
    public DeleteCommand(String command) {
        super(command);
    }
    @Override
    public void execute(TaskList tasks , Ui ui, Storage storage) {
        String[] words = this.fullCommand.split(" ", 2);
        try {
            tasks.deleteTask(Integer.parseInt(words[1]) - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("☹ OOPS!!! I'm sorry, please enter a valid index to mark");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
