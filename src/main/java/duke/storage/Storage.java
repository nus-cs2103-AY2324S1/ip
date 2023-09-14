package duke.storage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.ToDo;




public class Storage {
    public final Path filepath;
    public final String path;

    public Storage(String filePath) {
        this.filepath = Paths.get(filePath);
        this.path = filePath;
    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * Overwrites content in a file with new content.
     *
     * @param textToAdd String to be added to file.
     * @throws IOException if writing to file is not allowed.
     */
    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.path);
        fw.write(textToAdd);
        fw.close();
    }
    /**
     * Appends a new line of content to a file.
     *
     * @param textToAppend String to be appended to file.
     * @throws IOException if writing to file is not allowed.
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.path, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Rewrites the whole txt file.
     *
     * @param tasklist ArrayList of tasks to write to file.
     */
    public void rewriteFile(ArrayList<Task> tasklist) {
        try {
            writeToFile(tasklist.get(0).saveString() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        for (int i = 1; i < tasklist.size(); i++) {
            try {
                appendToFile(tasklist.get(i).saveString() + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    /**
     * Loads content from text file into an ArrayList of Tasks.
     *
     * @return ArrayList of Tasks with content of text file.
     * @throws FileNotFoundException if file is not valid.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        try {
            File f = new File(this.getPath());
            Scanner s = new Scanner(f);
            ArrayList<Task> tasklist = new ArrayList<>();
            while (s.hasNext()) {
                String nextTask = s.nextLine();
                String[] input = nextTask.split(" \\| ");
                String category = input[0];
                String status = input[1];
                String description = input[2];

                switch (category) {
                case "T":
                    Task toDoTask = new ToDo(description);
                    markTask(toDoTask, status);
                    tasklist.add(toDoTask);
                    break;
                case "D":
                    String deadline = input[3];
                    Task deadlineTask = new Deadline(description, deadline);
                    markTask(deadlineTask, status);
                    tasklist.add(deadlineTask);
                    break;
                case "E":
                    String from = input[3].split("-")[0];
                    String to = input[3].split("-")[1];
                    Task eventTask = new Event(description, from, to);
                    markTask(eventTask, status);
                    tasklist.add(eventTask);
                    break;
                default:
                }
            }
            return tasklist;
        } catch (FileNotFoundException e) {
            throw new DukeException("File Cannot be Found");
        }
    }

    public void markTask(Task t, String status) {
        if (!status.equals("0")) {
            t.mark();
        }
    }

    public String getPath() {
        return filepath.toString();
    }


}
