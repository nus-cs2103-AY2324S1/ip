package duke;

import duke.task.TaskList;
import duke.task.TaskType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a parser that parses user input.
 */
public class Parser {

    private TaskList tasks;
    private boolean isRunning;

    /**
     * Initializes the parser with the specified task list and sets the running status to true.
     *
     * @param tasks the task list to be used.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
        this.isRunning = true;
    }

    /**
     * Returns whether the parser is still running.
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Parses the user input and returns the corresponding message.
     *
     * @param repeat the user input.
     * @throws DukeException if the user input is invalid.
     */
    public String parse(String... inputs) throws DukeException {
        String repeat = inputs[0];
        Pattern markPattern = Pattern.compile("(mark|unmark|delete) (\\d+)");
        Matcher markMatcher = markPattern.matcher(repeat);
        Pattern taskPattern = Pattern.compile("(todo|deadline|event) (.+)");
        Matcher taskMatcher = taskPattern.matcher(repeat);
        Pattern findPattern = Pattern.compile("find (.+)");
        Matcher findMatcher = findPattern.matcher(repeat);
        if (repeat.equals("bye") || repeat.equals("88")) {
            this.isRunning = false;
            return "";
        } else if (findMatcher.find()) {
            return this.tasks.findTask(findMatcher.group(1));
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
