import java.time.LocalDate;

public class Parser {
    public Command parse(String fullCommand) throws BertInvalidTaskException, BertEmptyTaskException {
        String command;
        String arguments = "";
        int indexOfFirstSpace = fullCommand.indexOf(" ");
        if (indexOfFirstSpace == -1) {
            command = fullCommand;
        } else {
            command = fullCommand.substring(0, indexOfFirstSpace);
            arguments = fullCommand.substring(indexOfFirstSpace + 1);
        }

        switch (command) {
            // Typing 'list' prints out the list of tasks
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            // Typing 'mark x' marks a task at a specific index on the list
            case MarkCommand.COMMAND_WORD:
                return prepareMark(arguments);
            // Typing 'unmark x' unmarks a task at a specific index on the list
            case UnmarkCommand.COMMAND_WORD:
                return prepareUnmark(arguments);
            // Typing 'todo...' stores a todo task
            case AddToDoCommand.COMMAND_WORD:
                return prepareToDo(arguments);
            // Typing 'deadline...' stores a deadline task
            case AddDeadlineCommand.COMMAND_WORD:
                return prepareDeadline(arguments);
            // Typing 'event...' stores an event task
            case AddEventCommand.COMMAND_WORD:
                return prepareEvent(arguments);
            // Typing 'delete x' deletes a task
            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(arguments);
            // Typing 'bye' ends the program
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            default:
                throw new BertInvalidTaskException();
        }
    }

    private Command prepareMark(String arguments) {
        int index = Integer.parseInt(arguments) - 1;
        return new MarkCommand(index);
    }

    private Command prepareUnmark(String arguments) {
        int index = Integer.parseInt(arguments) - 1;
        return new UnmarkCommand(index);
    }

    private Command prepareToDo(String arguments) throws BertEmptyTaskException {
        if (arguments.isBlank()) {
            throw new BertEmptyTaskException();
        }
        return new AddToDoCommand(arguments);
    }

    private Command prepareDeadline(String arguments) throws BertEmptyTaskException {
        if (arguments.isBlank()) {
            throw new BertEmptyTaskException();
        }
        String[] descriptionAndTime = arguments.split(" /by ");
        LocalDate ld = LocalDate.parse(descriptionAndTime[1]);
        return new AddDeadlineCommand(descriptionAndTime[0], ld);
    }

    private Command prepareEvent(String arguments) throws BertEmptyTaskException {
        if (arguments.isBlank()) {
            throw new BertEmptyTaskException();
        }
        String[] descriptionAndTimes = arguments.split(" /from ");
        String[] times = descriptionAndTimes[1].split(" /to ");
        LocalDate ld1 = LocalDate.parse(times[0]);
        LocalDate ld2 = LocalDate.parse(times[1]);
        return new AddEventCommand(descriptionAndTimes[0], ld1, ld2);
    }

    private Command prepareDelete(String arguments) {
        int index = Integer.parseInt(arguments) - 1;
        return new DeleteCommand(index);
    }
}
