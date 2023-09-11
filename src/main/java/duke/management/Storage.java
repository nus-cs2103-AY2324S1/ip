package duke.management;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.note.Note;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Contains Data File and methods for writing and reading the file.
 */
public class Storage {
    private File dataFile;
    private File notesFile;
    private String directoryPath;

    /**
     * Storage Constructor.
     *
     * @param directoryPath Relative directory path.
     */
    public Storage(String directoryPath) {
        this.directoryPath = directoryPath;
        findDirectory(this.directoryPath);
        this.dataFile = new File(this.directoryPath, "/data.txt");
        this.notesFile = new File(this.directoryPath, "/notes.txt");
        try {
            findFile(dataFile);
            findFile(notesFile);
        } catch (IOException e) {
            throw new DukeException("Cannot find file.");
        }
    }

    /**
     * Checks if the file directory to data file exists, if not creates the path.
     *
     * @param directory The relative filepath.
     */
    public static void findDirectory(String directory) {
        File dataDirectory = new File(directory);
        if (dataDirectory.exists() && dataDirectory.isDirectory()) {
            System.out.println("Directory exists.");
        } else {
            if (dataDirectory.mkdir()) {
                System.out.println("Directory created.");
            }
        }
    }

    /**
     * Checks if the data file exists, if not creates the file.
     *
     * @param dataFile The Data File.
     * @throws IOException Throws Exception if file cannot be created.
     */
    public static void findFile(File dataFile) throws IOException {
        if (dataFile.exists() && dataFile.isFile()) {
            System.out.println("Data file exists.");
        } else {
            if (dataFile.createNewFile()) {
                System.out.println("Data file created.");
            }
        }
    }

    /**
     * Loads the information from the datafile into an ArrayList.
     *
     * @return ArrayList containing all the Tasks from the data file.
     */
    public ArrayList<Task> loadData() {
        try {
            ArrayList<Task> storedList = new ArrayList<>();
            Scanner scanner = new Scanner(this.dataFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] existingData = line.split("\\s*\\|\\s*");
                if (existingData[0].equals("T")) {
                    Todo todo = new Todo(existingData[2]);
                    if (existingData[1].equals("1")) {
                        todo.markAsDone();
                    }
                    storedList.add(todo);
                } else if (existingData[0].equals("D")) {
                    Deadline deadline = new Deadline(existingData[2], existingData[3]);
                    if (existingData[1].equals("1")) {
                        deadline.markAsDone();
                    }
                    storedList.add(deadline);
                } else {
                    Event event = new Event(existingData[2], existingData[3], existingData[4]);
                    if (existingData[1].equals("1")) {
                        event.markAsDone();
                    }
                    storedList.add(event);
                }
            }
            return storedList;
        } catch (FileNotFoundException e) {
            throw new DukeException("Data File Not Found.");
        }
    }

    /**
     * Loads the information from the notesfile into an ArrayList.
     *
     * @return ArrayList containing all the Notes from the notes file.
     */
    public ArrayList<Note> loadNotes() {
        try {
            ArrayList<Note> notesList = new ArrayList<>();
            Scanner scanner = new Scanner(this.notesFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Note note = new Note(line);
                notesList.add(note);
            }
            return notesList;
        } catch (FileNotFoundException e) {
            throw new DukeException("Notes File Not Found.");
        }
    }

    /**
     * Writes the tasks in the tasklist into the data file.
     *
     * @param tasks The tasklist.
     * @throws IOException Throws if FileWriter cannot be created.
     */
    public void writeTasksToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.directoryPath + "/data.txt");
        for (Task task : tasks) {
            fw.write(task.writeToFile());
        }
        fw.close();
    }

    /**
     * Writes the notes in the noteslist into the notes file.
     *
     * @param notes The noteslist.
     * @throws IOException Throws if FileWriter cannot be created.
     */
    public void writeNotesToFile(ArrayList<Note> notes) throws IOException {
        FileWriter fw = new FileWriter(this.directoryPath + "/notes.txt");
        for (Note note : notes) {
            fw.write(note.toString() + "\n");
        }
        fw.close();
    }
}
