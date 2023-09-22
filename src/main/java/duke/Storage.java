package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.ArrayList;

/**
 * Storage stores task data from the chatbot to a local folder,
 * and read/write to the data file when the bot it used.
 */
public class Storage {
    private static final String FOLDER_PATH = "./data";
    private static final String FILE_PATH = "./data/duke.txt";

    /**
     * Reads the data file from the default FILE_PATH and parses the data file
     * to return an ArrayList containing Tasks based on the data file.
     *
     * @return An ArrayList of Tasks based on the data file.
     * @throws DukeException If there is an error reading from the data file.
     */
    public ArrayList<Task> readTasks() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File folder = new File(FOLDER_PATH);
        File data = new File(FILE_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (!data.exists()) {
            return tasks;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(parseTasks(line));
            }
        } catch (IOException e) {
            throw new DukeException("There's something wrong with your save file! " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Writes the current task list into the data file by converting items
     * in the ArrayList to a String.
     *
     * @param tasks An ArrayList of Task objects.
     * @throws DukeException If there are any errors writing to the data file.
     */
    public void writeTasks(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.save() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("There was an issue saving your file! " + e.getMessage());
        }
    }

    /**
     * Parses an individual line from the data file and converts it into a Task Object.
     *
     * @param data A String from the data file.
     * @return A Task object made based on the String from the data file.
     */
    public Task parseTasks(String data) {
        String[] parsedData = data.split("\\|");
        Task task = null;
        try{
            switch (parsedData.length) {
            case 3:
                task = new Todo(parsedData[2]);
                break;
            case 4:
                task = new Deadline(parsedData[2], parsedData[3]);
                break;
            default:
                task = new Event(parsedData[2], parsedData[3], parsedData[4]);
                break;
            }
            if (parsedData[1].equals("Y")) {
                task.markAsDone();
            }
            return task;
        } catch (DukeException e) {
            System.out.println("OOPS!" + e.toString().split("DukeException:")[1]);
        }
        return task;
    }
}
