package duke.commands;


import duke.exception.DukeException;

/**
 * An abstract class for all commands.
 */
public abstract class Command {
    /**
     * An abstract method that executes the command the user gives.
     * @throws DukeException
     */
    public abstract void execute() throws DukeException;

    /**
     * A function to obtain the description of the task at hand.
     *
     * @param task The task to get description from.
     * @param input The input to obtain the type of task.
     * @return description of task.
     * @throws DukeException throws an error when string input is not compatible.
     */
    public static String getDescription(String task, String input) throws DukeException {
        try {
            if (task.equalsIgnoreCase("todo")) {
                // check that string contains task to prevent indexOutOfBoundsException
                if (input.length() < 5) {
                    throw new DukeException("You are missing the task to do. This is unacceptable.");
                }
                String description = input.substring(5);
                return description;
            } else if (task.equalsIgnoreCase("deadline")) {
                int endIndex = input.indexOf("/by");
                if (input.length() < 9 || endIndex == -1) {
                    throw new DukeException("You are missing the deadline. This is unacceptable.");
                }
                String description = input.substring(9, endIndex);
                return description;
            } else if (task.equalsIgnoreCase("event")) {
                int endIndex = input.indexOf("/from");
                if (input.length() < 6 || endIndex == -1) {
                    throw new DukeException("You are missing the event. This is unacceptable.");
                }
                String description = input.substring(6, endIndex);
                return description;
            } else {
                throw new DukeException("You are missing the task. This is unacceptable.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        //unreachable task
        return "String provided has invalid format";
    }

    /**
     * A function to obtain the start date of the task at hand.
     *
     * @param input The String to obtain the start date of the event from.
     * @return The start date of the event.
     * @throws DukeException throws an error when string input is not compatible.
     */
    public static String getStartDate(String input) throws DukeException {
        String startWord = "/from";
        String endWord = "/to";
        int startIndex = input.indexOf(startWord) + startWord.length() + 1;
        int endIndex = input.indexOf(endWord);

        try {
            if (startIndex == input.length()) {
                throw new DukeException("You are missing the keyword " + startWord + ". This is unacceptable.");
            } else if (startIndex > input.length()) {
                throw new DukeException("You are missing the start date. This is unacceptable.");
            } else if (endIndex == -1) {
                throw new DukeException("You are missing the keyword " + endWord + ". This is unacceptable.");
            } else if (startIndex > endIndex) {
                throw new DukeException("You are missing the end date. This is unacceptable.");
            }
            return input.substring(startIndex, endIndex);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        // unreachable statement
        return "Error, invalid string input";
    }

    /**
     * A function that returns the end date of task.
     *
     * @param task The task at hand.
     * @param input The input to obtain the end date from.
     * @return The end date we wish to obtain.
     * @throws DukeException throws an error when string input is not compatible.
     */
    public static String getEndDate(String task, String input) throws DukeException {
        String startWord = task.equalsIgnoreCase("deadline") ? "/by" : "/to";
        int startIndex = input.indexOf(startWord) + startWord.length() + 1;

        if (startIndex > input.length()) {
            throw new DukeException("You are missing the end date. This is unacceptable.");
        }
        return input.substring(startIndex);
    }
}
