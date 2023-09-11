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
    private enum Stage {
        FIRST, SECOND, THIRD, FOURTH, FIFTH
    }
    private TaskList tasks = TaskList.init();
    private Stage stage = Stage.FIRST;
    private String name = null;
    private String from = null;
    private String to = null;
    private boolean isComplete = false;

    @Override
    public String processInput(String input) {
        assert input.toLowerCase().equals("event") : "user input does not start with the correct word";

        String reply = "";
        switch (stage) {
        case FIRST:
            reply = processFirstStep(input);
            break;
        case SECOND:
            reply = processSecondStep(input);
            break;
        case THIRD:
            reply = processThirdStep(input);
            break;
        case FOURTH:
            reply = processFourthStep(input);
            break;
        case FIFTH:
            reply = processLastStep(input);
            break;
        default:
            assert false : "should not reach default case in event.java";
        }
        return reply;
    }

    @Override
    public String start() {
        return "So you want to add a event task. Tell me what's the task.";
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    private String processFirstStep(String name) {
        this.name = name;
        stage = Stage.SECOND;
        return "Now indicate the start date.";
    }

    private String processSecondStep(String date) {
        try {
            from = Time.formatDate(date);
        } catch (InvalidDateException e) {
            return e.toString();
        }
        stage = Stage.THIRD;
        return "Indicate a start time in ranging from 0000 - 2359. "
                + "You may enter 'Skip' to not indicate a time";
    }

    private String processFourthStep(String date) {
        try {
            to = Time.formatDate(date);
        } catch (InvalidDateException e) {
            return e.toString();
        }
        stage = Stage.FIFTH;
        return "Indicate a start time in ranging from 0000 - 2359. "
                + "You may enter 'Skip' to not indicate a time";
    }

    private String processThirdStep(String input) {
        if (!input.toLowerCase().equals(enums.Command.SKIP.getCommand())) {
            try {
                from = Time.formatTime(from, input);
            } catch (InvalidTimeException | InvalidDateException e) {
                return e.toString();
            } catch (NumberFormatException e) {
                return "Non-numerical characters detected. Please enter numbers only. "
                        + "Returning to homepage...";
            }
        }
        stage = Stage.FOURTH;
        return "Now indicate the end date.";
    }

    private String processLastStep(String input) {
        if (!input.toLowerCase().equals(enums.Command.SKIP.getCommand())) {
            try {
                to = Time.formatTime(to, input);
            } catch (InvalidTimeException | InvalidDateException e) {
                return e.toString();
            } catch (NumberFormatException e) {
                return "Non-numerical characters detected. Please enter numbers only. "
                        + "Returning to homepage...";
            }
        }
        isComplete = true;
        return tasks.addTask(new Events(name, from, to));
    }
}
