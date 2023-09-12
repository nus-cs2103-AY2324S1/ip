package duke.assets.commands;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import duke.assets.storage.TaskList;

public abstract class OperationOnListCommandAbstract extends CommandAbstract {
    public OperationOnListCommandAbstract(String input) {
        super(input);
    }

    @Override
    protected boolean isValid(TaskList tasklist) {
        Pattern commandRegex = Pattern.compile("^(mark|unmark|delete)\\s\\d+$", Pattern.CASE_INSENSITIVE);
        Matcher inputMatcher = commandRegex.matcher(this.input);
        if (!inputMatcher.find()) {
            inputMatcher.reset();
            Pattern inputStartRegex = Pattern.compile("^(mark|unmark|delete)\\s", Pattern.CASE_INSENSITIVE);
            if (inputMatcher.usePattern(inputStartRegex).find()) {
                System.out.println("Ensure that you have included the index value of the task you would like to" +
                        "alter");
            }
            return false;
        }
        return true;
    }

    @Override
    protected abstract void completeOperation(TaskList taskList);

    @Override
    public void printChatbotLine() {
        return;
    }
}
