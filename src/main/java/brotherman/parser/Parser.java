package brotherman.parser;

import brotherman.commands.AddCommand;
import brotherman.commands.Command;
import brotherman.commands.ExitCommand;
import brotherman.commands.ListCommand;
import brotherman.commands.MarkDoneCommand;
import brotherman.commands.UnmarkCommand;
import brotherman.commands.DeleteCommand;

import brotherman.tasks.Todo;
import brotherman.tasks.Event;
import brotherman.tasks.Deadline;

import brotherman.exceptions.BrothermanException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String userCommand) throws BrothermanException {

        if (userCommand.equals("bye")) {
            return new ExitCommand();
        }

        isValidCommands(userCommand);

        if (userCommand.equals("list")) {
            return new ListCommand();

        } else if (userCommand.split(" ")[0].equals("mark")) {
            int task = Integer.parseInt(userCommand.split(" ")[1]) - 1;
            return new MarkDoneCommand(task);

        } else if (userCommand.split(" ")[0].equals("unmark")) {
            int task = Integer.parseInt(userCommand.split(" ")[1]) - 1;
            return new UnmarkCommand(task);

        } else if (userCommand.split(" ")[0].equals("delete")) {

            int task = Integer.parseInt(userCommand.split(" ")[1]) - 1;
            return new DeleteCommand(task);

        } else if (userCommand.split(" ")[0].equals("todo")) {
            return new AddCommand(parseTodo(userCommand.split("todo")[1]));

        } else if (userCommand.split(" ")[0].equals("deadline")) {

            String desc = userCommand.split("deadline|/by")[1].trim();
            String time = userCommand.split("/by")[1].trim();

            return new AddCommand(parseDeadline(desc, time));

        } else if (userCommand.split(" ")[0].equals("event")) {

            String desc = userCommand.split("event|/from")[1].trim();
            String[] parts = userCommand.split("\\s*/from\\s*|\\s*/to\\s*");
            String startTime = parts[1];
            String endTime = parts[2];

            return new AddCommand(parseEvent(desc, startTime, endTime));
        } else {
            System.out.println("brotherman input a todo, event or deadline!");
        }

        return new ListCommand();
    }

    public static boolean isValidCommands(String command) throws BrothermanException {
        if (command.equals("list")) {
            return true;
        }

        if (command.split(" ")[0].equals("mark") && command.split(" ").length <= 1) {
            throw new BrothermanException("Error! Something has to be marked!");
        }

        if (command.split(" ")[0].equals("unmark") && command.split(" ").length <= 1) {
            throw new BrothermanException("Error! Something has to be unmarked!");
        }

        if (command.split(" ")[0].equals("todo") && command.split(" ").length <= 1) {
            throw new BrothermanException("Error! There must be a description!!!");
        }

        if (command.split(" ")[0].equals("deadline") && command.split(" ")[1].equals("/by")) {
            throw new BrothermanException("Error! There has to be a description!!");
        }

        if (command.split(" ")[0].equals("deadline") && !command.contains("/by")) {
            throw new BrothermanException("Error! There has to be a due date/time!");
        }

        if (command.split(" ")[0].equals("event") && !command.contains("/from")) {
            throw new BrothermanException("Error! There has to be a start date/time!");
        }

        if (command.split(" ")[0].equals("event") && !command.contains("/to")) {
            throw new BrothermanException("Error! There has to be a end date/time!");
        }

        if (command.split(" ")[0].equals("event") && command.split(" ")[1].equals("/from")) {
            throw new BrothermanException("Error! There has to be a description");
        }
        return true;
    }

    public static Todo parseTodo(String description) {
        return new Todo(description);
    }

    public static Deadline parseDeadline(String description, String time) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "[yyyy-MM-dd HH:mm:ss][yyyy-MM-dd][MM/dd/yyyy HH:mm:ss][dd/MM/yyyy][d MMM yyyy HHmm]"
        );

        LocalDateTime dateTimeObj = null;


        try {
            dateTimeObj = LocalDateTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            // If datetime parsing fails, try parsing as date-only
            try {
                LocalDate date = LocalDate.parse(time, formatter);
                dateTimeObj = date.atStartOfDay();
            } catch (DateTimeParseException ex) {
                System.out.println("Error parsing datetime: " + ex.getMessage());
            }
        }

        return new Deadline(description, dateTimeObj);
    }

    public static Event parseEvent(String description, String startTime, String endTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "[yyyy-MM-dd HH:mm:ss][yyyy-MM-dd][MM/dd/yyyy HH:mm:ss][dd/MM/yyyy][d MMM yyyy HHmm]"
        );

        LocalDateTime startDateTimeObj = null;
        LocalDateTime endDateTimeObj = null;


        try {
            startDateTimeObj = LocalDateTime.parse(startTime, formatter);
            endDateTimeObj = LocalDateTime.parse(endTime, formatter);
        } catch (DateTimeParseException e) {
            // If datetime parsing fails, try parsing as date-only
            try {
                LocalDate startDate = LocalDate.parse(startTime, formatter);
                LocalDate endDate = LocalDate.parse(endTime, formatter);
                startDateTimeObj = startDate.atStartOfDay();
                endDateTimeObj = endDate.atStartOfDay();
            } catch (DateTimeParseException ex) {
                System.out.println("Error parsing datetime: " + ex.getMessage());
            }
        }

        return new Event(description, startDateTimeObj, endDateTimeObj);
    }
}
