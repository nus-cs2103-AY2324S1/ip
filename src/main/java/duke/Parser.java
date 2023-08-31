package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class that deals with making sense of the user command.
 */
public class Parser {

    /**
     * Decides which command to give to the bot based on user input.
     *
     * @param input The String input by the user.
     * @return The command to be used by the bot.
     */
    public static Command decideCommand(String input) {
        // return BYE and LIST only if the input matches exactly
        if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.equals(("list"))) {
            return Command.LIST;
        } else {
            String[] words = input.split(" ");
            String command = words[0];

            for (Command c : Command.values()) {
                if (c.name().toLowerCase().equals(command) && !c.equals(Command.BYE) && !c.equals(Command.LIST)) {
                    return c;
                }
            }

            return Command.INVALID;
        }
    }

    /**
     * Converts the String stored in the file into a Task object.
     *
     * @param string The string stored in the file.
     * @return Task representation of the string stored.
     * @throws DukeException If the string stored is not in the correct format.
     */
    public static Task stringToTask(String string) throws DukeException {
        String[] words = string.split("\\s\\|\\s");
        String taskType = words[0];
        boolean done;
        if (words[1].equals("0")) {
            done = false;
        } else if (words[1].equals("1")) {
            done = true;
        } else {
            System.out.println(words[1]);
            throw new DukeException("Field 2 (isMarked) is invalid");
        }
        Task t;
        switch (taskType) {
        case "T":
            if (words.length != 3) {
                throw new DukeException("Incorrect Format for todo task in file");
            } else {
                t = new Todo(words[2]);
            }
            break;
        case "D":
            if (words.length != 4) {
                throw new DukeException("Incorrect Format for deadline task in file");
            } else {
                t = new Deadline(stringToDate(words[3]), words[2]);
            }
            break;
        case "E":
            if (words.length != 5) {
                throw new DukeException("Incorrect Format for event task in file");
            } else {
                t = new Event(stringToDate(words[3]), stringToDate(words[4]), words[2]);
            }
            break;
        default:
            throw new DukeException("Field 1 (Task type) is invalid");
        }

        if (done) {
            t.markAsDone();
        }
        return t;
    }

    /**
     * Converts the String input date to LocalDate.
     *
     * @param input String representation of date in YYYY-MM-DD format.
     * @return LocalDate of date.
     * @throws DukeException If the string input does not match the required format.
     */
    public static LocalDate stringToDate(String input) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        try {
            date = LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date input in wrong format. Please input as YYYY-MM-DD");
        }
        return date;
    }

}
