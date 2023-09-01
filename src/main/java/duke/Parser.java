package duke;

import java.util.ArrayList;

/**
 * A parser for the Duke program.
 */
public class Parser {

    /**
     * Takes a Storage object and attempts to parse the tasks stored in it into an array of Task objects.
     * If a line in the storage file cannot be parsed into a Task object, it throws a DukeException.
     *
     * @param storage The Storage object to be parsed.
     * @return An array of Task objects representing the tasks stored in the Storage object.
     * @throws DukeException If a line in the storage file cannot be parsed into a Task object.
     */
    public static Task[] parseFile(Storage storage) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        String[] lines = storage.readLines();
        for (String line : lines) {
            tasks.add(parseTaskFromFileLine(line));
        }
        return tasks.toArray(new Task[]{});
    }

    /**
     * Takes a string representing a line from the storage file and attempts to parse it into a Task object.
     * If the string cannot be parsed into a Task object, it throws a DukeException.
     *
     * @param line The string to be parsed into a Task object.
     * @return A Task object representing the parsed task.
     * @throws DukeException If the string cannot be parsed into a Task object.
     */
    private static Task parseTaskFromFileLine(String line) throws DukeException {
        String[] tokens = line.split(" ", 3);
        try {
            Commands c = Commands.valueOf(tokens[0]);
            boolean done = !tokens[1].equals("0");
            String desc = tokens[2];
            Task task = Task.create(c, desc);
            if (done) {
                task.markAsDone();
            } else {
                task.markAsNotDone();
            }

            return task;
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            throw new DukeException("Corrupted file. Delete all text in the duke.txt to use it again.");
        } catch (RuntimeException e) {
            throw new DukeException("Corrupted file. Delete all text in the duke.txt to use it again.");
        }
    }

    /**
     * Takes a string (from the command line) and attempts to parse it into a Task object.
     * If the string is empty, it throws a DukeException.
     *
     * @param s The string to be parsed into a Task object.
     * @return A Task object representing the parsed task.
     * @throws DukeException If the string is empty or cannot be parsed into a Task object.
     */
    public static Task parseTask(String s) throws DukeException {
        if (s.length() == 0) {
            throw new DukeException("Empty line found");
        }
        String[] instructions = s.split(" ", 2);
        Commands cmd = Commands.get(instructions[0]);
        return Parser.parseTask(cmd, instructions);
    }

    /**
     * Takes a Commands enum value and an array of strings and attempts to create a Task object from them.
     * If the array of strings does not contain enough information to create a Task object, it throws a DukeException.
     *
     * @param cmd The Commands enum value representing the type of task to be created.
     * @param instructions The array of strings containing information about the task to be created.
     * @return A Task object representing the created task.
     * @throws DukeException If the array of strings does not contain enough information to create a Task object.
     */
    public static Task parseTask(Commands cmd, String[] instructions) throws DukeException {
        if (instructions.length != 2) {
            throw new DukeException("No details given for command: " + cmd);
        }
        return Task.create(cmd, instructions[1]);
    }

    public static String findAll(String[] instructions, StoreList src) {
        String listString;
        if (instructions.length <= 1) {
            listString = src.toString();
            return String.format("Here are the tasks:\n%s", listString);
        }

        String term = instructions[1];
        StoreList list = src.findAll(term);
        listString = list.toString();
        return String.format("Here are the tasks with '%s':\n%s", term, listString);
    }

}
