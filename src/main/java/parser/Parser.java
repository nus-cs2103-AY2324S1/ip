package parser;

import commands.*;
import duke.DukeException;

public class Parser {
    public Command issueCommand(String input) throws DukeException{
        String lowerInput = input.toLowerCase();
        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0];
        switch (command) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "mark":
                if (inputParts.length < 2) {
                    throw new DukeException(" Provide the task number.");
                }
                int markTaskNumber = Integer.parseInt(inputParts[1].split(" ")[0]) - 1;
                return new MarkCommand(markTaskNumber);
            case "unmark":
                if (inputParts.length < 2) {
                    throw new DukeException(" Provide the task number.");
                }
                int unmarkTaskNumber = Integer.parseInt(inputParts[1].split(" ")[0]) - 1;
                return new UnmarkCommand(unmarkTaskNumber);
            case "todo":
                return new TodoCommand(inputParts.length > 1 ? inputParts[1] : "");
            case "deadline":
                return new DeadlineCommand(inputParts.length > 1 ? inputParts[1] : "", this);
            case "event":
                return new EventCommand(inputParts.length > 1 ? inputParts[1] : "", this);
            case "delete":
                if (inputParts.length < 2) {
                    throw new DukeException(" Please provide a task number. " +
                            "Format: delete task_number");
                }
                int deleteTaskNumber = Integer.parseInt(inputParts[1].split(" ")[0]) - 1;
                return new DeleteCommand(deleteTaskNumber);
            case "schedule":
                if (inputParts.length < 2 || !inputParts[1].contains("/on")) {
                    throw new DukeException(" Please provide the date in the format:\n" +
                            "schedule /on dd/MM/yyyy");
                }
                String dateInput = inputParts[1].split("/on")[1].trim() + " 0000";
                return new ScheduleCommand(dateInput);
            default:
                return new UnknownCommand();
        }
    }

    public String[] splitInput(String input) {
        return input.split(" ", 2);
    }

    public String[] splitByKeyword(String input, String keyword) {
        return input.split(keyword, 2);
    }
}