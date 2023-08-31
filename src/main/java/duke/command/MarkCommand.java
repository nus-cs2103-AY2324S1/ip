package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.task.Task;

public class MarkCommand extends Command {
    public MarkCommand(String fullCommand) {
        super(fullCommand);
    }
    @Override
    public void execute(TaskList tasks , Ui ui, Storage storage) {
        try {
            String[] words = this.fullCommand.split(" ", 2);
            Task t = tasks.getTasks().get(Integer.parseInt(words[1]) - 1);
            t.markDone();
            Ui.showLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(t);
            Ui.showLine();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("☹ OOPS!!! I'm sorry, please enter a valid index to mark");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
