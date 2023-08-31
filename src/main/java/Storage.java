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
    public void add(Pair<CommandType, String> input) {
        Task newTask;
        String[] segments;
        try {
            switch (input.getFirst()) {
            case TODO:
                if (input.getSecond().equals("")) {
                    System.out.println("I can't track a task that doesn't have a description!");
                    return;
                }
                newTask = new Todo(input.getSecond());
                break;
            case DEADLINE:
                segments = input.getSecond().split("/by");
                if (segments.length < 2) {
                    System.out.println("Use 1 /by flag in the argument, followed by the deadline.");
                    System.out.println("Format: deadline [description] /by [end-date]");
                    return;
                } else if (segments.length > 2) {
                    System.out.println("Too many flags specified! Use only 1 /by flag.");
                    System.out.println("Format: deadline [description] /by [end-date]");
                    return;
                }

                if (segments[0].trim().equals("")) {
                    System.out.println("I can't track a task that doesn't have a description!");
                    return;
                }
                if (segments[1].trim().equals("")) {
                    System.out.println("Specify a deadline please.");
                    return;
                }

                newTask = new Deadline(segments[0].trim(), segments[1].trim());
                break;
            case EVENT:
                // this will check for the standard format, and will also guarantee that segment length is min 3.
                if (!input.getSecond().matches("^.+ /from .+ /to .+")) {
                    System.out.println("event is not used in the correct format.");
                    System.out.println("Format: event [description] /from [start-date] /to [end-date]");
                    return;
                }

                segments = input.getSecond().split("/from|/to");
                if (segments.length > 3) {
                    System.out.println("Too many flags specified for event!");
                    System.out.println("Format: event [description] /from [start-date] /to [end-date]");
                    return;
                }

                if (segments[0].trim().equals("")) {
                    System.out.println("I can't track a task that doesn't have a description!");
                    return;
                }
                if (segments[1].trim().equals("")) {
                    System.out.println("Specify a start date please.");
                    return;
                }
                if (segments[2].trim().equals("")) {
                    System.out.println("Specify an end date please.");
                    return;
                }

                newTask = new Event(segments[0].trim(), segments[1].trim(), segments[2].trim());
                break;
            default:
                System.out.println("That wasn't supposed to happen...");
                return;
            }
            TASKS.add(newTask);

            System.out.println("Now tracking: \n  " + newTask);
        } catch (DateTimeParseException e) {
            System.out.println("Additional Date Fields should be in the format DD/MM(/YYYY)( HHmm).");
        }
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
