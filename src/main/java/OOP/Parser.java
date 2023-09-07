package OOP;

import Commands.*;
import Duke.DukeException;

public class Parser {
    /**
     * Parses the users command text and returns an appropriate command.
     *
     * @param userCommandText The user's input which is the desired command.
     * @return A Command object which has a certain execution behaviour depending on the command.
     */
    public static Command parseCommand(String userCommandText) {
            if (!userCommandText.equals("bye")) {
                if (!userCommandText.equals("list")) {
                    String[] inputWords = userCommandText.split(" ");
                    int id;
                    switch (inputWords[0]) {
                        case "todo":
                            String todoName = extractSecondWordOnwards(userCommandText);
                            if (todoName.length() == 0) {
                                throw new DukeException("\tEmpty Description");
                            }
                            return new AddToDoCommand(todoName);
                        case "deadline":
                            String[] twoParts = userCommandText.split ("/by ");
                            String deadlineName = extractSecondWordOnwards(twoParts[0]);
                            if (deadlineName.length() == 0) {
                                throw new DukeException("\tEmpty Description");
                            } else if (twoParts.length != 2) {
                                throw new DukeException("\tUsage: deadline {taskName} /by {yyyy-MM-dd HHmm}");
                            }
                            String deadlineString = twoParts[1];
                            return new AddDeadlineCommand(deadlineName, deadlineString);
                        case "event":
                            String[] threeParts = userCommandText.split ("/");
                            String eventName = extractSecondWordOnwards(threeParts[0]);
                            if (eventName.length() == 0) {
                                throw new DukeException("\tEmpty Description");
                            }
                            if (threeParts.length != 3) {
                                throw new DukeException("\tIncorrect format for event." +
                                                        "\n\tExpected usage: " +
                                                        "event {eventName} /from {eventStart} /to {eventEnd}");
                            }
                            String eventStart = extractSecondWordOnwards(threeParts[1]);
                            String eventEnd = extractSecondWordOnwards(threeParts[2]);
                            if (eventStart.length() == 0 || eventEnd.length() == 0) {
                                throw new DukeException("\tBoth event start and end date times must be specified.");
                            }
                            return new AddEventCommand(eventName, eventStart, eventEnd);
                        case "mark":
                            try {
                                id = Integer.valueOf(inputWords[1]) - 1;
                            } catch (RuntimeException e) {
                                throw new DukeException("\tExpected usage: mark {id}");
                            }
                            return new MarkTaskCommand(id);
                        case "unmark":
                            try {
                                id = Integer.valueOf(inputWords[1]) - 1;
                            } catch (RuntimeException e) {
                                throw new DukeException("\tExpected usage: unmark {id}");
                            }
                            return new UnmarkTaskCommand(id);
                        case "delete":
                            try {
                                id = Integer.valueOf(inputWords[1]) - 1;
                            } catch (RuntimeException e) {
                                throw new DukeException("\tExpected usage: delete {id}");
                            }
                            return new DeleteTaskCommand(id);
                        case "find":
                            String searchText = extractSecondWordOnwards(userCommandText);
                            return new FindTasksCommand(searchText);

                        default:
                            return new InvalidCommand();
                    }
                } else {
                    return new ListTasksCommand();
                }
            }
            return new ExitCommand();


    }

    /**
     * Returns a string that contains everything after the first whitespace character.
     *
     * @param str The original string.
     * @return secondWordOnwards The string containing only the string from the second word onwards of the original string.
     */

    public static String extractSecondWordOnwards(String str) {
        String[] wordArray = str.split(" ");
        String secondWordOnwards = wordArray.length >= 2 ? wordArray[1] : "";
        for (int i = 2; i < wordArray.length; i++)  {
            secondWordOnwards += " " + wordArray[i];
        }
        return secondWordOnwards;
    }

}
