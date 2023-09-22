package echobot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import echobot.note.Note;
import echobot.task.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


/**
 * The Storage class handles loading and saving tasks and notes.
 */
public class Storage {
    private String tasksFilePath;
    private String notesFilePath;

    /**
     * Constructs a Storage object with file paths for tasks and notes.
     *
     * @param tasksFilePath The file path for storing tasks.
     * @param notesFilePath The file path for storing notes.
     */
    public Storage(String tasksFilePath, String notesFilePath) {
        this.tasksFilePath = tasksFilePath;
        this.notesFilePath = notesFilePath;
    }
    /**
     * Loads tasks from the file and returns them as a list.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(tasksFilePath);
        File folder = file.getParentFile();

        if (createFolderIfNotExist(folder) && createFileIfNotExist(file)) {
            readTasksFromFile(file, tasks);
        }

        return tasks;
    }

    private void readTasksFromFile(File file, ArrayList<Task> tasks) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String formattedTask = scanner.nextLine();

                if (!formattedTask.isEmpty()) {
                    processTasks(formattedTask, tasks);
                }
            }
            scanner.close();
        } catch (IOException e) {
            logError("An error occurred while reading from the file: ", e);
        }
    }

    private void processTasks(String formattedTask, ArrayList<Task> tasks) {
        try {
            Task task = Task.fromFileString(formattedTask);
            tasks.add(task);
        } catch (NumberFormatException e) {
            logError("Corrupted data: " + formattedTask, e);
        } catch (IllegalArgumentException e) {
            logError("Invalid data: " + formattedTask, e);
        }
    }

    /**
     * Saves the list of tasks to the tasks file.
     *
     * @param tasks           The list of tasks to be saved.
     * @param dialogContainer The container for displaying dialog messages.
     */
    public void saveTasks(ArrayList<Task> tasks, VBox dialogContainer) {
        try (PrintWriter writer = new PrintWriter(tasksFilePath)) {
            for (Task task : tasks) {
                writer.println(task.toFileString());
            }
        } catch (FileNotFoundException e) {
            if (dialogContainer == null) {
                dialogContainer = new VBox();
            }

            dialogContainer.getChildren().add(new Label("Error saving tasks: " + e.getMessage()));
        }
    }

    /**
     * Loads notes from the notes file and returns them as a list.
     *
     * @return The list of notes.
     */
    public ArrayList<Note> loadNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        File file = new File(notesFilePath);
        File folder = file.getParentFile();

        if (createFolderIfNotExist(folder) && createFileIfNotExist(file)) {
            readNotesFromFile(file, notes);
        }

        return notes;
    }

    private void readNotesFromFile(File file, ArrayList<Note> notes) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String formattedNote = scanner.nextLine();

                if (!formattedNote.isEmpty()) {
                    processNotes(formattedNote, notes);
                }
            }
            scanner.close();
        } catch (IOException e) {
            logError("An error occurred while reading from the file: ", e);
        }
    }

    private void processNotes(String formattedNote, ArrayList<Note> notes) {
        try {
            Note note = Note.fromFileString(formattedNote);
            notes.add(note);
        } catch (NumberFormatException e) {
            logError("Corrupted data: " + formattedNote, e);
        } catch (IllegalArgumentException e) {
            logError("Invalid data: " + formattedNote, e);
        }
    }

    /**
     * Saves the list of notes to the notes file.
     *
     * @param notes           The list of notes to be saved.
     * @param dialogContainer The container for displaying dialog messages.
     */
    public void saveNotes(ArrayList<Note> notes, VBox dialogContainer) {
        // Save notes to notesFilePath
        try (PrintWriter writer = new PrintWriter(notesFilePath)) {
            for (Note note : notes) {
                writer.println(note.toFileString());
            }
        } catch (FileNotFoundException e) {
            dialogContainer.getChildren().add(new Label("Error saving tasks: " + e.getMessage()));
        }
    }

    private boolean createFolderIfNotExist(File folder) {
        if (!folder.exists() && !folder.mkdirs()) {
            return false;
        }
        return true;
    }

    private boolean createFileIfNotExist(File file) {
        try {
            if (!file.exists() && !file.createNewFile()) {
                return false;
            }
            return true;
        } catch (IOException e) {
            logError("An error occurred while creating a new file: ", e);
            return false;
        }
    }

    private void logError(String message, Exception e) {
        Logger logger = Logger.getLogger(Storage.class.getName());
        logger.log(Level.SEVERE, message, e);
    }
}

