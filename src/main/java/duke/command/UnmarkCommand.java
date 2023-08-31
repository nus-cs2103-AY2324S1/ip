package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.task.Task;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String fullCommand) {
        super(fullCommand);
    }
    @Override
    public void execute(TaskList tasks , Ui ui, Storage storage) {
        try {
            String[] words = this.fullCommand.split(" ", 2);
            Task t = tasks.getTasks().get(Integer.parseInt(words[1]) - 1);
            t.markUndone();
            Ui.showLine();
            System.out.println("OK, I've marked this task as not done yet:");
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
