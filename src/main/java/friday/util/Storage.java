package friday.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import friday.item.Deadline;
import friday.item.Event;
import friday.item.Note;
import friday.item.Task;
import friday.item.Todo;

/**
 * Represents a storage system to save and view tasks.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a new Storage system.
     *
     * @param filePath Path to the file where tasks will be saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        ensureDirectoryAndFileExists();
    }

    /**
     * Ensures the necessary directories and files exist, creating them if they don't.
     */
    private void ensureDirectoryAndFileExists() {
        String directoryPath = filePath.substring(0, filePath.lastIndexOf('/'));
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Saves the provided list to a file.
     *
     * @param content String representation of items to be saved.
     * @throws IOException If there's an error writing to the file.
     */
    public void saveFile(String content) throws IOException {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(content);
        }
    }

    /**
     * Reads tasks from the file and returns them as a list.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> readTaskItems() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                Task task = parseTaskFromLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }

            sc.close();

        } catch (FileNotFoundException e) {
            ensureDirectoryAndFileExists();
        }

        return tasks;
    }

    /**
     * Parses a task from the provided line.
     *
     * @param line Line containing task data.
     * @return Task object or null if the line format is incorrect.
     */
    private Task parseTaskFromLine(String line) {
        Pattern pattern = Pattern.compile("^\\d+\\. \\[([TDE])\\]\\[([ X])\\] (.+)$");
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            String type = matcher.group(1);
            char status = matcher.group(2).charAt(0);
            String description = matcher.group(3);

            switch (type) {
            case "T":
                return parseTodo(description, status);
            case "D":
                return parseDeadline(description, status);
            case "E":
                return parseEvent(description, status);
            default:
                return null;
            }
        }
        return null;
    }

    /**
     * Parses a Todo task from the provided description and status.
     *
     * @param description Description of the task.
     * @param status Task status.
     * @return A Todo task.
     */
    private Todo parseTodo(String description, char status) {
        Todo todo = new Todo(description);
        if (status == 'X') {
            todo.mark();
        }
        return todo;
    }

    /**
     * Parses a Deadline task from the provided description and status.
     *
     * @param description Description of the task.
     * @param status Task status.
     * @return A Deadline task or null if the format is incorrect.
     */
    private Deadline parseDeadline(String description, char status) {
        Pattern datePattern = Pattern.compile(" \\(by: (.+)\\)");
        Matcher dateMatcher = datePattern.matcher(description);
        if (dateMatcher.find()) {
            String date = dateMatcher.group(1);
            description = description.replaceFirst(datePattern.pattern(), "").trim();
            Deadline deadline = new Deadline(description, date);
            if (status == 'X') {
                deadline.mark();
            }
            return deadline;
        }
        return null;
    }

    /**
     * Parses an Event task from the provided description and status.
     *
     * @param description Description of the task.
     * @param status Task status.
     * @return An Event task or null if the format is incorrect.
     */
    private Event parseEvent(String description, char status) {
        Pattern eventPattern = Pattern.compile("(.+) \\(from: (.+) to: (.+)\\)");
        Matcher eventMatcher = eventPattern.matcher(description);
        if (eventMatcher.find()) {
            String eventDescription = eventMatcher.group(1).trim();
            String startTimeString = eventMatcher.group(2).trim();
            String endTimeString = eventMatcher.group(3).trim();
            Event event = new Event(eventDescription, startTimeString, endTimeString);
            if (status == 'X') {
                event.mark();
            }
            return event;
        }
        return null;
    }

    /**
     * Reads notes from the file and returns them as a list.
     *
     * @return List of notes.
     */
    public ArrayList<Note> readNotesItems() {
        ArrayList<Note> notes = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                Note note = parseNoteFromLine(line);
                if (note != null) {
                    notes.add(note);
                }
            }

            sc.close();

        } catch (FileNotFoundException e) {
            ensureDirectoryAndFileExists();
        }

        return notes;
    }

    /**
     * Parses a note from the provided line.
     *
     * @param line Line containing note data.
     * @return Note object or null if the line format is incorrect.
     */
    private Note parseNoteFromLine(String line) {
        Pattern pattern = Pattern.compile("^\\d+\\. (.+)$");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String description = matcher.group(1);
            return new Note(description);
        }
        return null;
    }
}
