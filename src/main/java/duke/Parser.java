package duke;

import java.util.ArrayList;

public class Parser {

    public static Task[] parseFile(Storage storage) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        String[] lines = storage.readLines();
        for (String line : lines) {
            tasks.add(parseTaskFromFileLine(line));
        }
        return tasks.toArray(new Task[]{});
    }

    private static Task parseTaskFromFileLine(String s) throws DukeException {
        String[] tokens = s.split(" ", 3);
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

    public static Task parseTask(String s) throws DukeException {
        if (s.length() == 0) {
            throw new DukeException("Empty line found");
        }
        String[] instructions = s.split(" ", 2);
        Commands cmd = Commands.get(instructions[0]);
        return Parser.parseTask(cmd, instructions);
    }

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
