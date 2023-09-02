package duke.assets.commands;

import duke.data.TaskList;
import duke.assets.tasks.TaskAbstract;

import java.io.IOException;

public class Bye extends Command {
    public Bye(String input) {
        super(input);
    }

    protected boolean isValid() {
        return true;
    }

    protected void completeOperation(TaskList taskList) {
        taskList.writeToFile();
        System.out.print("ChadGPT: Bye. Hope to see you again soon!\n" + horizontal);
        System.exit(0);
    }
}
