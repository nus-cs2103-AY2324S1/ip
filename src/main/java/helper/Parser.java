package helper;

import storage.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static TaskList taskList;

    public static void setTaskList(TaskList tasks) {
        taskList = tasks;
    }

    public static void parse(String input) throws DukeException {
        String command = input.split("\\s")[0].toUpperCase();
        String content = input.replace(input.split("\\s")[0], "");
        switch(command){
            case "BYE":
                parseBye(content);
                return;
            case "CLEAR":
                parseClear(content);
                return;
            case "DELETE":
                parseDelete(content);
                return;
            case "MARK":
                parseMark(content);
                return;
            case "UNMARK":
                parseUnmark(content);
                return;
            case "PRINT":
                parsePrint(content);
                return;
            case "TODO":
                parseTodo(content);  
                return;              
            case "EVENT":
                parseEvent(content);
                return;
            case "DEADLINE":
                parseDeadline(content);   
                return;             
            default:
                throw new DukeException("Sorry, I don't recognize this command. Please try again.");
        }  
    }

    private static void parseBye(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || content == null) {
            Ui.exit();
            return;
        } else {
            throw new DukeException("The clear command will clear all stored tasks, please try again.");
        }
        
    }

    private static void parseClear(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || content == null) {
            taskList.clear();
            return;
        } else {
            throw new DukeException("The clear command will clear all stored tasks, please try again.");
        }
    }

    private static void parseDelete(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || content == null) {
            throw new DukeException("Please specify the item number you wish to delete.");
        }
        int deleteItem = -1;
        try {
            deleteItem = Integer.valueOf(content);
        } catch (NumberFormatException e) {
            System.out.println("Please enter the item number you wish to delete.");
        }
        if (deleteItem <= 0 || deleteItem > taskList.size()) {
            throw new DukeException("Please specify the item number you wish to delete.");
        }
        taskList.delete(deleteItem);
        return;
    }

    private static void parseMark(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || content == null) {
            throw new DukeException("Please specify the item number you wish to mark.");
        }
        int markItem = -1;
        try {
            markItem = Integer.valueOf(content.substring(1));
        } catch (NumberFormatException e) {
            System.out.println("Please enter the item number you wish to mark.");
        }
        if (markItem <= 0 || markItem > taskList.size()) {
            throw new DukeException("Please specify the item number you wish to mark.");
        }
        taskList.mark(markItem, true);
        return;
    }

    private static void parseUnmark(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || content == null) {
            throw new DukeException("Please specify the item number you wish to unmark.");
        }
        int unmarkItem = -1;
        try {
            unmarkItem = Integer.valueOf(content.substring(1));
        } catch (NumberFormatException e) {
            System.out.println("Please enter the item number you wish to unmark.");
        }
        if (unmarkItem <= 0 || unmarkItem > taskList.size()) {
            throw new DukeException("Please specify the item number you wish to unmark.");
        }
        taskList.mark(unmarkItem, false);
        return;
    }

    private static void parsePrint(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || content == null) {
            taskList.print();
            return;
        }
        String time = formatTime(content.substring(1));
        if (time == null) {
            String messageSorry = "Sorry, you have entered the wrong format for time.";
            String messageFormat = "Please enter in the format of yyyy-MM-dd HH:mm:ss or yyyy-MM-dd";
            throw new DukeException(messageSorry + "\n" + messageFormat);
        }
        taskList.print(time);
        return;
    }

    private static void parseTodo(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || content == null) {
            throw new DukeException("Sorry, the todo task must have a title.");
        }
        Task todoTask = new ToDo(content);
        taskList.add(todoTask);
        return;
    }

    private static void parseEvent(String content) throws DukeException {
        //when user didn't provide title and start & end time
        if (content.isBlank() || content.isEmpty() 
        || !content.contains(" /from ") || !content.contains(" /to ") || content == null) {
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
        if (event[0].isBlank() || event[0].isEmpty() || event[0] == null) {
             throw new DukeException("Sorry, the event must have a title.");
        }
        //when user didn't provide the starting time
        if (event[1].isBlank() || event[1].isEmpty() || event[1] == null) {
            throw new DukeException("Sorry, the event must have a starting time");
        }
        //when user didn't provide the end time
        if (event[2].isBlank() || event[2].isEmpty() || event[2] == null) {
            throw new DukeException("Sorry, the event must have an end time");
        }
        //when user provide wrong time format
        String startTime = formatTime(event[1]);
        String endTime = formatTime(event[2]);
        if (startTime == null || endTime == null) {
            throw new DukeException("Please enter the time with this format: yyyy-MM-dd HH:mm:ss");
        }
        Task eventTask = new Event(event[0], startTime, endTime);
        taskList.add(eventTask);
        return;
    }

    private static void parseDeadline(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || !content.contains(" /by ") || content == null) {
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
        if (ddl[0].isBlank() || ddl[0].isEmpty() || ddl[0] == null) {
            throw new DukeException("Sorry, the deadline event must have a title.");
        }
        //when user didn't provide ddl
        if (ddl[1].isBlank() || ddl[1].isEmpty() || ddl[1] == null) {
            throw new DukeException("Sorry, the deadline event must have a dealine");
        }
        String ddlTime = formatTime(ddl[1]);
        //when user provide wrong time format
        if (ddlTime == null) {
            throw new DukeException("Please enter the time with this format: yyyy-MM-dd HH:mm:ss");
        }
        Task ddlTask = new Deadline(ddl[0], ddlTime);
        taskList.add(ddlTask);
        return;
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
