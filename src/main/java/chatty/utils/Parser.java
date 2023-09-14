package chatty.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import chatty.command.Command;
import chatty.command.CommandNotFound;
import chatty.command.DeadlineCommand;
import chatty.command.DeleteCommand;
import chatty.command.DoneCommand;
import chatty.command.EventCommand;
import chatty.command.ExitCommand;
import chatty.command.FindCommand;
import chatty.command.ListCommand;
import chatty.command.ToDoCommand;
import chatty.command.UndoneCommand;
import chatty.exception.DetailsUnknownException;
import chatty.exception.IncompleteMessageException;
import chatty.exception.InvalidTaskNumberException;

/**
 * Responsible to do the parsing required for the chatbot
 */
public class Parser {

    /**
     * return the specific command to execute depending on the command given by the user
     * @param input the input by user
     * @return The specific command
     * @throws DetailsUnknownException when the user did not enter the details for the task
     * @throws IncompleteMessageException when the user did not enter the task description
     * @throws InvalidTaskNumberException when the task number entered by the user is out of the range
     */
    public static Command parse(String input) throws DetailsUnknownException,
            IncompleteMessageException, InvalidTaskNumberException {

        String exitC = "bye";
        String listC = "list";
        String deleteC = "delete";
        String doneC = "mark";
        String undoneC = "unmark";
        String deadlineC = "deadline";
        String eventC = "event";
        String todoC = "todo";
        String findC = "find";

        if (input.equalsIgnoreCase(exitC)) {
            return new ExitCommand();
        }

        if (input.equalsIgnoreCase(listC)) {
            return new ListCommand();
        }

        if (input.startsWith(doneC)) {
            if (input.length() == doneC.length() - 1) {
                throw new IncompleteMessageException("Done");
            }

            String getIndex = input.substring(doneC.length() + 1);
            int taskIndex = Integer.parseInt(getIndex);
            return new DoneCommand(taskIndex);
        }

        if (input.startsWith(undoneC)) {
            if (input.length() == undoneC.length() - 1) {
                throw new IncompleteMessageException("Undone");
            }

            String getIndex = input.substring(undoneC.length() + 1);
            int taskIndex = Integer.parseInt(getIndex);
            return new UndoneCommand(taskIndex);
        }

        if (input.startsWith(deleteC)) {
            if (input.length() == deleteC.length() - 1) {
                throw new IncompleteMessageException("Delete");
            }

            try {
                String getIndex = input.substring(deleteC.length() + 1);
                int taskIndex = Integer.parseInt(getIndex);
                return new DeleteCommand(taskIndex);
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidTaskNumberException();
            }
        }

        if (input.startsWith(deadlineC)) {
            try {

                if (input.length() == deadlineC.length() - 1) {
                    throw new IncompleteMessageException("D");
                }

                // return [deadline, chatty.task/by deadline]
                String[] inputArr = input.split(" ", 2);
                // return [chatty.task description, deadline]
                String[] splitInput = inputArr[1].split("/by ", 2);
                String taskDescription = splitInput[0];
                LocalDate date = LocalDate.parse(splitInput[1]);
                String formattedDate = Parser.formatDate(date);
                return new DeadlineCommand(taskDescription, formattedDate);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DetailsUnknownException();
            } catch (IncompleteMessageException e) {
                e.getMessage();
            }
        }

        if (input.startsWith(todoC)) {
            try {
                if (input.length() == todoC.length() || input.length() == todoC.length() + 1) {
                    throw new IncompleteMessageException("T");
                }

                //return [todoC, taskDescription];
                String[] inputArr = input.split(" ", 2);
                String taskDescription = inputArr[1];
                return new ToDoCommand(taskDescription);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DetailsUnknownException();
            }
        }

        if (input.startsWith(eventC)) {
            try {
                if (input.length() == eventC.length() - 1) {
                    throw new IncompleteMessageException("E");
                }

                //return [event, chatty.task/from time /to time]
                String[] inputArr = input.split(" ", 2);
                //return [taskDescription, /from time /to time]
                String[] splitInput = inputArr[1].split("/from ", 2);
                String taskDescription = splitInput[0];
                String[] splitTime = splitInput[1].split(" /to ", 2);
                LocalDate start = LocalDate.parse(splitTime[0]);
                String formattedStart = Parser.formatDate(start);
                LocalDate end = LocalDate.parse(splitTime[1]);
                String formattedEnd = Parser.formatDate(end);
                return new EventCommand(taskDescription, formattedStart, formattedEnd);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DetailsUnknownException();
            } catch (IncompleteMessageException e) {
                e.getMessage();
            }
        }

        if (input.startsWith(findC)) {
            if (input.length() == findC.length() - 1) {
                throw new IncompleteMessageException("F");
            }

            //return [find, keyword]
            String[] inputArr = input.split(" ", 2);
            String keyword = inputArr[1];
            return new FindCommand(keyword);
        }

        return new CommandNotFound();
    }

    /**
     * Format the date in LocalDate format to String
     * @param date the date in LocalDate format
     * @return the formatted date in String representation
     */
    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }
}
