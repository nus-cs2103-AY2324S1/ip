package duke.command;

import duke.Ui;
import duke.task.TaskList;
import duke.Duke;

public class UnmarkCommand extends Command{

    private int index;
    public UnmarkCommand (int i) {
        index = i;
    }

    @Override
    public void execute(TaskList list) {
        list.unmark(index);
        Ui.ui.unmarkPrompt(list.list().get(index));
        Duke.run();
    }

    public int index() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UnmarkCommand) {
            UnmarkCommand temp = (UnmarkCommand) o;
            return this.index == temp.index();
        }
        return false;
    }
}
