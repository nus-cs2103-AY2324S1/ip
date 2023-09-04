package duke.assets.commands;

import duke.data.TaskList;

public class ByeCommand extends CommandAbstract {
    public ByeCommand(String input) {
        super(input);
    }

    @Override
    protected boolean isValid() {
        return true;
    }

    @Override
    protected void completeOperation(TaskList taskList) {
        taskList.writeToFile();
        System.out.print("ChadGPT: Bye. Hope to see you again soon!\n" + horizontal);
        System.exit(0);
    }
}
