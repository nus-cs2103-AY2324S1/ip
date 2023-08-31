package OOP;

import Commands.Command;
import Commands.AddToDoCommand;
import Commands.AddDeadlineCommand;
import Commands.AddEventCommand;
import Commands.MarkTaskCommand;
import Commands.UnmarkTaskCommand;
import Commands.DeleteTaskCommand;
import Commands.InvalidCommand;
import Commands.ListTasksCommand;
import Commands.ExitCommand;
import Duke.DukeException;

public class Parser {
    public Command parseCommand(String userCommandText) {
            if (!userCommandText.equals("bye")) {
                if (!userCommandText.equals("list")) {
                    String[] inputWords = userCommandText.split(" ");
                    int id;
                    switch (inputWords[0]) {
                        case "todo":
                            String todoName = extractSecondWordOnwards(userCommandText);
                            if (todoName.length() == 0) {
                                throw new DukeException("Empty Description");
                            }
                            return new AddToDoCommand(todoName);
                        case "deadline":
                            String[] twoParts = userCommandText.split ("/by ");
                            String deadlineName = extractSecondWordOnwards(twoParts[0]);
                            if (deadlineName.length() == 0) {
                                throw new DukeException("Empty description");
                            }
                            String deadlineString = twoParts[1];
                            return new AddDeadlineCommand(deadlineName, deadlineString);
                        case "event":
                            String[] threeParts = userCommandText.split ("/");
                            String eventName = extractSecondWordOnwards(threeParts[0]);
                            if (eventName.length() == 0) {
                                throw new DukeException("Empty description");
                            }
                            try {
                                String eventStart = extractSecondWordOnwards(threeParts[1]);
                                String eventEnd = extractSecondWordOnwards(threeParts[2]);
                                return new AddEventCommand(eventName, eventStart, eventEnd);
                            } catch (RuntimeException e) {
                                throw new DukeException("Index likely out of bounds due to incorrect format of input. Expected usage: event {eventName} /from {eventStart} /to {eventEnd}");
                            }
                        case "mark":
                            id = Integer.valueOf(inputWords[1]) - 1;
                            return new MarkTaskCommand(id);
                        case "unmark":
                            id = Integer.valueOf(inputWords[1]) - 1;
                            return new UnmarkTaskCommand(id);
                        case "delete":
                            id = Integer.valueOf(inputWords[1]) - 1;
                            return new DeleteTaskCommand(id);
                        default:
                            return new InvalidCommand();
                    }
                } else {
                    return new ListTasksCommand();
                }
            }
            return new ExitCommand();


    }

    public static String extractSecondWordOnwards(String str) {
        String[] wordArray = str.split(" ");
        String secondWordOnwards = wordArray.length >= 2 ? wordArray[1] : "";
        for (int i = 2; i < wordArray.length; i++)  {
            secondWordOnwards += " " + wordArray[i];
        }
        return secondWordOnwards;
    }

}
