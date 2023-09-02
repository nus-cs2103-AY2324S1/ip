package com.mimi.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.mimi.commands.Command;
import com.mimi.commands.DeadlineCommand;
import com.mimi.commands.DeleteCommand;
import com.mimi.commands.EventCommand;
import com.mimi.commands.ExitCommand;
import com.mimi.commands.FindCommand;
import com.mimi.commands.InvalidCommand;
import com.mimi.commands.ListCommand;
import com.mimi.commands.MarkCommand;
import com.mimi.commands.TodoCommand;
import com.mimi.commands.UnmarkCommand;

/**
 * A class that takes given inputs from the user and interprets it for a Command.
 * @author Yuheng
 */
public class Parser {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private String input;
    private Storage storage;

    private ReadWriteData readWriteData;


    private enum ValidCommands {
        bye, list, mark, unmark, delete, todo, deadline, event, find
    }

    /**
     * Creates an instance of Parser
     * @param input the string input to be interpreted
     * @param storage an instance of the Storage class
     * @param readWriteData an instance of the ReadWriteData class
     */
    public Parser(String input, Storage storage, ReadWriteData readWriteData) {
        this.input = input;
        this.storage = storage;
        this.readWriteData = readWriteData;
    }

    /**
     * Parses the given string input and returns the appropriate command
     * @return a Command instance represented by the given input
     */
    public Command parse() {
        boolean isValidCommand = false;
        String cmd = input;

        if (cmd.contains(" ")) {
            int i = cmd.indexOf(' ');
            cmd = cmd.substring(0, i);
        }

        //TODO: make sure commands handle the case where there are no spaces present
        //check if this is a valid command

        for (ValidCommands v: ValidCommands.values()) {
            if (cmd.equals(v.toString())) {
                isValidCommand = true;
                break;
            }
        }


        if (isValidCommand) {
            switch (cmd) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand(this.storage);

            case "mark":
                return markCommand();

            case "unmark":
                return unmarkCommand();

            case "delete":
                return deleteCommand();

            case "todo":
                return todoCommand();

            case "deadline":
                return deadlineCommand();

            case "event":
                return eventCommand();

            case "find":
                return findCommand();

            default:
                return new InvalidCommand();

            }
        }

        return new InvalidCommand();
    }

    private Command findCommand() {
        String taskDescription = input;

        //first remove the find keyword
        if (!taskDescription.contains(" ")) {
            return new FindCommand();
        }
        int i = taskDescription.indexOf(' ');
        taskDescription = taskDescription.substring(i + 1);

        return new FindCommand(taskDescription, this.storage);


    }

    private Command eventCommand() {
        String temp = input;

        //first remove the event keyword
        int i = temp.indexOf(' ');
        temp = temp.substring(i + 1);

        //check if the task has specified a start and end time
        if (!temp.contains("/from") || !temp.contains("/to")) {
            return new EventCommand(false, "", LocalDateTime.MIN,
                    LocalDateTime.MIN, this.storage, this.readWriteData);
        }

        //next isolate the task name
        int j = temp.indexOf("/from");
        String taskName = temp.substring(0, j - 1);

        //next isolate the start date
        int k = temp.indexOf("/to");
        String startDate = temp.substring(j + 6, k - 1);

        //next isolate the end date
        String endDate = temp.substring(k + 4);

        try {
            LocalDateTime startTime = LocalDateTime.parse(startDate, Parser.FORMATTER);
            LocalDateTime endTime = LocalDateTime.parse(endDate, Parser.FORMATTER);
            return new EventCommand(true, taskName,
                    startTime, endTime, this.storage, this.readWriteData);
        } catch (DateTimeParseException e) {
            return new EventCommand(true, taskName,
                    LocalDateTime.MIN, LocalDateTime.MIN, this.storage, this.readWriteData);
        }



    }

    private Command deadlineCommand() {
        String temp = input;


        //first remove the deadline keyword
        int i = temp.indexOf(' ');
        temp = temp.substring(i + 1);

        //check if the task has specified a deadline time
        if (!temp.contains("/by")) {
            return new DeadlineCommand(false, "",
                    LocalDateTime.MIN, this.storage, this.readWriteData);
        }

        //next isolate the deadline
        int j = temp.indexOf("/by");
        String deadline = temp.substring(j + 4);

        //next isolate the taskName
        String taskName = temp.substring(0, j - 1);

        try {
            LocalDateTime localDateTime = LocalDateTime.parse(deadline, Parser.FORMATTER);
            return new DeadlineCommand(true, taskName,
                    localDateTime, this.storage, this.readWriteData);
        } catch (DateTimeParseException e) {
            return new DeadlineCommand(true, taskName,
                    LocalDateTime.MIN, this.storage, this.readWriteData);
        }


    }

    private Command deleteCommand() {
        String temp = input;

        try {
            int taskNumber = Integer.parseInt(temp.replaceAll("[^0-9]", ""));
            return new DeleteCommand(true, taskNumber, this.storage, this.readWriteData);
        } catch (NumberFormatException e) {
            return new DeleteCommand(false, -1, this.storage, this.readWriteData);
        }

    }

    private Command unmarkCommand() {
        String temp = input;

        try {
            int taskNumber = Integer.parseInt(temp.replaceAll("[^0-9]", ""));
            return new UnmarkCommand(true, taskNumber, this.storage, this.readWriteData);
        } catch (NumberFormatException e) {
            return new UnmarkCommand(false, -1, this.storage, this.readWriteData);
        }
    }

    private Command markCommand() {
        String temp = input;

        try {
            int taskNumber = Integer.parseInt(temp.replaceAll("[^0-9]", ""));
            return new MarkCommand(true, taskNumber, this.storage, this.readWriteData);
        } catch (NumberFormatException e) {
            return new MarkCommand(false, -1, this.storage, this.readWriteData);
        }

    }

    private Command todoCommand() {
        String temp = input;
        int i = temp.indexOf(' ');

        temp = temp.substring(i + 1);

        return new TodoCommand(temp, storage, readWriteData);
    }


}
