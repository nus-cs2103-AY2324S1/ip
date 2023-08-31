package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String userInput) throws DukeException {
        String[] splitCommand = userInput.trim().split(" ", 2);
        switch (splitCommand[0]) {
            case "list": {
                return new ListCommand();
            }
            case "bye": {
                return new ExitCommand();
            }
            case "event": {
                // Add event task into task list
                if (!userInput.matches("event .*/from .* /to .*")) {
                    throw new DukeException("OOPS!!! The format of an event task is " +
                            "\"event TASK_DESCRIPTION /from START /to END\"");
                }
                String[] taskParts = splitCommand[1].split("/");
                return new AddCommand(new Event(taskParts[0], taskParts[1], taskParts[2]));
            }
            case "deadline": {
                // Add deadline task into task list
                return parseDeadlineCommand(splitCommand[1]);
            }
            case "todo": {
                // Add to-do task into task list
                if (!userInput.matches("todo .*")) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                return new AddCommand(new ToDo(splitCommand[1]));
            }
            case "mark": {
                // Mark tasks as done
                if (!userInput.matches("mark \\d+")) {
                    String errorMessage = "OOPS!!! The format of marking a task done is \"mark TASK_NUMBER\".\n" +
                            "Task number must exist in the task list.";
                    throw new DukeException(errorMessage);
                }
                int taskNumber = Integer.parseInt(splitCommand[1]);
                return new MarkCommand(taskNumber);
            }
            case "unmark": {
                // Mark tasks as undone
                if (!userInput.matches("unmark \\d+")) {
                    String errorMessage = "OOPS!!! The format of marking a task done is \"unmark TASK_NUMBER\".\n" +
                            "Task number must exist in the task list.";
                    throw new DukeException(errorMessage);
                }
                int taskNumber = Integer.parseInt(splitCommand[1]);
                return new UnmarkCommand(taskNumber);
            }
            case "delete": {
                // Delete task from list
                if (!userInput.matches("delete \\d+")) {
                    String errorMessage = "OOPS!!! The format of marking a task done is \"delete TASK_NUMBER\".\n" +
                            "Task number must exist in the task list.";
                    throw new DukeException(errorMessage);
                }
                int taskNumber = Integer.parseInt(splitCommand[1]);
                return new DeleteCommand(taskNumber);
            }
            default: {
                throw new DukeException();
            }
        }
    }

    public static Command parseDeadlineCommand(String stringCommand)
            throws DateTimeParseException, DukeException {
        // Add deadline task into task list
        String errorMessage = "OOPS!!! The format of a deadline task is " +
                "\"deadline TASK_DESCRIPTION /by DD/MM/YYYY 24H_TIME\"";

        if (!stringCommand.matches(".*/by \\d{1,2}/\\d{1,2}/\\d{4} \\d{4}")) {
            throw new DukeException(errorMessage);
        }

        String[] taskParts = stringCommand.split(" /by ");
        if (taskParts.length != 2) {
            throw new DukeException(errorMessage);
        }

        String[] dateAndTime = taskParts[1].split(" ");
        try {
            LocalDate date = parseDate(dateAndTime[0]);
            LocalTime time = parseTime(dateAndTime[1]);

            return new AddCommand(new Deadline(taskParts[0], date, time));
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Invalid date or time format in deadline task.");
        }
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("d/MM/yyyy"));
    }

    public static LocalTime parseTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm"));
    }
}