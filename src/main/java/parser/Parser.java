package parser;

import command.*;
import exception.KoraException;

public class Parser {

    public Parser() {

    }
    public static Command parse(String userInput) throws KoraException {
        String line = "------------------------------" + "\n";
        String[] userInputArray = userInput.split("/");
        Command command;
        try {
            if (userInputArray[0].contains("bye")) {
                command = new ByeCommand();
            } else if (userInputArray[0].contains("list")) {
                command = new ListCommand();
            } else if (userInputArray[0].contains("unmark")) {
                command = new UnmarkCommand(userInputArray);
            } else if (userInputArray[0].contains("mark")) {
                command = new MarkCommand(userInputArray);
            } else if (userInputArray[0].contains("deadline")) {
                command = new DeadlineCommand(userInputArray);
            } else if (userInputArray[0].contains("event")) {
                command = new EventCommand(userInputArray);
            } else if (userInputArray[0].contains("todo")) {
                command = new ToDoCommand(userInputArray);
            } else if (userInputArray[0].contains("delete")) {
                command = new DeleteCommand(userInputArray);
            } else {
                throw new KoraException("I do not understand");
            }
        } catch (Exception e) {
            throw new KoraException(e.getMessage());
        }
        return command;
    }
}
