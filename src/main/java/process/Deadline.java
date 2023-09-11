package process;

import exception.InvalidDateException;
import exception.InvalidTimeException;
import parser.Time;
import task.Deadlines;
import task.TaskList;

/**
 * A class for the process of creating a deadline task
 */
public class Deadline implements ComplexProcess {
    private enum Stage {
        FIRST, SECOND, THIRD
    }
    private TaskList tasks = TaskList.init();
    private Stage stage = Stage.FIRST;
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
        assert input.toLowerCase().equals("deadline") : "user input does not start with the correct word";

        String reply = "";

        switch (stage) {
        case FIRST:
            reply = processFirstStep(input);
            break;
        case SECOND:
            reply = processSecondStep(input);
            break;
        case THIRD:
            reply = processLastStep(input);
            break;
        default:
            assert false : "should not reach default case in deadline.java";
        }
        return reply;
    }

    private String processFirstStep(String input) {
        name = input;
        stage = Stage.SECOND;
        return "Now indicate the deadline date.";
    }

    private String processSecondStep(String input) {
        try {
            deadline = Time.formatDate(input);
        } catch (InvalidDateException e) {
            return e.toString();
        }
        stage = Stage.THIRD;
        return "Indicate a start time in ranging from 0000 - 2359. "
                + "You may enter 'Skip' to not indicate a time";
    }

    private String processLastStep(String input) {
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
