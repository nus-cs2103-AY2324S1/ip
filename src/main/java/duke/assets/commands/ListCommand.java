package duke.assets.commands;

import duke.data.TaskList;

public class ListCommand extends CommandAbstract {
    public ListCommand(String input) {
        super(input);
    }

    @Override
    protected boolean isValid() {
        return true;
    }

    @Override
    protected void completeOperation(TaskList taskList) {
        System.out.println("ChadGPT: Here are your tasks: ");
        taskList.listTasks();
    }
}
