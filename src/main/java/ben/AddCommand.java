package ben;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Arrays;


/**
 * Represents a command to add task to a taskList.
 */
public class AddCommand extends Command{
    private String command;

    private Task task;

    /**
     * Constructor takes in a command
     *
     * @param command The input command by the user
     */
    public AddCommand(String command) {
        this.command = command;
    }

    /**
     * Interprets the command and initialises the task field if the command is valid. Throws InvalidCommandException,
     * DateTimeParseException or EmptyDescriptionException if the command is not valid.
     *
     * @throws InvalidCommandException Error for invalid commands.
     * @throws EmptyDescriptionException Error for empty description commands.
     * @throws DateTimeParseException Error for when DateTime cannot be parsed.
     */
    public void interpretTask() throws InvalidCommandException, EmptyDescriptionException, DateTimeParseException {
        String[] words = command.split("\\s+");

        if (words[0].equalsIgnoreCase("todo")) {
            String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));

            if (description.isEmpty()) {
                throw new EmptyDescriptionException("Description cannot be empty");
            }

            task = new ToDos(description, false);
            return;
        } else if (words[0].equalsIgnoreCase("deadline")) {
            int positionBy = 0;

            for (int i = 1; i < words.length; i++) {
                if (words[i].equalsIgnoreCase("/by")) {
                    positionBy = i;
                    break;
                }
            }

            if (positionBy == 0) {
                throw new InvalidCommandException("Did not include /by");
            }

            String description = String.join(" ", Arrays.copyOfRange(words, 1, positionBy));

            if (description.isEmpty()) {
                throw new EmptyDescriptionException("Description cannot be empty");
            }

            String by = String.join(" ", Arrays.copyOfRange(words, positionBy + 1, words.length));

            if (by.isEmpty()) {
                throw new EmptyDescriptionException("/by cannot be empty");
            }

            task = new Deadlines(description, false, dateTimeParser(by));
            return;
        } else if (words[0].toLowerCase().contains("event")) {
            int positionFrom = 0;
            int positionTo = 0;

            for (int i = 1; i < words.length; i++) {
                if (words[i].equalsIgnoreCase("/from")) {
                    positionFrom = i;
                }

                if (words[i].equalsIgnoreCase("/to")) {
                    positionTo = i;
                }
            }

            if (positionFrom == 0 || positionTo == 0) {
                throw new InvalidCommandException("Did not include both /from and /to");
            }

            String description = String.join(" ", Arrays.copyOfRange(words, 1, positionFrom));

            if (description.isEmpty()) {
                throw new EmptyDescriptionException("Description cannot be empty");
            }

            String from = String.join(" ", Arrays.copyOfRange(words, positionFrom + 1, positionTo));

            if (from.isEmpty()) {
                throw new EmptyDescriptionException("/from cannot be empty");
            }

            String to = String.join(" ", Arrays.copyOfRange(words, positionTo + 1, words.length));

            if (to.isEmpty()) {
                throw new EmptyDescriptionException("/to cannot be empty");
            }

            task = new Events(description, false, dateTimeParser(from), dateTimeParser(to));
            return;
        }
        throw new InvalidCommandException("Oops this Command: " + command + " is not found");
    }

    /**
     * Converts a String to a LocalDateTime object if is in the valid format. If HHmm is not included in the String,
     * the time 2359 will be appended. Throws DateTImeParseException if the format is invalid.
     *
     * @param dateTime String representation of a LocalDateTime.
     * @return LocalDateTime object.
     * @throws DateTimeParseException Error for when DateTime cannot be parsed.
     */
    public LocalDateTime dateTimeParser(String dateTime) throws DateTimeParseException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        if (dateTime.length() <= 10) {
            return LocalDateTime.parse(dateTime + " 2359", formatter);
        }

        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Checks whether command causes the chatbot to exit.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes adding the task to the taskList.
     *
     * @param tasks The taskList
     * @param ui The UI handling user interaction
     * @throws InvalidCommandException Error for invalid commands.
     * @throws EmptyDescriptionException Error for empty description commands.
     * @throws DateTimeParseException Error for when DateTime cannot be parsed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidCommandException,
            EmptyDescriptionException, DateTimeParseException {
        interpretTask();
        tasks.add(task, true);
    }
}
