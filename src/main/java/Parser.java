import duke.exceptions.InvalidFileTypeException;
import duke.exceptions.InvalidTaskException;
import duke.exceptions.TaskIndexOutOfBoundsException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public Parser() {}

    public Matcher regexParse(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher;
    }

    private String getCommand(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null; // Return null for empty input or whitespace
        }

        input = input.trim(); // Remove leading and trailing whitespace
        int spaceIndex = input.indexOf(' '); // Find the index of the first space

        if (spaceIndex == -1) {
            return input; // If no space found, the whole string is the first word
        } else {
            return input.substring(0, spaceIndex); // Extract the first word using substring
        }
    }

    public Command parse(String response) throws InvalidTaskException {
        int taskIndex;
        Matcher matcher;

        switch(response.split(" ")[0]) {
            case "list":
                return new PrintListCommand();
            case "mark":
            case "unmark":
                boolean done = response.split(" ")[0].equals("mark") ? true : false;
                taskIndex = Integer.parseInt(response.split(" ")[1]) - 1;
                return new MarkCommand(done, taskIndex);
            case "todo":
                matcher = this.regexParse("^todo\\s([\\w\\s]*)$", response);
                if (!matcher.find() || matcher.groupCount() != 1) {
                    throw new InvalidTaskException("Invalid use of todo. Usage: todo <task description>");
                }
                return new ToDoCommand(matcher.group(1), false);
            case "deadline":
                matcher = regexParse("^deadline\\s([\\w\\s]*)\\s\\/by\\s([\\w\\s]*)$", response);
                if (!matcher.find() || matcher.groupCount() != 2) {
                    throw new InvalidTaskException(
                            "Invalid use of deadline. Usage: deadline <task description> /by <date & time>"
                    );
                }
                return new DeadlineCommand(matcher.group(1), matcher.group(2), false);
            case "event":
                matcher = regexParse("^event\\s([\\w\\s]*)\\s\\/from\\s([\\w\\s]*)\\s\\/to\\s([\\w\\s]*)$", response);
                if (!matcher.find() || matcher.groupCount() != 3) {
                    throw new InvalidTaskException(
                            "Invalid use of event. Usage: event <task description> /from <date & time> /to <date & time>"
                    );
                }
                return new EventCommand(matcher.group(1), matcher.group(2), matcher.group(3), false);
            case "delete":
                matcher = regexParse("^delete\\s([\\w\\s]*)$", response);
                if (!matcher.find() || matcher.groupCount() != 1) {
                    throw new InvalidTaskException(
                            "Invalid input. Usage: delete <task_index>"
                    );
                }
                return new DeleteCommand(Integer.parseInt(matcher.group(1)));
            case "save":
                return new SaveCommand(response);
            case "load":
                return new LoadCommand(response);
            default: {
                return new DefaultCommand(response);
            }
        }
    }

    public Task parseSave(String response) throws InvalidFileTypeException {
        boolean done;
        String[] responseList = response.split("\\|");
        Task newtask;

        switch(responseList[0]) {
            case "T":
                done = responseList[1] == "X" ? true : false;
                if (responseList.length != 3) {
                    throw new InvalidFileTypeException(String.format("line: %s is invalid: Todo needs 3 parameters", response));
                }
                newtask = new ToDo(responseList[2]);
                newtask.setDone(done);
                return newtask;
            case "D":
                done = responseList[1] == "X" ? true : false;
                if (responseList.length != 4) {
                    throw new InvalidFileTypeException(String.format("line: %s is invalid: Deadline needs 4 parameters", response));
                }
                newtask = new Deadlines(responseList[2], responseList[3]);
                newtask.setDone(done);
                return newtask;
            case "E":
                done = responseList[1] == "X" ? true : false;
                if (responseList.length != 5) {
                    throw new InvalidFileTypeException(String.format("line: %s is invalid: Event requires 5 parameters", response));
                }
                newtask = new Event(responseList[2], responseList[3], responseList[4]);
                newtask.setDone(done);
                return newtask;
            default: {
                throw new InvalidFileTypeException(String.format("line: %s is invalid: Event requires 5 parameters", response));
            }
        }
    }
}
