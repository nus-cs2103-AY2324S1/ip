package max.parser;

import max.commands.*;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.tasks.TaskList;
import max.tasks.*;
import max.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static java.lang.Integer.parseInt;

public class Parser {
    private String input;
    public Parser(String input) {
        this.input = input;
    }
    public Parser() {

    }

    public Command parse(String fullCommand) throws MaxException {
        String com = fullCommand.split(" ")[0];

        switch (com) {
        case AddCommand.COMMAND_WORD_DEADLINE:
            return handleDeadline(fullCommand);

        case AddCommand.COMMAND_WORD_EVENT:
            return handleEvent(fullCommand);

        case AddCommand.COMMAND_WORD_TODO:
            return handleTodo(fullCommand);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case MarkCommand.COMMAND_WORD:
            return handleMark(fullCommand);
        case UnmarkCommand.COMMAND_WORD:
            return handleUnmark(fullCommand);
        case DeleteCommand.COMMAND_WORD:
            return handleDelete(fullCommand);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            throw new MaxException("Invalid command sir.");
        }

//        max.commands.CommandEnum commandEnum;
////        switch (command) {
////            case
////        }
//        if (command.equals("bye")) {
//            // User wants to exit the chatbot
//            commandEnum = max.commands.CommandEnum.BYE;
//        } else if (command.equals("list")) {
//            commandEnum = max.commands.CommandEnum.LIST;
//        } else if (command.contains("unmark")) {
//            commandEnum = max.commands.CommandEnum.UNMARK;
//        } else if (command.contains("mark")) {
//            commandEnum = max.commands.CommandEnum.MARK;
//        } else if (command.contains("event") || command.contains("todo") ||
//                command.contains("deadline")) {
//            commandEnum = max.commands.CommandEnum.ADD;
//        } else if (command.contains("delete")) {
//            commandEnum = max.commands.CommandEnum.DELETE;
//        } else {
//            commandEnum = max.commands.CommandEnum.UNKNOWN;
//        }
//        return commandEnum;
    }
    public Command handleTodo(String fullCommand) throws MaxException {
        // Error checking: empty fields
        if (fullCommand.length() < 6) {
            throw new MaxException("     Watch out -- todo description cannot be empty.");
        }
        String description = fullCommand.substring(5).trim();

        return new AddCommand(new Todo(description));
    }
    public Command handleEvent(String fullCommand) throws MaxException {
        int fromIndex = fullCommand.indexOf("/from");
        int toIndex = fullCommand.indexOf("/to");
        // Error checking: no /from or /to tag
        if (fromIndex == -1 || toIndex == -1) {
            throw new MaxException("     Hey! Event must contain '/from' and '/to' tags.");
        }
        String item = fullCommand.substring(5, fromIndex - 1).trim();
        String from = fullCommand.substring(fromIndex + 5, toIndex -1).trim();
        String to = fullCommand.substring(toIndex + 3).trim();

        // Error checking: empty fields
        if (item.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new MaxException("     Oh no! Event item, 'from' date, or 'to' date cannot be empty.");
        }

        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);


        return new AddCommand(new Event(item, fromDate, toDate));
    }
    public Command handleDeadline(String fullCommand) throws MaxException {
        int byIndex = fullCommand.indexOf("/by");
        // Error checking: no /by tag
        if (byIndex == -1) {
            throw new MaxException("     Try again... deadline must include a '/by' tag!");
        }
        String item = fullCommand.substring(8, byIndex - 1).trim();
        String by = fullCommand.substring(byIndex + 3).trim();
        // Error checking: empty fields
        if (item.isEmpty() || by.isEmpty()) {
            throw new MaxException("     Oops... Deadline item or 'by' date cannot be empty.");
        }

        LocalDate byDate = LocalDate.parse(by);

        return new AddCommand(new Deadline(item, byDate));
    }
    public Command handleDelete(String fullCommand) throws MaxException {
        int deleteNumber = parseInt(fullCommand.substring(7));
        return new DeleteCommand(deleteNumber);
    }
    public Command handleMark(String fullCommand) throws MaxException {
        int markNumber = parseInt(fullCommand.substring(5));
        return new MarkCommand(markNumber);
    }
    public Command handleUnmark(String fullCommand) throws MaxException {
        int markNumber = parseInt(fullCommand.substring(7));
        return new MarkCommand(markNumber);
    }
}

