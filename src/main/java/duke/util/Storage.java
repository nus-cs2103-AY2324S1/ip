package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage class is used to load and save the task list to a file.
 */
public class Storage {

    public static final String SEPARATOR = " !%&%! ";
    private File folder;
    private File file;
    private String filePath;
    private String folderPath;

    /**
     * Constructor for Storage class.
     *
     * @param folderPath The path of the folder to store the file.
     * @param fileName   The name of the file to store the task list.
     */
    public Storage(String folderPath, String fileName) {
        this.filePath = folderPath + "/" + fileName;
        this.folderPath = folderPath;
        this.file = new File(this.filePath);
        this.folder = new File(this.folderPath);
    }

    /**
     * Loads the task list from the file.
     * <p>If the file is not found or the format is wrong, it will throw an exception.</p>
     *
     * @return List of tasks.
     * @throws DukeException If the file is not found or the format is wrong.
     */
    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        assert file.exists() : "File should exist";

        try {
            Scanner sc = new Scanner(file);
            loadToList(sc, taskList);
            sc.close();
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }
        return taskList;
    }

    /**
     * Loads the file to the task list.
     *
     * @param sc       The scanner of the file.
     * @param taskList The list of tasks.
     * @throws DukeException If the format is wrong.
     */
    private void loadToList(Scanner sc, List<Task> taskList) throws DukeException {
        while (sc.hasNext()) {
            String[] temp = sc.nextLine().split(SEPARATOR);
            Task task;
            switch (temp[0]) {
            case "T":
                task = new Todo(temp[2]);
                break;
            case "D":
                task = new Deadline(temp[2], Time.parseDateTime(temp[3]));
                break;
            case "E":
                task = new Event(temp[2], Time.parseDateTime(temp[3]), Time.parseDateTime(temp[4]));
                break;
            default:
                throw new DukeException();
            }

            if (temp[1].equals("1")) {
                task.mark(true);
            } else if (temp[1].equals("0")) {
                task.mark(false);
            } else {
                throw new DukeException();
            }

            taskList.add(task);
        }
    }

    /**
     * Creates a new file to store the task list.
     * <p>If the file already exists, it will clear the file.<br>
     * If the folder does not exist, it will create the folder.<br>
     * If the file cannot be created, it will throw a runtime exception.</p>
     */
    public void createFile() {
        if (!this.folder.exists()) {
            folder.mkdirs();
        }
        assert this.folder.exists() : "Folder should be created";
        try {
            if (!file.exists()) {
                Files.createFile(Paths.get(this.filePath));
            } else {
                clearFile();
            }
            assert this.file.exists() : "File should be created";
        } catch (IOException e) {
            /* Here is reach if something terrible happened.
               It is best to throw a runtime exception. */
            throw new RuntimeException(e);
        }
    }

    /**
     * Clears the file.
     *
     * @throws IOException If the file is not found.
     */
    private void clearFile() throws IOException {
        assert file.exists() : "File should exist";

        FileWriter fw = new FileWriter(this.filePath);
        fw.write("");
        fw.close();
    }

    /**
     * Appends the text to the end of the file.
     *
     * @param text The text to be appended.
     * @throws DukeException If the file is not found.
     */
    public void appendFile(String text) throws DukeException {
        assert file.exists() : "File should exist";
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(text);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    /**
     * Changes the file according to the keyword and index.
     * <p>If the index is <b>NEGATIVE</b>, it will change <b>ALL</b> the tasks.<br>
     * If the index is <b>POSITIVE</b>, it will change the task with the index.<br>
     * If the keyword is <b>DELETE</b>, it will delete the task.<br>
     * If the keyword is <b>MARK</b>, it will mark the task.<br>
     * If the keyword is <b>UNMARK</b>, it will unmark the task.</p>
     *
     * @param key   The keyword to change the file.
     * @param index The index of the task to be changed.
     * @throws DukeException If the file is not found.
     */
    public void changeFile(Keyword key, int index) throws DukeException {
        assert file.exists() : "File should exist";

        try {
            String tempPath = this.folderPath + "/temp.txt";
            Files.copy(Paths.get(this.filePath), Paths.get(tempPath));
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(""); // Clear the file
            Scanner sc = new Scanner(new File(tempPath));

            processChange(key, index, sc, fw);
            sc.close();
            fw.close();
            Files.delete(Paths.get(tempPath));
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    /**
     * Process the change of the file.
     * <p>If the index is <b>NEGATIVE</b>, it will change <b>ALL</b> the tasks.<br>
     * If the index is <b>POSITIVE</b>, it will change the task with the index.<br>
     * If the keyword is <b>DELETE</b>, it will delete the task.<br>
     * If the keyword is <b>MARK</b>, it will mark the task.<br>
     * If the keyword is <b>UNMARK</b>, it will unmark the task.</p>
     *
     * @param key   The keyword to change the file.
     * @param index The index of the task to be changed.
     * @param sc    The scanner of the file.
     * @param fw    The file writer of the file.
     * @throws IOException If the file is not found.
     */
    private void processChange(Keyword key, int index, Scanner sc, FileWriter fw) throws IOException {
        if (key.equals(Keyword.DELETE)) {
            if (index >= 0) {
                removeLine(index, sc, fw);
            }
        } else if (index >= 0) {
            markLine(index, key.equals(Keyword.MARK), sc, fw);
        } else {
            markAll(key.equals(Keyword.MARK), sc, fw);
        }
    }

    /**
     * Removes the line with the index in the file.
     *
     * @param index The index of the line to be removed.
     * @param sc    The scanner of the file.
     * @param fw    The file writer of the file.
     * @throws IOException If the file is not found.
     */
    private void removeLine(int index, Scanner sc, FileWriter fw) throws IOException {
        int curr = 0;

        while (sc.hasNext()) {
            if (curr != index) {
                fw.write(sc.nextLine());
                fw.write("\n");
            } else {
                sc.nextLine();
            }
            curr++;
        }
    }

    /**
     * Marks or unmark the line with the index in the file.
     *
     * @param index  The index of the line to be marked.
     * @param isMark Whether to mark or unmark the line.
     * @param sc     The scanner of the file.
     * @param fw     The file writer of the file.
     * @throws IOException If the file is not found.
     */
    private void markLine(int index, boolean isMark, Scanner sc, FileWriter fw) throws IOException {
        assert index >= 0 : "Index should be positive";
        assert file.exists() : "File should exist";

        int curr = 0;

        while (sc.hasNext()) {
            if (curr != index) {
                fw.write(sc.nextLine());
            } else {
                String task = sc.nextLine();
                String result = task.substring(0, SEPARATOR.length() + 1)
                        + (isMark ? "1" : "0")
                        + task.substring(SEPARATOR.length() + 2);
                fw.write(result);
            }
            fw.write("\n");
            curr++;
        }
    }

    /**
     * Marks or unmark all the lines in the file.
     *
     * @param isMark Whether to mark or unmark the line.
     * @param sc     The scanner of the file.
     * @param fw     The file writer of the file.
     * @throws IOException If the file is not found.
     */
    private void markAll(boolean isMark, Scanner sc, FileWriter fw) throws IOException {
        while (sc.hasNext()) {
            String task = sc.nextLine();
            String result = task.substring(0, SEPARATOR.length() + 1)
                    + (isMark ? "1" : "0")
                    + task.substring(SEPARATOR.length() + 2)
                    + "\n";
            fw.write(result);
        }
    }
}
