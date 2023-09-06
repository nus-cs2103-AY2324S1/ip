package duke.assets.commands;

import duke.data.TaskList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ListCommand extends CommandAbstract {
    public ListCommand(String input) {
        super(input);
    }

    @Override
    protected boolean isValid(TaskList tasklist) {
        return this.isValid();
    }

    private boolean isValid() {
        Pattern commandRegex = Pattern.compile("^list$", Pattern.CASE_INSENSITIVE);
        Matcher inputMatcher = commandRegex.matcher(this.input);
        return inputMatcher.find();
    }

    @Override
    protected void completeOperation(TaskList taskList) {
        System.out.println("ChadGPT: Here are your tasks: ");
        taskList.listTasks();
    }
}
