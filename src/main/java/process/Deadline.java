package process;

import exception.InvalidDateException;
import exception.InvalidTimeException;
import parser.Time;
import task.Deadlines;
import task.TaskList;

public class Deadline implements ComplexProcess {
    private TaskList tasks = TaskList.init();
    private String name = null;
    private String deadline = null;
    private boolean isComplete = false;

    @Override
    public String start() {
        return "So you want to add a task with deadline. Tell me what's the task.";
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public String processInput(String input) {
        if (name == null) {
            name = input;
            return "Now indicate the deadline date.";
        } else if (deadline == null) {
            try {
                deadline = Time.formatDate(input);
                return "Indicate a start time in ranging from 0000 - 2359. "
                        + "You may enter 'Skip' to not indicate a time";
            } catch (InvalidDateException e) {
                return e.toString();
            }
        } else {
            if (!input.toLowerCase().equals(enums.Command.SKIP.getCommand())) {
                try {
                    deadline = Time.formatTime(deadline, input);

                } catch (InvalidTimeException | InvalidDateException e) {
                    return e.toString();
                } catch (NumberFormatException e) {
                    return "Non-numerical characters detected. Please enter numbers only. "
                            + "Returning to homepage...";
                }
            }
            isComplete = true;
            return tasks.addTask(new Deadlines(name, deadline));
        }
    }
}
