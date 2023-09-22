package dude;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import dude.exception.StorageException;
import dude.note.Note;
import dude.note.NoteList;
import dude.task.Deadline;
import dude.task.Event;
import dude.task.Task;
import dude.task.TaskList;
import dude.task.ToDo;

/**
 * Represents the Storage system of Dude and deals with loading tasks from
 * the file and saving tasks in the file.
 */
public class Storage {

    private static final DateTimeFormatter DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private String filepath;

    /**
     * Constructs a new Storage object with the specified filepath.
     *
     * @param filepath Path to the file.
     */
    public Storage(String filepath) {
        assert !this.filepath.trim().isEmpty() : "Filepath should not be empty";
        this.filepath = filepath;
    }

    /**
     * Saves the task list to the file specified by filepath.
     *
     * @param taskList List of tasks to be saved.
     */
    public void saveToDisk(TaskList taskList, NoteList noteList) throws StorageException {
        File file = new File(this.filepath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            String data = convertTaskListToString(taskList) + convertNoteListToString(noteList);
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            throw new StorageException("something is wrong: \n" + e.getMessage());
        }
    }

    /**
     * Converts task list into String for saving to file.
     * @param taskList
     * @return
     */
    public String convertTaskListToString(TaskList taskList) {
        String data = "";

        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task != null) {
                data += task.saveTask();
            }
        }

        return data;
    }

    /**
     * Converts task list into String for saving to file.
     * @param noteList
     * @return
     */
    public String convertNoteListToString(NoteList noteList) {
        String data = "";

        for (int i = 0; i < noteList.getSize(); i++) {
            Note note = noteList.getNote(i);
            if (note != null) {
                data += note.saveNote();
            }
        }

        return data;
    }

    /**
     * Loads the saved tasks from the file specified by filepath.
     *
     * @return List of tasks from the file.
     * @throws FileNotFoundException If no file is not found in the filepath.
     */
    public TaskList loadFromDisk() throws StorageException {
        TaskList taskList = new TaskList();
        File file = new File(this.filepath);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String storedTaskDetails = sc.nextLine();
                Task task = convertStringToTask(storedTaskDetails);
                if (task != null) {
                    taskList.addTask(task);
                }
            }
        } catch (IOException e) {
            throw new StorageException("something is wrong: \n" + e.getMessage());
        }

        return taskList;
    }

    /**
     * Converts a string of task details into Task.
     * @param storedTaskDetails
     * @return
     */
    public Task convertStringToTask(String storedTaskDetails) {
        String[] taskDetails = storedTaskDetails.split("\\s+\\|\\s+");
        String taskType = taskDetails[0].trim();
        String taskIsDone = taskDetails[1].trim();

        Task task = null;

        if (taskType.equals("T")) {
            String taskDescription = taskDetails[2].trim();
            task = new ToDo(taskDescription);
            task.setDone(taskIsDone.equals("1"));
        } else if (taskType.equals("D")) {
            String taskDescription = taskDetails[2].trim();
            LocalDateTime by = LocalDateTime.parse(taskDetails[3]);
            task = new Deadline(taskDescription, by);
            task.setDone(taskIsDone.equals("1"));
        } else if (taskType.equals("E")) {
            String taskDescription = taskDetails[2].trim();
            LocalDateTime from = LocalDateTime.parse(taskDetails[3]);
            LocalDateTime to = LocalDateTime.parse(taskDetails[4]);
            task = new Event(taskDescription, from, to);
            task.setDone(taskIsDone.equals("1"));
        }

        return task;
    }

    /**
     * Loads the saved notes from the file specified by filepath.
     *
     * @return List of notes from the file.
     * @throws FileNotFoundException If no file is not found in the filepath.
     */
    public NoteList loadNotesFromDisk() throws StorageException {
        NoteList noteList = new NoteList();
        File file = new File(this.filepath);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String storedNoteDetails = sc.nextLine();
                Note note = convertStringToNote(storedNoteDetails);
                if (note != null) {
                    noteList.addNote(note);
                }
            }
        } catch (IOException e) {
            throw new StorageException("something is wrong: \n" + e.getMessage());
        }

        return noteList;
    }

    /**
     * Converts a string of task details into Task.
     * @param storedNoteDetails
     * @return
     */
    public Note convertStringToNote(String storedNoteDetails) {
        String[] noteDetails = storedNoteDetails.split("\\s+\\|\\s+");
        String noteType = noteDetails[0].trim();
        String noteDescription = noteDetails[1].trim();

        Note note = null;

        if (noteType.equals("N")) {
            note = new Note(noteDescription);
        }

        return note;
    }
}
