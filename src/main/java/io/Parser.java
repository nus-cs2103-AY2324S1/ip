package io;

import exceptions.ParserException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;

public class Parser {

    public Parser() {
        inputTokens = null;
        inputString = null;
        scanner = new Scanner(System.in);
    }

    public void update() throws NoSuchElementException {
        inputString = scanner.nextLine();
        inputTokens = inputString.split(" ");
    }


    public String getInputString() {
        return this.inputString;
    }

    public String[] getInputTokens() {
        return this.inputTokens;
    }

    public int getIndex() throws IndexOutOfBoundsException {
        int index = Integer.parseInt(inputTokens[1]);
        index--;
        return index;
    }


    public Deadline parseDeadline() throws ParserException {
        Deadline result = null;

        try {
            String taskName = this.getTaskName();
            String[] parts = taskName.split("/by", 2);

            String name = parts[0];
            String endDate = parts[1];
            endDate = endDate.replace(" ", "");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(endDate, formatter);

            result = new Deadline(name, date);

        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ParserException("Please include a (/by) command, followed by a date");
        } catch (StringIndexOutOfBoundsException ex) {
            throw new ParserException(
                "Please enter a name, followed by a (/by) command, followed by a date");
        } catch (DateTimeParseException ex) {
            throw new ParserException("Please enter a time format as dd/MM/yyyy");
        }

        return result;

    }

    public Event parseEvent() throws ParserException {
        Event result = null;
        try {
            String taskName = this.getTaskName();
            String[] parts = taskName.split("/from", 2);
            String name = parts[0];
            String dates = parts[1];
            String[] datesplit = dates.split("/to", 2);
            String startDate = datesplit[0];
            String endDate = datesplit[1];

            result = new Event(name, startDate, endDate);
        } catch (StringIndexOutOfBoundsException ex) {
            throw new ParserException("The event command cannot be empty!");
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ParserException(
                "Please enter a name, followed by a (/from) command, followed by a date, followed by a (/to) command and a date");
        }
        return result;
    }


    public String getCommandString() {
        if (inputTokens.length == 0) {
            return "";
        } else {
            return inputTokens[0];
        }
    }

    public String getTaskName() {
        String commandString = this.getCommandString();
        int commandLength = commandString.length() + 1;
        return inputString.substring(commandLength);

    }

    public boolean isInputThere() {
        return inputTokens.length == 0;
    }


    private String inputString;
    private String[] inputTokens;


    private Scanner scanner;
}
