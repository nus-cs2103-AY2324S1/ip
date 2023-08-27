package duke;

import java.util.Arrays;

public class Parser {

    public String parseActionWord(String userInput) {
        String[] userInputSegmented = userInput.split(" ");
        return userInputSegmented[0];
    }

    public int parseTaskNumber(String userInput) {
        String[] userInputSegmented = userInput.split(" ");
        return Integer.parseInt(userInputSegmented[1]) - 1;
    }

    public Task parseAddTaskInput(String userInput, String actionWord) throws InvalidTaskException {
        String[] userInputSegmented = userInput.split(" ");
        if (userInputSegmented.length == 1) {
            throw new InvalidTaskException("ERROR: The description of a " + userInputSegmented[0] +
                    " cannot be empty.");
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
