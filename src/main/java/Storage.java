import ip.utils.Pair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

// TODO: Remove UI logic from Storage

public class Storage {
    /**
     * Task Array - as TrackerBot is not instantiated, this must be static.
     * The Task List array itself should be immutable, in case we override it
     * during runtime.
     */
    private final ArrayList<Task> TASKS = new ArrayList<>();

    /**
     * Gets the Task at the provided index.
     * @param index The index of the list to check.
     * @return The Task object at the index, if it exists, and null otherwise.
     */
    private Task getTask(int index) {
        // happy path: return null if out of bounds.
        if (index <= 0 || index > TASKS.size()) {
            return null;
        }
        return TASKS.get(index - 1);
    }

    public boolean markTask(int index) {
        Task task = getTask(index);
        // happy path: the task does not exist.
        if (task == null) {
            System.out.println("That task is not on the list!");
            System.out.println("Use \"list\" to display what I am currently tracking.");
            return false;
        }

        // we can use an exception here to denote the task is completed
        if (!task.markTask()) {
            System.out.println("This task has already been completed!\n  " + task);
            return false;
        }
        System.out.println("This task has been marked as completed.\n  " + task);
        return true;
    }

    public boolean unmarkTask(int index) {
        Task task = getTask(index);
        // happy path: the task does not exist.
        if (task == null) {
            System.out.println("That task is not on the list!");
            System.out.println("Use \"list\" to display what I am currently tracking.");
            return false;
        }

        // we can use an exception here to denote the task is completed
        if (!task.unmarkTask()) {
            System.out.println("This task is already in progress.\n  " + task);
            return false;
        }
        System.out.println("The task has been marked as incomplete.\n  " + task);
        return true;
    }

    /**
     * Delete function for the app. <br>
     * Attempts to delete the item in the task list. If the Task does not exist,
     * prints an appropriate error message.
     * @param index The index of the list to unmark.
     */
    public void delete(int index) {
        Task task = getTask(index);
        // happy path: the task does not exist.
        if (task == null) {
            System.out.println("That task is not on the list!");
            System.out.println("Use \"list\" to display what I am currently tracking.");
            return;
        }

        TASKS.remove(index - 1);
        System.out.println("I have removed this task off of my list.\n  " + task);
        System.out.println(TASKS.size() + " task(s) remain on my list.");
    }

    /**
     * Function that adds a task to the app. <br>
     * Adds a To-Do, Event or Deadline task to the task list.
     * @param input The Pair&lt;Command, String&gt; of the task to add to the list.
     */
    public void add(Pair<CommandType, String> input) throws TrackerBotException {
        TASKS.add(Parser.parseAdd(input));
    }

    public void read() {
        Path path = Paths.get("TrackerBot", "data.txt");
        if (Files.notExists(path)) {
            return;
        }

        try (Scanner input = new Scanner(new FileReader(path.toFile()))) {
            while (input.hasNextLine()) {
                TASKS.add(Task.parseSaveLine(input.nextLine()));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            TASKS.clear();
        }
    }

    public void save() {
        Path path = Paths.get("TrackerBot", "data.txt");
        File file = path.toFile();
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (FileOutputStream output = new FileOutputStream(file, false)) {
            for (int i = 1; i < TASKS.size() + 1; i++) {
                output.write(TASKS.get(i - 1).toSaveString().getBytes());
                output.write("\n".getBytes());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } // the try with resources statement auto-closes output.
    }
}
