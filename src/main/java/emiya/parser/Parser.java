package emiya.parser;

import emiya.emiyaexception.*;
import emiya.logic.Logic;

import static emiya.logic.Logic.enumContainsKeyword;

public class Parser {
    public Parser() {

    }

    public String[] parseToRemoveUnknownCommands(Integer[] position, String input) throws UnknownCommandException {
        // this part splits the input into 2 parts, depending on whitespace (if possible)
        String[] partsOfInput = input.split("\\s+", 2);

        // if input is a single word AND it is not a keyword, throw UnknownCommandException
        if (partsOfInput.length < 2 && !enumContainsKeyword(partsOfInput[0].toUpperCase())) {
            throw new UnknownCommandException();
        }

        // figure out what command is being input
        String typeOfTask = partsOfInput[0];
        // if it is a mark/unmark, this will be number, else is part of command
        String taskDetails = "";
        if (partsOfInput.length > 1) {
            taskDetails = partsOfInput[1];
        }

        // for mark/unmark commands
        if (Logic.isNumeric(taskDetails)) {
            position[0] = Integer.parseInt(taskDetails);
            return new String[] {typeOfTask};
        } else { // any other command, need pass in task details as well
            return new String[] {typeOfTask, taskDetails};
        }
    }

    public String[] parseForDeadline(String taskDetails) throws NoByException {
        String[] deadlineDetails = taskDetails.split(" /by ", 2);
        if (deadlineDetails.length <= 1) {
            throw new NoByException();
        }
        return deadlineDetails;
    }

    public String[] parseForEvent(String taskDetails) throws NoToException, NoFromException {
        String[] eventDetails = taskDetails.split(" /from ", 2);
        if (eventDetails.length <= 1) {
            throw new NoFromException();
        }
        String[] eventDurationDetails = eventDetails[1].split(" /to ", 2);
        if (eventDurationDetails.length <= 1) {
            throw new NoToException();
        }
        return new String[] {eventDetails[0], eventDurationDetails[0], eventDurationDetails[1]};
    }

    public String[] parseForEventFrom(String taskDetails) throws NoFromException {
        String[] eventDetails = taskDetails.split(" /from ", 2);
        if (eventDetails.length <= 1) {
            throw new NoFromException();
        }
        return eventDetails;
    }

    public String[] parseForEventTo(String taskDetails) throws NoToException {
        String[] eventDetails = taskDetails.split(" /from ", 2);
        if (eventDetails.length <= 1) {
            throw new NoToException();
        }
        return eventDetails;
    }
}
