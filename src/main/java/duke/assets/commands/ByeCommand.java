package duke.assets.commands;

import duke.assets.storage.TaskList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ByeCommand extends CommandAbstract {
    public ByeCommand(String input) {
        super(input);
    }

    @Override
    protected boolean isValid(TaskList tasklist) {
        return this.isValid();
    }

    private boolean isValid() {
        Pattern commandRegex = Pattern.compile("^bye\\s", Pattern.CASE_INSENSITIVE);
        Matcher inputMatcher = commandRegex.matcher(this.input);
        return inputMatcher.find();
    }

    @Override
    protected void completeOperation(TaskList taskList) {
        taskList.writeToFile();
        System.out.print("ChadGPT: Bye. Hope to see you again soon!\n" + HORIZONTAL);
        System.exit(0);
    }
}
