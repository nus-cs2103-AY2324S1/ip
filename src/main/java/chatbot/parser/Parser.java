package chatbot.parser;

import chatbot.commands.*;
import chatbot.exceptions.DukeException;

/**
 * Represents the class that is responsible for parsing user inputs and generating the relevant commands
 */
public class Parser {
    /**
     * Checks if the user input is valid
     * @param command User input to be validated
     * @return Returns a boolean that indicates if the input is valid
     * @throws DukeException If the user input is not valid
     */
    public static boolean isValid (String command) throws DukeException {
        if(command.equals("list") || command.equals("bye")) {
            return true;
        } else if(command.split(" ")[0].equals("todo") && command.split(" ")[1].length() < 1) {
            throw new DukeException("Error! Please include a description of the todo");
        } else if(command.split(" ")[0].equals("deadline") && command.split(" ")[1].length() < 1
                && !command.contains("/by")) {
            throw new DukeException("Error! Input should have a description and date");
        } else if(command.split(" ")[0].equals("event") && command.split(" ")[1].length() < 1
                && !command.contains("/from") && !command.contains("/to")) {
            throw new DukeException("Error! Input should have a description, start date and end date");
        } else if(command.split(" ")[0].equals("mark") && command.split(" ")[1].length() < 1) {
            throw new DukeException("Error! Please include a description of the task to be marked");
        } else if(command.split(" ")[0].equals("unmark") && command.split(" ")[1].length() < 1) {
            throw new DukeException("Error! Please include a description of the task to be unmarked");
        } else {
            return true;
        }
    }

    /**
     * Parses the user input and returns the corresponding command
     * @param command The user input to be parsed.
     * @return Command that corresponds to the parsed user input
     * @throws DukeException If the user input is not valid
     */
    public static Command parse(String command) throws DukeException {

        isValid(command);

        if(command.equals("bye")) {
            return new ExitCommand();
        } else if(command.split(" ")[0].equals("todo")) {
            return new TodoCommand(command.split("todo")[1]);
        } else if(command.split(" ")[0].equals("event")) {
            String description = command.split("event")[1].split("/from")[0].stripLeading();
            String from = command.split("\\s*/from\\s*")[1].split("\\s*/to\\s*")[0].trim();
            String to = command.split("\\s*/to\\s*")[1].trim();
            return new EventCommand(description, from, to);
        } else if(command.split(" ")[0].equals("deadline")) {
            String description = command.split("/by")[0].trim();
            String date = command.split("by")[1].trim();
            return new DeadlineCommand(description, date);
        } else if(command.equals("list")) {
            return new ListCommand();
        } else if(command.split(" ")[0].equals("delete")) {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            return new DeleteCommand(index);
        } else if(command.split(" ")[0].equals("mark")) {
            return new MarkCommand(Integer.parseInt(command.split(" ")[1].trim()));
        } else if(command.split(" ")[0].equals("unmark")) {
            return new UnmarkCommand(Integer.parseInt(command.split(" ")[1].trim()));
        } else if(command.split(" ")[0].equals("find")) {
            return new FindCommand(command.split(" ")[1]);
        } else {
            System.out.println("Invalid Command. Please Try Again!");
        }
        return new ListCommand();
    }
}
