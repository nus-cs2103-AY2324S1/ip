package duke;

import java.util.Arrays;

/** Class that deals with making sense of the user command. */
public class Parser {

    /**
     * Returns the description of the task the user is trying to filter for.
     *
     * @param userInput the entire user input.
     * @return a String value containing the desired description.
     */
    public String parseFindDescription(String userInput) {
        String[] userInputSegmented = userInput.split(" ");
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < userInputSegmented.length; i++) {
            description.append(userInputSegmented[i] + " ");
        }
        return description.toString();
    }

    /**
     * Returns a string representation of the type of command the user inputted.
     *
     * @param userInput the entire user input.
     * @return a String value indicating the type of user command.
     */
    public String parseActionWord(String userInput) {
        String[] userInputSegmented = userInput.split(" ");
        return userInputSegmented[0];
    }

    /**
     * Returns the task number associated with the task.
     *
     * @param userInput the entire user input.
     * @return an integer indicating the number associated with the task.
     */
    public int parseTaskNumber(String userInput) {
        String[] userInputSegmented = userInput.split(" ");
        return Integer.parseInt(userInputSegmented[1]) - 1;
    }

    /**
     * Returns the Task the user wants to add into Duke.
     *
     * @param userInput the entire user input.
     * @param actionWord the type of task the user wants to add.
     * @return the Task the user wants to add into Duke.
     * @throws InvalidTaskException if the description of the task is missing or the task type is invalid.
     */
    public Task parseAddTaskInput(String userInput, String actionWord) throws InvalidTaskException {
        String[] userInputSegmented = userInput.split(" ");
        if (userInputSegmented.length == 1) {
            throw new InvalidTaskException("ERROR: The description of a " + userInputSegmented[0]
                    + " cannot be empty.");
        }

        switch (actionWord) {
        case "todo":
            return parseTodoInput(userInputSegmented);
        case "deadline":
            return parseDeadlineInput(userInputSegmented);
        case "event":
            return parseEventInput(userInputSegmented);
        default:
            throw new InvalidTaskException("ERROR: The task is not of a valid type");
        }
    }

    private Deadline parseDeadlineInput(String[] userInputSegmented) {
        int startIndex = 0;
        while (startIndex < userInputSegmented.length) {
            startIndex++;
            if (userInputSegmented[startIndex].equals("/by")) {
                startIndex++;
                break;
            }
        }

        return new Deadline(
                String.join(" ", Arrays.copyOfRange(userInputSegmented, 1, startIndex - 1)),
                String.join(" ", Arrays.copyOfRange(userInputSegmented, startIndex, userInputSegmented.length)));
    }

    private Event parseEventInput(String[] userInputSegmented) {
        int fromIndex = 0;
        int toIndex = 0;
        while (fromIndex < userInputSegmented.length) {
            fromIndex++;
            toIndex++;
            if (userInputSegmented[fromIndex].equals("/from")) {
                fromIndex++;
                toIndex++;
                break;
            }
        }
        while (toIndex < userInputSegmented.length) {
            toIndex++;
            if (userInputSegmented[toIndex].equals("/to")) {
                toIndex++;
                break;
            }
        }

        return new Event(
                String.join(" ", Arrays.copyOfRange(userInputSegmented, 1, fromIndex - 1)),
                String.join(" ", Arrays.copyOfRange(userInputSegmented, fromIndex, toIndex - 1)),
                String.join(" ", Arrays.copyOfRange(userInputSegmented, toIndex, userInputSegmented.length))
        );
    }

    private Todo parseTodoInput(String[] userInputSegmented) {
        return new Todo(String.join(" ", Arrays.copyOfRange(userInputSegmented, 1,
                userInputSegmented.length)));
    }
}
