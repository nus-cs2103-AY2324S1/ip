package bareum;

import bareum.commands.Command;
import bareum.commands.AddDeadlineCommand;
import bareum.commands.AddEventCommand;
import bareum.commands.AddTodoCommand;
import bareum.commands.ByeCommand;
import bareum.commands.DeleteCommand;
import bareum.commands.IncorrectCommand;
import bareum.commands.ListCommand;
import bareum.commands.MarkCommand;
import bareum.commands.UnmarkCommand;

public class Parser {
    public Parser() {

    }

    static Command parse(String input) throws BareumException {
        String[] commandInputs = input.split(" ", 2);
        Command cmd = null;
        if (commandInputs[0].equals("bye")) {
            cmd = new ByeCommand();

        } else if (commandInputs[0].equals("list")) {
            cmd = new ListCommand();

        } else if (commandInputs[0].equals("mark")) {
            if (commandInputs.length == 1) {
                throw new BareumException("Oops! Please give the index of the task you would like to mark.\n" +
                        "Correct format: mark <index>");
            }
            int index = Integer.parseInt(commandInputs[1]) - 1;
            cmd = new MarkCommand(index);

        } else if (commandInputs[0].equals("unmark")) {
            if (commandInputs.length == 1) {
                throw new BareumException("Oops! Please give the index of the task you would like to unmark.\n" +
                        "Correct format: unmark <index>");
            }
            int index = Integer.parseInt(commandInputs[1]) - 1;
            cmd = new UnmarkCommand(index);

        } else if (commandInputs[0].equals("delete")) {
            if (commandInputs.length == 1) {
                throw new BareumException("Oops! Please give the index of the task you would like to delete.\n" +
                        "Correct format: delete <index>");
            }
            int index = Integer.parseInt(commandInputs[1]) - 1;
            cmd = new DeleteCommand(index);

        } else if (commandInputs[0].equals("todo")) {
            if (commandInputs.length == 1) {
                throw new BareumException("Oops! Please include the description of your todo :(\n" +
                        "Correct format: todo <description>");
            }
            cmd = new AddTodoCommand(commandInputs[1]);

        } else if (commandInputs[0].equals("deadline")){
            if (commandInputs.length == 1) {
                throw new BareumException("Oops! The details of your deadline are missing :(\n" +
                        "\nCorrect format: deadline <description> /by <due date in YYYY-MM-DD>");
            }

            String allDetails = commandInputs[1];
            int indexOfKeywordBy = allDetails.indexOf("/by");
            if (indexOfKeywordBy == -1) {
                throw new BareumException("Oops! Please include the due date of your deadline :(\n" +
                        "\nCorrect format: deadline <description> /by <due date in YYYY-MM-DD>");
            } else if (indexOfKeywordBy == 0) {
                throw new BareumException("Oops! Please include the description of your deadline :(\n" +
                        "Correct format: deadline <description> /by <due date in YYYY-MM-DD>");
            }

            String[] descriptionDueTime = allDetails.split("/by");
            String description = descriptionDueTime[0];
            if (descriptionDueTime.length == 1) {
                throw new BareumException("Oops! Please include the due date of your deadline :(\n" +
                        "Correct format: deadline <description> /by <due date in YYYY-MM-DD>");
            }

            String dueDate = descriptionDueTime[1];
            cmd = new AddDeadlineCommand(description, dueDate);

        } else if (commandInputs[0].equals("event")) {
            if (commandInputs.length == 1) {
                throw new BareumException("The details of your event are missing :(\n" +
                        "\nCorrect format: event <description> /from <start time> /to <end time>");
            }

            String allDetails = commandInputs[1];
            int indexOfKeywordFrom = allDetails.indexOf("/from");
            if (indexOfKeywordFrom == -1) {
                throw new BareumException("Oops! Please include the start time of your event :(\n" +
                        "Correct format: event <description> /from <start time> /to <end time>");
            } else if (indexOfKeywordFrom == 0) {
                throw new BareumException("Oops! Please include the description of your event :(\n" +
                        "Correct format: event <description> /from <start time> /to <end time>");
            }

            String[] descriptionStartEndTime = allDetails.split("/from");
            String description = descriptionStartEndTime[0];
            int indexOfKeywordTo = descriptionStartEndTime[1].indexOf("/to");
            if (indexOfKeywordTo == -1) {
                throw new BareumException("Oops! Please include the end time of your event :(\n" +
                        "Correct format: event <description> /from <start time> /to <end time>");
            } else if (indexOfKeywordTo == 0) {
                throw new BareumException("Oops! Please include the start time of your event :(\n" +
                        "Correct format: event <description> /from <start time> /to <end time>");
            }

            String[] startEndTime = descriptionStartEndTime[1].split("/to");
            String startDateTime = startEndTime[0];
            if (startEndTime.length == 1) {
                throw new BareumException("Oops! Please include the end time of your event :(\n" +
                        "Correct format: event <description> /from <start time> /to <end time>");
            }
            String endDateTime = startEndTime[1];

            cmd = new AddEventCommand(description, startDateTime, endDateTime);

        } else {
            cmd = new IncorrectCommand();
        }

        return cmd;
    }
}
