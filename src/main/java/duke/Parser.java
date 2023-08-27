package duke;

import java.util.Arrays;

import static java.lang.String.join;

public class Parser {

    public String parseFindDescription(String userInput) {
        String[] userInputSegmented = userInput.split(" ");

        StringBuilder joinedString = new StringBuilder();
        for (int i = 1; i < userInputSegmented.length; i++) {
            joinedString.append(userInputSegmented[i]);
            if (i < userInputSegmented.length - 1) {
                joinedString.append(" ");
            }
        }

        return joinedString.toString();
    }
    public String parseActionWord(String userInput) {
        String[] userInputSegmented = userInput.split(" ");
        return userInputSegmented[0];
    }

    public int parseTaskNumber(String userInput) throws InvalidInputException {
        String[] userInputSegmented = userInput.split(" ");
        if (userInputSegmented.length <= 1) {
            throw new InvalidInputException("ERROR: Invalid Input");
        }
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
                join(" ", Arrays.copyOfRange(userInputSegmented, 1, startIndex - 1)),
                join(" ", Arrays.copyOfRange(userInputSegmented, startIndex, userInputSegmented.length)));
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
                join(" ", Arrays.copyOfRange(userInputSegmented, 1, fromIndex - 1)),
                join(" ", Arrays.copyOfRange(userInputSegmented, fromIndex, toIndex - 1)),
                join(" ", Arrays.copyOfRange(userInputSegmented, toIndex, userInputSegmented.length))
        );
    }

    private Todo parseTodoInput(String[] userInputSegmented) {
        return new Todo(join(" ", Arrays.copyOfRange(userInputSegmented, 1,
                userInputSegmented.length)));
    }
}
