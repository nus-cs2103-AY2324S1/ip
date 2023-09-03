package duke.main;

import duke.exceptions.InvalidArgumentException;


public class Parser {

    protected String getCommand(String input) {
        return input.split(" ")[0];
    }

    protected String parseToDo(String input) throws InvalidArgumentException {
        int indexOfSpace = input.indexOf(" ");
        if (indexOfSpace == -1 || indexOfSpace == input.length() - 1) {
            throw new InvalidArgumentException("Please enter a task description.");
        }
        String taskName = input.substring(input.indexOf(" ") + 1).trim();
        if (taskName.isEmpty()) {
            throw new InvalidArgumentException("Please enter a task description.");
        }

        return taskName;
    }

    protected String[] parseDeadline(String input) throws InvalidArgumentException {
        String suffix = input.substring(input.indexOf(" ") + 1);
        String[] parts = suffix.split(" /due ");
        if (parts.length != 2) {
            throw new InvalidArgumentException("Invalid format for deadline. " +
                    "Please use: deadline task name /due due Date");
        }
        String taskName = parts[0].trim();
        String dueDate = parts[1].trim();
        return new String[]{taskName, dueDate};
    }

    protected String[] parseEvent(String input) throws InvalidArgumentException {
        String suffix = input.substring(input.indexOf(" ") + 1);
        String[] parts = suffix.split(" /from ");
        if (parts.length != 2) {
            throw new InvalidArgumentException("Invalid format for event. " +
                    "Please use: event task_name /from start /to end");
        }
        String taskName = parts[0].trim();
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length != 2) {
            throw new InvalidArgumentException("Invalid format for event. " +
                    "Please use: event task_name /from start /to end");
        }
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        return new String[]{taskName, from, to};
    }

}