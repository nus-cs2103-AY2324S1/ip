package ben;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final TaskList tasks;
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }
    public Command parse(String command) throws InvalidCommandException{
        String[] words = command.split("\\s+");
        if (words[0].equalsIgnoreCase("delete") ||
                words[0].equalsIgnoreCase("unmark") ||
                words[0].equalsIgnoreCase("mark")) {
            return referenceListCommandParser(command);
        } else if (words[0].equalsIgnoreCase("find")) {
            return new FindCommand(command);
        } else if (words[0].equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else if (words[0].equalsIgnoreCase("list")) {
            return new ListCommand();
        }
        return new AddCommand(command);
    }

    public Command referenceListCommandParser(String message) throws InvalidCommandException{
        Pattern pattern = Pattern.compile("(unmark|mark|delete)\\s*(-?\\d+)");
        Matcher matcher = pattern.matcher(message.toLowerCase());

        if (matcher.find()) {
            // extract command
            String command = matcher.group(1);

            // extract task number
            String TaskNumber = matcher.group(2);
            int num = Integer.parseInt(TaskNumber) - 1;

            // check whether number is valid
            if (num < 0 || num >= tasks.size()) {
                throw new InvalidCommandException("Please input a valid task number");
            }

            // if valid, mark or unmark the task
            Task task = tasks.get(num);

            if (Objects.equals(command, "mark")) {
                return new MarkCommand(task);
            } else if ((Objects.equals(command, "delete"))) {
                return new DeleteCommand(task);
            } else {
                return new UnmarkCommand(task);
            }
        }
        return null;
    }
}
