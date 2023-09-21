package barbie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import barbie.types.Deadlines;
import barbie.types.Party;
import barbie.types.Task;
import barbie.types.Todo;



/**
 * This class deals with all file reading and writing.
 */
public class Storage {
    private static final Path path = Paths.get("barbie.txt");


    /**
     * Writes to the file given, in the format of [type][status] [desc].
     * Eg. [T][ ] read book
     * The status is always 0 when creating a new Task, so this method assumes
     * that the task is marked as undone in this method.
     * The type is also always Todo, as it takes in the "desc" parameter only.
     *
     * @param desc the description of the Task
     */
    public static void addToList(String desc) {
        assert Objects.equals(desc.split(" ", 2)[0], "T") : "Item added should be a T class";

        String line = "T" + "," + 0 + "," + desc + "\n";
        try {
            Files.write(path, line.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Writes to the file given, in the format of [type][status] [desc].
     * Eg. [T][ ] read book
     * The status is always 0 when creating a new Task, so this method assumes
     * that the task is marked as undone in this method.
     * The type is also always Deadline, as it takes in the "desc" and "deadline" parameter only.
     *
     * @param desc the description of the Task
     * @param deadline the deadline the task has to be completed by
     */
    public static void addToList(String desc, LocalDate deadline) {
        assert Objects.equals(desc.split(" ", 2)[0], "D") : "Item added should be a D class";

        String line = "D" + "," + 0 + "," + desc + "," + deadline + "\n";
        try {
            Files.write(path, line.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Writes to the file given, in the format of [type][status] [desc].
     * Eg. [T][ ] read book
     * The status is always 0 when creating a new Task, so this method assumes
     * that the task is marked as undone in this method.
     * The type is also always Party, as it takes in the "desc", "from" and "to" parameter.
     *
     * @param desc the description of the Task
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public static void addToList(String desc, LocalDate from, LocalDate to) {
        assert Objects.equals(desc.split(" ", 2)[0], "P") : "Item added should be a P class";

        String line = "P" + "," + 0 + "," + desc + "," + from + "," + to + "\n";
        try {
            Files.write(path, line.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the latest list from the storage file upon starting up.
     *
     * @return the last list loaded from the storage file
     */
    protected static ArrayList<Task> getLastList() {
        ArrayList<Task> finalList = new ArrayList<>();

        try {
            Path path = Paths.get("barbie.txt");

            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("[A new list created for current user]");
            } else {
                System.out.println("-------------------------------------------------");
                System.out.println("[A current list is being used for current user]");
                Files.readAllLines(path).forEach(x -> {
                    Task task = stringToTask(x);
                    finalList.add(task);
                });
                System.out.println(finalList);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalList;

    }

    private static Task stringToTask(String taskInString) {

        String[] taskParts = taskInString.split(",");
        String taskType = taskParts[0];
        String taskStatus = taskParts[1];
        String desc = taskParts[2];

        Task task;

        if (Objects.equals(taskType, "T")) {
            task = new Todo(desc);
        } else if (Objects.equals(taskType, "D")) {
            task = new Deadlines(desc, LocalDate.parse(taskParts[3]));
        } else if (Objects.equals(taskType, "P")) {
            task = new Party(desc, LocalDate.parse(taskParts[3]), LocalDate.parse(taskParts[4]));
        } else {
            task = new Task(desc);
        }

        if (Integer.parseInt(taskStatus) == 1) {
            task.mark();
        }

        return task;
    }

    /**
     * Changes the status of a Task in the storage file.
     *
     * @param status status to change the current Task to
     * @param lineToChange the Task number to change
     */
    public static void changeLineStatus(String status, int lineToChange) {
        try {
            List<String> lines = Files.readAllLines(path);

            if (lineToChange >= 0 && lineToChange < lines.size()) {
                String[] newContent = lines.get(lineToChange).split(",");
                newContent[1] = status;
                lines.set(lineToChange, arrayToString(newContent));
                Files.write(path, lines);
            } else {
                throw new IllegalArgumentException("Invalid line number to change.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String arrayToString(String[] arr) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            result.append(arr[i]);
            if (i < arr.length - 1) {
                result.append(",");
            }
        }
        return result.toString();
    }

    /**
     * Deletes the line of the task from the list, and shifts the rest accordingly.
     *
     * @param lineToDelete the Task number to delete
     */
    public static void deleteLine(int lineToDelete) {
        try {
            List<String> lines = Files.readAllLines(path);
            assert lines.size() > 0 : "Current task list should not be empty";

            if (lineToDelete >= 0 && lineToDelete < lines.size()) {
                lines.remove(lineToDelete);
                if (lines.size() > 0) {
                    Files.write(path, lines);

                }
            } else {
                throw new IllegalArgumentException("Invalid line number to delete.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
