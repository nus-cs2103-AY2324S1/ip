package duke.command;
import duke.task.*;
import duke.helper.*;
public class ListCommand extends Command{
    /**
     * execute for ListCommand lists the tasks out
     * @param tasks takes in TaskList of tasks in MeowBot
     * @param ui the same object reference for the UI class
     * @param store reference
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) {
        tasks.display();
    }
}
