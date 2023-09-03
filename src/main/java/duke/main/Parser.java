package duke.main;

import duke.command.*;
import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    private static String[] splitText;
    public static Command parse(String commandString) throws InvalidCommandException, MissingParametersException,
            InvalidParametersException, InvalidDateFormatException, MissingCommandException {

        commandString = commandString.toLowerCase();
        splitText = commandString.split(" ", 2);
        String command = splitText[0];
        String task;

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "delete":
            checkLength();
            return new DeleteCommand(splitText[1]);
        case "todo":
            checkLength();
            return new AddCommand(new ToDo(splitText[1]));
        case "event":
            checkLength();
            LocalDate from = getDateWithCommand(splitText[1], "from");
            LocalDate to = getDateWithCommand(splitText[1], "to");
            task = getTask(splitText[1]);
            return new AddCommand(new Event(task, from, to));
        case "deadline":
            checkLength();
            LocalDate by = getDateWithCommand(splitText[1], "by");
            task = getTask(splitText[1]);
            return new AddCommand(new Deadline(task, by));
        case "mark":
            checkLength();
            return new ChangeMarkCommand(splitText[1], true);
        case "unmark":
            checkLength();
            return new ChangeMarkCommand(splitText[1], false);
        case "find":
            checkLength();

        default:
            throw new InvalidCommandException("I don't understand.");
        }
    }

    private static LocalDate getDateWithCommand(String str, String command) throws MissingCommandException,
            MissingParametersException, InvalidDateFormatException {
        boolean found = false;
        LocalDate date = null;

        String[] splitStr = str.split(" ");
        for (String word : splitStr) {
            if (!found) {
                if (word.equals("/" + command)) {
                    found = true;
                }
            } else {
                try {
                    date = LocalDate.parse(word);
                    break;
                } catch (DateTimeParseException e) {
                    throw new InvalidDateFormatException("Add a date in the foll0wing format: yyyyy-mm-dd");
                }

            }
        }

        if (!found) {
            throw new MissingCommandException("Command " + command + "could not be found");
        } else if (date == null) {
            throw new MissingParametersException("Command " + command + " does not contain any parameters");
        } else {
            return date;
        }
    }

    private static String getTask(String str) throws MissingParametersException {
        StringBuilder task = new StringBuilder();

        String[] splitStr = str.split(" ");
        for (String word : splitStr) {
            if (!word.isEmpty() && word.charAt(0) != '/') {
                task.append(" ").append(word);
            } else {
                break;
            }
        }

        if (task.length() > 0) {
            return task.substring(1);
        } else {
            throw new MissingParametersException("Task not found, please type a task >:(");
        }
    }

    private static void checkLength() throws MissingParametersException {
        if (splitText.length < 2) {
            throw new MissingParametersException("You need to add something after the command LOL");
        }
    }
}
