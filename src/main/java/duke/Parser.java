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

    /**
     * Parses task and adds task to list, and gives string response.
     *
     * @param cmd the command task
     * @param instructions the list of instructions for the task
     * @param list the input list
     * @return the response of adding the task to the list.
     */
    public static String parseTaskAndAddToList(Commands cmd, String[] instructions, StoreList list) {
        String response;
        try {
            Task task = Parser.parseTask(cmd, instructions);
            response = list.add(task);
            return response;
        } catch (DukeException e) {
            return e.toString();
        }
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

    /**
     * Gives response to updating a task in the list.
     *
     * @param instructions the set of instructions for the update.
     * @return the response to updating the task in the list.
     */
    public static String parseUpdate(String[] instructions, StoreList list) {
        String invalidCommand = "Invalid command. Format should be: update <index> <description|start|end|deadline> <updatedInfo>";
        if (instructions.length != 2) {
            return invalidCommand;
        }

        String[] indexWithUpdateInformation = instructions[1].split(" ", 2);
        if (indexWithUpdateInformation.length != 2) {
            return invalidCommand;
        }
        int index;
        try {
            index = Integer.parseInt(indexWithUpdateInformation[0]);
        } catch (NumberFormatException e) {
            return invalidCommand;
        }

        String[] attributeWithUpdateInfo = indexWithUpdateInformation[1].split(" ", 2);
        if (attributeWithUpdateInfo.length != 2) {
            return invalidCommand;
        }

        TaskAttribute attribute = TaskAttribute.get(attributeWithUpdateInfo[0]);
        return list.updateTask(attribute, index, attributeWithUpdateInfo[1]);
    }

}
