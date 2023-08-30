package duke;

import duke.task.TaskList;
import duke.task.TaskType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private TaskList tasks;
    private boolean isRunning;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
        this.isRunning = true;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public String parse(String repeat) throws DukeException {
        Pattern markPattern = Pattern.compile("(mark|unmark|delete) (\\d+)");
        Matcher markMatcher = markPattern.matcher(repeat);
        Pattern taskPattern = Pattern.compile("(todo|deadline|event) (.+)");
        Matcher taskMatcher = taskPattern.matcher(repeat);
        if (repeat.contains("bye") || repeat.contains("88")) {
            this.isRunning = false;
            return "Bye!\n\"Beware the barrenness of a busy life.\"";

        } else if (markMatcher.matches()) {
            String action = markMatcher.group(1);
            int taskIndex = Integer.parseInt(markMatcher.group(2));
            if (action.equals("delete")) {
                return this.tasks.deleteTask(taskIndex);
            } else {
                boolean isDone = markMatcher.group(1).equals("mark");
                return this.tasks.markTask(taskIndex, isDone);
            }
        } else if (taskMatcher.matches()) {
            return this.tasks.addTask(TaskType.valueOf(taskMatcher.group(1).toUpperCase()), taskMatcher.group(2));
        } else if (repeat.contains("list") || repeat.contains("List")) {
            return this.tasks.getTasks();
        } else {
            throw new DukeException("undefined");
        }
    }
}
