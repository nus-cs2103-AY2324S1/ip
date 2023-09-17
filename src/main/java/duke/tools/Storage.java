package duke.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.UnrecognisedFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Handles loading tasks from storage and saving tasks to storage.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from storage.
     * @throws IOException If an I/O error occurs.
     */
    public List<Task> loadTasksFromStorage() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        assert file.exists() : "Storage file does not exist";

        // scan for the storage file
        try {
            readTasks(tasks, file);
        } catch (FileNotFoundException e) { // File does not exist
            handleFileCreation(file);
        } catch (UnrecognisedFormatException e) { // File is corrupted
            handleCorruptedFile(file);
        }
        return tasks;
    }

    private void handleFileCreation(File file) {
        try {
            if (new File(filePath.split("/")[0]).mkdir()) {
                System.out.println("Sorry, directory does not exist. Creating now...");
            }
            if (file.createNewFile()) {
                System.out.println("Sorry, file does not exist. Creating now...");
            }
        } catch (Exception e) {
            handleError(e);
        }
    }

    private void readTasks(List<Task> tasks, File file)
            throws IOException, UnrecognisedFormatException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {

            String[] segments = processInput(s);
            boolean isDone = segments[1].equals("1");

            switch (segments[0]) {
            case "T": // To Do
                tasks.add(new ToDo(segments[2], isDone));
                break;
            case "D": // Deadline
                tasks.add(new Deadline(segments[2], LocalDateTime.parse(segments[3]), isDone));
                break;
            case "E": // Event
                String[] times = segments[3].split("--");
                assert times.length == 2 : "Format is not correct";

                tasks.add(new Event(segments[2], LocalDateTime.parse(times[0]),
                        LocalDateTime.parse(times[1]), isDone));
                break;
            default:
                throw new UnrecognisedFormatException();
            }
        }
    }

    private String[] processInput(Scanner s) throws UnrecognisedFormatException {
        String curr = s.nextLine();
        assert curr != null;
        String[] segments = curr.split(" \\| ");

        // check for the correct format - minimum 3 different segments
        if ((!segments[0].equals("T") && !segments[0].equals("D")
                && !segments[0].equals("E")) || segments.length < 3) {
            // Close the file to allow replacement
            s.close();
            throw new UnrecognisedFormatException();
        }
        return segments;
    }

    private void handleError(Exception error) {
        System.out.println(error.getMessage());
        System.out.println("Error... Unable to create files");
    }

    private void handleCorruptedFile(File file) {
        try {
            boolean isDeleted = file.delete();

            if (!isDeleted) {
                System.out.println("Unable to delete file");
                return;
            }

            System.out.println("Deleting corrupted file...");

            boolean isCreated = file.createNewFile();

            if (!isCreated) {
                System.out.println("Unable to create new file");
            }

            System.out.println("Replacing file now...");
        } catch (Exception e) {
            handleError(e);
        }

    }

    /**
     * Writes the provided text to the storage file.
     *
     * @param text The text to be written to the storage file.
     */
    public void writeFile(String text) {

        assert text != null : "Text to be written is null";

        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write(text);
            fw.close();
        } catch (Exception e) {
            System.out.println("Sorry... Unable to store tasks...");
        }

    }

    /**
     * Writes the provided text to the archive file.
     *
     * @param text The text to be written to the archive file.
     */
    public void archiveFile(String text) {

        assert text != null;

        try {
            FileWriter fw = new FileWriter("data" + File.separator + "archive.txt", true);
            fw.write(text + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Sorry... Unable to store tasks...");
        }

    }
}
