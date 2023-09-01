package blip.parser;

import blip.exceptions.*;
import blip.ui.*;
import blip.commands.*;

public class BlipParser {

    private BlipUI ui = new BlipUI();

    public static int parseToGetIndex (String input) throws EmptyTaskNumberException {
        String[] components = input.split("\\s+", 2);
        // Missing Index.
        if (components.length < 2 || components[1].equals("")) {
            throw new EmptyTaskNumberException("!!! Missing Task Number !!!");
        }
        int taskNum = Integer.parseInt(components[1]) - 1;
        return taskNum;
    }

    public static String parseToDoInfo (String input) throws EmptyDescriptionException {
        String[] components = input.split("\\s+", 2);
        // Missing To Do Description.
        if (components.length < 2 || components[1].equals("")) {
            throw new EmptyDescriptionException("!!! Missing TO DO Description !!!\n");
        }
        return components[1];
    }

    public static String[] parseDeadlineInfo (String input) throws EmptyDescriptionException {
            String[] test = input.split("\\s+", 2);
            // Missing Deadline Description.
            if (test.length < 2 || test[1].equals("")) {
                throw new EmptyDescriptionException("!!! Missing DEADLINE Description !!!");
            }
            String[] components = test[1].split("\\s*/by\\s*");
            if (components.length < 2 || components[1].equals("")) {
                throw new EmptyDescriptionException("!!! Missing DEADLINE Date Time !!!");
            }
            String description = components[0];
            String deadlineDateTime = components[1];
            return new String[] {description, deadlineDateTime};

    }

    public static String[] parseEventInfo (String input) throws EmptyDescriptionException {
        String[] test = input.split("\\s+", 2);
        // Missing Event Description.
        if (test.length < 2 || test[1].equals("")) {
            throw new EmptyDescriptionException("!!! Missing EVENT Description !!!");
        }
        String[] components = test[1].split(" /from | /to ");
        if (components.length < 3 || components[1].equals("") || components[2].equals("")) {
            throw new EmptyDescriptionException("!!! Missing EVENT Start/End Date Time !!!");
        }
        String description = components[0];
        String eventStart = components[1];
        String eventEnd = components[2];
        return new String[] {description, eventStart, eventEnd};
    }

    public static String parseFindInfo (String input) throws EmptyDescriptionException {
        String[] components = input.split("\\s+", 2);
        // Missing Find Description.
        if (components.length < 2 || components[1].equals("")) {
            throw new EmptyDescriptionException("!!! Missing FIND Description !!!\n");
        }
        return components[1];
    }


    public Command parse(String input) {
        try {
            String[] parts = input.split(" ");

            String command = parts[0].toLowerCase();
            switch (command) {
                case "bye":
                    return new ByeCommand();
                case "list":
                    return new ListCommand();
                case "delete":
                    int indexToDelete = parseToGetIndex(input);
                    return new DeleteCommand(indexToDelete);
                case "todo":
                    String toDoDescription = parseToDoInfo(input);
                    return new ToDoCommand(toDoDescription);
                case "deadline":
                    String[] deadlineInfo = parseDeadlineInfo(input);
                    return new DeadlineCommand(deadlineInfo[0], DateConverter.convertToDateTime(deadlineInfo[1]));
                case "event":
                    String[] eventInfo = parseEventInfo(input);
                    return new EventCommand(eventInfo[0], DateConverter.convertToDateTime(eventInfo[1]),
                            DateConverter.convertToDateTime(eventInfo[2]));
                case "mark":
                    int indexToMark = parseToGetIndex(input);
                    return new MarkCommand(indexToMark);
                case "unmark":
                    int indexToUnmark = parseToGetIndex(input);
                    return new UnmarkCommand(indexToUnmark);
                case "find":
                    String findDescription = parseFindInfo(input);
                    return new FindCommand(findDescription);
                default:
                    return new InvalidCommand(input);
            }
        } catch (EmptyTaskNumberException e2) {
            System.out.println(e2.getMessage());
            ui.showEmptyTaskNumErr();
        } catch (EmptyDescriptionException e3) {
            System.out.println(e3.getMessage());
            ui.showEmptyDescErr();
        } catch (DateTimeFormatException e4) {
            System.out.println(e4.getMessage());
            ui.showDateTimeFormatErr();
        }
        return new InvalidCommand(input);
    }
}
