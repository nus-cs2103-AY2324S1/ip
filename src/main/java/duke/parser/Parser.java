package duke.parser;

import duke.commands.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {

    public static Command parse(String input) {
        String[] split = input.split(" ", 2);
        Command c = null;

        switch (split[0]) {
            case "bye":
                c = validateExit(split);
                break;
            case "list":
                c = validateList(split);
                break;
            case "mark":
            case "unmark":
                c = validateMark(split);
                break;
            case "todo":
                c = validateTodo(split);
                break;
            case "deadline":
                c = validateDeadline(split);
                break;
            case "event":
                c = validateEvent(split);
                break;
            case "delete":
                c = validateDelete(split);
                break;
            case "find":
                c = validateFind(split);
                break;
            default:
                c = new IncorrectCommand("I'm sorry, but I don't know what that means :-(");
        }
        return c;
    }


    public static Command validateTodo(String[] split) {
        if (split.length == 1 || split[1].isBlank()) {
            return new IncorrectCommand("Please enter a valid task.");
        }
        return new AddCommand(split[1]);
    }

    public static Command validateDeadline(String[] split) {
        System.out.println(Arrays.toString(split));
        if (split.length == 1 || split[1].isBlank()) {
            return new IncorrectCommand("Please enter a valid task.");
        }
        if (!split[1].contains(" /by ")) {
            return new IncorrectCommand("Please indicate a deadline using /by");
        }

        String[] task = split[1].split(" /by ", 2);
        if (task.length <= 1 || task[1].isBlank() || task[0].isBlank()) {
            return new IncorrectCommand("Please enter a valid task and/or deadline.");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            return new AddCommand(task[0], LocalDateTime.parse(task[1], formatter));
        } catch (DateTimeParseException e) {
            return new IncorrectCommand("Please enter the date & time in DD/MM/YY HHMM format");
        }
    }

    public static Command validateEvent(String[] split) {
        if (!split[1].contains(" /from ")) {
            return new IncorrectCommand("Please indicate a start datetime using /from.");
        }

        String[] task = split[1].split(" /from ", 2);

        if (task.length <= 1 || task[1].isBlank() || task[0].isBlank()) {
            return new IncorrectCommand("Please enter a valid task.");
        }

        if (!task[1].contains(" /to ")) {
            return new IncorrectCommand("Please indicate an end datetime using /to.");
        }

        String[] to = task[1].split(" /to ", 2);

        if (to.length <= 1 || to[1].isBlank() || to[0].isBlank()) {
            return new IncorrectCommand("Please enter valid to & from dates");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            LocalDateTime from = LocalDateTime.parse(to[0], formatter);
            LocalDateTime till = LocalDateTime.parse(to[1], formatter);
            if (from.isAfter(till) || from.isEqual(till)) {
                return new IncorrectCommand("Please ensure that the start date is not equal to" +
                        " or later than the end date");
            }
            return new AddCommand(task[0], from, till);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand("Please enter the date & time in DD/MM/YY HHMM format");
        }
    }

    private  static Command validateFind(String[] split) {
        if (split.length == 1 || split[1].isBlank()) {
            return new IncorrectCommand("Please enter a valid find command.");
        }
        return new FindCommand(split[1]);
    }

    private static Command validateExit(String[] split) {
        if (split.length != 1) {
            return new IncorrectCommand("The bye command should not have " +
                    "any additional words appended to it");
        }
        return new ExitCommand();
    }

    private static Command validateList(String[] split) {
        if (split.length != 1) {
            return new IncorrectCommand("The list command should not have any " +
                    "additional words appended to it");
        }
        return new ListCommand();
    }

    private static Command validateMark(String[] split) {
        // Check if mark is receiving any input or receiving extra input
        if (split.length != 2 || split[1].isBlank()) {
            return new IncorrectCommand("Please enter a valid mark command!");
        }

        // Check if mark is not receiving a number.
        if (!Character.isDigit(split[1].charAt(0))) {
            return new IncorrectCommand("The second argument must be a digit!");
        }

        int index = Integer.parseInt(split[1]);

        // Check if index is greater than 0.
        if (index <= 0) {
            return new IncorrectCommand("Please enter a number greater than 0!");
        }

        return new MarkCommand(index, split[0]);
    }

    private static Command validateDelete(String[] split) {
        // Check if mark is receiving any input or receiving extra input
        if (split.length != 2 || split[1].isBlank()) {
            return new IncorrectCommand("Please enter a valid delete command!");
        }

        // Check if mark is not receiving a number.
        if (!Character.isDigit(split[1].charAt(0))) {
            return new IncorrectCommand("The second argument must be a digit!");
        }

        int index = Integer.parseInt(split[1]);

        // Check if index is greater than 0.
        if (index <= 0) {
            return new IncorrectCommand("Please enter a number greater than 0!");
        }

        System.out.println(index);

        return new DeleteCommand(index);
    }
}