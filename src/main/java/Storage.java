import types.Deadlines;
import types.Party;
import types.Task;
import types.Todo;

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

/**
 * This class deals with all file reading and writing.
 */
public class Storage {

    /**
     * Writes to the file given, in the format of [type][status] [desc].
     * Eg. [T][ ] read book
     * The status is always 0 when creating a new Task, so this method assumes
     * that the task is marked as undone in this method.
     * The type is also always Todo, as it takes in the "desc" parameter only.
     *
     * @param path the path of file to write to
     * @param desc the description of the Task
     */
    protected static void addToList(Path path, String desc) {
        String line = "P" + "," + 0 + "," + desc + "\n";
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
     * @param path the path of the file to write to
     * @param desc the description of the Task
     * @param deadline the deadline the task has to be completed by
     */
    protected static void addToList(Path path, String desc, LocalDate deadline) {
        String line = "P" + "," + 0 + "," + desc + "," + deadline + "\n";
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
     * @param path the path of the file to write to
     * @param desc the description of the Task
     * @param from the start time of the event
     * @param to the end time of the event
     */
    protected static void addToList(Path path, String desc, LocalDate from, LocalDate to) {
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

                    String[] taskParts = x.split(",");
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
                    if (Integer.parseInt( taskStatus) == 1) { task.mark(); }
                    finalList.add(task);
                    System.out.println(finalList);
                });

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalList;

    }

    /**
     * Changes the status of a Task in the storage file.
     *
     * @param path path of the storage file (to allow for saving to a specified list. See Level-7.1)
     * @param status status to change the current Task to
     * @param lineToChange the Task number to change
     */
    protected static void changeLineStatus(Path path, String status, int lineToChange) {
        try {
            List<String> lines = Files.readAllLines(path);

            if (lineToChange >= 0 && lineToChange < lines.size()) {
                String[] newContent = lines.get(lineToChange).split(",");
                newContent[1] = status;
                lines.set(lineToChange, Arrays.stream(newContent).reduce("", (x, acc) -> x + "," + acc));
                Files.write(path, lines);
            } else {
                throw new IllegalArgumentException("Invalid line number to change.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes the line of the task from the list, and shifts the rest accordingly.
     *
     * @param path path of the storage file
     * @param lineToDelete the Task number to delete
     */
    protected static void deleteLine(Path path, int lineToDelete) {
        try {
            List<String> lines = Files.readAllLines(path);

            if (lineToDelete >= 0 && lineToDelete < lines.size()) {
                lines.remove(lineToDelete);
                Files.write(path, lines);
            } else {
                throw new IllegalArgumentException("Invalid line number to delete.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
