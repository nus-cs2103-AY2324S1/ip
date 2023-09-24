package parser;

import command.*;
import exception.DukeException;
import ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Parser {
    Ui ui = new Ui();
    private enum Instruction {
        BYE,
        CLEAR,
        DELETE,
        MARK,
        UNMARK,
        PRINT,
        TODO,
        EVENT,
        DEADLINE
    }    

    public static Command parse(String input) throws DukeException {
        String command = input.split("\\s")[0].toUpperCase();
        String content = input.replace(input.split("\\s")[0], "");
        switch(command){
            case "BYE":
                return ByeCommand(content);
            case "CLEAR":
                return ClearCommand(content);
            case "DELETE":
                return parseDeleteCommand(content);
            case "MARK":
                return parseMarkCommand(content);
            case "UNMARK":
                return parseUnmarkCommand(content);
            case "PRINT":
                return parsePrintCommand(content);
            case "TODO":
                return parseTodoCommand(content);                
            case "EVENT":
                return parseEventCommand(content);
            case "DEALDINE":
                return parseDeadlineCommand(content);                
            default:
                throw new DukeException("Sorry, I don't recognize this command. Please try again.");
        }  
    }

    public static Commmand parseDeleteCommand (String content) {
        if (content.isBlank() || content.isEmpty()) {
            throw new DukeException("Please specify the item number you wish to delete.");
        }
        int deleteItem;
        try {
            deleteItem = Integer.valueOf(content);
        } catch (NumberFormatException e) {
            System.out.println("Please enter the item number you wish to delete.");
        }
        return deleteCommand(deleteItem);
    }

    public static Commmand parseMarkCommand (String content) {
        if (content.isBlank() || content.isEmpty()) {
            throw new DukeException("Please specify the item number you wish to mark.");
        }
        int markItem;
        try {
            markItem = Integer.valueOf(content);
        } catch (NumberFormatException e) {
            System.out.println("Please enter the item number you wish to mark.");
        }
        return markCommand(markItem, true);
    }

    public static Commmand parseUnmarkCommand (String content) {
        if (content.isBlank() || content.isEmpty()) {
            throw new DukeException("Please specify the item number you wish to unmark.");
        }
        int unmarkItem;
        try {
            unmarkItem = Integer.valueOf(content);
        } catch (NumberFormatException e) {
            System.out.println("Please enter the item number you wish to unmark.");
        }
        return markCommand(unmarkItem, false);
    }

    public static Commmand parsePrintCommand (String content) {
        boolean printAll = false;
        if (content.isBlank() || content.isEmpty()) {
            printAll = true;
            return printCommand(printAll, content);
        }
        String time = formatTime(content);
        if (time == null) {
            String messageSorry = "Sorry, you have entered the wrong format for time.";
            String messageFormat = "Please enter in the format of yyyy-MM-dd HH:mm:ss or yyyy-MM-dd";
            throw new DukeException(messageSorry + "\n" + messageFormat);
        }
        return printComand(printAll, time);
    }

    public static Command parseTodoCommand (String content) throws DukeException {
        if (content.isBlank() || content.isEmpty()) {
            throw new DukeException("Sorry, the todo task must have a title.");
        }
        return new TodoCommand(content);
    }

    public static Command parseEventCommand (String content) throws DukeException {
        //when user didn't provide title and start & end time
        if (content.isBlank() || content.isEmpty() 
        || !content.contains(" /from ") || !content.contains(" /to ")) {
            throw new DukeException("Sorry, this event must have a title, start time, and end time.");
        }
        String[] event = new String[3];
        try {
            event[0] = content.substring(1, content.indexOf(" /"));
            event[1] = content.substring(content.indexOf("/from") + 6, 
            content.indexOf(" /to"));
            event[2] = content.substring(content.indexOf("/to") + 4);
        }
        catch (StringIndexOutOfBoundsException e) {
            System.out.println("Sorry, this event must have a title, start time, and end time.");
        }
        //when user provide empty title
        if (event[0].isBlank() || event[0].isEmpty()) {
             throw new DukeException("Sorry, the event must have a title.");
        }
        //when user didn't provide the starting time
        if (event[1].isBlank() || event[1].isEmpty()) {
            throw new DukeException("Sorry, the event must have a starting time");
        }
        //when user didn't provide the end time
        if (event[2].isBlank() || event[2].isEmpty()) {
            throw new DukeException("Sorry, the event must have an end time");
        }
        //when user provide wrong time format
        String startTime = formatTime(event[1]);
        String endTime = formatTime(event[2]);
        if (startTime.isBlank() || startTime.isEmpty() || endTime.isBlank() || endTime.isEmpty()) {
            throw new DukeException("Please enter the time with this format: yyyy-MM-dd HH:mm:ss");
        }
        return new EventCommand(event[0], startTime, endTime);
    }

    public static Command parseDeadlineCommand (String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || !content.contains(" /by ")) {
            throw new DukeException("Sorry, the deadline task must have a title and a deadline.");
        }
        String[] ddl = new String[2];
        try {
            ddl = content.split(" /by ");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Sorry, this deadline task must have a title and a deadline.");
        }
        //when user provide empty title
        if (ddl[0].isBlank() || ddl[0].isEmpty()) {
            throw new DukeException("Sorry, the deadline event must have a title.");
        }
        //when user didn't provide ddl
        if (ddl[1].isBlank() || ddl[1].isEmpty()) {
            throw new DukeException("Sorry, the deadline event must have a dealine");
        }
        String ddlTime = formatTime(ddl[1]);
        //when user provide wrong time format
        if (ddlTime.isBlank() || ddlTime.isEmpty()) {
            throw new DukeException("Please enter the time with this format: yyyy-MM-dd HH:mm:ss");
        }

        return new DeadlineCommand(ddl[0], ddlTime);
    }

    private static String formatTime(String input) {
        try {
            // when input have a date and a time
            // convert string to LocalDateTime then convert to another format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
            return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        } catch (DateTimeParseException notDateTime) {
            try {
                // input is only a date
                LocalDate date = LocalDate.parse(input);
                return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException notDate) {
                try {
                    // input is only a time
                    LocalTime time = LocalTime.parse(input);
                    return time.format(DateTimeFormatter.ofPattern("HH:mm"));
                } catch (DateTimeParseException nullError) {
                    return null;
                }
            }
        }
    }
}
