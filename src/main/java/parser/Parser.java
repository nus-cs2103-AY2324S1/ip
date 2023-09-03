package parser;

import commands.*;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parseCommand(String input) {
        String[] split = input.split(" ", 2);
        Command c = null;
        switch (split[0]) {
            case "bye":
                c = checkExit(split);
                break;
            case "todo":
                c = checkTodo(split);
                break;
            case "event":
                c = checkEvent(split);
                break;
            case "deadline":
                c = checkDeadline(split);
                break;
            case "unmark":
                c = checkUnmark(split);
                break;
            case "mark":
                c = checkMark(split);
                break;
            case "delete":
                c = checkDelete(split);
                break;
            case "listout":
                c = checkListout(split);
                break;
            default:
                c = new IncorrectCommand("idk what u saying.");

        }
        return c;
    }

    private static Command checkListout(String[] split) {
        if (split.length == 1) {
            return new ListoutCommand();
        } else {
            return new IncorrectCommand("why are u telling me things after listout.");
        }
    }


    private static Command checkMark(String[] split) {
        if (split.length == 1) {
            return new IncorrectCommand("You didn't tell me ur task to MARK!");
        }
        if (!Character.isDigit(split[1].charAt(split[1].charAt(0)))) {
            return new IncorrectCommand("Enter task number u wish to MARK");
        }

        int taskNumber = Integer.parseInt(split[1]);
        if (taskNumber <= 0) {
            return new IncorrectCommand("Enter valid task number.");
        } else {
            return new MarkCommand(taskNumber);
        }

    }

    public static Command checkExit(String[] split) {
        if (split.length > 1) {
            return new IncorrectCommand("Cannot have anything after bye");
        }
        return new ExitCommand();
    }

    public static Command checkTodo(String[] split) {
        if (split.length == 1) {
            return new IncorrectCommand("You didn't tell me ur task.");
        }
        return new AddCommand(split[1]);
    }

    public static Command checkDeadline(String[] split) {
        if (split.length == 1) {
            return new IncorrectCommand("You didn't tell me ur task and the deadline it's due by. BUCK UP!");
        }
        String[] deadline = split[1].split(" /by ", 2);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return new AddCommand(deadline[0], LocalDateTime.parse(deadline[1], formatter));
        } catch (DateTimeParseException e) {
            return new IncorrectCommand("Enter in format yyyy-mm-dd 24:00");
        }
    }

    public static Command checkEvent(String[] split) {
        if (split.length == 1) {
            return new IncorrectCommand("You didn't tell me ur task and the time period it's due by. BUCK UP!");
        }
        try {
            String[] task = split[1].split(" /from ", 2);
            String[] from = task[1].split(" /to ", 2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return new AddCommand(task[0], LocalDateTime.parse(from[0], formatter), LocalDateTime.parse(from[1], formatter));
        } catch (DateTimeParseException e) {
            return new IncorrectCommand("Enter in format yyyy-mm-dd 24:00");
        }
    }

    public static Command checkUnmark(String[] split) {
        if (split.length == 1) {
            return new IncorrectCommand("You didn't tell me ur task to UNMARK!");
        }
        if (!Character.isDigit(split[1].charAt(split[1].charAt(0)))) {
            return new IncorrectCommand("Enter task number u wish to unmark");
        }

        int taskNumber = Integer.parseInt(split[1]);
        if (taskNumber <= 0) {
            return new IncorrectCommand("Enter valid task number.");
        } else {
            return new UnmarkCommand(taskNumber);
        }

    }
    public static Command checkDelete(String[] split) {
        if (split.length == 1) {
            return new IncorrectCommand("You didn't tell me ur task to DELETE!");
        }
        if (!Character.isDigit(split[1].charAt(split[1].charAt(0)))) {
            return new IncorrectCommand("Enter task number u wish to DELETE");
        }

        int taskNumber = Integer.parseInt(split[1]);
        if (taskNumber <= 0) {
            return new IncorrectCommand("Enter valid task number FOR DELETE.");
        } else {
            return new DeleteCommand(taskNumber);
        }
    }

    public static Task parseFomSaveFormat(String sf) {
        String[] parts = sf.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String description = parts[2];
        if (type.equals("T")) {
            Task task = new ToDo(description);
            task.setStatus(isDone);
            return task;
        }
        String date = parts[3];
        if (type.equals("D")) {
            Task task = new Deadline(description, LocalDateTime.parse(date, formatter));
            task.setStatus(isDone);
            return task;
        }
        if (type.equals("E")) {

            String[] timeline = date.split("-", 2);
            String from = timeline[0];
            String to = timeline[1];
            Task task = new Event(description, LocalDateTime.parse(from, formatter), LocalDateTime.parse(to, formatter));
            task.setStatus(isDone);
            return task;
        }
        return null;
    }
}

