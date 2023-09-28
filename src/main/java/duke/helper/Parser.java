package duke.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.storage.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Parser class for taking in user input and analyzing it
 */
public class Parser {
    private static TaskList taskList;

    /**
    * initializes the task list
    *
    * @param tasks the task list to be initialized
    */
    public static void setTaskList(TaskList tasks) {
        taskList = tasks;
    }

    /**
    * categorises command based on user input
    *
    * @param input the user input
    * @return the bot response
    */
    public static String parse(String input) throws DukeException {
        String command = input.split("\\s")[0].toUpperCase();
        String content = input.replace(input.split("\\s")[0], "");
        switch(command) {
        case "BYE":
            return parseBye(content);
        case "CLEAR":
            return parseClear(content);
        case "DELETE":
            return parseDelete(content);
        case "MARK":
            return parseMark(content);
        case "UNMARK":
            return parseUnmark(content);
        case "FIND":
            return parseFind(content);
        case "PRINT":
            return parsePrint(content);
        case "TODO":
            return parseTodo(content);
        case "EVENT":
            return parseEvent(content);
        case "DEADLINE":
            return parseDeadline(content);
        default:
            throw new DukeException("Sorry, I don't recognize this command. Please try again.");
        }
    }

    /**
    * executes the bye command and checks for exceptions
    *
    * @param content the message of the command
    * @return the bot response
    */
    private static String parseBye(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || content == null) {
            return Ui.exit();
        } else {
            throw new DukeException("The clear command will clear all stored tasks, please try again.");
        }
    }

    /**
    * executes the clear command and checks for exceptions
    *
    * @param content the message of the command
    * @return the bot response
    */
    private static String parseClear(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || content == null) {
            return taskList.clear();
        } else {
            throw new DukeException("The clear command will clear all stored tasks, please try again.");
        }
    }

    /**
    * executes the delete command and checks for exceptions
    *
    * @param content the message of the command
    * @return the bot response
    */
    private static String parseDelete(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || content == null) {
            throw new DukeException("Please specify the item number you wish to delete.");
        }
        int deleteItem = -1;
        try {
            deleteItem = Integer.valueOf(content.substring(1));
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter the item number you wish to delete.");
        }
        if (deleteItem <= 0 || deleteItem > taskList.size()) {
            throw new DukeException("Please specify the item number you wish to delete.");
        }
        return taskList.delete(deleteItem);
    }

    /**
    * executes the mark command and checks for exceptions
    *
    * @param content the message of the command
    */
    private static String parseMark(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || content == null) {
            throw new DukeException("Please specify the item number you wish to mark.");
        }
        int markItem = -1;
        try {
            markItem = Integer.valueOf(content.substring(1));
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter the item number you wish to mark.");
        }
        if (markItem <= 0 || markItem > taskList.size()) {
            throw new DukeException("Please specify the item number you wish to mark.");
        }
        return taskList.mark(markItem, true);
    }

    /**
    * executes the unmark command and checks for exceptions
    *
    * @param content the message of the command
    * @return the bot response
    */
    private static String parseUnmark(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || content == null) {
            throw new DukeException("Please specify the item number you wish to unmark.");
        }
        int unmarkItem = -1;
        try {
            unmarkItem = Integer.valueOf(content.substring(1));
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter the item number you wish to unmark.");
        }
        if (unmarkItem <= 0 || unmarkItem > taskList.size()) {
            throw new DukeException("Please specify the item number you wish to unmark.");
        }
        return taskList.mark(unmarkItem, false);
    }

    /**
    * executes the find command and checks for exceptions
    *
    * @param content the message of the command
    * @return the bot response
    */
    private static String parseFind(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || content == null) {
            throw new DukeException("Please specify the keyword you wish to find.");
        }
        String keyword = content.substring(1);
        return taskList.find(keyword);
    }

    /**
    * executes the print command and checks for exceptions
    *
    * @param content the message of the command
    * @return the bot response
    */
    private static String parsePrint(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || content == null) {
            return taskList.print();
        }
        String time = formatTime(content.substring(1));
        if (time == null) {
            String messageSorry = "Sorry, you have entered the wrong format for time.";
            String messageFormat = "Please enter in the format of yyyy-MM-dd HH:mm:ss or yyyy-MM-dd";
            throw new DukeException(messageSorry + "\n" + messageFormat);
        }
        return taskList.print(time);
    }

    /**
    * executes the todo command and checks for exceptions
    *
    * @param content the message of the command
    * @return the bot response
    */
    private static String parseTodo(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || content == null) {
            throw new DukeException("Sorry, the todo task must have a title.");
        }
        Task todoTask = new ToDo(content);
        return taskList.add(todoTask);
    }

    /**
    * executes the event command and checks for exceptions
    *
    * @param content the message of the command
    * @return the bot response
    */
    private static String parseEvent(String content) throws DukeException {
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
        } catch (StringIndexOutOfBoundsException e) {
            return ("Sorry, this event must have a title, start time, and end time.");
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
        return taskList.add(eventTask);
    }

    /**
    * executes the deadline command and checks for exceptions
    *
    * @param content the message of the command
    * @return the bot response
    */
    private static String parseDeadline(String content) throws DukeException {
        if (content.isBlank() || content.isEmpty() || !content.contains(" /by ") || content == null) {
            throw new DukeException("Sorry, the deadline task must have a title and a deadline.");
        }
        String[] ddl = new String[2];
        try {
            ddl = content.split(" /by ");
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Sorry, this deadline task must have a title and a deadline.";
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
        return taskList.add(ddlTask);
    }

    /**
    * format date-time string input into another date-time format
    *
    * @param input the message to be translated into another date-time format
    * @return the reformatted time string
    */
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

    /**
    * dummy method for testing parse
    *
    * @param input the user input
    * @return the detected command
    */
    public static String parseTest(String input) throws DukeException {
        String testCommand = input.split("\\s")[0].toUpperCase();
        return testCommand;
    }
}
