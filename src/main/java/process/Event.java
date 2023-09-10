package process;

import exception.InvalidDateException;
import exception.InvalidTimeException;
import parser.Time;
import task.Events;
import task.TaskList;

/**
 * A class for the process of creating an event task
 */
public class Event implements ComplexProcess {
    private TaskList tasks = TaskList.init();
    private String name = null;
    private String from = null;
    private String to = null;
    private boolean isCheckingTime = false;
    private boolean isComplete = false;

    @Override
    public String processInput(String input) {
        assert input.toLowerCase().equals("event") : "user input does not start with the correct word";
        if (name == null) {
            name = input;
            return "Now indicate the start date.";
        } else if (from == null) {
            try {
                from = Time.formatDate(input);
                isCheckingTime = true;
            } catch (InvalidDateException e) {
                return e.toString();
            }
            return "Indicate a start time in ranging from 0000 - 2359. "
                    + "You may enter 'Skip' to not indicate a time";
        } else if (to == null && isCheckingTime) {
            if (input.toLowerCase().equals(enums.Command.SKIP.getCommand())) {
                isCheckingTime = false;
            } else {
                try {
                    from = Time.formatTime(from, input);
                    isCheckingTime = false;
                } catch (InvalidTimeException | InvalidDateException e) {
                    return e.toString();
                } catch (NumberFormatException e) {
                    return "Non-numerical characters detected. Please enter numbers only. "
                            + "Returning to homepage...";
                }
            }
            return "Now indicate the end date.";
        } else if (to == null) {
            try {
                to = Time.formatDate(input);
                isCheckingTime = true;
            } catch (InvalidDateException e) {
                return e.toString();
            }
            return "Indicate a start time in ranging from 0000 - 2359. "
                    + "You may enter 'Skip' to not indicate a time";
        } else if (isCheckingTime) {
            if (input.toLowerCase().equals(enums.Command.SKIP.getCommand())) {
                isCheckingTime = false;
            } else {
                try {
                    to = Time.formatTime(to, input);
                    isCheckingTime = false;
                } catch (InvalidTimeException | InvalidDateException e) {
                    return e.toString();
                } catch (NumberFormatException e) {
                    return "Non-numerical characters detected. Please enter numbers only. "
                            + "Returning to homepage...";
                }
            }
        }
        isComplete = true;
        return tasks.addTask(new Events(name, from, to));
    }

    @Override
    public String start() {
        return "So you want to add a event task. Tell me what's the task.";
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }
}
