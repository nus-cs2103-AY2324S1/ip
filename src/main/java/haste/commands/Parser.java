package haste.commands;

import haste.data.Storage;
import haste.data.TaskList;
import haste.tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// Solution inspired by CLEON TAN DE XUAN
public class Parser {
    // used to handle type of tasks
    public static Command handleCommand(String cmd, Storage store) {
        String[] words = cmd.split("\\s+");
        Command c;

        switch (words[0]) {
        case "bye":
            c = new ExitCommand(store);
            break;
        case "list":
            c = new ListCommand();
            break;
        case "mark":
            c = verifyMark(words);
            break;
        case "unmark":
            c = verifyUnmark(words);
            break;
        case "delete":
            c = verifyDelete(words);
            break;
        case "todo":
            c = verifyTodo(cmd);
            break;
        case "deadline":
            c = verifyDeadline(cmd);
            break;
        case "event":
            c = verifyEvent(cmd);
            break;
        default:
            c = new InvalidCommand("Command should start with bye/list/mark/unmark/todo/deadline/event");
        }

        return c;
    }
    public static Command verifyDelete(String[] words) {
        int id;

        if (words.length != 2) {
            return new InvalidCommand("input (only) number after \"delete\"!");
        }
        try {
            id = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            return new InvalidCommand("only numerical input allowed!");
        }
        if (id >= TaskList.numOfTasks || id < 0) {
            return new InvalidCommand("task does not exist! input within the list of numbered tasks.");
        }

        return new DeleteCommand(id);
    }
    public static Command verifyMark(String[] words) {
        int id ;

        if (words.length != 2) {
            return new InvalidCommand("input (only) number after \"mark\"!");
        }
        try {
            id = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            return new InvalidCommand("only numerical input allowed!");
        }
        if (id >= TaskList.numOfTasks || id < 0) {
            return new InvalidCommand("task does not exist! input within the list of numbered tasks.");
        }

        return new MarkCommand(id);
    }

    public static Command verifyUnmark(String[] words) {
        int id;

        if (words.length != 2) {
            return new InvalidCommand("input (only) number after \"unmark\"!");
        }
        try {
            id = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            return new InvalidCommand("only numerical input allowed!");
        }
        if (id >= TaskList.numOfTasks || id < 0) {
            return new InvalidCommand("task does not exist! input within the list of numbered tasks.");
        }

        return new UnmarkCommand(id);
    }

    public static Command verifyTodo(String cmd) {
        Pattern pt = Pattern.compile("todo( (.+))?"); // ( ...)? is optional group
        Matcher mt = pt.matcher(cmd);
        mt.find();

        String desc = mt.group(2);

        if (Task.checkEmpty(desc)) { // check if desc is null
            return new InvalidCommand("Description of ToDo cannot be empty!");
        }

        return new AddCommand(desc);
    }

    public static Command verifyDeadline(String cmd) {
        Pattern pt = Pattern.compile("deadline(( (.*) )?/by( (.*))?)?");
        Matcher mt = pt.matcher(cmd);
        mt.find();

        String overall = mt.group(1);
        String desc = mt.group(3);
        LocalDateTime end;

        if (Task.checkEmpty(overall)|| Task.checkEmpty(desc)) {
            return new InvalidCommand("Description of Deadline cannot be empty!");
        }
        try {
            end = Parser.parseTime(mt.group(5));
        } catch (DateTimeParseException e) {
            return new InvalidCommand("Date Time should be in yyyy-MM-dd HHmm format!");
        }
        return new AddCommand(desc, end);
    }

    public static Command verifyEvent(String cmd) {
        Pattern pt = Pattern.compile("event(( (.*) )?/from( (.*) )?/to( (.*))?)?");
        Matcher mt = pt.matcher(cmd);
        mt.find();

        String overall = mt.group(1);
        String desc = mt.group(3);
        LocalDateTime start;
        LocalDateTime end;

        if (Task.checkEmpty(overall) || Task.checkEmpty(desc)) {
            return new InvalidCommand("Description of Event cannot be empty!");
        }
        try {
            start = Parser.parseTime(mt.group(5));
            end = Parser.parseTime(mt.group(7));
        } catch (DateTimeParseException e) {
            return new InvalidCommand("Date Time should be in yyyy-MM-dd HHmm format!");
        }

        return new AddCommand(desc, end, start);
    }

    // time related
    // convert cmd into local date time
    public static LocalDateTime parseTime(String input) throws DateTimeParseException {
        // formatting command input into LocalDateTime args
        String formatPattern = "yyyy-MM-dd HHmm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        LocalDateTime parsedTime = LocalDateTime.parse(input, formatter);

        return parsedTime;
    }
    // convert local date time into printable string
    public static String formatTime(LocalDateTime input) {
        // format time into a string object components
        String year = String.valueOf(input.getYear());
        String month = input.getMonth().toString();
        String day = String.valueOf(input.getDayOfMonth());
        int hourAndTime = input.getHour() * 100 + input.getMinute();
        // ensure that hourAndTime would always have 4 digits
        return hourAndTime < 1000
                ? month + " " + day + " " + year + " 0" + (hourAndTime)
                : month + " " + day + " " + year + " " + (hourAndTime);
    }
    //converts local date time back to command format
    public static String getCmd(LocalDateTime savedTime) {
        // format LocalDateTime back into command format
        int year = savedTime.getYear();
        String month = savedTime.getMonthValue() >= 10 ? String.valueOf(savedTime.getMonthValue())
                : "0" + savedTime.getMonthValue();
        int day = savedTime.getDayOfMonth();
        // ensure that hourAndTime would always have 4 digits
        String hourAndTime = savedTime.getHour() >= 10
                ? String.valueOf(savedTime.getHour() * 100 + savedTime.getMinute())
                : "0" + (savedTime.getHour() * 100 + savedTime.getMinute());

        return year + "-" + month + "-" + day + " " + hourAndTime;
    }
}
