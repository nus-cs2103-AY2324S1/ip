package duke.assets.commands;

import duke.data.TaskList;

public class List extends Command {
    public List(String input) {
        super(input);
    }

    protected boolean isValid() {
        return true;
    }

    protected void completeOperation(TaskList taskList) {
        System.out.println("ChadGPT: Here are your tasks: ");
        taskList.listTasks();
    }
}
