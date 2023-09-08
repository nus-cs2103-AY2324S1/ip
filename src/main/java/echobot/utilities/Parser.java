package echobot.utilities;

import echobot.exceptions.DukeException;
import echobot.exceptions.InvalidCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deals with making sense of user commands
 */
public class Parser {

    /**
     * Parses the user input to identify its important parts
     *
     * @param input The user input
     * @return Input object containing key points of the user input
     */
    public Input parse(String input) {
        String[] split = input.split(" ");
        String command = split[0].toLowerCase();
        int length = split.length;
        return new Input(command, input, length);
    }

    /**
     * Parses and formats the date input into another format
     *
     * @param strDate Date in String format
     * @return Date in "MMM dd yyyy" format
     */
    public String formatDate(String strDate) {
        String result;
        try {
            LocalDate parseDate = LocalDate.parse(strDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            result = parseDate.format(formatter);
        } catch (DateTimeParseException e) {
            result = "Invalid date";
        }
        return result;
    }

    /**
     * Handles the various cases of user inputs
     *
     * @param tasks TaskList object that contains the list
     * @param input Input object that contains parsed user input
     * @param ui Ui where the helper functions are located
     * @return True or false signifying breaking or continuing the loop
     */
    public String handleInput(TaskList tasks, Input input, Ui ui) {
        String output = "";
        try {
            String command = input.getCommand();
            String fullInput = input.getFullInput();
            int numberOfWords = input.getLength();
            switch (command) {
                case "list":
                    output = ui.showList(tasks, numberOfWords);
                    break;
                case "mark":
                case "unmark":
                case "delete":
                    output = ui.showManipulateTasks(tasks, command, fullInput, numberOfWords);
                    break;
                case "todo":
                    output = ui.showAddToDo(tasks, fullInput, numberOfWords);
                    break;
                case "deadline":
                    output = ui.showAddDeadline(tasks, fullInput, numberOfWords, this);
                    break;
                case "event":
                    output = ui.showAddEvent(tasks, fullInput, numberOfWords, this);
                    break;
                case "find":
                    output = ui.showFind(tasks, fullInput, numberOfWords);
                    break;
                case "bye":
                    output = ui.showBye(numberOfWords);
                    break;
                default:
                    throw new InvalidCommandException("No such command exists");
            }
        } catch (DukeException e) {
            output = e.getMessage() + "\nPlease be careful next time";
        }
        return output;
    }
}
