package duke.assets.commands;

import duke.assets.storage.TaskList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public abstract class OperationOnListCommandAbstract extends CommandAbstract {
    public OperationOnListCommandAbstract(String input) {
        super(input);
    }

    protected boolean isValid(TaskList tasklist) {
        Pattern commandRegex = Pattern.compile("^(mark|unmark|delete)\\s\\d+$", Pattern.CASE_INSENSITIVE);
        Matcher inputMatcher = commandRegex.matcher(this.input);
        if (!inputMatcher.find()) {
            Pattern inputStartRegex = Pattern.compile("^(mark|unmark|delete)\\s", Pattern.CASE_INSENSITIVE);
            if (inputMatcher.usePattern(inputStartRegex).find()) {
                System.out.println("Ensure that you have included the index value of the task you would like to" +
                        "alter");
            }
            return false;
        }
        return true;
    }

    protected void completeOperation(TaskList taskList) {
        if (this.input.toLowerCase().startsWith("mark")) {
            taskList.markTaskAt(Integer.parseInt(input.split(" ")[1]) - 1);
        } else {
            taskList.unmarkTaskAt(Integer.parseInt(input.split(" ")[1]) - 1);
        }
    }
}
